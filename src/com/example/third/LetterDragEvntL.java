package com.example.third;

import android.app.Activity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.TextView;

public class LetterDragEvntL implements OnDragListener {
	boolean flagBackDrop = false;
	@Override
	public boolean onDrag(View v, DragEvent e) {
		String letter = ((TextView)v).getText().toString();
		switch (e.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED  : { 
			// записываем в журнал начало драга
			Log.d(MainActivity.TAG,""+v.getId()+" drag started");
			if (v.equals(e.getLocalState())) Log.d(MainActivity.TAG,"LetterL "+letter+" drag started");
//			v.on
			if (!(e.getLocalState() instanceof BoardCell)) return false;
			}
		case DragEvent.ACTION_DRAG_ENDED : {
			Log.d(MainActivity.TAG, ""+v.getId()+ " drag ended with result "+e.getResult());
			if (v.equals(e.getLocalState())) {
				Log.d(MainActivity.TAG,"result = "+e.getResult()+" LetterL "+letter+" drag ended");
//				((TextView)v).setText("");
			}

//			Log.d(MainActivity.TAG,"LetterL "+v.getId()+" drag ended");
//			if (v.equals(e.getLocalState())&&!flagBackDrop) ((TextView)v).setText("");
//			flagBackDrop = false;
			break;
		}
		case DragEvent.ACTION_DROP : {
			Log.d(MainActivity.TAG, ""+v.getId()+" drop" );
			
			if (v.equals(e.getLocalState())) {
				Log.d(MainActivity.TAG,"LetterL "+letter+" dropped back"); 
				return false;
			}
			if (e.getLocalState() instanceof BoardCell && ((TextView)v).getText().toString().contentEquals("")) {
				((TextView)v).setText(((BoardCell)(e.getLocalState())).getText());
				MainActivity.incSelectedLetters((int) -1* BoardCell.letterPoints(((BoardCell)(e.getLocalState())).getText().toString()));
				MainActivity.getCount().setText("очки за выделенные слова: "+MainActivity.getSelectedLetters());
				((BoardCell)(e.getLocalState())).setText("");
			}
			break;
//			if (v.equals(e.getLocalState())) {
//				((TextView)v).setText(((TextView)e.getLocalState()).getText());
//				Log.d(MainActivity.TAG,v.getId()+" dropping back");
//			}
			
		}
		default: break; 
		}
		return true;

	}

}
