package com.example.third;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;

public class BoardDragListener implements OnDragListener {

	@Override
	public boolean onDrag(View v, DragEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		
		//кодга наше поле получает событие DragEvent.ACTION_DRAG_ENTERED, он становится невидимым
		case DragEvent.ACTION_DRAG_ENTERED : {
			if (v.getId()== R.id.imageView1) {v.setVisibility(View.INVISIBLE);}

			Log.d(MainActivity.TAG,"BoardL"+v.getId()+" drag entered");
			break;
		}
		
		//кодга наше поле получает событие DragEvent.ACTION_DRAG_EXITED, он становится видимым
		case DragEvent.ACTION_DRAG_EXITED : {
			v.setVisibility(View.VISIBLE);
			Log.d(MainActivity.TAG,""+v.getId()+" drag exited");

			break;
		}
	
		default : break;
		}
		
		return true;
	}

}
