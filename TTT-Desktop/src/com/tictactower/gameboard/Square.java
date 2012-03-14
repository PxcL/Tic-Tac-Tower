package com.tictactower.gameboard;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Square {

	private Vector2 position;
	private Mark mark;
	public final static int WIDTH = Gdx.graphics.getWidth() / 14;
	public final static int HEIGHT = 20;
	
	public Square(int x, int y) {
		position = new Vector2(Gameboard.X_OFFSET + WIDTH * x, Gameboard.Y_OFFSET + HEIGHT * y);
		mark = Mark.EMPTY;
	}
	
	public void clear() {
		mark = Mark.EMPTY;
	}
	
	public void setMark(Mark mark) {
		this.mark = mark;
	}
	
	public Mark getMark() {
		return mark;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	
}
