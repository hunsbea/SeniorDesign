import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class QuizChallenge extends Challenge
{
	public enum Style { MULTIPLE_CHOICE }
	public enum RewardScheme { UNDEFINED }
	
	@XmlElement
	public Timer timer;
	@XmlElement
	public boolean isTimed;
	@XmlElement
	public String purpose;
	@XmlElementWrapper(name = "questions")
	@XmlElement(name = "question")
	public List<Question> questions;
	@XmlElement
	public Style style;
	@XmlElement
	public RewardScheme rewardScheme;
	@XmlElementWrapper(name = "elements")
	@XmlElement(name = "element")
	public List<GameElement> elements;
	@XmlElement
	public Reward reward;
	@XmlElement
	public boolean isCompetitive;
	@XmlElement
	public boolean isProctored;
}
