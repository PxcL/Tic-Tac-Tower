package com.tictactower.gamelogic;

import java.util.ArrayList;

import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;
import com.tictactower.player.Player;
import com.tictactower.skills.Skill;
import com.tictactower.skills.SkillBuild;
import com.tictactower.skills.SkillCap;
import com.tictactower.skills.SkillShoot;
import com.tictactower.skills.SkillSilence;


/*
 * Har laget alle tower-funksjonene, men de kalles ikke noe sted.
 * De farger heller ikke brikkene som finnes til BUILT
 * Jeg har tenkt på mange måter å gjøre dette på, f.eks.:
 * Lage Towers-klassen til en klasse som holder posisjonen og tårn-type (akkurat som Tower i unity)
 * Blir dette for rotete?
 */

public class Towers {

	public static boolean findTowers(int x, int y, Player firstPlayer) {
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
		field = FindClusterRecurse(new FieldIndex(x,y), field);
		
		//2. find towers in this cluster
		ArrayList<Skill> skillList = FindTowersInCluster(field, firstPlayer); 
				
		//3. adding skills to the player
		if(!firstPlayer.IsSilenced()){
			for (Skill s : skillList)
				if( s instanceof SkillShoot){
					firstPlayer.addShootCount();
				}else if(s instanceof SkillBuild){
					firstPlayer.addBuildCount();
				}else if(s instanceof SkillSilence){
					firstPlayer.addSilenceCount();
				}else if(s instanceof SkillCap){
					firstPlayer.addSkillCap();
				}
		}
		
		return !skillList.isEmpty();
	}
	
	
	private static boolean[][] FindClusterRecurse(FieldIndex ind, boolean[][] taken){
		//recursive function that finds a cluster
		Gameboard board = Game.getInstance().getGameboard();
		
		taken[(int)ind.x()][(int)ind.y()] = true;
		
		for (FieldIndex i : ind.GetNeigbours()){
			if( board.getMark(i.x(), ind.y()) == board.getMark(i.x(), ind.y()) && taken[i.x()][i.y()] == false ){
				taken = FindClusterRecurse(i, taken);
			}
		}
		return taken;
	}
	
	private static ArrayList<Skill> FindTowersInCluster(boolean[][] cluster, Player player){
		// checks if there is a piece in front of the given piece, 
		// if there is, there might be a tower; calling specific find-tower functions
		// does this for all 8 directions.

		ArrayList<Towers> towerList = new ArrayList<Towers>();
		for (int nx=0; nx<Gameboard.NUMBER_OF_COLUMNS; nx++){ //checking for all elements in the cluster...
			for (int ny=0; ny<Gameboard.NUMBER_OF_ROWS; ny++){
				if( cluster[nx][ny]){
					
					for (int i=0; i<8; i++){ //...in all directions
						FieldIndex f = new FieldIndex(nx,ny);
						if(f.Up(i).Valid()){
							towerList.addAll(FindShootTower(i, f, cluster));//number of towers
							towerList.addAll(FindBuildTower(i,f, cluster));
							towerList.addAll(FindSilenceTower(i,f, cluster));
							towerList.addAll(FindMultipleSkillsTower(i,f, cluster));
						}
					}
				}
					
			}
		}
		ArrayList<Skill> skillList = new ArrayList<Skill>();
		//marking the positions of the towers as built:
		if(!player.IsSilenced()){
			for( Towers t : towerList){
				for (FieldIndex f : t.tower){
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
			tow.towerType = new SkillShoot();
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
				leftTower.towerType = new SkillBuild();
				towerList.add(leftTower);
			}
			if( rightPart ){
				Towers rightTower = new Towers(startPoint, direction); //initing the tower
				rightTower.add(up);
				rightTower.add(right);
				rightTower.towerType = new SkillBuild();
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
				leftTower.towerType = new SkillSilence();
				towerList.add(leftTower);
			}
		}
		if(rightPart){
			FieldIndex up = right.Up(direction);
			if(up.Valid() && cluster[up.x()][up.y()]){
				Towers rightTower = new Towers(startPoint, direction); //initing the tower
				rightTower.add(left);
				rightTower.add(up);
				rightTower.towerType = new SkillSilence();
				towerList.add(rightTower);
			}
		}
		return towerList;
		// FEIL: den må si fra om tårnets posisjon
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
				tower.towerType = new SkillCap();
				towerList.add(tower);
			}
		}
		return towerList;
	}
	
	// No-static properties (private only):

	private ArrayList<FieldIndex> tower;
	private Skill towerType;
	
	private Towers(){
		tower = new ArrayList<FieldIndex>(4);
	}
	private Towers(FieldIndex f, int direction){
		tower.add(f.Down(direction));
		tower.add(f);
	}

	private void add(FieldIndex f){
		tower.add(f);
	}
	
}