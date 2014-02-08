package edu.utdallas.gamegenerator.Challenge;
import java.util.List;

public class MultipleChoiceItem extends Item
{
	private Stem stem;
	private ItemImage image;
	private List<Option> options;
	
	public Stem getStem() 
	{
		return stem;
	}
	public void setStem(Stem stem) 
	{
		this.stem = stem;
	}
	public ItemImage getImage() 
	{
		return image;
	}
	public void setImage(ItemImage image) 
	{
		this.image = image;
	}
	public List<Option> getOptions() 
	{
		return options;
	}
	public void setOptions(List<Option> options) 
	{
		this.options = options;
	}
}
