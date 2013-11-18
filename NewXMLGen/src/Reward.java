import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reward 
{
	public enum Trophy { UNDEFINED }
	public enum Certificate { UNDEFINED }
	
	@XmlElement
	public Trophy trophy;
	@XmlElement
	public Certificate certificate;
	@XmlElement
	public boolean promotion;
	@XmlElement
	public boolean demotion;
}
