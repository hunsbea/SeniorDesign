import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Act 
{
	@XmlElement
	public String purpose;
	@XmlElement
	public String learningObjective;
	@XmlElement
	public int currentScene;
	@XmlElement
	public List<Scene> scenes;
}
