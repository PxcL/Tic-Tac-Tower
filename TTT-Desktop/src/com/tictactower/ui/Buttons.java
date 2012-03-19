package com.tictactower.ui;

import java.util.ArrayList;

public class Buttons {

	private final static ArrayList<Button> buttonList = new ArrayList<Button>();
	private final static ButtonEndTurn buttonEndTurn = new ButtonEndTurn(false);
	private final static ButtonSilence buttonSilence = new ButtonSilence(false);
	
	public Buttons() {
		createButtons();
	}	
	private void createButtons() {
		buttonList.add(buttonEndTurn);
		buttonList.add(buttonSilence);
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
}
