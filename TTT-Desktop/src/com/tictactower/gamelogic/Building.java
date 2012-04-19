package com.tictactower.gamelogic;

import java.util.ArrayList;
import com.tictactower.skills.*;

class Building{
	
	private ArrayList<FieldIndex> indexes;
	
	private SkillContainer skills;
	
	public Building(){
		skills = new SkillContainer();
		indexes =  new ArrayList<FieldIndex>();
	}
	
	public void AddTower(ArrayList<FieldIndex> list, int type){
		//add indexes if unique. Add tower type
	}
	
	public void AddIndex(FieldIndex f){
		for (FieldIndex ind : indexes){
			if(ind == f){
				return;
			}
		}
		indexes.add(f);
	}
	
	
}