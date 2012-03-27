package com.tictactower.player;

import java.util.ArrayList;

import com.tictactower.Game;
import com.tictactower.skills.Skill;
import com.tictactower.ui.Buttons;

public abstract class Player {
	
	protected boolean notUsedMark;
	
	protected int score;
	
	protected int empCount = 0;
	protected int newTowerCount = 0;
	protected int destroyTowerCount = 0;
	protected int multipleTowersCount = 0;
	
	protected int silenceUsage = 0;
	protected int buildUsage = 0;
	protected int shootUsage = 0;
	
	protected boolean isSilenced = false;
	
	protected ArrayList<Skill> skills;
	
	public Player() {
		notUsedMark = false;
	}

	public void setMark(int x, int y, Player player) {
		Game.getInstance().getGameboard().setMarkToActive(x, y, player);
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
	}

	public int getNewTowerCount() {
		return newTowerCount;
	}

	public void addNewTowerCount() {
		newTowerCount++;
		Buttons.getButtonNewTower().setActive(true);
	}
	
	public void subNewTowerCount() {
		newTowerCount--;
		if (newTowerCount <= 0) {
			newTowerCount = 0;
			Buttons.getButtonNewTower().setActive(false);
		}
	}

	public int getDestroyTowerCount() {
		return destroyTowerCount;
	}

	public void addDestroyTowerCount() {
		destroyTowerCount++;
		Buttons.getButtonDestroyTower().setActive(true);
	}
	
	public void subDestroyTowerCount() {
		destroyTowerCount--;
		if (destroyTowerCount <= 0) {
			destroyTowerCount = 0;
			Buttons.getButtonDestroyTower().setActive(false);
		}
	}

	public int getMultipleTowerCount() {
		return multipleTowersCount;
	}

	public void addMultipleTowerCount() {
		multipleTowersCount++;
		Buttons.getButtonMultipleTowers().setActive(true);
	}
	
	public void subMultipleTowersCount() {
		multipleTowersCount--;
		if (multipleTowersCount <= 0) {
			multipleTowersCount = 0;
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
	
	public boolean IsSilenced(){
		return isSilenced;
	}
	
	public void SetSilenced(boolean b){
		isSilenced = b;
	}
	
}
