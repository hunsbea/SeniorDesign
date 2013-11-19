import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game 
{
	@XmlElementWrapper(name = "acts")
	@XmlElement(name = "act")
	public List<Act> acts;
	@XmlElement
	public PlayerCharacter player;
	@XmlElementWrapper(name = "nonPlayers")
	@XmlElement(name = "nonPlayer")
	public List<NonPlayerCharacter> nonPlayers;
}
