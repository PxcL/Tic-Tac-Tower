package com.tictactower.ui.buttons;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;
import com.tictactower.player.Player;
import com.tictactower.skills.SkillType;

public class ButtonDestroyTower extends Button {
	
	private final static int WIDTH = (Gdx.graphics.getWidth() - Gameboard.X_OFFSET * 2 - 15) / 4;
	private final static int HEIGHT = 50;
	private final static int POSITION_X = Gameboard.X_OFFSET + WIDTH * 2 + 10;
	private final static int POSITION_Y = (int)Buttons.getButtonEndTurn().getPosition().y - (HEIGHT + 5);
	
	public ButtonDestroyTower(boolean active) {
		super(WIDTH, HEIGHT, POSITION_X, POSITION_Y);
		this.active = active;
	}

	@Override
	public void execute() {
		Player activePlayer = Game.getInstance().getActivePlayer();
		if(activePlayer.getShootCount()>0 && activePlayer.GetShootUsage()>=activePlayer.getSkillCap()){
			Game.getInstance().getSkill().useSkill(SkillType.SHOOT);
			Gdx.app.log("Skill", "DESTROY!!!");
		}else{Gdx.app.log("Skill", "Cannot destroy!");
		}
	}
}
