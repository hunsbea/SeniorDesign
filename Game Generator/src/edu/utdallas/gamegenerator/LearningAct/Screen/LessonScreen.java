package edu.utdallas.gamegenerator.LearningAct.Screen;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 4:37 PM
 */
@XmlRootElement(name = "LessonScreen")
public class LessonScreen extends BaseScreen {
    @Override
    public ScreenType getType() {
        return ScreenType.LESSON;
    }
}
