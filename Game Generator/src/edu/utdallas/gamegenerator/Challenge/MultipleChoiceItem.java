package edu.utdallas.gamegenerator.Challenge;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "MultipleChoiceItem")
@XmlType(name = "MultipleChoiceItem")
public class MultipleChoiceItem extends Item
{
	private Stem stem;
	private ItemImage image;
	private List<Option> options;

    @XmlElement(name = "Stem")
	public Stem getStem() 
	{
		return stem;
	}
	public void setStem(Stem stem) 
	{
		this.stem = stem;
	}
    @XmlElement(name = "ItemImage")
	public ItemImage getImage() 
	{
		return image;
	}
	public void setImage(ItemImage image) 
	{
		this.image = image;
	}
	@XmlElementWrapper(name = "Options")
    @XmlElement(name = "Option")
	public List<Option> getOptions() 
	{
		return options;
	}
	public void setOptions(List<Option> options) 
	{
		this.options = options;
	}
}
