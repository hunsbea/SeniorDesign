package edu.utdallas.gamegenerator.LearningAct.Challenge;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 4:23 PM
 */
@XmlType(name = "ChallengeOptionType")
@XmlEnum
public enum ChallengeOptionType {
    BUTTON,
    TIMER;
}
