package com.tictactower.gamelogic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;
import com.tictactower.gameboard.Mark;
import com.tictactower.player.Player;
import com.tictactower.skills.SkillType;


public class Towers {

	public static boolean findTowers(int x, int y, Player activePlayer) {
		// The input variables is the position of the new mark.
		// Find towers by iterating from that square on the board
		// Returning a bool; true if one or more towers has been found
		boolean[][] field = new boolean[Gameboard.NUMBER_OF_COLUMNS][Gameboard.NUMBER_OF_ROWS];
		for(int i=0; i<Gameboard.NUMBER_OF_COLUMNS; i++){ // initializing the array...
			for(int j=0; j<Gameboard.NUMBER_OF_ROWS; j++){
				field[i][j] = false;
			}
		}
		
		//1. find cluster around x,y
		field = FindClusterRecurse(new FieldIndex(x,y), field, activePlayer.getActiveMark());
		
		//printing the cluster for debug: 
//		Gdx.app.log("Tower", "Found cluster:");
//		for(int i=0; i<Gameboard.NUMBER_OF_ROWS; i++){ 
//			String s = "";
//			for(int j=0; j<Gameboard.NUMBER_OF_COLUMNS; j++){
//				if(field[j][Gameboard.NUMBER_OF_ROWS-i-1])
//					s += "*";
//				else
//					s += " ";
//			}
//			Gdx.app.log("", s);
//		}

		//2. find towers in this cluster
		ArrayList<SkillType> skillList = FindTowersInCluster(field, activePlayer); 
		
		//3. adding skills to the player
		
		if(!activePlayer.isSilenced()){
			String debugString = "";
			for (SkillType s : skillList){
				if( s == SkillType.SHOOT){
					activePlayer.addShootCount();
					debugString += "shoot, ";
				}else if(s == SkillType.BUILD){
					activePlayer.addBuildCount();
					debugString += "build, ";
				}else if(s == SkillType.SILENCE){
					activePlayer.addSilenceCount();
					debugString += "silence, ";
				}else if(s == SkillType.SKILLCAP){
					activePlayer.addSkillCap();
					debugString += "skillCap, ";
				}
			}
			Gdx.app.log("Tower", "Found skills: "+debugString);
		}
		return !skillList.isEmpty();
	}
	
	
	private static boolean[][] FindClusterRecurse(FieldIndex ind, boolean[][] taken, Mark type){
		//recursive function that finds a cluster
		Gameboard board = Game.getInstance().getGameboard();
		
		taken[(int)ind.x()][(int)ind.y()] = true;
		
		for (FieldIndex i : ind.GetNeigbours()){
			if( board.getMark(i.x(), i.y()) == type && taken[i.x()][i.y()] == false ){
				taken = FindClusterRecurse(i, taken, type);
			}
		}
		return taken;
	}
	
	private static ArrayList<SkillType> FindTowersInCluster(boolean[][] cluster, Player player){
		// checks if there is a piece in front of the given piece, 
		// if there is, there might be a tower; calling specific find-tower functions
		// does this for all 8 directions.

		ArrayList<Towers> towerList = new ArrayList<Towers>();
		for (int nx=0; nx<Gameboard.NUMBER_OF_COLUMNS; nx++){ //checking for all elements in the cluster...
			for (int ny=0; ny<Gameboard.NUMBER_OF_ROWS; ny++){
				if( cluster[nx][ny]){

					for (int i=0; i<8; i++){ //...in all directions
						FieldIndex f = new FieldIndex(nx,ny);
						FieldIndex start = f.Up(i);
						if(start.Valid() && cluster[start.x()][start.y()]){
							towerList.addAll(FindShootTower(i, start, cluster));
							towerList.addAll(FindBuildTower(i,start, cluster));
							if(i < 4){
								towerList.addAll(FindSilenceTower(i,start, cluster));
								towerList.addAll(FindFiveTower(i,start,cluster));
							}if(i < 2){
								towerList.addAll(FindMultipleSkillsTower(i,start, cluster));
							}
						}
					}
				}
			}
		}
		ArrayList<SkillType> skillList = new ArrayList<SkillType>();
		//marking the positions of the towers as built:
		if(!player.isSilenced()){
			//Gdx.app.log("Tower", "found "+Integer.toString(towerList.size())+" towers:");
			for( Towers t : towerList){
				//Gdx.app.log("", t.toString());
				for (FieldIndex f : t.tower){
					if(!f.Valid()){
						Gdx.app.error("Tower", "Invalid Tower: "+t.toString());
					}
					Game.getInstance().getGameboard().setMarkToBuilt(f.x(), f.y(), player);
				}
				skillList.add(t.towerType);
			}
		}
		return skillList;
	}
	
	private static ArrayList<Towers> FindShootTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a shoot tower in the given direction
		//startpoint should be the second index
		//returns number of found towers
		
		FieldIndex right = startPoint.Right(direction);
		FieldIndex left = startPoint.Left(direction);
		boolean leftPart = left.Valid() && cluster[left.x()][left.y()];
		boolean rightPart = right.Valid() && cluster[right.x()][right.y()];
		
		ArrayList<Towers> towerList = new ArrayList<Towers>();
		
		if (leftPart && rightPart){
			Towers tow = new Towers(startPoint,direction);
			tow.add(left);
			tow.add(right);
			tow.towerType = SkillType.SHOOT;
			towerList.add(tow);
		}
		return towerList;
	}
	private static ArrayList<Towers> FindBuildTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a build tower in the given direction
		//startPoint should be the second index
		//returns number of found towers

		ArrayList<Towers> towerList = new ArrayList<Towers>();
		FieldIndex up = startPoint.Up(direction);
		if(up.Valid() && cluster[up.x()][up.y()]){
			FieldIndex right = up.Right(direction);
			FieldIndex left = up.Left(direction);
			boolean leftPart = left.Valid() && cluster[left.x()][left.y()];
			boolean rightPart = right.Valid() && cluster[right.x()][right.y()];
			if (leftPart){
				Towers leftTower = new Towers(startPoint, direction); //initing the tower
				leftTower.add(up);
				leftTower.add(left);
				leftTower.towerType = SkillType.BUILD;
				towerList.add(leftTower);
			}
			if( rightPart ){
				Towers rightTower = new Towers(startPoint, direction); //initing the tower
				rightTower.add(up);
				rightTower.add(right);
				rightTower.towerType = SkillType.BUILD;
				towerList.add(rightTower);
			}
			
		}
		return towerList;
	}
	private static ArrayList<Towers> FindSilenceTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a silence tower in the given direction
		//startPoint should be the second index
		//returns number of found towers
		
		ArrayList<Towers> towerList = new ArrayList<Towers>();
		FieldIndex right = startPoint.Right(direction);
		FieldIndex left = startPoint.Left(direction);
		boolean leftPart = left.Valid() && cluster[left.x()][left.y()];
		boolean rightPart = right.Valid() && cluster[right.x()][right.y()];
		
		if(leftPart){
			FieldIndex up = left.Up(direction);
			if(up.Valid() && cluster[up.x()][up.y()]){
				Towers leftTower = new Towers(startPoint, direction); //initing the tower
				leftTower.add(left);
				leftTower.add(up);
				leftTower.towerType = SkillType.SILENCE;
				towerList.add(leftTower);
			}
		}
		if(rightPart){
			FieldIndex up = right.Up(direction);
			if(up.Valid() && cluster[up.x()][up.y()]){
				Towers rightTower = new Towers(startPoint, direction); //initing the tower
				rightTower.add(right);
				rightTower.add(up);
				rightTower.towerType = SkillType.SILENCE;
				towerList.add(rightTower);
			}
		}
		return towerList;

	}
	private static ArrayList<Towers> FindMultipleSkillsTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a multiple-skills tower in the given direction
		//startPoint should be the second index
		//returns number of found towers
		
		ArrayList<Towers> towerList = new ArrayList<Towers>();
		FieldIndex right = startPoint.Right(direction);
		if( right.Valid() && cluster[right.x()][right.y()] ){
			FieldIndex down = right.Down(direction);
			if( down.Valid() && cluster[down.x()][down.y()] ){
				Towers tower = new Towers(startPoint, direction); //initing the tower
				tower.add(right);
				tower.add(down);
				tower.towerType = SkillType.SKILLCAP;
				towerList.add(tower);
			}
		}
		return towerList;
	}
	

	private static ArrayList<Towers> FindFiveTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the three last pieces of a five-in-a-row tower in the given direction
		//startPoint should be the second index
		//returns number of found towers
		ArrayList<Towers> towerList = new ArrayList<Towers>();
		Towers tower = new Towers(startPoint, direction);
		FieldIndex up = startPoint.Up(direction);
		if( up.Valid() && cluster[up.x()][up.y()] ){
			up = up.Up(direction);
			tower.add(up);
			if( up.Valid() && cluster[up.x()][up.y()] ){
				up = up.Up(direction);
				tower.add(up);
				if( up.Valid() && cluster[up.x()][up.y()] ){
					towerList.add(tower);
					return towerList;
				}
			}
		}
		return towerList; // returning the empty tower list
	}

	// No-static properties (private only):

	private ArrayList<FieldIndex> tower;
	private SkillType towerType;
	
	private Towers(){
		tower = new ArrayList<FieldIndex>(4);
	}
	private Towers(FieldIndex f, int direction){
		tower = new ArrayList<FieldIndex>(4);
		tower.add(f.Down(direction));
		tower.add(f);
	}

	private void add(FieldIndex f){
		tower.add(f);
	}

	public String toString(){
		String s = towerType();
		for (int i = 0; i<tower.size();i++){
			s += tower.get(i).toString();
			if( i < i-1)
				s += ", ";
		}
		return s;
	}
	
	private String towerType(){
		String s;
		switch(towerType){
		case SHOOT:
			s = "shoot: ";
			break;
		case BUILD:
			s = "build: ";
			break;
		case SILENCE:
			s = "silence: ";
			break;
		case SKILLCAP:
			s = "skillCap: ";
			break;
		default:
			s = "no tower";
			break;
		}
		return s;
	}
	
}