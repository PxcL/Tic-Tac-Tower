package com.tictactower.gamelogic;

import com.tictactower.gameboard.Gameboard;

import com.badlogic.gdx.math.Vector2;

public class FieldIndex{
	public Vector2 position;
	public boolean valid;
		
	public boolean Valid(float x, float y){
		return ( x>=0 && x<Gameboard.NUMBER_OF_ROWS ) && ( y>=0 && y<Gameboard.NUMBER_OF_COLUMNS );
	}
	
	FieldIndex(float x, float y){
		position = new Vector2(x,y);
	}
	FieldIndex(Vector2 v){
		position = v;
		// evt position = new Vector2(v);?
	}
	
	public FieldIndex Up(){
		return new FieldIndex(position.x, position.y+1);
	}
	public FieldIndex UpLeft(){
		return new FieldIndex( position.x + 1, position.y+1);
	}
	public FieldIndex Left(){
		return new FieldIndex( position.x +1, position.y);
	}
	public FieldIndex DownLeft(){
		return new FieldIndex( position.x +1, position.y -1);
	}
	public FieldIndex Down(){
		return new FieldIndex( position.x, position.y -1);
	}
	public FieldIndex DownRight(){
		return new FieldIndex( position.x-1, position.y-1);
	}
	public FieldIndex Right(){
		return new FieldIndex( position.x -1, position.y);
	}
	public FieldIndex UpRight(){
		return new FieldIndex( position.x -1, position.y+1);
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

}