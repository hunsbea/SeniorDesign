package edu.utdallas.gamegenerator.LearningAct;

import edu.utdallas.gamegenerator.LearningAct.Screen.ChallengeScreen;
import edu.utdallas.gamegenerator.LearningAct.Screen.LessonScreen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 5:15 PM
 */
public class LessonAct {
    private List<LessonScreen> lessonScreens;
    private List<ChallengeScreen> lessonChallenges;

    public List<LessonScreen> getLessonScreens() {
        return lessonScreens;
    }

    @XmlElementWrapper(name = "LessonScreens")
    @XmlElement(name = "LessonScreen")
    public void setLessonScreens(List<LessonScreen> lessonScreens) {
        this.lessonScreens = lessonScreens;
    }

    public List<ChallengeScreen> getLessonChallenges() {
        return lessonChallenges;
    }

    @XmlElementWrapper(name = "LessonChallenges")
    @XmlElement(name = "LessonChallenge")
    public void setLessonChallenges(List<ChallengeScreen> lessonChallenges) {
        this.lessonChallenges = lessonChallenges;
    }
}
