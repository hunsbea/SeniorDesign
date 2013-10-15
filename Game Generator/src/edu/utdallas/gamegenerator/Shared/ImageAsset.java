package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 4/13/13
 * Time: 4:00 PM
 */
@XmlType(name = "ImageAsset")
public class ImageAsset extends Asset {
    public ImageAsset() {
    }

    public ImageAsset(Asset asset) {
        super(asset);
    }
}
