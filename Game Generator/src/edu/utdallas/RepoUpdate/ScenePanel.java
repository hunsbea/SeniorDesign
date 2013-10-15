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
	private ArrayList<Point> infoBoxPoints;
	private ArrayList<Font> infoBoxFonts;
	private String charBaseDir = "Office, Classroom\\Characters\\";
	private String imageBaseDir = "Office, Classroom\\";
	//private String inforBoxBaseDir = "Office, Classroom\\Characters\\";
	
	public ScenePanel()
	{
		background = null;
		assets = new ArrayList<Asset>();
		assetImages = new ArrayList<BufferedImage>();
		infoBoxStrings = new ArrayList<String>();
		infoBoxPoints = new ArrayList<Point>();
		infoBoxFonts = new ArrayList<Font>();
	}
	
	public void loadBackground(String imageFile)
	{
		try 
		{                
			background = ImageIO.read(new File("Office, Classroom\\" + imageFile));
			System.out.println("success read in image");
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
				infoBoxStrings.add(a.getName());
				infoBoxPoints.add(new Point((int)a.getLocX(), (int)a.getLocY()));
				infoBoxFonts.add(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
				repaint();
			}
		}
	}
	
	public void loadAsset(Asset a, String baseDir)
	{
		try 
		{                
			BufferedImage image = ImageIO.read(new File(baseDir + a.getDisplayImage()));
			System.out.println("success read in image");
			
			assets.add(a);
			assetImages.add(image);
			
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
        for(int j = 0; j < infoBoxStrings.size(); j++)
        {
        	g.setFont(infoBoxFonts.get(j));
        	g.drawString(infoBoxStrings.get(j), infoBoxPoints.get(j).x, infoBoxPoints.get(j).y);
        }
    }
}
