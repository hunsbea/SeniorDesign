import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GameElement 
{
	@XmlElement
	public Animation animation;
	@XmlElement
	public Sound sound;
	@XmlElement
	public String name;
	@XmlElement
	public int x;
	@XmlElement
	public int y;
	@XmlElement
	public int width;
	@XmlElement
	public int height;
	@XmlElement
	public String imagePath;
}
