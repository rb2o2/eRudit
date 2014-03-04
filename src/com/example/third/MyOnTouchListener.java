package com.example.third;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MyOnTouchListener implements OnTouchListener {

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		ClipData cd;
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			cd = ClipData.newPlainText("letter", ((TextView)v).getText());
			DragShadowBuilder sb = new View.DragShadowBuilder(v);
			v.startDrag(cd, sb, v, 0);
//			v.setVisibility(View.INVISIBLE);
			return true;
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
//			v.setVisibility(View.VISIBLE);
			return true;
		}
		else return false;
	}

}
