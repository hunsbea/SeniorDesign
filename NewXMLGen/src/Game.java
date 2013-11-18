import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game 
{
	@XmlElement
	public List<Act> acts;
	@XmlElement
	public PlayerCharacter player;
	@XmlElement
	public List<NonPlayerCharacter> nonPlayers;
}
