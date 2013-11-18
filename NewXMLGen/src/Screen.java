import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Screen 
{
	@XmlElement
	public List<GameElement> elements;
	@XmlElement
	public Challenge challenge;
	@XmlElement
	public boolean hasChallenge;
}
