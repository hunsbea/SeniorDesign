import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "settings")
public class ResumeSettingsModel {

	@XmlElement
	public int colorR;
	@XmlElement
	public int colorG;
	@XmlElement
	public int colorB;
	
	@XmlElement
	public String fontName;
	@XmlElement
	public int fontStyle;
	@XmlElement
	public int fontSize;
}
