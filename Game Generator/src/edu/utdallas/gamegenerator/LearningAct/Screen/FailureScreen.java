package edu.utdallas.gamegenerator.LearningAct.Screen;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 4:43 PM
 */
@XmlRootElement(name = "FailureScreen")
public class FailureScreen extends BaseScreen {
    @Override
    public ScreenType getType() {
        return ScreenType.FAILURE;
    }
}
