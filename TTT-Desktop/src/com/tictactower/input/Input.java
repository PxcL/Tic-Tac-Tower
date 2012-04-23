package com.tictactower.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;
import com.tictactower.gameboard.Mark;
import com.tictactower.gameboard.Square;
import com.tictactower.gamelogic.Towers;
import com.tictactower.player.*;
import com.tictactower.ui.buttons.Button;
import com.tictactower.ui.buttons.Buttons;

public class Input implements InputProcessor {
	
	public Input() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// Gj¿r om y slik at posisjon 0 er i bunn av skjermen, likt slik det er nŒr man tegner teksturer/sprite.
		y = Gdx.graphics.getHeight() - y;
		
		if (wasGameboardClicked(x, y) && Game.getInstance().getActivePlayer().getNotUsedMark()) {
			updateGameboard(x, y);
		}
		else {		
			checkForButtonClicks(x, y);	
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean wasGameboardClicked(int x, int y) {
		if (x > Gameboard.X_OFFSET && x < Gameboard.X_OFFSET + Gameboard.GAMEBOARD_EDGE_LENGTH
				&& y > Gameboard.Y_OFFSET && y < Gameboard.Y_OFFSET + Gameboard.GAMEBOARD_EDGE_LENGTH)
			return true;
		else
			return false;
	}

	private void updateGameboard(int x, int y) {
		/*
		 *  Her finner man de relative posisjonene til trykket i forhold til spillbrettet.
		 *  Merk at det mŒ trikses litt med y-posisjonen siden origo paa input er overst til venstre, 
		 *  mens origo naar man tegner er nederst til venstre.
		 */
		x -= Gameboard.X_OFFSET;
		y -= Gameboard.Y_OFFSET;
		// Saa deler man paa bredden/hoyden til ruten for aa faa hvilken rad/kolonne trykket kom i.
		x /= Square.EDGE_LENGTH;
		y /= Square.EDGE_LENGTH;
		Player activePlayer = Game.getInstance().getActivePlayer();
		switch(Game.getInstance().getSkill().getFlag()){
		
			case NO_SKILL:
				if (Game.getInstance().getGameboard().getMark(x, y) == Mark.EMPTY) {
					if(!activePlayer.isSilenced()){
						activePlayer.setMark(x, y);
						activePlayer.setNotUsedMark(false);
						Buttons.getButtonEndTurn().setActive(true);
						Towers.findTowers(x, y, activePlayer);
					}else{ //if the player has been silenced
						activePlayer.setMark(x, y);
						if(Towers.findTowers(x, y, activePlayer)){ 
							//this means that a tower has been found, and the move is illegal
							Game.getInstance().getGameboard().clearMark(x, y);
							//print something saying the move is illegal...
						}else{
							//the move is accepted
							activePlayer.setNotUsedMark(false);
							Buttons.getButtonEndTurn().setActive(true);					
						}
					}
				}
				break;
			
			case SHOOT:
				if(activePlayer instanceof Player1){
					if(Game.getInstance().getGameboard().getMark(x, y) == Mark.P2_ACTIVE){
						Game.getInstance().getGameboard().setMark(x, y, Mark.DESTROYED);
						activePlayer.subShootCount();
						activePlayer.IncShootUsage();
					}
				}else{
					if(Game.getInstance().getGameboard().getMark(x, y) == Mark.P1_ACTIVE){
						Game.getInstance().getGameboard().setMark(x, y, Mark.DESTROYED);
						activePlayer.subShootCount();
						activePlayer.IncShootUsage();
					}
				}
				break;
				
			case BUILD:
				if (Game.getInstance().getGameboard().getMark(x, y) == Mark.EMPTY) {
					if(!activePlayer.isSilenced()){
						activePlayer.setMark(x, y);
						activePlayer.subBuildCount();
						activePlayer.IncBuildUsage();
						Towers.findTowers(x, y, activePlayer);
					}else{ //if the player has been silenced
						activePlayer.setMark(x, y);
						if(Towers.findTowers(x, y, activePlayer)){ 
							//this means that a tower has been found, and the move is illegal
							Game.getInstance().getGameboard().clearMark(x, y);
							//print something saying the move is illegal...
						}else{
							//the move is accepted
							activePlayer.subBuildCount();
							activePlayer.IncBuildUsage();

						}
					}
				}
				break;
				
			case SILENCE:
				if(activePlayer instanceof Player1){
					Game.getInstance().getPlayer2().setSilenced(true);
				}else{
					Game.getInstance().getPlayer1().setSilenced(true);
				}
				activePlayer.subSilenceCount();
				activePlayer.IncSilenceUsage();
				break;
		}
		Game.getInstance().getSkill().cancelSkill();
	}
	
	private void checkForButtonClicks(int x, int y) {
		/*
		 * Her itererer man gjennom liste med buttons. 
		 * Hvis klikket/touchen traff en button sŒ kj¿rer man dens execute-metode.
		 */
		for (Button button : Buttons.getButtonList()) {
			if (x > button.getPosition().x && x < button.getPosition().x + button.getWidth()
					&& y > button.getPosition().y && y < button.getPosition().y + button.getHeight()) {
				if (button.isActive()) button.execute();
			}
		}
	}
	
}
