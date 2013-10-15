package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 4/13/13
 * Time: 4:22 PM
 */
@XmlType(name = "ButtonAsset")
public class ButtonAsset extends Asset {
    public ButtonAsset() {
    }

    public ButtonAsset(Asset asset) {
        super(asset);
    }
}
