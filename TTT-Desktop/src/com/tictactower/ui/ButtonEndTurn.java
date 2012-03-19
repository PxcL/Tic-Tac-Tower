package com.tictactower.ui;

import com.badlogic.gdx.Gdx;

public class ButtonEndTurn extends Button {

	public ButtonEndTurn(int width, int height, int positionX, int positionY) {
		super(width, height, positionX, positionY);
	}

	@Override
	public void execute() {
		Gdx.app.log("Button clicked", "End Turn");		
	}
}
