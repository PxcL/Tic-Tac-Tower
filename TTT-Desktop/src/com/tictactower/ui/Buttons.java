package com.tictactower.ui;

import java.util.ArrayList;

public class Buttons {

	private final static ArrayList<Button> buttonList = new ArrayList<Button>();
	private final static ButtonEndTurn buttonEndTurn = new ButtonEndTurn(false);
	private final static ButtonSilence buttonSilence = new ButtonSilence(false);
	private final static ButtonNewTower buttonNewTower = new ButtonNewTower(false);

	private final static ButtonDestroyTower buttonDestroyTower = new ButtonDestroyTower(false);
	private final static ButtonMultipleTowers buttonMultipleTowers = new ButtonMultipleTowers(false);
	
	public Buttons() {
		createButtons();
	}	
	private void createButtons() {
		buttonList.add(buttonEndTurn);
		buttonList.add(buttonSilence);
		buttonList.add(buttonNewTower);
		buttonList.add(buttonDestroyTower);
		buttonList.add(buttonMultipleTowers);
	}
	
	public static ArrayList<Button> getButtonList() {
		return buttonList;
	}
	
	public static ButtonEndTurn getButtonEndTurn() {
		return buttonEndTurn;
	}
	
	public static ButtonSilence getButtonSilence() {
		return buttonSilence;
	}
	
	public static ButtonNewTower getButtonNewTower() {
		return buttonNewTower;
	}
	
	public static ButtonDestroyTower getButtonDestroyTower() {
		return buttonDestroyTower;
	}
	
	public static ButtonMultipleTowers getButtonMultipleTowers() {
		return buttonMultipleTowers;
	}
}
