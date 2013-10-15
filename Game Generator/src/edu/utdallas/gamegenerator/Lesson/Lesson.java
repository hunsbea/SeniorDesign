package edu.utdallas.gamegenerator.Lesson;

import edu.utdallas.gamegenerator.LearningAct.Screen.LessonScreen;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: clocke
 * Date: 4/28/13
 * Time: 7:49 PM
 */
@XmlRootElement(name = "Lesson")
public class Lesson {
    private List<LessonScreen> lessonScreens;

    public List<LessonScreen> getLessonScreens() {
        return lessonScreens;
    }

    @XmlElementWrapper(name = "LessonScreens")
    @XmlElement(name = "LessonScreen")
    public void setLessonScreens(List<LessonScreen> lessonScreens) {
        this.lessonScreens = lessonScreens;
    }
}
