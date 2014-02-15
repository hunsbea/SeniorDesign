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
		int startY = 50;
		for(int i = 0; i < 4; i++)
		{
			ButtonAsset option = new ButtonAsset();
			option.setWidth(200);
			option.setHeight(50);
			option.setLocX(300);
			option.setLocY(startY * (i + 1));
			option.setFontFamily("Comic Sans MS");
			option.setFontSize(15);
			option.setName(item.getOptions().get(i).getText());
			assets.add(option);
		}
		
		InformationBoxAsset stemText = new InformationBoxAsset();
		stemText.setWidth(200);
		stemText.setHeight(100);
		stemText.setLocX(50);
		stemText.setLocY(50);
		stemText.setFontFamily("Comic Sans MS");
		stemText.setFontSize(15);
		stemText.setName(item.getStem().getStemText().getText());
		assets.add(stemText);
		
		InformationBoxAsset stemQuestion = new InformationBoxAsset();
		stemQuestion.setWidth(200);
		stemQuestion.setHeight(100);
		stemQuestion.setLocX(50);
		stemQuestion.setLocY(150);
		stemQuestion.setFontFamily("Comic Sans MS");
		stemQuestion.setFontSize(15);
		stemQuestion.setName(item.getStem().getStemQuestion().getText());
		assets.add(stemQuestion);
	}
	
	public List<Asset> getAssets()
	{
		return assets;
	}
	
	
	
	
	
	
	
	
	
	
	/*private static final int hgap = 10, vgap = 10;
	private MultipleChoiceItem item; 
	
	
	public Layout(MultipleChoiceItem item)
	{
		super();
		this.item = item;
		setLayout(new BorderLayout());
		
		JPanel stemPanel = new JPanel(); //flow layout
		JPanel optionPanel = new JPanel(new GridLayout(4, 1, hgap, vgap));
		
		List<Option> options = item.getOptions();
		for(int i = 0; i < 4; i++)
		{
			JLabel option = new JLabel(options.get(i).getText());
			formatLabel(option);
			optionPanel.add(option);
		}
		
		JLabel description = new JLabel(item.getStem().getStemText());
		formatLabel(description);
		stemPanel.add(description);
		JLabel question = new JLabel(item.getStem().getStemQuestion());
		formatLabel(question);
		stemPanel.add(question);
	}
	
	private void formatLabel(JLabel option)
	{
		option.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		option.setHorizontalAlignment(JLabel.CENTER);
		option.setForeground(Color.BLACK);
		option.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		option.setBackground(Color.YELLOW);
		option.setOpaque(true);
	}*/
}
