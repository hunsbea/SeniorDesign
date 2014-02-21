package edu.utdallas.gamegenerator.Structure;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.UUID;
import edu.utdallas.gamegenerator.Character.Character;

/**
 * User: clocke
 * Date: 2/24/13
 * Time: 8:54 PM
 */
@XmlRootElement(name = "Game")
public class Game {
    List<Act> acts;
    private UUID id = UUID.randomUUID();
    private String name;
    private List<Character> characters;

    @XmlElementWrapper(name = "Acts")
    @XmlElement(name = "Act")
    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

    @XmlElement(name = "ID")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @XmlElement(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString()
    {
    	return name;
    }

    @XmlElementWrapper(name = "Characters")
    @XmlElement(name = "Character")
	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
}
