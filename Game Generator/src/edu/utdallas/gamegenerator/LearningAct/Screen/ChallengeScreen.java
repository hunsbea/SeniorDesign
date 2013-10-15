package edu.utdallas.gamegenerator.LearningAct.Screen;

import edu.utdallas.gamegenerator.LearningAct.Challenge.ChallengeOption;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 4:12 PM
 */
@XmlRootElement(name = "LessonChallenge")
public class ChallengeScreen extends BaseScreen {
    private int timer;
    private List<ChallengeOption> challengeOptions;

    @Override
    public ScreenType getType() {
        return ScreenType.CHALLENGE;
    }

    public int getTimer() {
        return timer;
    }

    @XmlElement(name = "Timer")
    public void setTimer(int timer) {
        this.timer = timer;
    }

    public List<ChallengeOption> getChallengeOptions() {
        return challengeOptions;
    }

    @XmlElementWrapper(name = "ChallengeOptions")
    @XmlElement(name = "ChallengeOption")
    public void setChallengeOptions(List<ChallengeOption> challengeOptions) {
        this.challengeOptions = challengeOptions;
    }
}
