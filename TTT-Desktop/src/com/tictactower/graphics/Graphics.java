package com.tictactower.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
import com.tictactower.Game;
import com.tictactower.gameboard.Mark;
import com.tictactower.gameboard.Square;
import com.tictactower.ui.*;
public class Graphics {
	
	public void draw(SpriteBatch spriteBatch) {
		drawGameboard(spriteBatch);
		drawButtons(spriteBatch);
		drawText(spriteBatch);
	}

	private void drawGameboard(SpriteBatch spriteBatch) {
		Square[][] gameboard = Game.getInstance().getGameboard().getGameboard();
		
		for (int i = 0; i < gameboard.length; i++) {
			for (int j = 0; j < gameboard[i].length; j++) {
				Texture markTexture = null;
				if (gameboard[i][j].getMark() == Mark.EMPTY)
					markTexture = Textures.MARK_EMPTY;
				else if (gameboard[i][j].getMark() == Mark.P1_ACTIVE)
					markTexture = Textures.MARK_P1_ACTIVE;
				else if (gameboard[i][j].getMark() == Mark.P2_ACTIVE)
					markTexture = Textures.MARK_P2_ACTIVE;
				else if (gameboard[i][j].getMark() == Mark.P1_BUILT)
					markTexture = Textures.MARK_P1_BUILT;
				else if (gameboard[i][j].getMark() == Mark.P2_BUILT)
					markTexture = Textures.MARK_P2_BUILT;
				spriteBatch.draw(markTexture, 
								gameboard[i][j].getPosition().x, 
								gameboard[i][j].getPosition().y, 
								Square.EDGE_LENGTH, 
								Square.EDGE_LENGTH);
			}
		}
	}
	
	private void drawButtons(SpriteBatch spriteBatch) {
		for (Button button : Buttons.getButtonList()) {
			spriteBatch.draw(findTexture(button),
							button.getPosition().x, 
							button.getPosition().y, 
							button.getWidth(),
							button.getHeight());
		}
	}
	
	private void drawText(SpriteBatch spriteBatch) {
		TextBox.font.draw(spriteBatch, TextBox.getPlayer1String(), TextBox.position.x, TextBox.position.y);
	}
	
	private Texture findTexture(Button button) {
		if (button instanceof ButtonEndTurn) return Textures.BUTTON_END_TURN;
		else if (button instanceof ButtonSilence) {
			if (Game.getInstance().getActivePlayer().getSilenceCount() > 0) return Textures.BUTTON_SILENCE_ACTIVE;
			else return Textures.BUTTON_SILENCE_DEACTIVE;
		}
		else if (button instanceof ButtonNewTower) {
			if (Game.getInstance().getActivePlayer().getBuildCount() > 0) return Textures.BUTTON_BUILD_ACTIVE;
			else return Textures.BUTTON_BUILD_DEACTIVE;
		}
		else if (button instanceof ButtonDestroyTower) {
			if (Game.getInstance().getActivePlayer().getShootCount() > 0) return Textures.BUTTON_SHOOT_ACTIVE;
			else return Textures.BUTTON_SHOOT_DEACTIVE;
		}
		else if (button instanceof ButtonMultipleTowers) {
			if (Game.getInstance().getActivePlayer().getSkillCap() > 0) return Textures.BUTTON_SKILL_CAP_ACTIVE;
			else return Textures.BUTTON_SKILL_CAP_DEACTIVE;
		}
		else return null;
	}
	
}
