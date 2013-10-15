package edu.utdallas.gamegenerator.LearningAct.Prop;

import javax.xml.bind.annotation.XmlElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 3:25 PM
 */
public class GameText {
    private TextType textType;
    private String text;
    private int timer;

    public TextType getTextType() {
        return textType;
    }

    @XmlElement(name = "TextType")
    public void setTextType(TextType textType) {
        this.textType = textType;
    }

    public String getText() {
        return text;
    }

    @XmlElement(name = "Text")
    public void setText(String text) {
        this.text = text;
    }

    public int getTimer() {
        return timer;
    }

    @XmlElement(name = "Timer")
    public void setTimer(int timer) {
        this.timer = timer;
    }
}
