package edu.utdallas.gamegenerator.Challenge;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import edu.utdallas.gamegenerator.Challenge.Layout.LayoutType;


@XmlRootElement(name = "QuizChallenge")
@XmlType(name = "QuizChallenge")
public class QuizChallenge extends Challenge
{
	private Introduction intro;
	private List<Item> items;
	private List<Summary> summaries;
	private PedagogyType pedagogy;
	private LayoutType layout;
	
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
    @XmlElement(name = "PedagogyType")
	public PedagogyType getPedagogy() 
	{
		return pedagogy;
	}
	public void setPedagogy(PedagogyType pedagogy) 
	{
		this.pedagogy = pedagogy;
	}
	@XmlElement(name = "Layout")
	public LayoutType getLayout() 
	{
		return layout;
	}
	public void setLayout(LayoutType layout) 
	{
		this.layout = layout;
	}
	@XmlElementWrapper(name = "Summaries")
    @XmlElement(name = "Summary")
	public List<Summary> getSummaries() {
		return summaries;
	}
	public void setSummaries(List<Summary> summaries) {
		this.summaries = summaries;
	}
}
