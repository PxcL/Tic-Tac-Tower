package com.tictactower.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.tictactower.Game;
import com.tictactower.player.Player;

public class TextBox{
	
	public static final BitmapFont font = new BitmapFont();
	
	public static final int WIDTH = 100;
	public static final int HEIGHT = 20;
	public static final Vector2 position = new Vector2(20, Gdx.graphics.getHeight() - 30);
	private static final Player player1 = Game.getInstance().getPlayer1();
	private static final Player player2 = Game.getInstance().getPlayer2();
	
	public static String getPlayer1String(){
		String str1 = "Player 1 skills\n";
		str1 += "Shoot:\t"+Integer.toString(player1.getShootCount())+"\n";
		str1 += "Build:\t"+Integer.toString(player1.getBuildCount())+"\n";
		str1 += "Silence:\t"+Integer.toString(player1.getSilenceCount())+"\n";
		str1 += "SkillCap:\t"+Integer.toString(player1.getSkillCap())+"\n";
		return str1;
	}
	
	public static String getPlayer2String(){	
		String str2 = "Player 2 skills\n";
		str2 += "Shoot:\t"+Integer.toString(player2.getShootCount())+"\n";
		str2 += "Build:\t"+Integer.toString(player2.getBuildCount())+"\n";
		str2 += "Silence:\t"+Integer.toString(player2.getSilenceCount())+"\n";
		str2 += "SkillCap:\t"+Integer.toString(player2.getSkillCap())+"\n";
		return str2;
	}
}