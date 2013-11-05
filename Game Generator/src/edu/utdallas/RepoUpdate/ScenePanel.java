package edu.utdallas.RepoUpdate;

import edu.utdallas.gamegenerator.Shared.*;

import java.awt.*;
import java.awt.Color;
import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ScenePanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private ArrayList<InformationBoxAsset> infoAssets; //separate because must be handled after loading all other assets
	private ArrayList<JLabel> assetPanels;
	private String charBaseDir = "Office, Classroom\\Characters\\";
	private String imageBaseDir = "Office, Classroom\\";
	private Point prevClickPoint;
	
	public ScenePanel()
	{
		clear();
		System.out.println("calling clear constructor\n");
	}
	
	public void clear()
	{
		background = null;
		infoAssets = new ArrayList<InformationBoxAsset>();
		assetPanels = new ArrayList<JLabel>();
		removeAll();
		updateUI();
	}
	
	public void loadBackground(String imageFile)
	{
		try 
		{
			background = getScaledImage(ImageIO.read(new File("Office, Classroom\\" + imageFile)), 1.5);
			
			repaint();
		} 
		catch (IOException ex) 
		{
			System.out.println(imageFile + " is missing from repository, cannot load");
		}
	}
	
	public void loadAssets(List<Asset> as)
	{
		clear();
		System.out.println("calling clear load assets\n");
		
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
				infoAssets.add((InformationBoxAsset)a);
			}
		}
		
		associateText(assetPanels, infoAssets);
		addNotify();
		revalidate();
		repaint();
	}
	public void loadAssetsToRoot(List<Asset> as)
	{
		clear();
		System.out.println("calling clear load assets to root\n");
		
		
		for(Asset a : as)
		{
			if(a instanceof CharacterAsset)
			{
				
				loadAssetToRoot(a, charBaseDir);
			}
		}
		
		associateText(assetPanels, infoAssets);
		addNotify();
		revalidate();
		repaint();
	}
	
	// Match the text strings to the correct (closest) text bubble image
	// NOTE: not all JPanels may be bubble images, but the closest JPanel to the text coordinates should be a bubble
	private void associateText(ArrayList<JLabel> bubbles, List<InformationBoxAsset> texts)
	{
		double closest = Double.MAX_VALUE;
		JLabel closestPanel = null;
		
		//TODO: there may be more text than bubbles due to bad XML formatting, this is a problem
		if(bubbles == null || bubbles.size() == 0 || texts == null || texts.size() == 0)
			return;
		
		// for each text, find the JPanel with closest x,y coordinates and set its font and text
		for(InformationBoxAsset text : texts)
		{
			closestPanel = bubbles.get(0);
			for(JLabel j : bubbles)
			{
				double dist = distSqrd(j.getLocation(), new Point((int)text.getLocX(), (int)text.getLocY()));
				if(dist < closest)
				{
					closest = dist;
					closestPanel = j;
				}
			}
			
			JLabel label = new JLabel("<html><p style=\"padding-left:12px\">" + text.getName() + "</p></html>");
			label.setFont(new Font(text.getFontFamily(), Font.BOLD, text.getFontSize()));
			label.setForeground(Color.BLACK);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setVerticalAlignment(JLabel.TOP);
			closestPanel.add(label, BorderLayout.CENTER);
			System.out.println(closestPanel.getX() + " " + closestPanel.getY() + " " + text.getName());
		}
	}
	
	// Save time, no need to calculate square root to determine shortest distance
	private double distSqrd(Point a, Point b)
	{
		return (b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y);
	}
	
	// ScenePanel.getScaledImage(yourImage, scale);
	public static BufferedImage getScaledImage(BufferedImage orig, double scale)
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
	
	public void loadAsset(final Asset a, String baseDir)
	{
		try 
		{
			
			BufferedImage image = ImageIO.read(new File(baseDir + a.getDisplayImage()));
			int width = image.getWidth();
			double desiredWidth = a.getWidth();
			double scaleFactor = desiredWidth / width;
			
			BufferedImage scaledImage = getScaledImage(image, scaleFactor);
			final JLabel panel = new JLabel(new ImageIcon(scaledImage));
			panel.setLayout(new BorderLayout());
			panel.setBounds((int)a.getLocX(), (int)a.getLocY(), scaledImage.getWidth(), scaledImage.getHeight());
			add(panel);
			
			panel.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) { }
		        public void mouseEntered(MouseEvent e) { }
		        public void mouseExited(MouseEvent e) { }
		        public void mousePressed(MouseEvent e) { prevClickPoint = e.getPoint(); }
		        public void mouseReleased(MouseEvent e) { }
		    });
			panel.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
		        	Point p = e.getPoint();
		        	int deltaX = p.x - prevClickPoint.x;
		        	int deltaY = p.y - prevClickPoint.y;
		        	int newX = panel.getX() + deltaX;
		        	int newY = panel.getY() + deltaY;
		        	panel.setBounds(newX, newY, (int)a.getWidth(), (int)a.getHeight());
				}
				public void mouseMoved(MouseEvent e) { }
			});
			
			assetPanels.add(panel);
			repaint();
		} 
		catch (IOException ex) 
		{
			System.out.println(a.getDisplayImage() + " is missing from repository, cannot load");
		}
	}
	public void loadAssetToRoot(final Asset a, String baseDir)
	{
		try 
		{
			
			BufferedImage image = ImageIO.read(new File(baseDir + a.getDisplayImage()));
			int width = image.getWidth();
			double desiredWidth = a.getWidth();
			double scaleFactor = desiredWidth / width;
			
			BufferedImage scaledImage = getScaledImage(image, scaleFactor);
			final JLabel panel = new JLabel(new ImageIcon(scaledImage));
			panel.setLayout(new BorderLayout());
			panel.setBounds((int)a.getLocX(), (int)a.getLocY(), scaledImage.getWidth(), scaledImage.getHeight());
			add(panel);
			
			panel.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) { }
		        public void mouseEntered(MouseEvent e) { }
		        public void mouseExited(MouseEvent e) { }
		        public void mousePressed(MouseEvent e) { prevClickPoint = e.getPoint(); }
		        public void mouseReleased(MouseEvent e) { }
		    });
			panel.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
		        	Point p = e.getPoint();
		        	int deltaX = p.x - prevClickPoint.x;
		        	int deltaY = p.y - prevClickPoint.y;
		        	int newX = panel.getX() + deltaX;
		        	int newY = panel.getY() + deltaY;
		        	panel.setBounds(newX, newY, (int)a.getWidth(), (int)a.getHeight());
				}
				public void mouseMoved(MouseEvent e) { }
			});
			
			assetPanels.add(panel);
			repaint();
		} 
		catch (IOException ex) 
		{
			System.out.println(a.getDisplayImage() + " is missing from repository, cannot load");
		}
	}
	
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
		//removeAll();
        
        if(background != null)
        {
        	g.drawImage(background, 0, 0, null);
        }
        // adding the panels is equivalent to painting
        /*for(int i = 0; i < assetPanels.size(); i++)
        {
        	add(assetPanels.get(i));
        }*/
    }
}
