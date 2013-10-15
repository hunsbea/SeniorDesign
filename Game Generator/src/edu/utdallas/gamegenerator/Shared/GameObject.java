package edu.utdallas.gamegenerator.Shared;

import javax.xml.bind.annotation.XmlElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:27 PM
 */
public class GameObject {
    private double locX;
    private double locY;
    private double width;
    private double height;
    private String pathToAsset;
    private String text;

    public GameObject() {

    }

    public GameObject(double locX, double locY, double width, double height, String pathToAsset) {
        this.locX = locX;
        this.locY = locY;
        this.width = width;
        this.height = height;
        this.pathToAsset = pathToAsset;
    }

    public double getLocX() {
        return locX;
    }

    @XmlElement(name = "LocX")
    public void setLocX(double locX) {
        this.locX = locX;
    }

    public double getLocY() {
        return locY;
    }

    @XmlElement(name = "LocY")
    public void setLocY(double locY) {
        this.locY = locY;
    }

    public double getWidth() {
        return width;
    }

    @XmlElement(name = "Width")
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    @XmlElement(name = "Height")
    public void setHeight(double height) {
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
