package com.tictactower.player;

import com.tictactower.Game;
import com.tictactower.gameboard.Mark;

public class Player2 extends Player {

	public void setMark(int x, int y) {
		Game.getInstance().getGameboard().setMarkToActive(x, y, this);
	}
	
	public Mark getActiveMark(){
		return Mark.P2_ACTIVE;
	}
}
