package com.tictactower.gamelogic;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.tictactower.gameboard.Gameboard;

public class FieldIndex{
	private int x;
	private int y;
		
	public Vector2 GetPosition(){
		return new Vector2(x,y);
	}
	
	public int x(){
		return x;
	}
	
	public int y(){
		return y;
	}
	public String toString(){
		return "("+Integer.toString(x)+","+Integer.toString(y)+")";
	}
	public boolean Valid(){
		return ( x>=0 && x<Gameboard.NUMBER_OF_ROWS ) && ( y>=0 && y<Gameboard.NUMBER_OF_COLUMNS );
	}
	
	FieldIndex(int _x, int _y){
		x = _x;
		y = _y;
	}
	FieldIndex(Vector2 v){
		x = (int)v.x;
		y = (int)v.y;
	}
	
	public FieldIndex Up(){
		return new FieldIndex(x, y+1);
	}
	public FieldIndex UpLeft(){
		return new FieldIndex( x + 1, y+1);
	}
	public FieldIndex Left(){
		return new FieldIndex( x +1, y);
	}
	public FieldIndex DownLeft(){
		return new FieldIndex( x +1, y -1);
	}
	public FieldIndex Down(){
		return new FieldIndex( x, y -1);
	}
	public FieldIndex DownRight(){
		return new FieldIndex( x-1, y-1);
	}
	public FieldIndex Right(){
		return new FieldIndex( x -1, y);
	}
	public FieldIndex UpRight(){
		return new FieldIndex( x -1, y+1);
	}

	public FieldIndex Up(int direction){
		switch(direction){
			case 0: return this.Up();
			case 1: return this.UpLeft();
			case 2: return this.Left();
			case 3: return this.DownLeft();
			case 4: return this.Down();
			case 5: return this.DownRight();
			case 6: return this.Right();
			case 7: return this.UpRight();
			default: return new FieldIndex(-1,-1);
		}
	}
	public FieldIndex Left(int direction){
		switch(direction){
			case 0: return this.Left();
			case 1: return this.DownLeft();
			case 2: return this.Down();
			case 3: return this.DownRight();
			case 4: return this.Right();
			case 5: return this.UpRight();
			case 6: return this.Up();
			case 7: return this.UpLeft();
			default: return new FieldIndex(-1,-1);
		}
	}
	public FieldIndex Down(int direction){
		switch(direction){
			case 0: return this.Down();
			case 1: return this.DownRight();
			case 2: return this.Right();
			case 3: return this.UpRight();
			case 4: return this.Up();
			case 5: return this.UpLeft();
			case 6: return this.Left();
			case 7: return this.DownLeft();
			default: return new FieldIndex(-1,-1);
		}
	}

	public FieldIndex Right(int direction){
		switch(direction){
			case 0: return this.Right();
			case 1: return this.UpRight();
			case 2: return this.Up();
			case 3: return this.UpLeft();
			case 4: return this.Left();
			case 5: return this.DownLeft();
			case 6: return this.Down();
			case 7: return this.DownRight();
			default: return new FieldIndex(-1,-1);
		}
	}
	
	public ArrayList<FieldIndex> GetNeigbours(){
		ArrayList<FieldIndex> set = new ArrayList<FieldIndex>();
		if( Up().Valid())
			set.add(Up());
		if( Right().Valid())
			set.add(Right());
		if( Down().Valid())
			set.add(Down());
		if( Left().Valid())
			set.add(Left());
		if( UpRight().Valid())
			set.add(UpRight());
		if( UpLeft().Valid())
			set.add(UpLeft());
		if( DownRight().Valid())
			set.add(DownRight());
		if( DownLeft().Valid())
			set.add(DownLeft());
		return set;
	}

}