import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sound 
{
	public enum Type { MUSIC, SOUND_EFFECT, VOICE_OVER }
	
	@XmlElement
	public Type type;
	@XmlElement
	public String audioFilePath;
}
