import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Character 
{
	public enum MetaModelType { PROTAGONIST, ANTAGONIST, INTERLOCUTOR, DIRECTOR, CONSTRUCTOR, PROP }
	public enum Behavior { VERY_POSITIVE, POSITIVE, NEUTRAL, NEGATIVE, VERY_NEGATIVE }
	
	@XmlElement
	public String name;
	@XmlElement
	public MetaModelType modelType;
	@XmlElement
	public Profile profile;
	@XmlElementWrapper(name = "rewards")
	@XmlElement(name = "reward")
	public List<Reward> rewards;
	@XmlElement
	public Behavior behavior;
	@XmlElement
	public int characterId;
}
