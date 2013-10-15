package edu.utdallas.gamegenerator.Characters;

/**
 * User: clocke
 * Date: 3/12/13
 * Time: 11:15 PM
 */
public enum CharacterAssetType {
    STAND_SMILE_CLOSED("StandSmileClosed"),
    WALK_RIGHT_BEHIND("WalkRBehind"),
    WALK_LEFT_OPEN("WalkLOpen"),
    RIGHT_POINT_UP("RPointUp"),
    RIGHT_POINT_NO("RPointNo"),
    WALK_RIGHT_CLOSED("WalkRClosed"),
    WALK_RIGHT_OPEN("WalkROpen"),
    WALK_LEFT_BEHIND("WalkLBehind"),
    LEFT_POINT_NO("LPointNo"),
    LEFT_EVIL("LEvil"),
    LEFT_POINT_UP("LPointUp");

    private String value;

    CharacterAssetType(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
