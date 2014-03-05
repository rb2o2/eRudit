package com.example.third;

import com.example.third.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String TAG = "mydbg";// Это тег для журналирования, вызываемого Log.d(TAG,"сообщение")
	private BoardCell tvTable[][] = new BoardCell[15][15];
	private View[] tokenArr;

	/**
	 * Этот метод вызывается при запуске активити, в onCreate() пишется инициализация
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); // конструкция super.имя метода нужна для вызова одноименного метода класса-родителя
		setContentView(R.layout.activity_main); //setContentView(id layout-ресурса) заполняет экран активити содержимым из соотв. layout.xml
		tokenArr = new View[] {findViewById(R.id.TextView07), //tokenArr - массив из 7 TextView с буквами
				findViewById(R.id.TextView06),
				findViewById(R.id.TextView05),
				findViewById(R.id.TextView04),
				findViewById(R.id.TextView03),
				findViewById(R.id.TextView02),
				findViewById(R.id.TextView01)};
		View groupLayout = findViewById(R.id.gl1);
		RelativeLayout rlBoard = (RelativeLayout)findViewById(R.id.rl1);
//		rlBoard.getLayoutParams().
		LinearLayout rows = (LinearLayout)findViewById(R.id.rows);
		LinearLayout rowsList[] = new LinearLayout[15];
		for (int i = 0; i<15; i++) {
			rowsList[i] = new LinearLayout(this);//HORIZONTAL by default
			for (int j = 0; j<15; j++)	{
				
				tvTable[i][j] = new BoardCell(this);
				tvTable[i][j].setText("");
				tvTable[i][j].setGravity(Gravity.CENTER);
				tvTable[i][j].setCoordX(j);
				tvTable[i][j].setCoordY(i);
				tvTable[i][j].setOnDragListener(new View.OnDragListener() {
					
					@Override
					public boolean onDrag(View v, DragEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction() == DragEvent.ACTION_DROP) ((TextView)v).setText(event.getClipData().getItemAt(0).getText());
						return true;
					}
				});
				tvTable[i][j].setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						((BoardCell)v).setHighlight(!((BoardCell)v).isHighlight());
						if (((BoardCell)v).isHighlight()) v.setBackgroundColor(0x44ff00ff);
						else v.setBackgroundColor(Color.TRANSPARENT);
					}
				});
				rowsList[i].addView(tvTable[i][j],14,14);
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
					for (int j = 0; j < 15; j++) tvTable[i][j].setText("");
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

	public String pickLetter() {
		// TODO переделать, чтобы брал из "колоды"
		double rand = Math.random();


		switch (2 + (int)(Math.random() * ((10 - 2) + 1))) {
		case 2: return "А";
		case 3: return "Е";
		case 4: return "О";
		case 5: return "Н";
		case 6: return "У";
		case 7: return "П";
		case 8: return "И";
		case 9: return "К";
		case 10: return "С";
		default: return "*";
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
