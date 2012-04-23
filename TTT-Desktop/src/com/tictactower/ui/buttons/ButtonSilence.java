package com.tictactower.ui.buttons;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;
import com.tictactower.player.Player;
import com.tictactower.skills.SkillType;

public class ButtonSilence extends Button {
	
	private final static int WIDTH = (Gdx.graphics.getWidth() - Gameboard.X_OFFSET * 2 - 15) / 4;
	private final static int HEIGHT = 50;
	private final static int POSITION_X = Gameboard.X_OFFSET;
	private final static int POSITION_Y = (int)Buttons.getButtonEndTurn().getPosition().y - (HEIGHT + 5);
	
	public ButtonSilence(boolean active) {
		super(WIDTH, HEIGHT, POSITION_X, POSITION_Y);
		this.active = active;
	}

	@Override
	public void execute() {
		Player activePlayer = Game.getInstance().getActivePlayer();
		if(activePlayer.getSilenceCount()>0 && activePlayer.GetSilenceUsage()==0){
			Game.getInstance().getSkill().useSkill(SkillType.SILENCE);
			Gdx.app.log("Skill", "SIIIIIILEEEEEEEEENCE");
		}else{Gdx.app.log("Skill", "Cannot silence!"); //For debug.
		}
	}
}
