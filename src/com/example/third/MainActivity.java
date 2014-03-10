package com.example.third;

import java.util.ArrayList;

import com.example.third.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String TAG = "mydbg";// Это тег для журналирования, вызываемого Log.d(TAG,"сообщение")
	private BoardCell tvTable[][] = new BoardCell[15][15]; //массив клеток поля -- экземпляров класса BoardCell
	private View[] tokenArr; //массив виджетов отображающих буквы на руках
	private static int selectedLetters = 0; //сумма очков за выделенные слова
	private ArrayList<String> deck = new ArrayList<String>(0); //колода - список букв
	public static int getSelectedLetters() {
		return selectedLetters;
	}

	public void setSelectedLetters(int selectedLetters) {
		this.selectedLetters = selectedLetters;
	}

	private static TextView count; //текстовое поле отображающее сумму очков за выделенные на поле буквы
	public static TextView getCount() {
		return count;
	}

	public void setCount(TextView count) {
		this.count = count;
	}
	private static int dragged = 0;//не нужно

	/**
	 * Этот метод вызывается при запуске активити, в onCreate() пишется инициализация
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); // конструкция super.имя метода нужна для вызова одноименного метода класса-родителя
		setContentView(R.layout.activity_main); //setContentView(id layout-ресурса) заполняет экран активити содержимым из соотв. layout.xml
		count = (TextView)findViewById(R.id.textView1); //находим текстовое поле для записи очков
		tokenArr = new View[] {findViewById(R.id.TextView07), //tokenArr - массив из 7 TextView с буквами в руке
				findViewById(R.id.TextView06),
				findViewById(R.id.TextView05),
				findViewById(R.id.TextView04),
				findViewById(R.id.TextView03),
				findViewById(R.id.TextView02),
				findViewById(R.id.TextView01)};
		View groupLayout = findViewById(R.id.gl1);//поока не нужно
		RelativeLayout rlBoard = (RelativeLayout)findViewById(R.id.rl1);//пока не нужно
//		rlBoard.getLayoutParams().
		LinearLayout rows = (LinearLayout)findViewById(R.id.rows); 
		LinearLayout rowsList[] = new LinearLayout[15];//строчки таблицы с клетками поля
		initDeck();//сдать полный набор букв в "колоду"
		for (int i = 0; i<15; i++) { //наполнение таблицы строками
			rowsList[i] = new LinearLayout(this);//HORIZONTAL by default
			for (int j = 0; j<15; j++)	{ //наполнение строки
				
				tvTable[i][j] = new BoardCell(this); //создаем клетку
				tvTable[i][j].setText("");//пишем в ней пустую строку
				tvTable[i][j].setGravity(Gravity.CENTER);//размещаем по центру
				tvTable[i][j].setCoordX(j);//устанавливаем свойства - координаты по горизонтали и вертикали
				tvTable[i][j].setCoordY(i);
				tvTable[i][j].setOnDragListener(new View.OnDragListener() { //задаем новый слушатель драг-события
					
					@Override
					public boolean onDrag(View v, DragEvent event) {//перегружаем в слушателе метод onDrag()
						if((event.getAction() == DragEvent.ACTION_DROP)) {//при дропе внутрь виджета v
							if(!(event.getLocalState().equals(v))) {//если дропаем на самое себя
								((TextView)v).setText(event.getClipData().getItemAt(0).getText());//пишем куда дропнули то что было в перетаскиваемой клетке
								if (((BoardCell)v).isHighlight()) {//если клетка куда дропнули подсвечена
									selectedLetters+= BoardCell.letterPoints(((BoardCell)v).getText().toString());//то добавляем за нее очки и обновляем отображение очков
									count.setText("очки за выделенные слова: "+selectedLetters);
								}
								if (event.getLocalState() instanceof BoardCell) {
									if (((BoardCell)(event.getLocalState())).isHighlight()) { //если то что драгаем было подсвечено, вычитаем из очков ее стоимость
									selectedLetters-= BoardCell.letterPoints(((BoardCell)(event.getLocalState())).getText().toString());
									count.setText("очки за выделенные слова: "+selectedLetters); }
									
								}
								((TextView)event.getLocalState()).setText("");//пишем пустую строку
								return true;//событие обработано
							}
							
						}
						//if(event.getAction() == DragEvent.ACTION_DRAG_ENDED) {}
						return true;//событие обработано
					}
				});
				tvTable[i][j].setOnClickListener(new View.OnClickListener() { //задаем клетке поля слушатель клика для подсветки
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((BoardCell)v).setHighlight(!((BoardCell)v).isHighlight());
						if (((BoardCell)v).isHighlight()) {//если клетка подсвечена
							v.setBackgroundColor(0x44ff00ff);//заливаем фон полупрозрачным розовым
							Log.d(MainActivity.TAG,""+BoardCell.letterPoints(((BoardCell)v).getText().toString()));
							selectedLetters+= BoardCell.letterPoints(((BoardCell)v).getText().toString());
							count.setText("очки за выделенные слова: "+selectedLetters);
						}
						else {
							selectedLetters-= BoardCell.letterPoints(((BoardCell)v).getText());
							count.setText("очки за выделенные слова: "+selectedLetters);
							v.setBackgroundColor(Color.TRANSPARENT);
						}
					}
				});
				tvTable[i][j].setOnLongClickListener( new View.OnLongClickListener() {
					
					@Override
					public boolean onLongClick(View v) {
						// TODO Auto-generated method stub
						if (!((BoardCell)v).getText().toString().equals("")) {
							ClipData cd = ClipData.newPlainText("letter", ((TextView)v).getText());
							DragShadowBuilder sb = new View.DragShadowBuilder(v);
							v.startDrag(cd, sb, v, 0);
							MainActivity.setDragged(1); 
//						v.setVisibility(View.INVISIBLE);
							return true; 
						}
						else return false;
					}
				});
				rowsList[i].addView(tvTable[i][j],28,28);
			}
			rows.addView(rowsList[i]);
		}
//здесь идет присвоение клеткам специальных значений для умножений номинала		
		tvTable[0][0].setSpecial(BoardCell.W3);
		tvTable[6][0].setSpecial(BoardCell.W3);
		tvTable[14][0].setSpecial(BoardCell.W3);
		tvTable[0][6].setSpecial(BoardCell.W3);
		tvTable[14][6].setSpecial(BoardCell.W3);
		tvTable[0][14].setSpecial(BoardCell.W3);
		tvTable[6][14].setSpecial(BoardCell.W3);
		tvTable[14][14].setSpecial(BoardCell.W3);
		
		tvTable[5][1].setSpecial(BoardCell.W2);
		tvTable[1][5].setSpecial(BoardCell.W2);
		tvTable[9][1].setSpecial(BoardCell.W2);
		tvTable[1][9].setSpecial(BoardCell.W2);
		tvTable[5][5].setSpecial(BoardCell.W2);
		tvTable[5][9].setSpecial(BoardCell.W2);
		tvTable[9][5].setSpecial(BoardCell.W2);
		tvTable[9][9].setSpecial(BoardCell.W2);
		tvTable[5][13].setSpecial(BoardCell.W2);
		tvTable[13][5].setSpecial(BoardCell.W2);
		tvTable[13][9].setSpecial(BoardCell.W2);
		tvTable[9][13].setSpecial(BoardCell.W2);
		
		tvTable[1][1].setSpecial(BoardCell.L3);
		tvTable[2][2].setSpecial(BoardCell.L3);
		tvTable[3][3].setSpecial(BoardCell.L3);
		tvTable[4][4].setSpecial(BoardCell.L3);
		tvTable[1][13].setSpecial(BoardCell.L3);
		tvTable[2][12].setSpecial(BoardCell.L3);
		tvTable[3][11].setSpecial(BoardCell.L3);
		tvTable[4][10].setSpecial(BoardCell.L3);
		tvTable[13][13].setSpecial(BoardCell.L3);
		tvTable[12][12].setSpecial(BoardCell.L3);
		tvTable[11][11].setSpecial(BoardCell.L3);
		tvTable[10][10].setSpecial(BoardCell.L3);
		tvTable[13][1].setSpecial(BoardCell.L3);
		tvTable[12][2].setSpecial(BoardCell.L3);
		tvTable[11][3].setSpecial(BoardCell.L3);
		tvTable[10][4].setSpecial(BoardCell.L3);
		
		//TODO доделать бонусы L2
		tvTable[1][1].setSpecial(BoardCell.L2);

		
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (int i = 0; i < 15; i++) {
					for (int j = 0; j < 15; j++) {
						if (tvTable[i][j].isHighlight()) {
							selectedLetters-= BoardCell.letterPoints(tvTable[i][j].getText().toString());
							tvTable[i][j].setHighlight(false);
							tvTable[i][j].setBackgroundColor(Color.TRANSPARENT);
							count.setText("очки за выделенные слова: "+selectedLetters);
						}
						tvTable[i][j].setText("");
					}
				}
				
			}
		});
		
		findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (View tok : tokenArr) {
					if(((TextView)tok).getText()=="") ((TextView)tok).setText(pickLetter());
				}
				// TODO Auto-generated method stub
				
			}
		});
//		ViewGroup boardL = (ViewGroup)findViewById(R.id.linL1);
		LetterDragEvntL odl = new LetterDragEvntL();     // odl - кастомный слушатель драг-события, который мы повесим на буквы
		BoardDragListener bdl = new BoardDragListener(); // bdl - кастомный слушатель драг-события, который мы повесим на игровое поле
		MyOnTouchListener otl = new MyOnTouchListener(); // otl - кастомный слушатель события прикосновения к экрану, вешаем на буквы
		for (View v : tokenArr) {
			if (v!=null) {
				v.setOnDragListener(odl);
				v.setOnTouchListener(otl);
			}
		}
//		View[][] fields = new View[15][15];
//		for (int i = 0;i<15;i++) {
//			for (int j =0; j<15; j++) {
////				fields[i][j] = ;
//			}
//		}
		
//		ImageView iv = (ImageView)findViewById(R.id.imageView1); //iv - это View с нашим игровым полем
//		/*
//		 * выставляем ширину и высоту поля*/
//		iv.getLayoutParams().height = 220;
//		iv.getLayoutParams().width = 220;
//		iv.setOnDragListener(bdl);

	}

	private void initDeck() {
		// TODO Auto-generated method stub
		for(int i = 0; i<8;i++) deck.add("А");
		for(int i = 0; i<2;i++) deck.add("Б");
		for(int i = 0; i<4;i++) deck.add("В");
		for(int i = 0; i<2;i++) deck.add("Г");
		for(int i = 0; i<4;i++) deck.add("Д");
		for(int i = 0; i<9;i++) deck.add("Е");
		for(int i = 0; i<1;i++) deck.add("Ж");
		for(int i = 0; i<2;i++) deck.add("З");
		for(int i = 0; i<6;i++) deck.add("И");
		for(int i = 0; i<1;i++) deck.add("Й");
		for(int i = 0; i<4;i++) deck.add("К");
		for(int i = 0; i<4;i++) deck.add("Л");
		for(int i = 0; i<3;i++) deck.add("М");
		for(int i = 0; i<5;i++) deck.add("Н");
		for(int i = 0; i<10;i++) deck.add("О");
		for(int i = 0; i<4;i++) deck.add("П");
		for(int i = 0; i<5;i++) deck.add("Р");
		for(int i = 0; i<5;i++) deck.add("С");
		for(int i = 0; i<5;i++) deck.add("Т");
		for(int i = 0; i<4;i++) deck.add("У");
		for(int i = 0; i<1;i++) deck.add("Ф");
		for(int i = 0; i<1;i++) deck.add("Х");
		for(int i = 0; i<1;i++) deck.add("Ц");
		for(int i = 0; i<1;i++) deck.add("Ч");
		for(int i = 0; i<1;i++) deck.add("Ш");
		for(int i = 0; i<1;i++) deck.add("Щ");
		for(int i = 0; i<1;i++) deck.add("Ъ");
		for(int i = 0; i<2;i++) deck.add("Ы");
		for(int i = 0; i<2;i++) deck.add("Ь");
		for(int i = 0; i<1;i++) deck.add("Э");
		for(int i = 0; i<1;i++) deck.add("Ю");
		for(int i = 0; i<2;i++) deck.add("Я");
		Log.d(TAG,"в колоде букв: "+deck.size());
	}

	public String pickLetter() {
		// TODO переделать, чтобы брал из "колоды"
		if (deck.size()>0) {
			double rand = Math.random();
			int index = (int)(Math.random() * (deck.size()-1));
			String result = deck.get(index);
			deck.remove(index);
			Log.d(TAG,"в колоде букв: "+deck.size());
			return result;
		} else return "";

//		switch (2 + (int)(Math.random() * ((10 - 2) + 1))) {
//		case 2: return "А";
//		case 3: return "Е";
//		case 4: return "О";
//		case 5: return "Н";
//		case 6: return "У";
//		case 7: return "П";
//		case 8: return "И";
//		case 9: return "К";
//		case 10: return "С";
//		default: return "*";
//		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static int getDragged() {
		return dragged;
	}

	public static void setDragged(int dragged) {
		MainActivity.dragged = dragged;
	}

	public static void incSelectedLetters(int i) {
		// TODO Auto-generated method stub
		selectedLetters+=i;
	}

}
