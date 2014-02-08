package edu.utdallas.gamegenerator.Challenge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Stem")
public class Stem 
{
	private String stemQuestion;
	private String stemText;

    @XmlElement(name = "StemText")
	public String getStemText() 
	{
		return stemText;
	}
	public void setStemText(String stemText) 
	{
		this.stemText = stemText;
	}
    @XmlElement(name = "StemQuestion")
	public String getStemQuestion() 
	{
		return stemQuestion;
	}
	public void setStemQuestion(String stemQuestion) 
	{
		this.stemQuestion = stemQuestion;
	}
}
