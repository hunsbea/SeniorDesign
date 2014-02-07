package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlType;

/**
 * User: Jacob Dahleen
 * Date: 2/7/14
 * Time: 2:20 PM
 */
@XmlType(name = "ThoughtBubbleAsset")
public class ThoughtBubbleAsset extends Asset {
    public ThoughtBubbleAsset() {
    }

    public ThoughtBubbleAsset(Asset asset) {
        super(asset);
    }
}
