import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Scene 
{
	@XmlElement
	public String purpose;
	@XmlElement
	public String learningObjective;
	@XmlElementWrapper(name = "screens")
	@XmlElement(name = "screen")
	public List<Screen> screens;
	@XmlElement
	public String backgroundImagePath;
	@XmlElement
	public String backgroundMusicPath;
}
