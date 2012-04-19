package com.tictactower.skills;


public class Skill {
	
	private SkillType flag;
	
	public void cancelSkill(){
		flag = SkillType.NO_SKILL;
	}
	
	public void useSkill(SkillType skill){
		flag = skill;
	}
	
	public SkillType getFlag(){
		return flag;
	}
	
	
}
