import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
	@XmlElementWrapper(name = "scenes")
	@XmlElement(name = "scene")
	public List<Scene> scenes;
}
