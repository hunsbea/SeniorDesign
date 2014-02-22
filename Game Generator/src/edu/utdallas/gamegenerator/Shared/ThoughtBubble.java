//Source baseline: https://code.google.com/p/jmingle/source/browse/trunk/src/org/oep/widgets/SpeechBubble.java
package edu.utdallas.gamegenerator.Shared;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class ThoughtBubble extends JLabel {
	private int ARROW_HEIGHT = 7;
    private int ARROW_WIDTH = 4;
    private int PADDING = 8;
    private int TEXT_TOP = 8;
    private int TEXT_LEFT = 8;
    private int xPos;
    private int yPos;
    private int widthC;
    private int heightC;
    private String textC;
    private Font fontC = new Font("Comic Sans MS", Font.BOLD, 15);
    public static enum PointDirection{LEFT_DOWN,CENTER_DOWN,RIGHT_DOWN};
    private PointDirection directionC = PointDirection.LEFT_DOWN;
    JLabel innerLabel = new JLabel();
    
    public ThoughtBubble()
    {
    	this("");
    }
    
    public ThoughtBubble(String text)
    {
    	super();
    	textC = text;
    	innerLabel.setFont(fontC);
    	innerLabel.setForeground(Color.BLACK);
    	innerLabel.setBackground(Color.WHITE);
    	innerLabel.setOpaque(true);
    	innerLabel.setHorizontalAlignment(JLabel.CENTER);
    	innerLabel.setVerticalAlignment(JLabel.TOP);
		add(innerLabel);
    }
    
    public void setPointDirection(PointDirection p)
    {
    	directionC = p;
    }
    
    /**
     * Set the bounds of the label capturing the necessary variables for drawing the bubble
     * 
     */
    public void setBounds(int x, int y, int width, int height)
    {
    	xPos = x;
    	yPos = y;
    	widthC = width;
    	heightC = height;
    	ARROW_HEIGHT = widthC/8;
    	ARROW_WIDTH = widthC/8;
    	PADDING = widthC/20;
    	TEXT_LEFT = PADDING/2 + (heightC-ARROW_HEIGHT-PADDING)/4;
    	TEXT_TOP = PADDING/2 + (heightC-ARROW_HEIGHT-PADDING)/8;
    	super.setBounds(x, y, width, height);
    }
    
    public void setFont(Font font)
    {
    	fontC = font;
    	super.setFont(font);
    }

    /**
     * Draw a string in a speech bubble
     * @param text, the string to draw
     */
    private void paintString(String text) {
    	innerLabel.setText("<html><p style=\"text-align:center\">" + text + "</p></html>");
    	innerLabel.setBounds(TEXT_LEFT, TEXT_TOP, widthC-TEXT_LEFT*2, heightC-TEXT_TOP*2-ARROW_HEIGHT);
    }
    
    /**
     * Draw an empty speech bubble with the point in the center
     * @param g, the graphics object to paint to
     * @param x, the x position of the object (relative to the pointy part)
     * @param y, the y position of the object (relative to the pointy part)
     * @param height, the height of the body part
     * @param width, the width of the body part
     */
    private void drawBubblePointCenter(Graphics g, int x, int y, int width, int height) {
        // Save the graphics object color and font so that we may restore it later
        Color origColor = g.getColor();
        Font origFont = g.getFont();
        int numTriangleCoords = 3;
        int[] xcoords = new int[numTriangleCoords];
        int[] ycoords = new int[numTriangleCoords];
        xcoords[0] = x - ARROW_WIDTH/10;
        ycoords[0] = y - ARROW_HEIGHT/5 - 1;
        xcoords[1] = x - ARROW_WIDTH/6;
        ycoords[1] = y - ARROW_HEIGHT/5 - ARROW_HEIGHT/3 - 1;
        xcoords[2] = x - ARROW_WIDTH/4;
        ycoords[2] = y - ARROW_HEIGHT/5 - ARROW_HEIGHT/3 - ARROW_HEIGHT/2 - 1;

        // Draw the base shape -- the rectangle the image will fit into as well as its outline
        g.setColor(Color.WHITE);
        g.fillRoundRect(x - width/2 + PADDING/2, y - height - ARROW_HEIGHT + PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x - width/2 + PADDING/2, y - height - ARROW_HEIGHT + PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        
        //draw little circles indicating who is thinking
        g.setColor(Color.WHITE);
        g.fillArc(xcoords[0], ycoords[0], ARROW_WIDTH/5, ARROW_HEIGHT/5, 0, 360);
        g.fillArc(xcoords[1], ycoords[1], ARROW_WIDTH/3, ARROW_HEIGHT/3, 0, 360);
        g.fillArc(xcoords[2], ycoords[2], ARROW_WIDTH/2, ARROW_HEIGHT/2, 0, 360);
        
        //outline the circles
        g.setColor(Color.BLACK);
        g.drawArc(xcoords[0], ycoords[0], ARROW_WIDTH/5, ARROW_HEIGHT/5, 0, 360);
        g.drawArc(xcoords[1], ycoords[1], ARROW_WIDTH/3, ARROW_HEIGHT/3, 0, 360);
        g.drawArc(xcoords[2], ycoords[2], ARROW_WIDTH/2, ARROW_HEIGHT/2, 0, 360);
        
        // Restore the font and color
        g.setColor(origColor);
        g.setFont(origFont);
    }
    
    /**
     * Draw an empty speech bubble with the point on the left
     * @param g, the graphics object to paint to
     * @param x, the x position of the object (relative to the pointy part)
     * @param y, the y position of the object (relative to the pointy part)
     * @param height, the height of the body part
     * @param width, the width of the body part
     */
    private void drawBubblePointLeft(Graphics g, int x, int y, int width, int height) {
        // Save the graphics object color and font so that we may restore it later
        Color origColor = g.getColor();
        Font origFont = g.getFont();
        int numTriangleCoords = 3;
        int[] xcoords = new int[numTriangleCoords];
        int[] ycoords = new int[numTriangleCoords];
        xcoords[0] = x;
        ycoords[0] = y - ARROW_HEIGHT/5 - 1;
        xcoords[1] = x + ARROW_WIDTH/5;
        ycoords[1] = y - ARROW_HEIGHT/5 - ARROW_HEIGHT/3 - 1;
        xcoords[2] = x + ARROW_WIDTH/5 + ARROW_WIDTH/3;
        ycoords[2] = y - ARROW_HEIGHT/5 - ARROW_HEIGHT/3 - ARROW_HEIGHT/2 - 1;

        // Draw the base shape -- the rectangle the image will fit into as well as its outline
        g.setColor(Color.WHITE);
        g.fillRoundRect(x+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        
        //draw little circles indicating who is thinking
        g.setColor(Color.WHITE);
        g.fillArc(xcoords[0], ycoords[0], ARROW_WIDTH/5, ARROW_HEIGHT/5, 0, 360);
        g.fillArc(xcoords[1], ycoords[1], ARROW_WIDTH/3, ARROW_HEIGHT/3, 0, 360);
        g.fillArc(xcoords[2], ycoords[2], ARROW_WIDTH/2, ARROW_HEIGHT/2, 0, 360);
        
        //outline the circles
        g.setColor(Color.BLACK);
        g.drawArc(xcoords[0], ycoords[0], ARROW_WIDTH/5, ARROW_HEIGHT/5, 0, 360);
        g.drawArc(xcoords[1], ycoords[1], ARROW_WIDTH/3, ARROW_HEIGHT/3, 0, 360);
        g.drawArc(xcoords[2], ycoords[2], ARROW_WIDTH/2, ARROW_HEIGHT/2, 0, 360);
        
        // Restore the font and color
        g.setColor(origColor);
        g.setFont(origFont);
    }
    
    /**
     * Draw an empty speech bubble with the point on the right
     * @param g, the graphics object to paint to
     * @param x, the x position of the object (relative to the pointy part)
     * @param y, the y position of the object (relative to the pointy part)
     * @param height, the height of the body part
     * @param width, the width of the body part
     */
    private void drawBubblePointRight(Graphics g, int x, int y, int width, int height) {
        // Save the graphics object color and font so that we may restore it later
        Color origColor = g.getColor();
        Font origFont = g.getFont();
        int numTriangleCoords = 3;
        int[] xcoords = new int[numTriangleCoords];
        int[] ycoords = new int[numTriangleCoords];
        xcoords[0] = x - ARROW_WIDTH/5;
        ycoords[0] = y - ARROW_HEIGHT/5 - 1;
        xcoords[1] = x - ARROW_WIDTH/5 - ARROW_WIDTH/3;
        ycoords[1] = y - ARROW_HEIGHT/5 - ARROW_HEIGHT/3 - 1;
        xcoords[2] = x - ARROW_WIDTH/5 - ARROW_WIDTH/3 - ARROW_WIDTH/2;
        ycoords[2] = y - ARROW_HEIGHT/5 - ARROW_HEIGHT/3 - ARROW_HEIGHT/2 - 1;

        // Draw the base shape -- the rectangle the image will fit into as well as its outline
        g.setColor(Color.WHITE);
        g.fillRoundRect(x-width+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x-width+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        
        //draw little circles indicating who is thinking
        g.setColor(Color.WHITE);
        g.fillArc(xcoords[0], ycoords[0], ARROW_WIDTH/5, ARROW_HEIGHT/5, 0, 360);
        g.fillArc(xcoords[1], ycoords[1], ARROW_WIDTH/3, ARROW_HEIGHT/3, 0, 360);
        g.fillArc(xcoords[2], ycoords[2], ARROW_WIDTH/2, ARROW_HEIGHT/2, 0, 360);
        
        //outline the circles
        g.setColor(Color.BLACK);
        g.drawArc(xcoords[0], ycoords[0], ARROW_WIDTH/5, ARROW_HEIGHT/5, 0, 360);
        g.drawArc(xcoords[1], ycoords[1], ARROW_WIDTH/3, ARROW_HEIGHT/3, 0, 360);
        g.drawArc(xcoords[2], ycoords[2], ARROW_WIDTH/2, ARROW_HEIGHT/2, 0, 360);
        
        // Restore the font and color
        g.setColor(origColor);
        g.setFont(origFont);
    }
    
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	g.setColor(Color.BLACK);
    	switch(directionC) {
    		case LEFT_DOWN:
    			drawBubblePointLeft(g,0,heightC,widthC,heightC-ARROW_HEIGHT);
    			break;
    		case CENTER_DOWN:
    			drawBubblePointCenter(g,widthC/2,heightC,widthC,heightC-ARROW_HEIGHT);
    			break;
    		case RIGHT_DOWN:
    			drawBubblePointRight(g,widthC,heightC,widthC,heightC-ARROW_HEIGHT);
    			break;
    		default:
    			break;
    	}
    	paintString(textC);
    }
}
