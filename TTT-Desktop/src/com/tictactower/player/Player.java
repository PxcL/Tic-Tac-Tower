package com.tictactower.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;
import com.tictactower.skills.Skill;
import com.tictactower.ui.Buttons;

public abstract class Player {
	
	protected boolean notUsedMark;
	
	protected int score;
	
	protected int empCount = 0;
	protected int newTowerCount = 0;
	protected int destroyTowerCount = 0;
	protected int lastSkillCount = 0;
	
	protected ArrayList<Skill> skills;
	
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
	
	public int getEmpCount() {
		return empCount;
	}
	
	public void subEmpCount() {
		empCount--;
		if (empCount <= 0) {
			empCount = 0;
			Buttons.getButtonSilence().setActive(false);
		}
	}

	public void addEmpCount() {
		empCount++;
		Buttons.getButtonSilence().setActive(true);
		Gdx.app.log("EMP", Integer.toString(empCount));
	}

	public int getNewTowerCount() {
		return newTowerCount;
	}

	public void setNewTowerCount(int newTowerCount) {
		this.newTowerCount = newTowerCount;
	}

	public int getDestroyTowerCount() {
		return destroyTowerCount;
	}

	public void setDestroyTowerCount(int destroyTowerCount) {
		this.destroyTowerCount = destroyTowerCount;
	}

	public int getLastSkillCount() {
		return lastSkillCount;
	}

	public void setLastSkillCount(int lastSkillCount) {
		this.lastSkillCount = lastSkillCount;
	}
	
	
	
}
