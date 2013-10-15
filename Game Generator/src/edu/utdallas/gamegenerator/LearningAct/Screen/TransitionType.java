package edu.utdallas.gamegenerator.LearningAct.Screen;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:33 PM
 */
@XmlEnum
@XmlType(name = "TransitionType")
public enum TransitionType {
    BEGINNING_OF_LESSON,
    BEGINNING_OF_CHALLENGE,
    CURRENT_CHALLENGE,
    NEXT_CHALLENGE,
    END_OF_STORY,
    ADDITIONAL,
    NEXT_SCREEN,
    NEXT_ACT;
}
