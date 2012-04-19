package com.tictactower.player;

import com.tictactower.Game;

public class Player1 extends Player {

	public void setMark(int x, int y) {
		Game.getInstance().getGameboard().setMarkToActive(x, y, this);
	}

}
