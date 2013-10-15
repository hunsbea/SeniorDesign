package edu.utdallas.gamegenerator.Characters;

import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacterType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:04 PM
 */
@XmlRootElement(name = "Characters")
public class Characters {
    private GameCharacter hero;
    private GameCharacter villain;
    private GameCharacter alt;
    private GameCharacter player;

    public GameCharacter getHero() {
        return hero;
    }

    public GameCharacter getCharacter(LearningActCharacterType characterType) {
        switch (characterType) {
            case HERO:
                return hero;
            case ALT:
                return alt;
            case VILLIAN:
                return villain;
            case PLAYER:
                return player;
            default:
                break;
        }
        return null;
    }

    @XmlElement(name = "Hero", required = true)
    public void setHero(GameCharacter hero) {
        this.hero = hero;
    }

    public GameCharacter getVillain() {
        return villain;
    }

    @XmlElement(name = "Villain", required = true)
    public void setVillain(GameCharacter villain) {
        this.villain = villain;
    }

    public GameCharacter getAlt() {
        return alt;
    }

    @XmlElement(name = "Alt", required = true)
    public void setAlt(GameCharacter alt) {
        this.alt = alt;
    }

    public GameCharacter getPlayer() {
        return player;
    }

    @XmlElement(name = "Player", required = true)
    public void setPlayer(GameCharacter player) {
        this.player = player;
    }
}
