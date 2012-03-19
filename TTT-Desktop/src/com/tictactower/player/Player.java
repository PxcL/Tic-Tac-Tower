package com.tictactower.player;

import com.tictactower.Game;

public abstract class Player {
	
	protected boolean notUsedMark;
	
	protected int score;
	
	protected int empCount;
	protected int newTowerCount;
	protected int destroyTowerCount;
	protected int lastSkillCount;
	
	public Player() {
		notUsedMark = false;
	}

	public void setMark(int x, int y, Player player) {
		Game.getInstance().getGameboard().setMark(x, y, player);
	}
	
	public boolean getNotUsedMark() {
		return notUsedMark;
	}
	
	public void setNotUsedMark(boolean notUsedMark) {
		this.notUsedMark = notUsedMark;
	}
	
}
