package com.tictactower.gameboard;

import com.badlogic.gdx.Gdx;
import com.tictactower.player.Player;
import com.tictactower.player.Player1;

public class Gameboard {
	
	public final static int GAMEBOARD_EDGE_LENGTH = Square.EDGE_LENGTH * 9;
	public final static int X_OFFSET = (Gdx.graphics.getWidth() - GAMEBOARD_EDGE_LENGTH) / 2;
	public final static int Y_OFFSET = (Gdx.graphics.getHeight() - (X_OFFSET + GAMEBOARD_EDGE_LENGTH));
	public final static int NUMBER_OF_COLUMNS = 9;
	public final static int NUMBER_OF_ROWS = 9;
	
	Square[][] gameboard;
	
	public Gameboard() {
		// Oppretter et gameboard med 9 x 9 ruter.
		gameboard = new Square[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
		
		
		// Initialiserer gameboardet med tom brikke i hver rute.
		resetGameboard();
	}
	
	public void setMarkToActive(int x, int y, Player player) {
		if (player instanceof Player1) {
			gameboard[x][y].setMark(Mark.P1_ACTIVE);
		}
		else {
			gameboard[x][y].setMark(Mark.P2_ACTIVE);
		}	
	}
	public void setMarkToBuilt(int x, int y, Player player) {
		if (player instanceof Player1) {
			gameboard[x][y].setMark(Mark.P1_BUILT);
		}
		else {
			gameboard[x][y].setMark(Mark.P2_BUILT);
		}	
	}	
	public void setMark(int x, int y, Mark m){
		gameboard[x][y].setMark(m);
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
