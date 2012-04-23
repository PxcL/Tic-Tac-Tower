package com.tictactower.ui.buttons;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;

public class ButtonEndTurn extends Button {
	
	private final static int WIDTH = Gdx.graphics.getWidth() - Gameboard.X_OFFSET * 2;
	private final static int HEIGHT = 40;
	private final static int POSITION_X = Gdx.graphics.getWidth() / 2 - WIDTH / 2;
	private final static int POSITION_Y = Gameboard.Y_OFFSET - HEIGHT - 5;
	
	public ButtonEndTurn(boolean active) {
		super(WIDTH, HEIGHT, POSITION_X, POSITION_Y);
		this.active = active;
	}
	

	@Override
	public void execute() {
		active = false;
		Game.getInstance().changeActivePlayer();	
	}
}
