package edu.utdallas.gamegenerator.Locale;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * User: clocke
 * Date: 2/24/13
 * Time: 8:46 PM
 */
@XmlType(name = "ObjectMovementType")
@XmlEnum
public enum ObjectMovementType {
    WALK_ONTO_SCREEN,
    WALK_OFF_SCREEN;
}
