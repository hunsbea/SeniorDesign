import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Question 
{
	@XmlElementWrapper(name = "responses")
	@XmlElement(name = "response")
	public List<Response> responses;
	@XmlElement
	public String questionText;
}
