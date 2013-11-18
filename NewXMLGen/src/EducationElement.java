import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EducationElement extends GameElement
{
	public enum Type { BLACKBOARD, WHITEBOARD, COMPUTER_DISPLAY, EASEL, CLICKER, PROJECTOR } 
	
	@XmlElement
	public Type type;
}
