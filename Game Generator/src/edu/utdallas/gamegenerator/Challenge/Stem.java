package edu.utdallas.gamegenerator.Challenge;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Stem")
public class Stem 
{
	private StemQuestion stemQuestion;
	private StemText stemText;

    @XmlElement(name = "StemText")
	public StemText getStemText() 
	{
		return stemText;
	}
	public void setStemText(StemText stemText) 
	{
		this.stemText = stemText;
	}
    @XmlElement(name = "StemQuestion")
	public StemQuestion getStemQuestion() 
	{
		return stemQuestion;
	}
	public void setStemQuestion(StemQuestion stemQuestion) 
	{
		this.stemQuestion = stemQuestion;
	}
}
