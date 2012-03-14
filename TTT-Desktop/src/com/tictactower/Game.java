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
	private Player player1;
	private Player player2;
	private Graphics graphics;
	private Input input;
	
	SpriteBatch spriteBatch;

	@Override
	public void create() {
		// Create the gameboard
		Gdx.gl.glClearColor(0, 1, 0, 1);
		
		gameboard = new Gameboard();
		player1 = new Player1();
		player2 = new Player2();
		graphics = new Graphics();
		input = new Input();
		
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
}
