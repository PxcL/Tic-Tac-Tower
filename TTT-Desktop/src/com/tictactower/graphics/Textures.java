package com.tictactower.graphics;

import com.badlogic.gdx.graphics.Texture;

/*
 * All the textures are saved in this class. This is a part of using the MVC-pattern. 
 * This makes the View divided from the Model, and changing textures is easily done here.
 */

public class Textures {
	public final static Texture MARK_EMPTY = new Texture("singleGrid.bmp");
	public final static Texture MARK_P1_ACTIVE = new Texture("P1_ACTIVE.png");
	public final static Texture MARK_P2_ACTIVE = new Texture("P2_ACTIVE.png");
	
	public final static Texture BUTTON_END_TURN = new Texture("txtBox_75black.png");
	public final static Texture BUTTON_SILENCE_ACTIVE = new Texture("emp_active.png");
	public final static Texture BUTTON_SILENCE_DEACTIVE = new Texture("emp_deactive.png");
	public final static Texture BUTTON_DESTROY_TOWER_ACTIVE = new Texture("destow_active.png");
	public final static Texture BUTTON_DESTROY_TOWER_DEACTIVE = new Texture("destow_deactive.png");
	public final static Texture BUTTON_NEW_TOWER_ACTIVE = new Texture("newtow_active.png");
	public final static Texture BUTTON_NEW_TOWER_DEACTIVE = new Texture("newtow_deactive.png");
	public final static Texture BUTTON_MULTIPLE_TOWERS_ACTIVE = new Texture("multow_active.png");
	public final static Texture BUTTON_MULTIPLE_TOWERS_DEACTIVE = new Texture("multow_deactive.png");
}
