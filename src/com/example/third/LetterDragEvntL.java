package com.example.third;

import android.app.Activity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.TextView;

public class LetterDragEvntL implements OnDragListener {

	@Override
	public boolean onDrag(View v, DragEvent e) {
		switch (e.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED  : { 
			// записываем в журнал начало драга
			Log.d(MainActivity.TAG,"LetterL"+v.getId()+" drag started");
			break;
			}
		
		default: break; 
		}
		return true;

	}

}
