import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Timer 
{
	public enum TransitionSpeed { SLOW_TRANSITION, MODERATE_TRANSITION, QUICK_TRANSITION }
	public enum PresentationSpeed { SLOW_PRESENTATION, MODERATE_PRESENTATION, QUICK_PRESENTATION }
	public enum HintSpeed { SLOW_HINT_TIMER, MODERATE_HINT_TIMER, QUICK_HINT_TIMER }
	public enum AnimationSpeed { SLOW_MOVEMENT, MODERATE_MOVEMENT, QUICK_MOVEMENT }
	public enum AnimationEffectSpeed { SLOW_EFFECT, MODERATE_EFFECT, QUICK_EFFECT }
	
	@XmlElement
	public TransitionSpeed transitionSpeed;
	@XmlElement
	public PresentationSpeed presentationSpeed;
	@XmlElement
	public HintSpeed hintSpeed;
	@XmlElement
	public AnimationSpeed animationSpeed;
	@XmlElement
	public AnimationEffectSpeed animationEffectSpeed;
}
