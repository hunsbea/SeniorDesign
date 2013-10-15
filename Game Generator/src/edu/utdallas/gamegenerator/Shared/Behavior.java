package edu.utdallas.gamegenerator.Shared;

import edu.utdallas.gamegenerator.LearningAct.Screen.TransitionType;
import edu.utdallas.gamegenerator.Locale.ObjectMovement;
import edu.utdallas.gamegenerator.Locale.ObjectMovementType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.UUID;

/**
 * User: clocke
 * Date: 3/3/13
 * Time: 8:59 PM
 */
@XmlRootElement(name = "Behavior")
@XmlSeeAlso({TransitionBehavior.class, EndGameBehavior.class})
public class Behavior {
    private BehaviorType behaviorType;
    private TriggerType trigger;
    private int time;
    private String displayName;
    private Integer points;
    private UUID transitionId;
    private TransitionType transition;
    private UUID entityId;
    private String newVisibility;
    private double startLocationX;
    private double startLocationY;
    private double endLocationX;
    private double endLocationY;
    private double speed;

    private ObjectMovementType objectMovement;


    public Behavior() {
    }

    public Behavior(Behavior behavior) {
        trigger = TriggerType.Click;
        time = behavior.getTime();
        displayName = behavior.getDisplayName();
        points = behavior.getPoints();
        transitionId = behavior.getTransitionId();
        transition = behavior.getTransition();
        entityId = behavior.getEntityId();
        newVisibility = behavior.getNewVisibility();
        startLocationY = behavior.getStartLocationY();
        startLocationX = behavior.getStartLocationX();
        endLocationY = behavior.getEndLocationY();
        endLocationX = behavior.getEndLocationX();
        speed = behavior.getSpeed();
    }

    public Behavior(BehaviorType behaviorType) {
        this.behaviorType = behaviorType;
    }

    public Behavior(ObjectMovement movement) {
        startLocationX = movement.getStartX();
        startLocationY = movement.getStartY();
        endLocationX = movement.getEndX();
        endLocationY = movement.getEndY();
        speed = movement.getSpeed();
        objectMovement = movement.getMovementType();
    }

    public BehaviorType getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(BehaviorType behaviorType) {
        this.behaviorType = behaviorType;
    }

    @XmlElement(name = "Trigger")
    public TriggerType getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerType trigger) {
        this.trigger = trigger;
    }

    @XmlElement(name = "Time")
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @XmlElement(name = "DisplayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @XmlElement(name = "Points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @XmlElement(name = "TransitionID")
    public UUID getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(UUID transitionId) {
        this.transitionId = transitionId;
    }

    @XmlElement(name = "Transition")
    public TransitionType getTransition() {
        return transition;
    }

    public void setTransition(TransitionType transition) {
        this.transition = transition;
    }

    @XmlElement(name = "EntityID")
    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    @XmlElement(name = "NewVisibility")
    public String getNewVisibility() {
        return newVisibility;
    }

    public void setNewVisibility(String newVisibility) {
        this.newVisibility = newVisibility;
    }

    @XmlElement(name = "StartLocationX")
    public double getStartLocationX() {
        return startLocationX;
    }

    public void setStartLocationX(double startLocationX) {
        this.startLocationX = startLocationX;
    }

    @XmlElement(name = "StartLocationY")
    public double getStartLocationY() {
        return startLocationY;
    }

    public void setStartLocationY(double startLocationY) {
        this.startLocationY = startLocationY;
    }

    @XmlElement(name = "EndLocationX")
    public double getEndLocationX() {
        return endLocationX;
    }

    public void setEndLocationX(double endLocationX) {
        this.endLocationX = endLocationX;
    }

    @XmlElement(name = "EndLocationY")
    public double getEndLocationY() {
        return endLocationY;
    }

    public void setEndLocationY(double endLocationY) {
        this.endLocationY = endLocationY;
    }

    @XmlElement(name = "Speed")
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @XmlElement(name = "ObjectMovement")
    public ObjectMovementType getObjectMovement() {
        return objectMovement;
    }

    public void setObjectMovement(ObjectMovementType objectMovement) {
        this.objectMovement = objectMovement;
    }
}
