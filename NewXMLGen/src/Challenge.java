import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Challenge 
{
	@XmlElement
	public String name;
	@XmlElementWrapper(name = "hints")
	@XmlElement(name = "hint")
	public List<Hint> hints;
}
