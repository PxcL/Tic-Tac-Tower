package com.tictactower.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tictactower.Game;
import com.tictactower.gameboard.Square;

public class Graphics {

	public void draw(SpriteBatch spriteBatch) {
		drawGameboard(spriteBatch);
	}

	private void drawGameboard(SpriteBatch spriteBatch) {
		Square[][] gameboard = Game.getInstance().getGameboard().getGameboard();
		
		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[i].length; j++) {
				spriteBatch.draw(Textures.EMPTY_SQUARE, 
								gameboard[i][j].getPosition().x, 
								gameboard[i][j].getPosition().y, 
								Square.WIDTH, 
								Square.HEIGHT);
			}
		}
		
	}
	
}
