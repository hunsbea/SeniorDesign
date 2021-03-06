import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile 
{
	public enum Proficiency { POOR, FAIR, GOOD, VERY_GOOD, EXCELLENT } //or call this Competency?
	public enum Title { SOFTWARE_DESIGNER, JUNIOR_SOFTWARE_DESIGNER, REQUIREMENTS_ENGINEER,
		JUNIOR_REQUIREMENTS_ENGINEER, MANAGER, INTERMEDIATE_MANAGER, INTERMEDIATE_REQUIREMENTS_ENGINEER, 
		INTERMEDIATE_BUSINESS_ANALYST, JUNIOR_SOFTWARE_TESTER }
	public enum Skill { UML, JAVA, C, PERL, PYTHON, SQL, JAVASCRIPT, C_SHARP, OOD }
	
	@XmlElement
	public String photoImagePath;
	@XmlElement
	public Title title;
	@XmlElementWrapper(name = "skills")
	@XmlElement(name = "skill")
	public List<Skill> skills;
	@XmlElement
	public int yearsOfExperience;
	@XmlElement
	public Proficiency communication;
	@XmlElement
	public Proficiency leadership;
	@XmlElement
	public Proficiency teamwork;
	@XmlElementWrapper(name = "demographics")
	@XmlElement(name = "demographic")
	public List<String> demographics;
	@XmlElement
	public Proficiency availability;
	@XmlElement
	public Proficiency attendance;
	@XmlElementWrapper(name = "degrees")
	@XmlElement(name = "degree")
	public List<String> degrees;
}
