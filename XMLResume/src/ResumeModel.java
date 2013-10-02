import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resume")
public class ResumeModel {
	
	@XmlAttribute
	public String name;
	
	@XmlElement
	public String photoPath;
	
	@XmlElement
	public String title;
	
	@XmlElement(name = "skill")
	public ArrayList<String> skills;
	
	@XmlElement
	public String yearsOfExp;
	
	@XmlElement
	public String communication;

	@XmlElement
	public String leadership;

	@XmlElement
	public String teamwork;

	@XmlElement(name = "demographic")
	public ArrayList<String> demographics;

	@XmlElement
	public String availability;
	
	@XmlElement
	public String attendance;
	
	@XmlElement(name = "degree")
	public ArrayList<String> degrees;
	
	/*@Override
	public String toString() {
		String s = "Name:\n" + Name + "\nPhone:\n" + Phone + "\nEmail:\n" + Email;
		s += Education.toString();
		for(JobModel j : Jobs) {
			s += "\n" + j.toString();
		}
		return s;
	}*/
}
