package edu.utdallas.gamegenerator.Character;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

//TODO: eventually use enumerations

@XmlRootElement(name = "Character")
@XmlSeeAlso({PlayerCharacter.class, NonPlayerCharacter.class})
public abstract class Character 
{
	private String name;
	private Profile profile;
	private String behavior; //TODO: Eventually use Behavior class
	private int characterID;
	private List<Reward> rewards;

    @XmlElement(name = "Name")
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
    @XmlElement(name = "Behavior")
	public String getBehavior() 
	{
		return behavior;
	}
	public void setBehavior(String behavior) 
	{
		this.behavior = behavior;
	}
    @XmlElement(name = "Profile")
	public Profile getProfile() 
	{
		return profile;
	}
	public void setProfile(Profile profile) 
	{
		this.profile = profile;
	}
    @XmlElement(name = "CharacterID")
	public int getCharacterID() 
	{
		return characterID;
	}
	public void setCharacterID(int characterID) 
	{
		this.characterID = characterID;
	}
	@XmlElementWrapper(name = "Rewards")
    @XmlElement(name = "Reward")
	public List<Reward> getRewards() 
	{
		return rewards;
	}
	public void setRewards(List<Reward> rewards) 
	{
		this.rewards = rewards;
	}
} 
