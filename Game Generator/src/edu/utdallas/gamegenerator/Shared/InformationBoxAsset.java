package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 4/13/13
 * Time: 4:20 PM
 */
@XmlType(name = "InformationBoxAsset")
public class InformationBoxAsset extends Asset {
    public InformationBoxAsset() {
    }

    public InformationBoxAsset(Asset asset) {
        super(asset);
    }
}
