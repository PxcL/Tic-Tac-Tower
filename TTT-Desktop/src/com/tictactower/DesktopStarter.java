package com.tictactower;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {
	
	public static void main(String[] args) {
		new JoglApplication(new Game(), "Tic-Tac-Tower", 480, 320, false);
	}
}
