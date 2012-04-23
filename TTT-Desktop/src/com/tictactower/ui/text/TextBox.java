package com.tictactower.ui.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public abstract class TextBox{
	
	public static final BitmapFont font = new BitmapFont();
	
	private Vector2 position;
	private String text;
	private float[] colorActive;
	private float[] colorDeactive;
	
	public TextBox(int position_x, int position_y, String text, float[] colorActive, float[] colorDeactive) {
		position = new Vector2(position_x, position_y);
		this.text = text;
		this.colorActive = colorActive;
		this.colorDeactive = colorDeactive;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public String getText() {
		return text;
	}
	
	public float[] getColorActive() {
		return colorActive;
	}
	
	public float[] getColorDeactive() {
		return colorDeactive;
	}
}