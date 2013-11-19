import java.util.List;

import javax.xml.bind.annotation.XmlElement;
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
	@XmlElement
	public List<Question> questions;
	@XmlElement
	public Style style;
	@XmlElement
	public RewardScheme rewardScheme;
	@XmlElement
	public List<GameElement> elements;
	@XmlElement
	public Reward reward;
	@XmlElement
	public boolean isCompetitive;
	@XmlElement
	public boolean isProctored;
}
