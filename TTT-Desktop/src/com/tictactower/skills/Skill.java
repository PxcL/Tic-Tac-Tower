package com.tictactower.skills;


public class Skill {
	
	private SkillType flag;
	
	public void cancelSkill(){
		flag = SkillType.NO_SKILL;
	}
	
	public void useSkill(SkillType skill){
		if(skill == SkillType.SHOOT){
				flag = skill;
		}
		if(skill == SkillType.BUILD){
				flag = skill;
		}
		if(skill == SkillType.SILENCE){
				flag = skill;
		}
	}
	
	public SkillType getFlag(){
		return flag;
	}
	
	
}
