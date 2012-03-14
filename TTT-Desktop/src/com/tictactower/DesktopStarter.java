package com.tictactower;

import com.badlogic.gdx.backends.jogl.JoglApplication;

public class DesktopStarter {
	
	public static void main(String[] args) {
		new JoglApplication(Game.getInstance(), "Tic-Tac-Tower", 320, 480, false);
	}
}
