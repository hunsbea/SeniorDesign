package edu.utdallas.gamegenerator.Character;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MainTestCharacter 
{
	public static void main(String[] args)
	{
		PlayerCharacter character = new PlayerCharacter();
		
		character = new PlayerCharacter();
		character.setCharacterID(10);
		character.setName("character_10");
		character.setBehavior("Neutral");
		Profile profile = new Profile();
		profile.setProfilePhoto("profile_pic.jpg");
		profile.setTitle("Software Engineer");
		ArrayList<String> skills = new ArrayList<String>(3);
		skills.add("Java");
		skills.add("C++");
		skills.add("Python");
		profile.setSkills(skills);
		profile.setYearsOfExperience(3);
		profile.setCommunication("Good");
		//profile.setLeadership("Fair");
		profile.setTeamwork("Poor");
		profile.setAvailability("Excellent");
		profile.setAttendance("Good");
		ArrayList<String> demographics = new ArrayList<String>(2);
		demographics.add("Male");
		demographics.add("Caucasian");
		profile.setDemographics(demographics);
		ArrayList<String> degrees = new ArrayList<String>(1);
		degrees.add("B.S. Computer Science, NorthEastern University");
		profile.setDegrees(degrees);
		character.setProfile(profile);
		
		try {
			 
			File file = new File("OutputCharacter.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Character.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			//jaxbMarshaller.marshal(character, file);
			jaxbMarshaller.marshal(character, System.out);
	 
		      } catch (JAXBException e) {
			e.printStackTrace();
		      }
		
		try
		{
			File file = new File("character.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Character.class);
	 
			javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Character c = (Character) jaxbUnmarshaller.unmarshal(file);
			System.out.println(c);
	 
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		  }

	}
}
