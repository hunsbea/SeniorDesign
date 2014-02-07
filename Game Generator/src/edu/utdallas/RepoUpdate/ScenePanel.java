package edu.utdallas.RepoUpdate;

import edu.utdallas.gamegenerator.Search.InputWizard;
import edu.utdallas.gamegenerator.Shared.*;

import java.awt.*;
import java.awt.Color;
import java.util.List;
import java.awt.event.ActionEvent;
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
	private ArrayList<JLabel> assetPanels;
	private String charBaseDir = "Office, Classroom\\Characters\\";
	private String imageBaseDir = "Office, Classroom\\";
	private Point prevClickPoint;
	private ScenePanel that = this;
	private Asset toDeleteAsset = null;
	private InputWizard parentWizard;
	private boolean resize = false;
	
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
				loadAsset(a, null);
			}
			else if(a instanceof ConversationBubbleAsset)
			{
				loadAsset(a, imageBaseDir);
			}
			else if(a instanceof ThoughtBubbleAsset)
			{
				loadAsset(a, imageBaseDir);
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
		
		addNotify();
		revalidate();
		repaint();
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
			final JLabel label;
			if(a instanceof ButtonAsset || a instanceof InformationBoxAsset)
			{
				label = new JLabel(a.getName());
				label.setFont(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setForeground(Color.BLACK);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
				label.setBackground(Color.YELLOW);
				label.setOpaque(true);
				
				label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int)a.getWidth(), (int)a.getHeight());
				add(label);
			}
			else {
				BufferedImage image = ImageIO.read(new File(baseDir + a.getDisplayImage()));
				int width = image.getWidth();
				final double desiredWidth = a.getWidth();
				double scaleFactor = desiredWidth / width;
				BufferedImage scaledImage = getScaledImage(image, scaleFactor);
				label = new JLabel(new ImageIcon(scaledImage));
				label.setLayout(new BorderLayout());
				label.setBounds((int)a.getLocX(), (int)a.getLocY(), scaledImage.getWidth(), scaledImage.getHeight());
				add(label);
				//if the asset is an conversation or thought bubble asset, add the text.
				if(a instanceof ConversationBubbleAsset || a instanceof ThoughtBubbleAsset)
				{
					int paddingTop = label.getHeight()/10;
					int paddingLeft = label.getWidth()/16;
					final JLabel textLabel = new JLabel("<html><p style=\"padding-left:" + paddingLeft + ";padding-top:" + paddingTop + "\">" + a.getName() + "</p></html>");
					textLabel.setBounds((int)a.getLocX(), (int)a.getLocY(), scaledImage.getWidth(), scaledImage.getHeight());
					textLabel.setFont(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
					textLabel.setForeground(Color.BLACK);
					textLabel.setHorizontalAlignment(JLabel.CENTER);
					textLabel.setVerticalAlignment(JLabel.TOP);
					label.add(textLabel);
				}
			}
			
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
		        	Border highlights = BorderFactory.createLineBorder(Color.MAGENTA, 5, true);
		        		label.setBorder(highlights);
		        }
		        public void mouseEntered(MouseEvent e) { }
		        public void mouseExited(MouseEvent e) { }
		        public void mousePressed(MouseEvent e) { prevClickPoint = e.getPoint(); }
		        public void mouseReleased(MouseEvent e) { 
		        	if(resize){
		        		parentWizard.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "resizeAsset"));
		        	}
		        }
		    });
			label.addMouseMotionListener(new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
		        	Point p = e.getPoint();
		        	int deltaX = p.x - prevClickPoint.x;
		        	int deltaY = p.y - prevClickPoint.y;
		        	int invDeltaX = -deltaX;
		        	int invDeltaY = -deltaY;
		        	String imgPath;
		        	if(a instanceof CharacterAsset)
		        	{
		        		imgPath = charBaseDir + a.getDisplayImage();
		        	}
		        	else
		        	{
		        		imgPath = imageBaseDir + a.getDisplayImage();
		        	}
		        	
		        	if(label.getRootPane().getCursor().getType() == Cursor.NW_RESIZE_CURSOR)
		        	{
		        		if((a.getWidth() <= 30 || label.getHeight() <= 30) && (p.x > prevClickPoint.x || p.y > prevClickPoint.y))
		        		{
		        			//do nothing, don't let the image disappear
		        		}
		        		else
		        		{
			        		a.setWidth(a.getWidth() + invDeltaX);
			        		a.setHeight(a.getHeight() + invDeltaY);
			        		a.setLocX(a.getLocX() + deltaX);
			        		a.setLocY(a.getLocY() + deltaY);
			        		a.setLocX2(a.getLocX() + a.getWidth());
			        		a.setLocY2(a.getLocY() + a.getHeight());
			        		if(a instanceof ButtonAsset || a instanceof InformationBoxAsset)
			        		{
			        			label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int)a.getWidth(), (int)a.getHeight());
			        		}
			        		else {
				        		label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int) a.getWidth(), label.getHeight()+invDeltaY);
				        		BufferedImage image;
								try {
									image = ImageIO.read(new File(imgPath));
					        		int width = image.getWidth();
					    			double desiredWidth = a.getWidth();
					    			double scaleFactor = desiredWidth / width;
					    			BufferedImage scaledImage = getScaledImage(image, scaleFactor);
					    			label.setIcon(new ImageIcon(scaledImage));
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
			        		resize = true;
		        		}
		        	}
					else if(label.getRootPane().getCursor().getType() == Cursor.SW_RESIZE_CURSOR)
					{
						if((a.getWidth() <= 30 || label.getHeight() <= 30) && (p.x > prevClickPoint.x || p.y < prevClickPoint.y))
		        		{
		        			//do nothing, don't let the image disappear
		        		}
						else
						{
							a.setWidth(a.getWidth() + invDeltaX);
			        		a.setHeight(a.getHeight() + deltaY);
			        		a.setLocX(a.getLocX() + deltaX);
			        		prevClickPoint.y = p.y;
			        		a.setLocX2(a.getLocX() + a.getWidth());
			        		a.setLocY2(a.getLocY() + a.getHeight());
			        		label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int) a.getWidth(), label.getHeight()+deltaY);
			        		BufferedImage image;
							try {
								image = ImageIO.read(new File(imgPath));
				        		int width = image.getWidth();
				    			double desiredWidth = a.getWidth();
				    			double scaleFactor = desiredWidth / width;
				    			BufferedImage scaledImage = getScaledImage(image, scaleFactor);
				    			label.setIcon(new ImageIcon(scaledImage));
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
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			        		resize = true;
						}
					}
					else if(label.getRootPane().getCursor().getType() == Cursor.NE_RESIZE_CURSOR)
					{
						if((a.getWidth() <= 30 || label.getHeight() <= 30) && (p.x < prevClickPoint.x || p.y > prevClickPoint.y))
		        		{
		        			//do nothing, don't let the image disappear
		        		}
						else
						{
							a.setWidth(a.getWidth() + deltaX);
			        		a.setHeight(a.getHeight() + invDeltaY);
			        		prevClickPoint.x = p.x;
			        		a.setLocY(a.getLocY() + deltaY);
			        		a.setLocX2(a.getLocX() + a.getWidth());
			        		a.setLocY2(a.getLocY() + a.getHeight());
			        		label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int) a.getWidth(), label.getHeight()+invDeltaY);
			        		BufferedImage image;
							try {
								image = ImageIO.read(new File(imgPath));
				        		int width = image.getWidth();
				    			double desiredWidth = a.getWidth();
				    			double scaleFactor = desiredWidth / width;
				    			BufferedImage scaledImage = getScaledImage(image, scaleFactor);
				    			label.setIcon(new ImageIcon(scaledImage));
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
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			        		resize = true;
						}
					}
					else if(label.getRootPane().getCursor().getType() == Cursor.SE_RESIZE_CURSOR)
					{
						if((a.getWidth() <= 30 || label.getHeight() <= 30) && (p.x < prevClickPoint.x || p.y < prevClickPoint.y))
		        		{
		        			//do nothing, don't let the image disappear
		        		}
						else
						{
							a.setWidth(a.getWidth() + deltaX);
			        		a.setHeight(a.getHeight() + deltaY);
			        		prevClickPoint.x = p.x;
			        		prevClickPoint.y = p.y;
			        		a.setLocX2(a.getLocX() + a.getWidth());
			        		a.setLocY2(a.getLocY() + a.getHeight());
			        		label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int) a.getWidth(), label.getHeight()+deltaY);
			        		BufferedImage image;
							try {
								image = ImageIO.read(new File(imgPath));
				        		int width = image.getWidth();
				    			double desiredWidth = a.getWidth();
				    			double scaleFactor = desiredWidth / width;
				    			BufferedImage scaledImage = getScaledImage(image, scaleFactor);
				    			label.setIcon(new ImageIcon(scaledImage));
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
							} catch (IOException e1) {
								e1.printStackTrace();
							}
			        		//parentWizard.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "resizeAsset"));
			        		resize = true;
						}
					}
					else
					{
			        	int newX = label.getX() + deltaX;
			        	int newY = label.getY() + deltaY;
			        	label.setLocation(newX, newY);
						a.setLocX(newX);
						a.setLocY(newY);
						resize = false;
					}
					
					//all - set highlighting
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
