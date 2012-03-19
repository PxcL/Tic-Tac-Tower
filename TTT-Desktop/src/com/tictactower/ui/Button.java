package com.tictactower.ui;

import com.badlogic.gdx.math.Vector2;

public abstract class Button {
	
	protected int width;
	protected int height;
	protected Vector2 position;
	protected boolean pressed;
	
	protected Button(int width, int height, int positionX, int positionY) {
		this.width = width;
		this.height = height;
		position = new Vector2(positionX, positionY);
		pressed = false;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public abstract void execute();
	
	
	
}
