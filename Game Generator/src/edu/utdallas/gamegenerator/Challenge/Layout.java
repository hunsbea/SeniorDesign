package edu.utdallas.gamegenerator.Challenge;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import oracle.jrockit.jfr.Options;

import edu.utdallas.gamegenerator.Shared.Asset;
import edu.utdallas.gamegenerator.Shared.ButtonAsset;
import edu.utdallas.gamegenerator.Shared.InformationBoxAsset;


/*

The Multiple Choice Layout should look like this: 

MZZZZ$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZM.
M  ~M?..............................$M.   ::::::::::::::::::::~::::::::::~::..D 
M .M,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.M  M..................................Z?8 
M =$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M..M,,,,,,,,,,,,,,,OPTION,,,,,,,,,,,,,7?8 
M =$,,,,,,,,,,,,,.,,,,,,,,,,,,,,,,,,,,M..M,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,7?8 
M =$,,,,,,,,,,,,,,STEM,,,,,,,,,,,,,,,,M..M,,,,,,,,,,,,,,.,,,,,,,.,,,,,,,,,,,7?8 
M =$,,,,,,,,,,DESCRIPTION,,,,,,,,,,,,,M...I?????????????????????????????????~ D 
M =$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M...7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$:.D 
M =$,,,,,,,,:,.,.,..,..,,..,.,,,,,,,,,M..M,,,,,,,,,,,,..,,,,,,,,,,,,,,,,,,,,$?8 
M =$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M..M,,,,,,,,,,,,,,,OPTION,,,,,,,,,,,,,7?8 
M =$,,,,,,,,.,,,.,...+.,.,.,.,,,,,,,,,M..M,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,7?8 
M =$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M..M..................................Z?8 
M ,M.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~..D 
M  .MO=============================?DN.  $MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMN D 
M    ===============================~   .M,,,,,,,,,,,,,~,,,,,,,,,,,,,,,,,,,,7?8 
M .M=,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,:78 .M,,,,,,,,,,,,,,,OPTION,,,,,,,,,,,,,7?8 
M =Z,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M..M,,,,,,,,,,,,.,,.,,,.,.,,,,,,,,,,,,7?8 
M =$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M..MIIIIIIIIIIIII7IIIIIIIIIIIIIIIIIIIIN~8 
M =$,,,,,~,,,,,,,,,,,,,,,,,,,,,,,,,,,,M..          ... .......... .... .... ..D 
M =$,,,,:,,,,,,STEM QUESTION,,,,,,,,,,M..~MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM=D 
M =$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M. M.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M8 
M =$,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M. M.,,,,,,,,,,,,,,OPTION,,,,,,,,,,,,,,M8 
M ~Z,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,M. M.,,,,,,,,,,,,,,,,...,..,,,,,,,,,,,,M8 
M  MI:::::::::::::::::::::::::::::::~8Z. ONDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDM8D 
M   ..................................  .    ..   ............................D 
M:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::D


TODO: Assumptions:
1. Only multiple choice questions are available
2. Each multiple choice question has a description, a question, and exactly 4 options

*/

public class Layout extends JPanel
{
	public static enum LayoutType { MULTIPLE_CHOICE_LAYOUT }
	
	private ArrayList<Asset> assets = new ArrayList<Asset>();
	
	public Layout(MultipleChoiceItem item)
	{
		int currentY = 50;
		int numButtons = item.getOptions().size();
		int bHeight = 300 / numButtons;
		for(int i = 0; i < numButtons; i++)
		{
			ButtonAsset option = new ButtonAsset();
			option.setWidth(300);
			option.setHeight(bHeight);
			option.setLocX(400);
			option.setLocY(currentY);
			currentY += bHeight;
			option.setFontFamily("Comic Sans MS");
			option.setFontSize(15);
			option.setName(item.getOptions().get(i).getText());
			option.setHint(item.getOptions().get(i).getHint());
			assets.add(option);
		}
		
		InformationBoxAsset stemText = new InformationBoxAsset();
		stemText.setWidth(300);
		stemText.setHeight(150);
		stemText.setLocX(50);
		stemText.setLocY(50);
		stemText.setFontFamily("Comic Sans MS");
		stemText.setFontSize(15);
		stemText.setName(item.getStem().getStemText().getText());
		stemText.setHint(item.getStem().getStemText().getHint());
		assets.add(stemText);
		
		InformationBoxAsset stemQuestion = new InformationBoxAsset();
		stemQuestion.setWidth(300);
		stemQuestion.setHeight(150);
		stemQuestion.setLocX(50);
		stemQuestion.setLocY(200);
		stemQuestion.setFontFamily("Comic Sans MS");
		stemQuestion.setFontSize(15);
		stemQuestion.setName(item.getStem().getStemQuestion().getText());
		stemQuestion.setHint(item.getStem().getStemQuestion().getHint());
		assets.add(stemQuestion);
	}
	
	public List<Asset> getAssets()
	{
		return assets;
	}
}
