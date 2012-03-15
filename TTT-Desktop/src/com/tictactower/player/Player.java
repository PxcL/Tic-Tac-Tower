package com.tictactower.player;

import com.tictactower.Game;

public abstract class Player {
	
	protected int score;
	
	protected int empCount;
	protected int newTowerCount;
	protected int destroyTowerCount;
	protected int lastSkillCount;

	public void setMark(int x, int y, Player player) {
		Game.getInstance().getGameboard().setMark(x, y, player);
	}
	
	
}
