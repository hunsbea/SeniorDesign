package edu.utdallas.gamegenerator.Structure;

import edu.utdallas.gamegenerator.Shared.Asset;
import edu.utdallas.gamegenerator.Shared.Behavior;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;

/**
 * User: clocke
 * Date: 2/24/13
 * Time: 8:58 PM
 */
@XmlRootElement(name = "Scene")
public class Scene {
    List<Screen> screens;
    String background;
    String name;
    String backgroundAudio;
    UUID id = UUID.randomUUID();
    List<Behavior> behaviorList;
    List<Asset> assets;

    public List<Screen> getScreens() {
        return screens;
    }

    @XmlElementWrapper(name = "Screens")
    @XmlElement(name = "Screen")
    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    @XmlElement(name = "Background")
    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "ID")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    @XmlElement(name = "BackgroundAudio")
    public String getBackgroundMusic() {
        return backgroundAudio;
    }
    
    public void setBackgroundMusic(String backgroundMusic) {
    	this.backgroundAudio=backgroundMusic;
    }

    @XmlElement
    public List<Behavior> getBehaviorList() {
        return behaviorList;
    }

    public void setBehaviorList(List<Behavior> behaviorList) {
        this.behaviorList = behaviorList;
    }

    @XmlElementWrapper(name = "Assets")
    @XmlElement(name = "Asset")
    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
    
    @Override
    public String toString()
    {
    	return name;
    }
}
