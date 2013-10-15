package edu.utdallas.gamegenerator.Locale;

import edu.utdallas.gamegenerator.Characters.CharacterAssetType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:33 PM
 */
public class ObjectMovement {
    private ObjectMovementType movementType;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double speed;
    private List<CharacterAssetType> animationSequence;

    public ObjectMovement() {
    }

    public ObjectMovement(ObjectMovementType movementType, double startX, double startY, double endX, double endY) {
        this.movementType = movementType;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.speed = 1;
    }

    public ObjectMovementType getMovementType() {
        return movementType;
    }

    @XmlElement(name = "MovementType")
    public void setMovementType(ObjectMovementType movementType) {
        this.movementType = movementType;
    }

    public double getStartX() {
        return startX;
    }

    @XmlElement(name = "StartX")
    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getStartY() {
        return startY;
    }

    @XmlElement(name = "StartY")
    public void setStartY(double startY) {
        this.startY = startY;
    }

    public double getEndX() {
        return endX;
    }

    @XmlElement(name = "EndX")
    public void setEndX(double endX) {
        this.endX = endX;
    }

    public double getEndY() {
        return endY;
    }

    @XmlElement(name = "EndY")
    public void setEndY(double endY) {
        this.endY = endY;
    }

    public double getSpeed() {
        return speed;
    }

    @XmlElement(name = "Speed")
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public List<CharacterAssetType> getAnimationSequence() {
        return animationSequence;
    }

    @XmlElementWrapper(name = "AnimationSequences")
    @XmlElement(name = "AnimationSequence")
    public void setAnimationSequence(List<CharacterAssetType> animationSequence) {
        this.animationSequence = animationSequence;
    }
}
