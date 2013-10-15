package edu.utdallas.gamegenerator.Subject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:48 PM
 */
@XmlRootElement(name = "Subject")
public class Subject {
    private String subject;
    private String introText;

    public String getSubject() {
        return subject;
    }

    @XmlElement(name = "Subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIntroText() {
        return introText;
    }

    @XmlElement(name = "IntroText")
    public void setIntroText(String introText) {
        this.introText = introText;
    }
}
