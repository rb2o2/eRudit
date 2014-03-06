package com.example.third;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class BoardCell extends TextView {
	private int special; 
	private boolean highlight;
	private int coordX,coordY;
	/**
	 * удвоение буквы 
	 * */
	public static final int L2 = 1;
	/**
	 * утроение буквы 
	 * */
	public static final int L3 = 2;
	/**
	 * удвоение слова 
	 * */
	public static final int W2 = 3;
	/**
	 * утроение слова
	 * */
	public static final int W3 = 4;
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
	/**
	 * @return целочисленное значение бонуса: 1,2,3 или 4 <br>
	 * @see 
	 * */
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
	
	public static int letterPoints(CharSequence letter_) {
		String letter = (String)letter_;
		Log.d(MainActivity.TAG,letter);
		if (letter.length() == 0) return 0;
		else if (letter.equals("А")) return 1;
		else if (letter.equals("Б")) return 3;
		else if (letter.equals("В")) return 1;
		else if (letter.equals("Г")) return 3;
		else if (letter.equals("Д")) return 2;
		else if (letter.equals("Е")) return 1;
		else if (letter.equals("Ж")) return 5;
		else if (letter.equals("З")) return 5;
		else if (letter.equals("И")) return 1;
		else if (letter.equals("Й")) return 4;
		else if (letter.equals("К")) return 2;
		else if (letter.equals("Л")) return 2;
		else if (letter.equals("М")) return 2;
		else if (letter.equals("Н")) return 1;
		else if (letter.equals("О")) return 1;
		else if (letter.equals("П")) return 2;
		else if (letter.equals("Р")) return 1;
		else if (letter.equals("С")) return 1;
		else if (letter.equals("Т")) return 1;
		else if (letter.equals("У")) return 2;
		else if (letter.equals("Ф")) return 8;
		else if (letter.equals("Х")) return 5;
		else if (letter.equals("Ц")) return 5;
		else if (letter.equals("Ч")) return 5;
		else if (letter.equals("Ш")) return 8;
		else if (letter.equals("Щ")) return 10;
		else if (letter.equals("Ъ")) return 15;
		else if (letter.equals("Ы")) return 4;
		else if (letter.equals("Ь")) return 3;
		else if (letter.equals("Э")) return 8;
		else if (letter.equals("Ю")) return 8;
		else if (letter.equals("Я")) return 3;
		
		else return 0;
	}

}
