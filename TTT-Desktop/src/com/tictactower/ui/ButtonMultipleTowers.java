package com.tictactower.ui;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;

public class ButtonMultipleTowers extends Button {
	
	private final static int WIDTH = (Gdx.graphics.getWidth() - Gameboard.X_OFFSET * 2 - 15) / 4;
	private final static int HEIGHT = 50;
	private final static int POSITION_X = Gameboard.X_OFFSET + WIDTH * 3 + 15;
	private final static int POSITION_Y = 75;
	
	public ButtonMultipleTowers(boolean active) {
		super(WIDTH, HEIGHT, POSITION_X, POSITION_Y);
		this.active = active;
	}

	@Override
	public void execute() {
		Game.getInstance().getActivePlayer().subSkillCap();
		Gdx.app.log("Skill", "build... Build... BUILD!!!");
	}
}