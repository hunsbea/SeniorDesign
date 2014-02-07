package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlType;

/**
 * User: Jacob Dahleen
 * Date: 2/7/14
 * Time: 2:20 PM
 */
@XmlType(name = "ConversationBubbleAsset")
public class ConversationBubbleAsset extends Asset {
    public ConversationBubbleAsset() {
    }

    public ConversationBubbleAsset(Asset asset) {
        super(asset);
    }
}
