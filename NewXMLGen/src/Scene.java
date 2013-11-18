import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Scene 
{
	@XmlElement
	public String purpose;
	@XmlElement
	public String learningObjective;
	@XmlElement
	public List<Screen> screens;
	@XmlElement
	public String backgroundImagePath;
	@XmlElement
	public String backgroundMusicPath;
}
