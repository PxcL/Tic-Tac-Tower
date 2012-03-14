package com.tictactower.gameboard;

import com.badlogic.gdx.Gdx;
import com.tictactower.player.Player;
import com.tictactower.player.Player1;

public class Gameboard {
	
	public final static int TOTAL_WIDTH = Square.WIDTH * 9;
	public final static int TOTAL_HEIGHT = Square.HEIGHT * 9;
	public final static int X_OFFSET = (Gdx.graphics.getWidth() - TOTAL_WIDTH) / 2;
	public final static int Y_OFFSET = 250;
	
	Square[][] gameboard;
	
	public Gameboard() {
		// Oppretter et gameboard med 9 x 9 ruter.
		gameboard = new Square[9][9];
		
		
		// Initialiserer gameboardet med tom brikke i hver rute.
		resetGameboard();
	}
	
	public void setMark(int x, int y, Player player) {
		if (player instanceof Player1) {
			gameboard[x][y].setMark(Mark.P1_ACTIVE);
		}
		else {
			gameboard[x][y].setMark(Mark.P2_ACTIVE);
		}	
	}
	
	public Mark getMark(int x, int y) {
		return gameboard[x][y].getMark();
	}
	
	public void clearMark(int x, int y) {
		gameboard[x][y].clear();
	}
	
	public void resetGameboard() {
		// Setter tom brikke i hver rute.
		for (int i = 0; i < gameboard.length; i++) {
			for (int y = 0; y < gameboard[i].length; y++) {
				gameboard[i][y] = new Square(i, y);
			}
		}
	}	
	
	public Square[][] getGameboard() {
		return gameboard;
	}
	
}
