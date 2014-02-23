package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:27 PM
 */
public class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private String pathToAsset;
    private String text;

    public GameObject() {

    }

    public GameObject(int locX, int locY, int width, int height, String pathToAsset) {
        this.x = locX;
        this.y = locY;
        this.width = width;
        this.height = height;
        this.pathToAsset = pathToAsset;
    }

    public int getX() {
        return x;
    }

    @XmlElement(name = "X")
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    @XmlElement(name = "Y")
    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    @XmlElement(name = "Width")
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    @XmlElement(name = "Height")
    public void setHeight(int height) {
        this.height = height;
    }

    public String getPathToAsset() {
        return pathToAsset;
    }

    @XmlElement(name = "PathToAsset")
    public void setPathToAsset(String pathToAsset) {
        this.pathToAsset = pathToAsset;
    }

    public String getText() {
        return text;
    }

    @XmlElement(name = "Text")
    public void setText(String text) {
        this.text = text;
    }
}
