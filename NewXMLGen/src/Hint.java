import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hint 
{
	public Hint(String hint)
	{
		text = hint;
	}
	
	public Hint() { }
	
	@XmlElement
	public String text;
}
