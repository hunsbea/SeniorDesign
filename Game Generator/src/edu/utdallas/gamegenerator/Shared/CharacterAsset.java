package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 4/13/13
 * Time: 4:18 PM
 */
@XmlType(name = "CharacterAsset")
public class CharacterAsset extends Asset {
    public CharacterAsset() {
    }

    public CharacterAsset(Asset asset) {
        super(asset);
    }
}
