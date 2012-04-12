package com.tictactower.gamelogic;

import java.util.ArrayList;

import com.tictactower.Game;
import com.tictactower.gameboard.Gameboard;
import com.tictactower.skills.*;

/*
 * Har laget alle tower-funksjonene, men de kalles ikke noe sted.
 * De farger heller ikke brikkene som finnes til BUILT
 * Jeg har tenkt på mange måter å gjøre dette på, f.eks.:
 * Lage Towers-klassen til en klasse som holder posisjonen og tårn-type (akkurat som Tower i unity)
 * Blir dette for rotete?
 */

public class Towers {

	public static void findTowers(int x, int y) {
		// The input variables are the position of the new mark.
		// Find towers by iterating from that square on the board
		
		boolean[][] field = new boolean[Gameboard.NUMBER_OF_COLUMNS][Gameboard.NUMBER_OF_ROWS];
		for(int i=0; i<Gameboard.NUMBER_OF_COLUMNS; i++){ // initializing the array...
			for(int j=0; j<Gameboard.NUMBER_OF_ROWS; j++){
				field[i][j] = false;
			}
		}
		
		//1. find cluster around x,y
		field = FindClusterRecurse(new FieldIndex(x,y), field);
		
		//2. find towers in this cluster
		ArrayList<Skill> BuildList = new ArrayList<Skill>(FindTowersInCluster(field)); //ta vare på retur-variabel...
		
		//3. adding skills to the player
		if(!Game.getInstance().getActivePlayer().isSilenced() || BuildList.isEmpty()){
			for(Skill s : BuildList){
				//if five-in-a-row detected, respond accordingly.
			}
		}
	}
	
	
	private static boolean[][] FindClusterRecurse(FieldIndex ind, boolean[][] taken ){
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
	
	private static ArrayList<Skill> FindTowersInCluster(boolean[][] cluster){
		// checks if there is a piece in front of the given piece, 
		// if there is, there might be a tower; calling specific find-tower functions
		// does this for all 8 directions.
		ArrayList<Skill> towerList = new ArrayList<Skill>();
		for (int nx=0; nx<Gameboard.NUMBER_OF_COLUMNS; nx++){ //checking for all elements in the cluster...
			for (int ny=0; ny<Gameboard.NUMBER_OF_ROWS; ny++){
				if( cluster[nx][ny]){
					
					for (int i=0; i<8; i++){ //...in all directions
						FieldIndex f = new FieldIndex(nx,ny);
						if(f.Up(i).Valid()){
							int n = FindShootTower(i, f, cluster);//number of towers
							for (int tmp=0; tmp<n; tmp++){
								towerList.add(new SkillDestroyTower());
							}
							n = FindBuildTower(i,f, cluster);
							for (int tmp=0; tmp<n; tmp++){
								towerList.add(new SkillNewTower());
							}
							n = FindSilenceTower(i,f, cluster);
							for (int tmp=0; tmp<n; tmp++){
								towerList.add(new SkillSilent());
							}
							n = FindMultipleSkillsTower(i,f, cluster);
							for (int tmp=0; tmp<n; tmp++){
								towerList.add(new SkillMultipleSkills());
							}
							n = FindFiveTower(i,f, cluster);
							for (int tmp=0; tmp<n; tmp++){
								//add response for five-in-a-row
							}
							
						}
					}
				}
					
			}
		}
		return towerList;
		// FEIL: den må si fra om tårnets posisjon
	}
	
	private static int FindShootTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a shoot tower in the given direction
		//startpoint should be the second index
		//returns number of found towers
		
		FieldIndex right = startPoint.Right(direction);
		FieldIndex left = startPoint.Left(direction);
		boolean leftPart = left.Valid() && cluster[left.x()][left.y()];
		boolean rightPart = right.Valid() && cluster[right.x()][right.y()];
		
		if (leftPart && rightPart){
			return 1;
		}else{
			return 0;
		}
		// FEIL: den må si fra om tårnets posisjon
	}
	private static int FindBuildTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a build tower in the given direction
		//startPoint should be the second index
		//returns number of found towers
		int nrOfTowers = 0;
		FieldIndex up = startPoint.Up(direction);
		if(up.Valid() && cluster[up.x()][up.y()]){
			FieldIndex right = up.Right(direction);
			FieldIndex left = up.Left(direction);
			boolean leftPart = left.Valid() && cluster[left.x()][left.y()];
			boolean rightPart = right.Valid() && cluster[right.x()][right.y()];
			if (leftPart){
				nrOfTowers++;
			}
			if( rightPart ){
				nrOfTowers++;
			}
			
		}
		return nrOfTowers;
		// FEIL: den må si fra om tårnets posisjon
	}
	private static int FindSilenceTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a silence tower in the given direction
		//startPoint should be the second index
		//returns number of found towers
		int nrOfTowers = 0;
		
		FieldIndex right = startPoint.Right(direction);
		FieldIndex left = startPoint.Left(direction);
		boolean leftPart = left.Valid() && cluster[left.x()][left.y()];
		boolean rightPart = right.Valid() && cluster[right.x()][right.y()];
		
		if(leftPart){
			FieldIndex up = left.Up(direction);
			if(up.Valid() && cluster[up.x()][up.y()]){
				nrOfTowers++;
			}
		}
		if(rightPart){
			FieldIndex up = right.Up(direction);
			if(up.Valid() && cluster[up.x()][up.y()]){
				nrOfTowers++;
			}
		}
		return nrOfTowers;
		// FEIL: den må si fra om tårnets posisjon
	}
	private static int FindMultipleSkillsTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the two last pieces of a multiple-skills tower in the given direction
		//startPoint should be the second index
		//returns number of found towers
		FieldIndex right = startPoint.Right(direction);
		if( right.Valid() && cluster[right.x()][right.y()] ){
			FieldIndex down = right.Down(direction);
			if( down.Valid() && cluster[down.x()][down.y()] ){
				return 1;
			}
		}
		return 0;
	}
	
	private static int FindFiveTower(int direction, FieldIndex startPoint, boolean[][] cluster){
		//checks for the three last pieces of a five-in-a-row tower in the given direction
		//startPoint should be the second index
		//returns number of found towers
		FieldIndex up = startPoint.Up(direction);
		if( up.Valid() && cluster[up.x()][up.y()] ){
			up = up.Up(direction);
			if( up.Valid() && cluster[up.x()][up.y()] ){
				up = up.Up(direction);
				if( up.Valid() && cluster[up.x()][up.y()] ){
					return 1;
				}
			}
		}
		return 0;
	}
}