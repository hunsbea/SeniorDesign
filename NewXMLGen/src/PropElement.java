import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PropElement extends GameElement
{
	public enum Type { COFFEE_CUP, OFFICE_DESK, OFFICE_CHAIR, OFFICE_CUBICLE, TABLE, SPEAKER_PODIUM, CLOUDS, PLANT }
	
	@XmlElement
	public Type type;
}
