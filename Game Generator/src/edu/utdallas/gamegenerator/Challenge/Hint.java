package edu.utdallas.gamegenerator.Challenge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Hint")
public class Hint 
{
	private String text;

    @XmlElement(name = "Text")
	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}
}
