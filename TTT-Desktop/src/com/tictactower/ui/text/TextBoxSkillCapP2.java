package com.tictactower.ui.text;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;

public class TextBoxSkillCapP2 extends TextBox {
	
	private final static int POSITION_X = 235;
	private final static int POSITION_Y = Gdx.graphics.getHeight() - 30;
	private final static String text = "Skill cap: " + Integer.toString(Game.getInstance().getPlayer2().getSkillCap());
	private final static float[] colorActive = {0, 0, 1, 1};
	private final static float[] colorDeactive = {0, 0, 0.8f, 1};
	
	public TextBoxSkillCapP2() {
		super(POSITION_X, POSITION_Y, text, colorActive, colorDeactive);
	}
	

}
