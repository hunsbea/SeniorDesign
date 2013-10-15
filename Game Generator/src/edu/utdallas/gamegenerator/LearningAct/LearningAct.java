package edu.utdallas.gamegenerator.LearningAct;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 4:58 PM
 */
@XmlRootElement(name = "LearningAct")
public class LearningAct {
    private List<LessonAct> lessonActs;

    public List<LessonAct> getLessonActs() {
        return lessonActs;
    }

    @XmlElementWrapper(name = "Lessons")
    @XmlElement(name = "LessonAct")
    public void setLessonActs(List<LessonAct> lessonActs) {
        this.lessonActs = lessonActs;
    }
}
