package com.tictactower.skills;

public abstract class Skill {

	public final static void use(Skill skill) {
		skill.execute();
	}
	
	public abstract void execute();
	
}
