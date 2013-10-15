package edu.utdallas.gamegenerator.Shared;

import edu.utdallas.gamegenerator.Characters.CharacterAssetType;
import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacterType;
import edu.utdallas.gamegenerator.Locale.ObjectMovement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:30 PM
 */
public class SharedCharacter extends GameObject {
    private LearningActCharacterType characterType;
    private List<ObjectMovement> movements;
    private CharacterAssetType characterAssetType;

    public SharedCharacter() {
        super();
    }

    public SharedCharacter(int locX, int locY, int width, int height, String pathToAsset, LearningActCharacterType characterType, List<ObjectMovement> movements) {
        super(locX, locY, width, height, pathToAsset);
        this.characterType = characterType;
        this.movements = movements;
    }

    public LearningActCharacterType getCharacterType() {
        return characterType;
    }

    @XmlElement(name = "CharacterType")
    public void setCharacterType(LearningActCharacterType characterType) {
        this.characterType = characterType;
    }

    public List<ObjectMovement> getMovements() {
        return movements;
    }

    @XmlElementWrapper(name = "Movements")
    @XmlElement(name = "Movement")
    public void setMovements(List<ObjectMovement> movements) {
        this.movements = movements;
    }

    public CharacterAssetType getCharacterAssetType() {
        return characterAssetType;
    }

    @XmlElement(name = "CharacterAssetType")
    public void setCharacterAssetType(CharacterAssetType characterAssetType) {
        this.characterAssetType = characterAssetType;
    }
}
