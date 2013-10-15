package edu.utdallas.gamegenerator.Theme;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: clocke
 * Date: 3/12/13
 * Time: 9:23 PM
 */
@XmlRootElement(name = "ThemeStory")
public class ThemeStory {
    private List<ThemeStoryScreenIntro> intro;
    private List<ThemeStoryScreenOutro> outro;

    public List<ThemeStoryScreenIntro> getIntro() {
        return intro;
    }

    @XmlElementWrapper(name = "StoryIntroScreens")
    @XmlElement(name = "StoryIntroScreen")
    public void setIntro(List<ThemeStoryScreenIntro> intro) {
        this.intro = intro;
    }

    public List<ThemeStoryScreenOutro> getOutro() {
        return outro;
    }

    @XmlElementWrapper(name = "StoryOutroScreens")
    @XmlElement(name = "StoryOutroScreen")
    public void setOutro(List<ThemeStoryScreenOutro> outro) {
        this.outro = outro;
    }
}
