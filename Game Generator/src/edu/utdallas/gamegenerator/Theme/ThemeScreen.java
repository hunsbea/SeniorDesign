package edu.utdallas.gamegenerator.Theme;

import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacterType;
import edu.utdallas.gamegenerator.Shared.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Map;

/**
 * User: clocke
 * Date: 3/3/13
 * Time: 6:29 PM
 */
public class ThemeScreen {
    private String background;
    private Map<LearningActCharacterType, SharedCharacter> themeCharacters;
    private List<GameObject> gameObjects;
    private Map<ButtonLocationType, SharedButton> buttons;
    private List<SharedInformationBox> informationBoxes;

    public String getBackground() {
        return background;
    }

    @XmlElement(name = "Background")
    public void setBackground(String background) {
        this.background = background;
    }

    public Map<LearningActCharacterType, SharedCharacter> getThemeCharacters() {
        return themeCharacters;
    }

    @XmlElementWrapper(name = "ThemeCharacters")
    public void setThemeCharacters(Map<LearningActCharacterType, SharedCharacter> themeCharacters) {
        this.themeCharacters = themeCharacters;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    @XmlElementWrapper(name = "GameObjects")
    @XmlElement(name = "GameObject")
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Map<ButtonLocationType, SharedButton> getButtons() {
        return buttons;
    }

    @XmlElementWrapper(name = "ThemeButtons")
    public void setButtons(Map<ButtonLocationType, SharedButton> buttons) {
        this.buttons = buttons;
    }

    public List<SharedInformationBox> getInformationBoxes() {
        return informationBoxes;
    }

    @XmlElementWrapper(name = "InformationBoxes")
    @XmlElement(name = "InformationBox")
    public void setInformationBoxes(List<SharedInformationBox> informationBoxes) {
        this.informationBoxes = informationBoxes;
    }
}
