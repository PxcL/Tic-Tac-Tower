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
import com.tictactower.skills.SkillType;
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
	private Skill skill;
	
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
	
	public Skill getSkill(){
		return skill;
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
	
	public boolean canUseSkill(SkillType st){
		if(st == SkillType.SHOOT){
			return (activePlayer.GetShootUsage()<activePlayer.getSkillCap() && !activePlayer.IsSilenced() && activePlayer.getShootCount()>0);
		}
		if(st == SkillType.BUILD){
			return (activePlayer.GetBuildUsage()<activePlayer.getSkillCap() && !activePlayer.IsSilenced() && activePlayer.getBuildCount()>0);
		}
		if(st == SkillType.SILENCE){
			return (activePlayer.GetSilenceUsage()<activePlayer.getSkillCap() && !activePlayer.IsSilenced() && activePlayer.getSilenceCount()>0);
		}
		return false;
	}
	
	public boolean chooseSkill(SkillType st){
		if(!canUseSkill(st)){
			skill.cancelSkill();
			return false;
		}
		skill.useSkill(st);
		return true;
	}
	
	public void incSkillUse(){
		if(skill.getFlag() == SkillType.SHOOT){
			activePlayer.IncShootUsage();
		}
		if(skill.getFlag() == SkillType.BUILD){
			activePlayer.IncBuildUsage();
		}
		if(skill.getFlag() == SkillType.SILENCE){
			activePlayer.IncSilenceUsage();
		}
	}
	
}
