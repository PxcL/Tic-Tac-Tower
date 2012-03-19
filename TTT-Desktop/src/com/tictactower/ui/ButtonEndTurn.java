package com.tictactower.ui;

import com.tictactower.Game;

public class ButtonEndTurn extends Button {

	public ButtonEndTurn(int width, int height, int positionX, int positionY) {
		super(width, height, positionX, positionY);
	}

	@Override
	public void execute() {
		Game.getInstance().changeActivePlayer();	
	}
}
