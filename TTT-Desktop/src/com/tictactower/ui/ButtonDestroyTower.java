package com.tictactower.ui;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;

public class ButtonDestroyTower extends Button {
	
	private final static int WIDTH = (Gdx.graphics.getWidth() - Gameboard.X_OFFSET * 2 - 15) / 4;
	private final static int HEIGHT = 50;
	private final static int POSITION_X = Gameboard.X_OFFSET + WIDTH * 2 + 10;
	private final static int POSITION_Y = 75;
	
	public ButtonDestroyTower(boolean active) {
		super(WIDTH, HEIGHT, POSITION_X, POSITION_Y);
		this.active = active;
	}

	@Override
	public void execute() {
		Game.getInstance().getActivePlayer().subShootCount();
		Gdx.app.log("Skill", "DESTROY!!!");
	}
}
