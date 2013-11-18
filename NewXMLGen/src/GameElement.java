import java.awt.Dimension;
import java.awt.Point;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GameElement 
{
	@XmlElement
	public Animation animation;
	@XmlElement
	public Sound sound;
	@XmlElement
	public String name;
	@XmlElement
	public Dimension size;
	@XmlElement
	public Point location;
}
