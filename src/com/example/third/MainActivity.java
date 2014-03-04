package com.example.third;

import com.example.third.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	public static final String TAG = "mydbg";// Это тег для журналирования, вызываемого Log.d(TAG,"сообщение")
	/**
	 * Этот метод вызывается при запуске активити, в onCreate() пишется инициализация
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); // конструкция super.имя метода нужна для вызова одноименного метода класса-родителя
		setContentView(R.layout.activity_main); //setContentView(id layout-ресурса) заполняет экран активити содержимым из соотв. layout.xml
		View[] tokenArr = {findViewById(R.id.TextView07), //tokenArr - массив из 7 TextView с буквами
				findViewById(R.id.TextView06),
				findViewById(R.id.TextView05),
				findViewById(R.id.TextView04),
				findViewById(R.id.TextView03),
				findViewById(R.id.TextView02),
				findViewById(R.id.TextView01)};
		View groupLayout = findViewById(R.id.gl1);
		LetterDragEvntL odl = new LetterDragEvntL();     // odl - кастомный слушатель драг-события, который мы повесим на буквы
		BoardDragListener bdl = new BoardDragListener(); // bdl - кастомный слушатель драг-события, который мы повесим на игровое поле
		MyOnTouchListener otl = new MyOnTouchListener(); // otl - кастомный слушатель события прикосновения к экрану, вешаем на буквы
		for (View v : tokenArr) {
			if (v!=null) {
				v.setOnDragListener(odl);
				v.setOnTouchListener(otl);
			}
		}
		
		ImageView iv = (ImageView)findViewById(R.id.imageView1); //iv - это View с нашим игровым полем
		/*
		 * выставляем ширину и высоту поля*/
		iv.getLayoutParams().height = 220;
		iv.getLayoutParams().width = 220;
		iv.setOnDragListener(bdl);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
