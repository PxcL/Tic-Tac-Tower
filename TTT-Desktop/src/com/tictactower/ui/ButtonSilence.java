package com.tictactower.ui;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;

public class ButtonSilence extends Button {
	
	private final static int WIDTH = 50;
	private final static int HEIGHT = 50;
	private final static int POSITION_X = 50;
	private final static int POSITION_Y = 50;
	
	public ButtonSilence(boolean active) {
		super(WIDTH, HEIGHT, POSITION_X, POSITION_Y);
		this.active = active;
	}

	@Override
	public void execute() {
		Game.getInstance().getActivePlayer().subEmpCount();
		Gdx.app.log("Skill", "SIIIIIILEEEEEEEEENCE");
	}
}
