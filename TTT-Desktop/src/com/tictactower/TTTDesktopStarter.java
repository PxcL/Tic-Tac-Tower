package com.tictactower;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class TTTDesktopStarter {
	
	public static void main(String[] args) {
		new JoglApplication(new TicTacTowerDesktop(), "Tic-Tac-Tower", 480, 320, false);
	}
}
