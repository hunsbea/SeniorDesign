package edu.utdallas.gamegenerator.LearningAct.Challenge;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:30 PM
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Reward")
public class Reward {
    private int points;

    public Reward() {
    }

    public Reward(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @XmlElement(name = "Points")
    public void setPoints(int points) {
        this.points = points;
    }
}
