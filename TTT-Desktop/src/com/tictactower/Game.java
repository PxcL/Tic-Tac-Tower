package com.tictactower;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tictactower.gameboard.Gameboard;
import com.tictactower.graphics.Graphics;
import com.tictactower.input.Input;
import com.tictactower.player.Player;
import com.tictactower.player.Player1;
import com.tictactower.player.Player2;
import com.tictactower.skills.Skill;
import com.tictactower.skills.SkillDestroyTower;
import com.tictactower.skills.SkillNewTower;
import com.tictactower.skills.SkillSilent;
import com.tictactower.ui.Buttons;

public class Game implements ApplicationListener {
	
	// Singleton holder
	private static class SingletonHolder { 
        public static final Game instance = new Game();
	}

	// Method to get the singleton
	public static Game getInstance() {
        return SingletonHolder.instance;
	}
	
	private Gameboard gameboard;
	private Player1 player1;
	private Player2 player2;
	private Graphics graphics;
	private Buttons buttons;
	
	private Player activePlayer;
	
	SpriteBatch spriteBatch;

	@Override
	public void create() {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		
		// Input trenger ingen peker siden kommunikasjonen bare går ut fra Input.
		new Input();
		
		gameboard = new Gameboard();
		player1 = new Player1();
		player2 = new Player2();
		graphics = new Graphics();
		buttons = new Buttons();
		
		activePlayer = player1;
		activePlayer.setNotUsedMark(true);
		
		spriteBatch = new SpriteBatch();
		
		
	}

	@Override
	public void dispose() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		graphics.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
	}

	@Override
	public void resume() {
	}
	
	public Gameboard getGameboard() {
		return gameboard;
	}
	
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
	public Buttons getButtons() {
		return buttons;
	}
	
	public Player getActivePlayer() {
		return activePlayer;
	}
	
	public void changeActivePlayer() {
		activePlayer.setNotUsedMark(false);
		activePlayer.addSilenceCount();
		activePlayer.addShootCount();
		activePlayer.ResetSkillUsage();
		if (activePlayer instanceof Player1)
			activePlayer = player2;
		else
			activePlayer = player1;
		activePlayer.setNotUsedMark(true);
		Gdx.app.log("EMP", Integer.toString(Game.getInstance().getActivePlayer().getSilenceCount()));
	}
	
	public boolean CanShoot(){
		return (activePlayer.GetShootUsage()<activePlayer.getSkillCap() && !activePlayer.IsSilenced() && activePlayer.getShootCount()>0);
	}
	
	public boolean CanBuild(){
		return (activePlayer.GetBuildUsage()<activePlayer.getSkillCap() && !activePlayer.IsSilenced() && activePlayer.getBuildCount()>0);
	}
	
	public boolean CanSilence(){
		return (activePlayer.GetSilenceUsage()<activePlayer.getSkillCap() && !activePlayer.IsSilenced() && activePlayer.getSilenceCount()>0);
	}
	
	public void IncSkillUse(Skill skill){
		if(skill instanceof SkillDestroyTower){
			activePlayer.IncShootUsage();
		}
		if(skill instanceof SkillNewTower){
			activePlayer.IncBuildUsage();
		}
		if(skill instanceof SkillSilent){
			activePlayer.IncSilenceUsage();
		}
	}
	
	public void useSkill(Skill skill){
		if(skill instanceof SkillDestroyTower){
			if(CanShoot()){
				skill.execute();
				IncSkillUse(skill);
				activePlayer.subShootCount();
			}
		}
		if(skill instanceof SkillNewTower){
			if(CanBuild()){
				skill.execute();
				IncSkillUse(skill);
				activePlayer.subBuildCount();
			}
		}
		if(skill instanceof SkillSilent){
			if(CanSilence()){
				skill.execute();
				IncSkillUse(skill);
				activePlayer.subSilenceCount();
			}
		}
	}
	
}
