package edu.utdallas.gamegenerator.Challenge;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

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
2. Each multiple choice question has a description, a question, and 1+ options

*/

public class Layout extends JPanel
{
	private static final long serialVersionUID = 1L;

	public static enum LayoutType { MULTIPLE_CHOICE_LAYOUT }
	
	private ArrayList<Asset> assets = new ArrayList<Asset>();
	
	public Layout(MultipleChoiceItem item)
	{
		// for now, don't display any of the challenge if any piece is missing
		if(item == null || item.getOptions() == null || item.getOptions().size() == 0
				|| item.getStem() == null || item.getStem().getStemText() == null
				|| item.getStem().getStemQuestion() == null)
			return;
		
		int startY = 30, startX = 30;
		int currentY = startY;
		int qPaddingY = 4;
		int numButtons = item.getOptions().size();
		int bHeight = 300 / numButtons;
		for(int i = 0; i < numButtons; i++)
		{
			ButtonAsset option = new ButtonAsset();
			option.setWidth(300);
			option.setHeight(bHeight);
			option.setX(400);
			option.setY(currentY);
			currentY += bHeight + qPaddingY;
			option.setFontFamily("Comic Sans MS");
			option.setFontSize(18);
			option.setName(item.getOptions().get(i).getText());
			option.setHint(item.getOptions().get(i).getHint());
			assets.add(option);
		}
		
		int qHeight = (currentY - startY - qPaddingY) / 2 - (qPaddingY / 2);
		InformationBoxAsset stemText = new InformationBoxAsset();
		stemText.setWidth(300);
		stemText.setHeight(qHeight);
		stemText.setX(startX);
		stemText.setY(startY);
		stemText.setFontFamily("Comic Sans MS");
		stemText.setFontSize(20);
		stemText.setName(item.getStem().getStemText().getText());
		stemText.setHint(item.getStem().getStemText().getHint());
		assets.add(stemText);
		
		InformationBoxAsset stemQuestion = new InformationBoxAsset();
		stemQuestion.setWidth(300);
		stemQuestion.setHeight(qHeight);
		stemQuestion.setX(startX);
		stemQuestion.setY(startY + qHeight + qPaddingY);
		stemQuestion.setFontFamily("Comic Sans MS");
		stemQuestion.setFontSize(30);
		stemQuestion.setName(item.getStem().getStemQuestion().getText());
		stemQuestion.setHint(item.getStem().getStemQuestion().getHint());
		assets.add(stemQuestion);
		
		InformationBoxAsset learningObjective = new InformationBoxAsset();
		learningObjective.setWidth(1);
		learningObjective.setHeight(1);
		learningObjective.setX(725);
		learningObjective.setY(15);
		learningObjective.setFontFamily("Comic Sans MS");
		learningObjective.setFontSize(18);
		learningObjective.setName("");
		Hint hint = new Hint();
		hint.setText("Learning Obj. " + item.getLearningObjective());
		learningObjective.setHint(hint);
		assets.add(learningObjective);
	}
	
	public List<Asset> getAssets()
	{
		return assets;
	}
}
