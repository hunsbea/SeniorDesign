package edu.utdallas.gamegenerator.Challenge;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class MainTestChallenge 
{
	public static void main(String[] args) 
	{
		/*QuizChallenge challenge = new QuizChallenge();
		ArrayList<Item> items = new ArrayList<Item>();
		MultipleChoiceItem item = new MultipleChoiceItem();
		ArrayList<Option> options = new ArrayList<Option>();
		Stem stem = new Stem();
		stem.setStemText("this explains whatever");
		stem.setStemQuestion("answer this question");
		item.setStem(stem);
		Option option = new Option();
		option.setText("an option");
		for(int i = 0; i < 4; i++)
		{
			options.add(option);
		}
		item.setOptions(options);
		items.add(item);
		challenge.setItems(items);
		
		try {
			 
			File file = new File("OutputChallenge.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Challenge.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller.marshal(challenge, file);
			jaxbMarshaller.marshal(challenge, System.out);
	 
		      } catch (JAXBException e) {
			e.printStackTrace();
		      }*/
		
		try
		{
			File file = new File("TestChallenge.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Challenge.class);
	 
			javax.xml.bind.Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Challenge challenge1 = (Challenge) jaxbUnmarshaller.unmarshal(file);
			System.out.println(challenge1);
	 
		} 
		catch (JAXBException e) {
			e.printStackTrace();
		  }

	}

}
