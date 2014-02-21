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
import javax.swing.JButton;
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
	private ArrayList<JLabel> assetLabels;
	private String charBaseDir = "Office, Classroom\\Characters\\";
	private String imageBaseDir = "Office, Classroom\\";
	private Point prevClickPoint;
	private ScenePanel that = this;
	private Asset targetedAsset = null;
	private InputWizard parentWizard;
	private boolean resize = false;
	
	public ScenePanel(InputWizard parent)
	{
		clear();
		parentWizard = parent;
		addMouseListener(new MouseListener() {
		        public void mouseClicked(MouseEvent e) { 
		        	for (JLabel label : assetLabels){
		        		if(label.getIcon()==null && !(label instanceof ConversationBubble || label instanceof ThoughtBubble))
		        		{
		        			label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		        		}
		        		else
		        		{
		        			label.setBorder(null);
		        		}
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
		assetLabels = new ArrayList<JLabel>();
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
	
	public void loadAssets(List<Asset> as, boolean readOnly)
	{
		clear();
		System.out.println("calling clear load assets\n");
		
		for(Asset a : as)
		{
			if(a instanceof CharacterAsset)
			{
				loadAsset(a, charBaseDir, readOnly);
				
			}
			else if(a instanceof ImageAsset)
			{
				loadAsset(a, imageBaseDir, readOnly);
			}
			else if(a instanceof InformationBoxAsset)
			{
				loadAsset(a, null, readOnly);
			}
			else if(a instanceof ConversationBubbleAsset)
			{
				loadAsset(a, imageBaseDir, readOnly);
			}
			else if(a instanceof ThoughtBubbleAsset)
			{
				loadAsset(a, imageBaseDir, readOnly);
			}
			else if (a instanceof ButtonAsset){
				loadAsset(a, imageBaseDir, readOnly);
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
	
	public void loadAsset(final Asset a, String baseDir, boolean readOnly)
	{
		try 
		{
			final JLabel label;
			if(a instanceof ButtonAsset || a instanceof InformationBoxAsset)
			{//asset is drawn yellow with a black border
				label = new JLabel("<html><p style=\"text-align:center\">" + a.getName() + "</p></html>");
				label.setFont(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setForeground(Color.BLACK);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
				label.setBackground(Color.YELLOW);
				label.setOpaque(true);
				
				if(a.getHint() != null)
				{
					label.setToolTipText(a.getHint().getText());
				}
				
				label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int)a.getWidth(), (int)a.getHeight());
				add(label);
			}
			else if(a instanceof ConversationBubbleAsset)
			{
				label = new ConversationBubble(a.getName());
				((ConversationBubble)label).setFont(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
				((ConversationBubble)label).setBounds((int)a.getLocX(), (int)a.getLocY(), (int)a.getWidth(), (int)a.getHeight());
				((ConversationBubble)label).setPointDirection(ConversationBubble.PointDirection.LEFT_DOWN);
				add(label);
			}
			else if(a instanceof ThoughtBubbleAsset)
			{
				label = new ThoughtBubble(a.getName());
				((ThoughtBubble)label).setFont(new Font(a.getFontFamily(), Font.BOLD, a.getFontSize()));
				((ThoughtBubble)label).setBounds((int)a.getLocX(), (int)a.getLocY(), (int)a.getWidth(), (int)a.getHeight());
				((ThoughtBubble)label).setPointDirection(ThoughtBubble.PointDirection.LEFT_DOWN);
				add(label);
			}
			else
			{//asset has an image
				BufferedImage image = ImageIO.read(new File(baseDir + a.getDisplayImage()));
				int width = image.getWidth();
				final double desiredWidth = a.getWidth();
				double scaleFactor = desiredWidth / width;
				BufferedImage scaledImage = getScaledImage(image, scaleFactor);
				label = new JLabel(new ImageIcon(scaledImage));
				label.setLayout(new BorderLayout());
				label.setBounds((int)a.getLocX(), (int)a.getLocY(), scaledImage.getWidth(), scaledImage.getHeight());
				add(label);
			}
			
			if(!readOnly){
				label.addMouseListener(new MouseListener() {
			        public void mouseClicked(MouseEvent e) { 
			        	//if right-click
			        	if((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){
			        		System.out.println("right-clicked");
			        		JPopupMenu pMenu = new JPopupMenu();
			        		JMenuItem menuItem = new JMenuItem("Delete");
			        		menuItem.setActionCommand("deleteElement");
			        		menuItem.addActionListener(parentWizard);
			        		
			        		//Preview Sound in right click menu
			        		JMenuItem menuItem2 = new JMenuItem("Preview Sound");
			        		menuItem2.setActionCommand("previewSound");
			        		menuItem2.addActionListener(parentWizard);
			        		if(a.getSoundEffect()==null){
			        			menuItem2.setEnabled(false);
			        		}
			        		
			        		pMenu.add(menuItem);
			        		pMenu.add(menuItem2);
			        		
			        		targetedAsset = a;
			        		that.add(pMenu);
			        		pMenu.show(e.getComponent(), e.getX(), e.getY());
						}
						
			        	for (JLabel label : assetLabels){
			        		if(label.getIcon()==null && !(label instanceof ConversationBubble || label instanceof ThoughtBubble))
			        		{
			        			label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
			        		}
			        		else
			        		{
			        			label.setBorder(null);
			        		}
			        	}
			        	Border highlights = BorderFactory.createLineBorder(Color.MAGENTA, 5);
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
				        		
				        		if(a instanceof ButtonAsset || a instanceof InformationBoxAsset || a instanceof ConversationBubbleAsset || label instanceof ThoughtBubble)
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
				        		
				        		if(a instanceof ButtonAsset || a instanceof InformationBoxAsset || a instanceof ConversationBubbleAsset || label instanceof ThoughtBubble)
				        		{
				        			label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int)a.getWidth(), (int)a.getHeight());
				        		}
				        		else {
					        		label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int) a.getWidth(), label.getHeight()+deltaY);
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
				        		
				        		if(a instanceof ButtonAsset || a instanceof InformationBoxAsset || a instanceof ConversationBubbleAsset || label instanceof ThoughtBubble)
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
				        		
				        		if(a instanceof ButtonAsset || a instanceof InformationBoxAsset || a instanceof ConversationBubbleAsset || label instanceof ThoughtBubble)
				        		{
				        			label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int)a.getWidth(), (int)a.getHeight());
				        		}
				        		else {
					        		label.setBounds((int)a.getLocX(), (int)a.getLocY(), (int) a.getWidth(), label.getHeight()+deltaY);
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
						for (JLabel label : assetLabels){
							if(label.getIcon()==null && !(label instanceof ConversationBubble || label instanceof ThoughtBubble))
			        		{
			        			label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
			        		}
			        		else
			        		{
			        			label.setBorder(null);
			        		}
			        	}
			        	label.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 5));
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
			}

			assetLabels.add(label);
			repaint();
		} 
		catch (IOException ex) 
		{
			System.out.println(a.getDisplayImage() + " is missing from repository, cannot load");
		}
	}
	
	public void backgroundMusicPreview(boolean ifMusic) {
		System.out.println(ifMusic);
		
		JButton backgroundMusic;
		JButton backgroundMusicStop;

        backgroundMusic = new JButton("Preview");
        backgroundMusic.addActionListener(parentWizard);
        backgroundMusic.setEnabled(ifMusic);
        backgroundMusic.setActionCommand("backgroundMusicPreviewPlay");
        backgroundMusic.setBounds(5, 5, 100, 30);
        BufferedImage img;
		try {
			img = getScaledImage(ImageIO.read(new File("Office, Classroom/Asst Bitstrips and Composite images/play.png")), 0.5);
			backgroundMusic.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			e.printStackTrace();
		}
        backgroundMusicStop = new JButton("Stop");
        backgroundMusicStop.addActionListener(parentWizard);
        backgroundMusicStop.setEnabled(ifMusic);
        backgroundMusicStop.setActionCommand("backgroundMusicPreviewStop");
        backgroundMusicStop.setBounds(105, 5, 100, 30);
        BufferedImage img2;
		try {
			img2 = getScaledImage(ImageIO.read(new File("Office, Classroom/Asst Bitstrips and Composite images/stop.png")), 1.0);
			backgroundMusicStop.setIcon(new ImageIcon(img2));
		} catch (IOException e) {
			e.printStackTrace();
		}

        add(backgroundMusic);
        add(backgroundMusicStop);
        
	}
		
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        if(background != null)
        {
        	g.drawImage(background, 0, 0, null);
        }
    }
    
	public Asset getTargetedAsset()
	{
		return targetedAsset;
	}

}
