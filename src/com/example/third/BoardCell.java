package com.example.third;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class BoardCell extends TextView {
	private int special; 
	public static final int L2 = 1, L3 = 2, W2 = 3, W3 = 4;
	public BoardCell(Context context) {
		super(context);
		special = 0;
		// TODO Auto-generated constructor stub
	}

	public BoardCell(Context context, AttributeSet attrs) {
		super(context, attrs);
		special = 0;
		// TODO Auto-generated constructor stub
	}

	public BoardCell(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		special = 0;
		// TODO Auto-generated constructor stub
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

}
