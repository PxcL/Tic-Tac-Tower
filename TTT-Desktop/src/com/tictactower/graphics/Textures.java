package com.tictactower.graphics;

import com.badlogic.gdx.graphics.Texture;

/*
 * All the textures are saved in this class. This is a part of using the MVC-pattern. 
 * This makes the View divided from the Model, and changing textures is easily done here.
 */

public class Textures {
	public final static Texture MARK_EMPTY = new Texture("singleGrid.bmp");
	public final static Texture MARK_P1_ACTIVE = new Texture("TTT_Route.jpg");
	public final static Texture MARK_P2_ACTIVE = new Texture("TTT_Route2.jpg");
	
	public final static Texture BUTTON_END_TURN = new Texture("txtBox_75black.png");
}
