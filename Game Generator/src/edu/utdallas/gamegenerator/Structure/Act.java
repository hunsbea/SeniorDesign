package edu.utdallas.gamegenerator.Structure;

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
@XmlRootElement(name = "Act")
public class Act {
    private List<Scene> scenes;
    private String name;
    private UUID id = UUID.randomUUID();
    private List<String> learningObjectives;

    @XmlElementWrapper(name = "Scenes")
    @XmlElement(name = "Scene")
    public List<Scene> getScenes() {
        return scenes;
    }

    public void setScenes(List<Scene> scenes) {
        this.scenes = scenes;
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
