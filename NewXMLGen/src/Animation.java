import java.awt.Point;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Animation 
{
	public enum Movement { WALK, GLIDE, SIT, STAND, TALK, HAND_WAVE, HAND_SHAKE, DANCE, NONE }
	public enum Timing { SLOW, MEDIUM, FAST }
	public enum Path { STRAIGHT }
	
	@XmlElement
	public Movement movement;
	@XmlElement
	public Timing timing;
	@XmlElement
	public Path path;
	@XmlElement
	public boolean loop;
	@XmlElement
	public int moveToX;
	@XmlElement
	public int moveToY;
}
