package edu.utdallas.gamegenerator.Characters;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.Map;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:08 PM
 */
public class GameCharacter {
    private String directory;
    private String prefix;
    private Map<CharacterAssetType, String> characterAssets;
    private String name;

    public GameCharacter() {
    }

    private void buildCharacterAssets() {
        characterAssets = new HashMap<CharacterAssetType, String>();
        for(CharacterAssetType characterAssetType : CharacterAssetType.values()) {
            characterAssets.put(characterAssetType,
                    buildCharacterAsset(characterAssetType));
        }
    }

    private String buildCharacterAsset(CharacterAssetType characterAssetType) {
        return directory + "\\" + prefix + "_" + characterAssetType.toString() + ".png";
    }

    public String getCharacterAsset(CharacterAssetType characterAssetType) {
        if(characterAssets == null) {
            buildCharacterAssets();
        }
        return characterAssets.get(characterAssetType);
    }

    public String getDirectory() {
        return directory;
    }

    @XmlElement(name = "Directory")
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getPrefix() {
        return prefix;
    }

    @XmlElement(name = "Prefix")
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Map<CharacterAssetType, String> getCharacterAssets() {
        return characterAssets;
    }

    @XmlTransient
    public void setCharacterAssets(Map<CharacterAssetType, String> characterAssets) {
        this.characterAssets = characterAssets;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }
}
