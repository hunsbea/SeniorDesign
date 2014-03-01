package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import edu.utdallas.gamegenerator.Shared.ConversationBubble.PointDirection;

/**
 * User: Jacob Dahleen
 * Date: 2/7/14
 * Time: 2:20 PM
 */
@XmlType(name = "ThoughtBubbleAsset")
public class ThoughtBubbleAsset extends Asset {
	private PointDirection point;
	
	public ThoughtBubbleAsset() {
    }

    public ThoughtBubbleAsset(Asset asset) {
        super(asset);
    }
    
    @XmlElement(name = "PointDirection")
	public PointDirection getPointDirection() 
	{
		return point;
	}
	public void setPointDirection(PointDirection pointDirection) 
	{
		this.point = pointDirection;
	}
}
