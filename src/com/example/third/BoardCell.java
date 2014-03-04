package com.example.third;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class BoardCell extends TextView {
	private int special; 
	private boolean highlight;
	private int coordX,coordY;
	public static final int L2 = 1, L3 = 2, W2 = 3, W3 = 4;
	public BoardCell(Context context) {
		super(context);
		special = 0;
		highlight = false;
		coordX = -1;
		coordY = -1;
		// TODO Auto-generated constructor stub
	}

	public BoardCell(Context context, AttributeSet attrs) {
		super(context, attrs);
		special = 0;
		highlight = false;
		coordX = -1;
		coordY = -1;
		// TODO Auto-generated constructor stub
	}

	public BoardCell(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		special = 0;
		highlight = false;
		coordX = -1;
		coordY = -1;
		// TODO Auto-generated constructor stub
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

}
