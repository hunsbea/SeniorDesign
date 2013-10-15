package edu.utdallas.gamegenerator.Shared;

/**
 * User: clocke
 * Date: 3/3/13
 * Time: 10:24 PM
 */
public class SharedButton extends GameObject {
    private String name;
    private Behavior behavior;

    public SharedButton() {
        super();
    }

    public SharedButton(String name, int locX, int locY, int width, int height, Behavior behavior) {
        super(locX, locY, width, height, null);
        this.name = name;
        this.behavior = behavior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }
}
