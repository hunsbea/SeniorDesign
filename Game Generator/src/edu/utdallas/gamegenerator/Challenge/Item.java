package edu.utdallas.gamegenerator.Challenge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement(name = "Item")
@XmlSeeAlso({MultipleChoiceItem.class})
public class Item 
{
	private String name;
	private String learningObjective;

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "Objective")
	public String getLearningObjective() {
		return learningObjective;
	}
	
	public void setLearningObjective(String learningObjective) {
		this.learningObjective = learningObjective;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
