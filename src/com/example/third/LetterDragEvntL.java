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
		Object drag = e.getLocalState();
		switch (e.getAction()) {
		case DragEvent.ACTION_DRAG_STARTED  : { 
			// записываем в журнал начало драга
			Log.d(MainActivity.TAG,""+v.getId()+" drag started");
			if (v.equals(drag)) Log.d(MainActivity.TAG,"LetterL "+letter+" drag started");
//			v.on
			if (!(drag instanceof BoardCell)) return false;
			}
		case DragEvent.ACTION_DRAG_ENDED : {
			Log.d(MainActivity.TAG, ""+v.getId()+ " drag ended with result "+e.getResult());
			if (v.equals(drag)) {
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
			
			if (v.equals(drag)) {
				Log.d(MainActivity.TAG,"LetterL "+letter+" dropped back"); 
				return false;
			}
			if (drag instanceof BoardCell && ((TextView)v).getText().toString().contentEquals("")) {
				((TextView)v).setText(((BoardCell)drag).getText());
				if (((BoardCell)(e.getLocalState())).isHighlight()) {
					MainActivity.incSelectedLetters((int) -1* BoardCell.letterPoints(((BoardCell)drag).getText().toString()));
					MainActivity.getCount().setText("очки за выделенные слова: "+MainActivity.getSelectedLetters());
				}
				
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
