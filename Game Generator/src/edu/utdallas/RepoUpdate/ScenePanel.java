package edu.utdallas.RepoUpdate;

import edu.utdallas.gamegenerator.Shared.*;

import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ScenePanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private ArrayList<Asset> assets;
	private ArrayList<BufferedImage> assetImages;
	private ArrayList<String> infoBoxStrings;
	private ArrayList<String[]> splitStrings;
	private ArrayList<Point> infoBoxPoints;
	private ArrayList<Font> infoBoxFonts;
	private String charBaseDir = "Office, Classroom\\Characters\\";
	private String imageBaseDir = "Office, Classroom\\";
	//private String inforBoxBaseDir = "Office, Classroom\\Characters\\";
	
	public ScenePanel()
	{
		clear();
	}
	
	public void clear()
	{
		background = null;
		assets = new ArrayList<Asset>();
		assetImages = new ArrayList<BufferedImage>();
		infoBoxStrings = new ArrayList<String>();
		infoBoxPoints = new ArrayList<Point>();
		infoBoxFonts = new ArrayList<Font>();
		splitStrings = new ArrayList<String[]>();
		removeAll();
		updateUI();
	}
	
	// Algorithm for splitting bubble text when it would exceed the width of the bubble
	// must split string because graphics object doesn't respect "\n" when painting strings
	private String[] splitTextBubble(String text, int textWidth, int bubbleWidth)
	{
		if(textWidth < bubbleWidth - 10)
		{
			String[] arrayText = { text };
			System.out.println("returning early");
			return arrayText;
		}
		
		// calculate number of splits needed,
		// insert newlines as placeholders where the string should be split
		// return the split string
		int numSplits = (textWidth / bubbleWidth);
		int currentIndex = 0;
		for(int i = 0; i < numSplits - 1; i++)
		{
			//TODO: ugly logic
			currentIndex += text.length() / numSplits;
			while(text.charAt(currentIndex - 1) != ' ' && currentIndex > 4)
				currentIndex--; //don't cut off a word
			text = text.substring(0, currentIndex - 1) + "\n" + text.substring(currentIndex, text.length());
		}
		
		//return text.split("\n");
		String[] strings = text.split("\n");
		//for(String s : strings)
			//System.out.println(s + " $");
		
		return strings;
	}
	
	public void loadBackground(String imageFile)
	{
		try 
		{
			background = ImageIO.read(new File("Office, Classroom\\" + imageFile));
			repaint();
		} 
		catch (IOException ex) 
		{
			System.out.println(imageFile + " is missing from repository, cannot load");
		}
	}
	
	public void loadAssets(List<Asset> as)
	{
		assets = new ArrayList<Asset>();
		assetImages = new ArrayList<BufferedImage>();
		infoBoxStrings = new ArrayList<String>();
		infoBoxPoints = new ArrayList<Point>();
		infoBoxFonts = new ArrayList<Font>();
		splitStrings = new ArrayList<String[]>();
		
		for(Asset a : as)
		{
			if(a instanceof CharacterAsset)
			{
				loadAsset(a, charBaseDir);
			}
			else if(a instanceof ImageAsset)
			{
				loadAsset(a, imageBaseDir);
			}
			else if(a instanceof InformationBoxAsset)
			{
				//loadAsset(a, infoBoxBaseDir);
				String name = a.getName();
				Font nameFont = new Font(a.getFontFamily(), Font.BOLD, a.getFontSize());
				FontMetrics fm = getGraphics().getFontMetrics(nameFont);
				int fontWidth = fm.stringWidth(name);
				int bubbleWidth = fontWidth / 2; // TODO: for now
				//there's no way of knowing which text bubble goes with which text
				// I would have to use a closest point algorithm to find the correct bubble-text match,
				// and that would assume all bubbles are initialized before all text,
				// or I wait until all elements have been read in, and then split the strings after the closest pair alg
				splitStrings.add(splitTextBubble(name, fontWidth, bubbleWidth));
				infoBoxStrings.add(a.getName());
				infoBoxPoints.add(new Point((int)a.getLocX(), (int)a.getLocY()));
				infoBoxFonts.add(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
				repaint();
			}
		}
	}
	
	private BufferedImage getScaledImage(BufferedImage orig, double scale)
	{
		int origW = orig.getWidth();
		int origH = orig.getHeight();
		double newW = origW * scale;
		double newH = origH * scale;
		
		BufferedImage resized = new BufferedImage((int)newW, (int)newH, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = resized.createGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(orig, 0, 0, (int)newW, (int)newH, 0, 0, orig.getWidth(), orig.getHeight(), null);
	    g.dispose();
	    
	    return resized;
	}
	
	public void loadAsset(Asset a, String baseDir)
	{
		try 
		{
			BufferedImage image = ImageIO.read(new File(baseDir + a.getDisplayImage()));
			int width = image.getWidth();
			double desiredWidth = a.getWidth();
			double scaleFactor = desiredWidth / width;
			
			assets.add(a);
			assetImages.add(getScaledImage(image, scaleFactor));
			
			repaint();
		} 
		catch (IOException ex) 
		{
			System.out.println(a.getDisplayImage() + " is missing from repository, cannot load");
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(background != null)
        {
        	g.drawImage(background, 0, 0, null);   
        }
        //same number of assets as assetImages
        for(int i = 0; i < assets.size(); i++)
        {
        	Asset a = assets.get(i);
        	g.drawImage(assetImages.get(i), (int)a.getLocX(), (int)a.getLocY(), null);
        }
        for(int j = 0; j < splitStrings.size(); j++)
        {
        	g.setFont(infoBoxFonts.get(j));
        	g.drawString(infoBoxStrings.get(j), infoBoxPoints.get(j).x, infoBoxPoints.get(j).y + 10);
        	//TODO: assuming text height known
        	/*int offsetY = 10;
        	for(int k = 0; k < splitStrings.get(j).length; k++)
        	{
        		g.drawString(splitStrings.get(j)[k], infoBoxPoints.get(j).x, infoBoxPoints.get(j).y + offsetY);
        		offsetY += 20;
        	}*/
        }
    }
}
