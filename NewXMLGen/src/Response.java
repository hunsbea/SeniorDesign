import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response 
{
	public enum Evaluation { UNDEFINED }
	
	@XmlElement
	public String answer;
	@XmlElement
	public Evaluation evaluation;
	@XmlElement
	public String feedback;
}
