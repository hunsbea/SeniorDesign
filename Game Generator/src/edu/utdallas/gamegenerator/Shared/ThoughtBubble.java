//Source baseline: https://code.google.com/p/jmingle/source/browse/trunk/src/org/oep/widgets/SpeechBubble.java
package edu.utdallas.gamegenerator.Shared;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JLabel;

public class ThoughtBubble extends JLabel {
	private int ARROW_HEIGHT = 7;
    private int ARROW_WIDTH = 4;
    private int PADDING = 8;
    private int TEXT_TOP = 8;
    private int TEXT_LEFT = 8;
    private int widthC;
    private int heightC;
    private String textC;
    private Font fontC = new Font("Comic Sans MS", Font.BOLD, 15);
    public static enum PointDirection{LEFT_DOWN,CENTER_DOWN,RIGHT_DOWN};
    private PointDirection directionC = PointDirection.LEFT_DOWN;
    
    public ThoughtBubble()
    {
    	super();
    	textC = "";
    }
    
    public ThoughtBubble(String text)
    {
    	super();
    	textC = text;
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
    	widthC = width;
    	heightC = height;
    	ARROW_HEIGHT = widthC/8;
    	ARROW_WIDTH = widthC/8;
    	PADDING = widthC/14;
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
    	JLabel innerLabel = new JLabel("<html><p>" + text + "</p></html>");
    	innerLabel.setBounds(TEXT_LEFT, TEXT_TOP, widthC-TEXT_LEFT*2, heightC-TEXT_TOP*2-ARROW_HEIGHT);
    	innerLabel.setFont(fontC);
    	innerLabel.setForeground(Color.BLACK);
    	innerLabel.setBackground(Color.WHITE);
    	innerLabel.setOpaque(true);
    	innerLabel.setHorizontalAlignment(JLabel.CENTER);
    	innerLabel.setVerticalAlignment(JLabel.TOP);
		add(innerLabel);
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
//        g.setColor(Color.BLACK);
//        g.drawRoundRect(x - width/2 + PADDING/2, y - height - ARROW_HEIGHT + PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        
        int per = perimeterRoundedRect(width-PADDING,height-PADDING);
        Point[] points = getRoundedRectPoints(x - width/2 + PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, (height-PADDING)/2, 30);
//        for(int i = 0; i<points.length;i++) {
//        	if(points[i] != null){
//        		drawPlus(g, points[i].x, points[i].y);
//        	}
//        }
        for(int i=0; i<points.length-1; i++) {
        	paintArc2PointsRadius(points[i].x, points[i].y, points[i+1].x, points[i+1].y, per/30+2, g);
        }
        
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
//        g.setColor(Color.BLACK);
//        g.drawRoundRect(x+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);

        int per = perimeterRoundedRect(width-PADDING,height-PADDING);
        Point[] points = getRoundedRectPoints(x+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, (height-PADDING)/2, 30);
//        for(int i = 0; i<points.length;i++) {
//        	if(points[i] != null){
//        		drawPlus(g, points[i].x, points[i].y);
//        	}
//        }
        for(int i=0; i<points.length-1; i++) {
        	paintArc2PointsRadius(points[i].x, points[i].y, points[i+1].x, points[i+1].y, per/30+2, g);
        }
        
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
//        g.setColor(Color.BLACK);
//        g.drawRoundRect(x-width+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, height-PADDING, height-PADDING);
        
        int per = perimeterRoundedRect(width-PADDING,height-PADDING);
        Point[] points = getRoundedRectPoints(x-width+PADDING/2, y - height - ARROW_HEIGHT+PADDING/2, width-PADDING, height-PADDING, (height-PADDING)/2, 30);
//        for(int i = 0; i<points.length;i++) {
//        	if(points[i] != null){
//        		drawPlus(g, points[i].x, points[i].y);
//        	}
//        }
        for(int i=0; i<points.length-1; i++) {
        	paintArc2PointsRadius(points[i].x, points[i].y, points[i+1].x, points[i+1].y, per/30+2, g);
        }
        
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
    
    private void drawPlus(Graphics g, int x, int y)
    {
    	g.drawLine(x-10, y, x+10, y);
    	g.drawLine(x, y-10, x, y+10);
    }
    
    //assumes the radius of the end arcs is height/2
    private Point[] getRoundedRectPoints(int x, int y, int width, int height, int radius, int numPoints)
    {
    	Point[] points = new Point[numPoints+1];
    	int perimeter = perimeterRoundedRect(width,height);
    	
    	//initial point
    	points[0] = new Point();
    	points[0].x = x;
    	points[0].y = y+height/2;
    	points[numPoints] = new Point();
    	points[numPoints].x = points[0].x;
    	points[numPoints].y = points[0].y;
    	
    	int curX = x + (int)((double)radius*(1-Math.cos(getAngleForArcLength((perimeter/30 + 1),radius))));
    	int i = 1;
    	
    	while(curX < x+radius){
    		points[i] = new Point();
    		points[i].x = curX;
    		points[i].y = y+height/2 - (int)((double)radius*Math.sin(getAngleForArcLength((i*perimeter/30 + 1),radius)));
    		points[numPoints-i] = new Point();
    		points[numPoints-i].x = curX;
    		points[numPoints-i].y = y+height/2 + (int)((double)radius*Math.sin(getAngleForArcLength((i*perimeter/30 + 1),radius)));
    		
    		i++;
    		curX = x + (int)((double)radius*(1-Math.cos(getAngleForArcLength((i*perimeter/30 + 1),radius))));
    	}
    	while(curX < x+width-radius){
    		points[i] = new Point();
    		points[i].x = curX;
    		points[i].y = y;
    		points[numPoints-i] = new Point();
    		points[numPoints-i].x = curX;
    		points[numPoints-i].y = y+height;
    		
    		i++;
    		curX = curX + (perimeter/30+1);
    	}
    	if(numPoints%2==0){
	    	int j = numPoints/2;
	    	points[j] = new Point();
	    	points[j].x = x+width;
	    	points[j].y = y+height/2;
	    	j--;
	    	curX = x + width - (int)((double)radius*(1-Math.cos(getAngleForArcLength(((15-j)*perimeter/30 + 1),radius))));
	    	while(j>=i){
	    		points[j] = new Point();
	    		points[j].x = curX;
	    		points[j].y = y+height/2 - (int)((double)radius*Math.sin(getAngleForArcLength(((15-j)*perimeter/30 + 1),radius)));
	    		points[numPoints-j] = new Point();
	    		points[numPoints-j].x = curX;
	    		points[numPoints-j].y = y+height/2 + (int)((double)radius*Math.sin(getAngleForArcLength(((15-j)*perimeter/30 + 1),radius)));
	    		j--;
	    		curX = x + width - (int)((double)radius*(1-Math.cos(getAngleForArcLength(((15-j)*perimeter/30 + 1),radius))));
	    	}
    	}
    	
    	return points;
    }
    
    //assumes the radius of the end arcs is height/2
    private int perimeterRoundedRect(int width, int height)
    {
    	int perimeter = 0;
    	perimeter = (width - height)*2;
    	perimeter += height*Math.PI;
    	return perimeter;
    }
    
    private double getAngleForArcLength(int length, int radius){
//    	double circ = 2*Math.PI*radius;
//    	double circPercent = ((double)length)/circ;
//    	double angle = 2*Math.PI*circPercent;
    	//the above simplifies to:
    	double angle = (double)length/(double)radius;
    	return angle;
    }
    
    //applies only for the rounded rectangle used in this class
    private void paintArc2PointsRadius(int x1, int y1, int x2, int y2, int diameter, Graphics g) {
    	int xm, ym, xo, yo;
    	int dist;
    	double angle1, angle2;
    	int deg1, deg2;
    	Color origColor = g.getColor();
    	//points along the top of the shape
    	if(x1 < x2) {
    		//find midpoint between points
    		dist = (int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    		xm = (x1+x2)/2;
    		ym = (y1+y2)/2;
    		
    		//shift toward center 1-2 px
    		//find arc origin x,y and angles
    		xo=xm-dist/2;
    		yo=ym-dist/2;
    		//need to fix this
//    		angle1 = Math.atan2(1*(y1-ym) - 0*(x1-xm), 1*(x1-xm) + 0*(y1-ym));
    		angle1 = Math.atan2(x1-xm, y1-ym);
    		deg1 = (int)Math.toDegrees(angle1)+90;
//    		angle2 = Math.atan2(1*(y2-ym) - 0*(x2-xm), 1*(x2-xm) + 0*(y2-ym));
    		angle2 = Math.atan2(x2-xm, y2-ym);
    		deg2 = (int)Math.toDegrees(angle2)+90;
    		//draw arc
//    		g.setColor(Color.WHITE);
//    		g.fillArc(xo, yo, dist-1, dist-1, 0, 360);
//    		g.setColor(Color.BLACK);
//    		g.drawArc(xo, yo, dist, dist, deg1, deg2-deg1);
    		g.setColor(Color.WHITE);
    		g.fillArc(xo, yo, diameter, diameter, 0, 360);
    		g.setColor(Color.BLACK);
    		g.drawArc(xo, yo, diameter, diameter, deg1, deg2-deg1);
    	}
    	//points along the bottom of the image
    	else {
    		//find midpoint between points
    		dist = (int)Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    		xm = (x1+x2)/2;
    		ym = (y1+y2)/2;
    		
    		//shift toward center 1-2 px
    		//find arc origin x,y and angles
    		xo=xm-dist/2;
    		yo=ym-dist/2;
    		//need to fix this
//    		angle1 = Math.atan2(1*(y1-ym) - 0*(x1-xm), 1*(x1-xm) + 0*(y1-ym));
    		angle1 = Math.atan2(x1-xm, y1-ym);
    		deg1 = (int)Math.toDegrees(angle1)+90;
//    		angle2 = Math.atan2(1*(y2-ym) - 0*(x2-xm), 1*(x2-xm) + 0*(y2-ym));
    		angle2 = Math.atan2(x2-xm, y2-ym);
    		deg2 = (int)Math.toDegrees(angle2)+90;
    		//draw arc
//    		g.setColor(Color.WHITE);
//    		g.fillArc(xo, yo, dist-1, dist-1, 0, 360);
//    		g.setColor(Color.BLACK);
//    		g.drawArc(xo, yo, dist, dist, deg1, deg1-deg2);
    		g.setColor(Color.WHITE);
    		g.fillArc(xo, yo, diameter, diameter, 0, 360);
    		g.setColor(Color.BLACK);
    		g.drawArc(xo, yo, diameter, diameter, deg1, deg1-deg2);
    	}
    	g.setColor(origColor);
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