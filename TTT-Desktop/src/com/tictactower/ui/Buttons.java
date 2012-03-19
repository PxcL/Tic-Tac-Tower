package com.tictactower.ui;

import java.util.ArrayList;

public class Buttons {

	private ArrayList<Button> buttonList = new ArrayList<Button>();
	
	public Buttons() {
		createButtons();
	}	
	private void createButtons() {
		ButtonEndTurn buttonEndTurn = new ButtonEndTurn(80, 30, 100, 100);
		buttonList.add(buttonEndTurn);
	}
	
	public ArrayList<Button> getButtonList() {
		return buttonList;
	}
}
