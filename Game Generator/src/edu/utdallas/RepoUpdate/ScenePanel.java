package edu.utdallas.RepoUpdate;

import edu.utdallas.gamegenerator.Search.InputWizard;
import edu.utdallas.gamegenerator.Shared.*;

import java.awt.*;
import java.awt.Color;
import java.util.List;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;

public class ScenePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private BufferedImage background;
	private ArrayList<InformationBoxAsset> infoAssets; //separate because must be handled after loading all other assets
	private ArrayList<JLabel> assetPanels;
	private String charBaseDir = "Office, Classroom\\Characters\\";
	private String imageBaseDir = "Office, Classroom\\";
	private Point prevClickPoint;
	private ScenePanel that = this;
	private Asset toDeleteAsset = null;
	private InputWizard parentWizard;
	
	public ScenePanel(InputWizard parent)
	{
		clear();
		parentWizard = parent;
		addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) { 
		        	for (JLabel label : assetPanels){
		        		label.setBorder(null);
		        	}
		        }
		        public void mouseEntered(MouseEvent e) { }
		        public void mouseExited(MouseEvent e) { }
		        public void mousePressed(MouseEvent e) { prevClickPoint = e.getPoint(); }
		        public void mouseReleased(MouseEvent e) { }
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {}
			public void mouseMoved(MouseEvent e) {
				getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
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
			else if (a instanceof ButtonAsset){
				if(a.getDisplayImage() == null)
				{
					a.setDisplayImage("Props\\GenericInteraction\\Button.png");
				}
				else if(a.getDisplayImage().equals(""))
				{
					a.setDisplayImage("Props\\GenericInteraction\\Button.png");
				}
				loadAsset(a, imageBaseDir);
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
	// NOTE: not all JLabels may be bubble images, but the closest JLabels to the text coordinates should be a bubble
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
			//drawing text over a label with an image - draw the text on the image then add to the label
			if(a instanceof ButtonAsset)
			{
				Graphics g = scaledImage.getGraphics();
				g.setFont(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
				FontMetrics fMetrics = g.getFontMetrics();
				g.setColor(Color.BLACK);
				int sWidth = fMetrics.stringWidth(a.getName());
				int sHeight = fMetrics.getHeight();
				g.drawString(a.getName(), scaledImage.getWidth()/2 - sWidth/2, scaledImage.getHeight()/2 + sHeight/4);
				g.dispose();
			}
			final JLabel label = new JLabel(new ImageIcon(scaledImage));
			label.setLayout(new BorderLayout());
			label.setBounds((int)a.getLocX(), (int)a.getLocY(), scaledImage.getWidth(), scaledImage.getHeight());
			add(label);
			
			label.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) { 
		        	//if right-click
		        	if((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){
		        		System.out.println("right-clicked");
		        		JPopupMenu pMenu = new JPopupMenu();
		        		JMenuItem menuItem = new JMenuItem("delete");
		        		menuItem.setActionCommand("deleteElement");
		        		menuItem.addActionListener(parentWizard);
		        		pMenu.add(menuItem);
		        		toDeleteAsset = a;
		        		that.add(pMenu);
		        		pMenu.show(e.getComponent(), e.getX(), e.getY());
					}
		        	for (JLabel label : assetPanels){
		        		label.setBorder(null);
		        	}
		        	Border highlights = BorderFactory.createLineBorder(Color.YELLOW, 5, true);
		        		label.setBorder(highlights);
		        }
		        public void mouseEntered(MouseEvent e) { }
		        public void mouseExited(MouseEvent e) { }
		        public void mousePressed(MouseEvent e) { prevClickPoint = e.getPoint(); }
		        public void mouseReleased(MouseEvent e) { }
		    });
			label.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
		        	Point p = e.getPoint();
		        	int deltaX = p.x - prevClickPoint.x;
		        	int deltaY = p.y - prevClickPoint.y;
		        	int newX = label.getX() + deltaX;
		        	int newY = label.getY() + deltaY;
		        	label.setLocation(newX, newY);
					a.setLocX(newX);
					a.setLocY(newY);
					for (JLabel label : assetPanels){
		        		label.setBorder(null);
		        	}
		        	Border highlights = BorderFactory.createLineBorder(Color.YELLOW, 5, true);
		        		label.setBorder(highlights);
				}
				public void mouseMoved(MouseEvent e) {
					Point p = e.getPoint();
					if(p.x <= 5 && p.y <= 5)
					{
						label.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
					}
					else if(p.x <= 5 && label.getHeight() - p.y <= 5)
					{
						label.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
					}
					else if(label.getWidth() - p.x <= 5 && p.y <= 5)
					{
						label.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
					}
					else if(label.getWidth() - p.x <= 5 && label.getHeight() - p.y <= 5)
					{
						label.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
					}
					else
					{
						label.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				}
			});

			assetPanels.add(label);
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
			final JLabel label = new JLabel(new ImageIcon(scaledImage));
			label.setLayout(new BorderLayout());
			label.setBounds((int)a.getLocX(), (int)a.getLocY(), scaledImage.getWidth(), scaledImage.getHeight());
			add(label);
			
			label.addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) { }
		        public void mouseEntered(MouseEvent e) { }
		        public void mouseExited(MouseEvent e) { }
		        public void mousePressed(MouseEvent e) { prevClickPoint = e.getPoint(); }
		        public void mouseReleased(MouseEvent e) { }
		    });
			label.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
		        	Point p = e.getPoint();
		        	int deltaX = p.x - prevClickPoint.x;
		        	int deltaY = p.y - prevClickPoint.y;
		        	int newX = label.getX() + deltaX;
		        	int newY = label.getY() + deltaY;
		        	label.setBounds(newX, newY, (int)a.getWidth(), (int)a.getHeight());
				}
				public void mouseMoved(MouseEvent e) { }
			});
			
			assetPanels.add(label);
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

        if(background != null)
        {
        	g.drawImage(background, 0, 0, null);
        }
    }
    
	public Asset getAssetToDelete()
	{
		return toDeleteAsset;
	}
}
