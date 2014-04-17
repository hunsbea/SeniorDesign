package edu.utdallas.gamegenerator.Structure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import edu.utdallas.gamegenerator.Challenge.Challenge;
import edu.utdallas.gamegenerator.Shared.Asset;

import java.util.List;
import java.util.UUID;

/**
 * User: clocke
 * Date: 2/15/13
 * Time: 9:44 PM
 */
@XmlRootElement(name = "Screen")
public class Screen {
    private UUID id;
    private String background;
    private String name;
    private List<Asset> assets;
    private Challenge challenge;
    private List<String> learningObjectives;

    public Screen() {
        id = UUID.randomUUID();
    }

    @XmlElement(name = "ID")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @XmlElementWrapper(name = "Assets")
    @XmlElement(name = "AssetBase")
    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    @XmlElement(name = "Challenge")
	public Challenge getChallenge() {
		return challenge;
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
	}
	
    
    @XmlElementWrapper(name = "LearningObjectives")
    @XmlElement(name = "Objective")
    public List<String> getLearningObjectives() {
        return learningObjectives;
    }

    public void setLearningObjectives(List<String> lOs) {
        this.learningObjectives = lOs;
    }
    
    @Override
    public String toString()
    {
    	return name;
    }

}
