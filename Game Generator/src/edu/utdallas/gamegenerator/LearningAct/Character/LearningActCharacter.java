package edu.utdallas.gamegenerator.LearningAct.Character;

import edu.utdallas.gamegenerator.Locale.ObjectMovementType;
import edu.utdallas.gamegenerator.Shared.GameObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:29 PM
 */
@XmlRootElement(name = "LOCharacters")
public class LearningActCharacter extends GameObject {
    private LearningActCharacterType characterType;
    private ObjectMovementType movementType;
    private int timer;

    public LearningActCharacterType getCharacterType() {
        return characterType;
    }

    @XmlElement(name = "CharacterType")
    public void setCharacterType(LearningActCharacterType characterType) {
        this.characterType = characterType;
    }

    public ObjectMovementType getMovementType() {
        return movementType;
    }

    @XmlElement(name = "MovementType")
    public void setMovementType(ObjectMovementType movementType) {
        this.movementType = movementType;
    }

    public int getTimer() {
        return timer;
    }

    @XmlElement(name = "Timer")
    public void setTimer(int timer) {
        this.timer = timer;
    }
}
