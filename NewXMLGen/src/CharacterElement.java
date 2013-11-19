import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CharacterElement extends GameElement
{
	@XmlElement
	public int characterId;
}
