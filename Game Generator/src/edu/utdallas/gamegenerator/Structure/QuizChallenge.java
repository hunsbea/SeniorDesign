package edu.utdallas.gamegenerator.Structure;

import java.util.List;

public class QuizChallenge extends Challenge
{
	private Introduction intro;
	private List<Item> items;
	private Summary summary;
	private PedagogyType pedagogy;
	private Layout layout;
	
	public Introduction getIntro() 
	{
		return intro;
	}
	public void setIntro(Introduction intro) 
	{
		this.intro = intro;
	}
	public List<Item> getItems() 
	{
		return items;
	}
	public void setItems(List<Item> items) 
	{
		this.items = items;
	}
	public Summary getSummary() 
	{
		return summary;
	}
	public void setSummary(Summary summary) 
	{
		this.summary = summary;
	}
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
