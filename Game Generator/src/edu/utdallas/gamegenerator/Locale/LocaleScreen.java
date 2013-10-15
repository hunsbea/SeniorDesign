package edu.utdallas.gamegenerator.Locale;

import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacterType;
import edu.utdallas.gamegenerator.LearningAct.Prop.TextType;
import edu.utdallas.gamegenerator.Shared.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Map;

/**
 * User: clocke
 * Date: 3/17/13
 * Time: 9:28 PM
 */
@XmlRootElement(name = "LocaleScreen")
public class LocaleScreen {
    private String background;
    private List<GameObject> gameObjects;
    private Map<LearningActCharacterType, SharedCharacter> characters;
    private Map<TextType, SharedInformationBox> informationBoxes;
    private Map<ButtonLocationType, SharedButton> buttons;

    public String getBackground() {
        return background;
    }

    @XmlElement(name = "Background")
    public void setBackground(String background) {
        this.background = background;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    @XmlElementWrapper(name = "GameObjects")
    @XmlElement(name = "GameObject")
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Map<LearningActCharacterType, SharedCharacter> getCharacters() {
        return characters;
    }

    @XmlElementWrapper(name = "Characters")
    public void setCharacters(Map<LearningActCharacterType, SharedCharacter> characters) {
        this.characters = characters;
    }

    public Map<TextType, SharedInformationBox> getInformationBoxes() {
        return informationBoxes;
    }

    @XmlElementWrapper(name = "InformationBoxes")
    public void setInformationBoxes(Map<TextType, SharedInformationBox> informationBoxes) {
        this.informationBoxes = informationBoxes;
    }

    public Map<ButtonLocationType, SharedButton> getButtons() {
        return buttons;
    }

    @XmlElementWrapper(name = "Buttons")
    public void setButtons(Map<ButtonLocationType, SharedButton> buttons) {
        this.buttons = buttons;
    }
}
