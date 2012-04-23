package com.tictactower.ui.text;

import java.util.ArrayList;

public class TextBoxes {

	private static final ArrayList<TextBox> textBoxListP1 = new ArrayList<TextBox>();
	private static final ArrayList<TextBox> textBoxListP2 = new ArrayList<TextBox>();
	
	private static final TextBoxShootP1 textBoxShootP1 = new TextBoxShootP1();
	private static final TextBoxShootP2 textBoxShootP2 = new TextBoxShootP2();
	private static final TextBoxBuildP1 textBoxBuildP1 = new TextBoxBuildP1();
	private static final TextBoxBuildP2 textBoxBuildP2 = new TextBoxBuildP2();
	private static final TextBoxSilenceP1 textBoxSilenceP1 = new TextBoxSilenceP1();
	private static final TextBoxSilenceP2 textBoxSilenceP2 = new TextBoxSilenceP2();
	private static final TextBoxSkillCapP1 textBoxSkillCapP1 = new TextBoxSkillCapP1();
	private static final TextBoxSkillCapP2 textBoxSkillCapP2 = new TextBoxSkillCapP2();
	
	public TextBoxes() {
		textBoxListP1.add(textBoxShootP1);
		textBoxListP2.add(textBoxShootP2);
		textBoxListP1.add(textBoxBuildP1);
		textBoxListP2.add(textBoxBuildP2);
		textBoxListP1.add(textBoxSilenceP1);
		textBoxListP2.add(textBoxSilenceP2);
		textBoxListP1.add(textBoxSkillCapP1);
		textBoxListP2.add(textBoxSkillCapP2);
	}
	
	public static ArrayList<TextBox> getTextBoxListP1() {
		return textBoxListP1;
	}
	
	public static ArrayList<TextBox> getTextBoxListP2() {
		return textBoxListP2;
	}
	
}
