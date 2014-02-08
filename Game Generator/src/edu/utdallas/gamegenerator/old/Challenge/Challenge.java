package edu.utdallas.gamegenerator.old.Challenge;

import edu.utdallas.gamegenerator.LearningAct.Screen.ChallengeScreen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: clocke
 * Date: 4/28/13
 * Time: 7:51 PM
 */
@XmlRootElement(name = "Challenge")
public class Challenge {
    private List<ChallengeScreen> lessonChallenges;

    public List<ChallengeScreen> getLessonChallenges() {
        return lessonChallenges;
    }

    @XmlElementWrapper(name = "LessonChallenges")
    @XmlElement(name = "LessonChallenge")
    public void setLessonChallenges(List<ChallengeScreen> lessonChallenges) {
        this.lessonChallenges = lessonChallenges;
    }
}
