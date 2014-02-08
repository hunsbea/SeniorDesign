package edu.utdallas.gamegenerator.Challenge;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "QuizChallenge")
public class QuizChallenge extends Challenge
{
	private Introduction intro;
	private List<Item> items;
	private Summary summary;
	private PedagogyType pedagogy;
	private Layout layout;
	
    @XmlElement(name = "Introduction")
	public Introduction getIntro() 
	{
		return intro;
	}
	public void setIntro(Introduction intro) 
	{
		this.intro = intro;
	}
	@XmlElementWrapper(name = "Items")
    @XmlElement(name = "Item")
	public List<Item> getItems() 
	{
		return items;
	}
	public void setItems(List<Item> items) 
	{
		this.items = items;
	}
    @XmlElement(name = "Summary")
	public Summary getSummary() 
	{
		return summary;
	}
	public void setSummary(Summary summary) 
	{
		this.summary = summary;
	}
    @XmlElement(name = "PedagogyType")
	public PedagogyType getPedagogy() 
	{
		return pedagogy;
	}
	public void setPedagogy(PedagogyType pedagogy) 
	{
		this.pedagogy = pedagogy;
	}
	public Layout getLayout() 
	{
		return layout;
	}
	public void setLayout(Layout layout) 
	{
		this.layout = layout;
	}
}
