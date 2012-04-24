package com.tictactower.player;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.tictactower.gameboard.Mark;
import com.tictactower.skills.Skill;
import com.tictactower.ui.buttons.Buttons;

public abstract class Player {
	
	protected boolean notUsedMark;
	
	protected int score;
	protected int id;
	protected int skillCap = 0;
	protected boolean silenced;

	protected int silenceCount = 0;
	protected int buildCount = 0;
	protected int shootCount = 0;
	
	protected int silenceUsage = 0;
	protected int buildUsage = 0;
	protected int shootUsage = 0;

	
	public abstract int getPlayerId();
	
	protected ArrayList<Skill> skills;
	
	public Player() {
		notUsedMark = false;
	}

	public abstract void setMark(int x, int y);
	
	public boolean getNotUsedMark() {
		return notUsedMark;
	}
	
	public void setNotUsedMark(boolean notUsedMark) {
		this.notUsedMark = notUsedMark;
	}
	
	public boolean isSilenced(){
		return silenced;
	}
	public void setSilenced(boolean b){
		silenced = b;
	}
		
	public int getSilenceCount() {
		return silenceCount;
	}
	
	public void subSilenceCount() {
		silenceCount--;
		if (silenceCount <= 0) {
			silenceCount = 0;
			Buttons.getButtonSilence().setActive(false);
		}
	}

	public void addSilenceCount() {
		Gdx.app.log("Silence", "Increase");
		silenceCount++;
		Buttons.getButtonSilence().setActive(true);
	}

	public int getBuildCount() {
		return buildCount;
	}

	public void addBuildCount() {
		Gdx.app.log("Build", "Increase");
		buildCount++;
		Buttons.getButtonNewTower().setActive(true);
	}
	
	public void subBuildCount() {
		buildCount--;
		if (buildCount <= 0) {
			buildCount = 0;
			Buttons.getButtonNewTower().setActive(false);
		}
	}

	public int getShootCount() {
		return shootCount;
	}

	public void addShootCount() {
		Gdx.app.log("Shoot", "Increase");
		shootCount++;
		Buttons.getButtonDestroyTower().setActive(true);
	}
	
	public void subShootCount() {
		shootCount--;
		if (shootCount <= 0) {
			shootCount = 0;
			Buttons.getButtonDestroyTower().setActive(false);
		}
	}

	public int getSkillCap() {
		return skillCap;
	}

	public void addSkillCap() {
		Gdx.app.log("Skill Cap", "Increase");
		skillCap++;
		Buttons.getButtonMultipleTowers().setActive(true);
	}
	
	public void subSkillCap() {
		skillCap--;
		if (skillCap <= 0) {
			skillCap = 0;
			Buttons.getButtonMultipleTowers().setActive(false);
		}
	}
	
	public void IncSilenceUsage(){
		silenceUsage++;
	}
	
	public int GetSilenceUsage(){
		return silenceUsage;
	}
	
	public void IncBuildUsage(){
		buildUsage++;
	}
	
	public int GetBuildUsage(){
		return buildUsage; 
	}
	
	public void IncShootUsage(){
		shootUsage++;
	}
	
	public int GetShootUsage(){
		return shootUsage;
	}
	
	public void ResetSkillUsage(){
		silenceUsage = 0;
		buildUsage = 0;
		shootUsage = 0;
	}
	
	public abstract Mark getActiveMark();
	
}
