package com.tictactower.player;

import com.tictactower.Game;
import com.tictactower.gameboard.Mark;

public class Player1 extends Player {

	public void setMark(int x, int y) {
		Game.getInstance().getGameboard().setMarkToActive(x, y, this);
	}

	public Mark getActiveMark(){
		return Mark.P1_ACTIVE;
	}
	
	public int getPlayerId(){
		return 1;
	}
}
