package com.tictactower.gameboard;

public class Gameboard {
	
	Piece[][] gameboard;
	
	public Gameboard() {
		// Oppretter et gameboard med 9 x 9 ruter.
		gameboard = new Piece[9][9];
		
		// Initialiserer gameboardet med tom brikke i hver rute.
		resetGameboard();
	}
	
	public void setPiece(int x, int y, Piece piece) {
		gameboard[x][y] = piece;
	}
	
	public Piece getPiece(int x, int y) {
		return gameboard[x][y];
	}
	
	public void clearPiece(int x, int y) {
		gameboard[x][y] = Piece.EMPTY;
	}
	
	public void resetGameboard() {
		// Setter tom brikke i hver rute.
		for (int i = 0; i < gameboard.length; i++) {
			for (int y = 0; y < gameboard[i].length; y++) {
				gameboard[i][y] = Piece.EMPTY;
			}
		}
	}	
	
}
