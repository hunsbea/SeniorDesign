package edu.utdallas.gamegenerator.Search;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import org.w3c.dom.Document;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

import edu.utdallas.gamegenerator.Characters.Characters;
//Entity Class- reads in the search space from a file
public class SearchSpace {
	/**
	 * @param String type : type of game component search. 
	 */
	private double[][] searchSpace;
	private int Criteria =0;
	private int row=0;
	private int col=0;
	public SearchSpace(String type){
		//READ FROM XML FILES
		try {
	//		System.out.println("SearchSpace Start");
			//			jaxbContext = JAXBContext.newInstance();
			//		
			//	        file = new File("XMLmetadata\\"+type+".xml");
			//	        unmarshaller = jaxbContext.createUnmarshaller();

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document component = docBuilder.parse (new File("XMLmetadata//"+type+".xml"));


			// normalize text representation
			component.getDocumentElement ().normalize ();
//			System.out.println ("Root element of the file is \"" + 
//								component.getDocumentElement().getNodeName() +"\" with type: "+type);
			NodeList listOfComponents = component.getElementsByTagName(type);
			int totalComponents= listOfComponents.getLength();
//			System.out.println("Total num of "+type+" : " + totalComponents );   
			rowSizeCalculator(listOfComponents,0); 	//rowSizeCalculator counts ALL the leaf nodes which 
			Criteria /= totalComponents; 		//is totalComponents*Criteria so divide that out
//			System.out.println(Criteria);
			searchSpace = new double[totalComponents][Criteria];
			XMLInputRecurr(listOfComponents,0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		/**READ FROM TXT FILES	
		try {
			//BufferedReader input = new BufferedReader(new FileReader(type+".txt"));
			Scanner input = new Scanner(new FileReader(type+".txt"));
			numberOfChoices= input.nextInt();
			numberOfCriteria = input.nextInt();
			searchSpace = new double[numberOfChoices][numberOfCriteria];
			int choiceCounter=0;

	 // do not uncomment the print statements without commenting out the input.next() statements 
	 // that immediately follow them. (one needs to be commented out at all times.)

			System.out.println("#"+input.next()); //list type name
			//input.next(); //ignore type name
			while (input.hasNextLine()) {
				System.out.println("#"+input.next()); //list choice name
				//input.next(); //ignore choice name
				for(int i=0; i<numberOfCriteria; i++)
					{
					System.out.println("#"+input.next()); //list criteria name
					//input.next(); //ignore criteria name
					searchSpace[choiceCounter][i]=input.nextInt();
					}// end of loop with i
				choiceCounter++;
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		 */
	}
	/**
	 * @param String the input integer in string form
	 * takes input string, parses it and adds it to a matrix. 
	 */
	public int getNumberOfCriteria()
	{
		return Criteria; 
	}
	private void matrixHandoff(String input)
	{
		double inputDouble = Double.parseDouble(input);
		searchSpace[col][row]= inputDouble; 
		row++;
		if(row>=searchSpace[col].length)
		{
			col++;
			row=0;
		}
		if( col>searchSpace.length)
		{
			col=-1;
		}
		
//	System.out.println("InputNumber:"+ inputDouble);
	}
	private void countUp()
	{
		Criteria++;
	}
	private void rowSizeCalculator(NodeList inList, int depthLevel)
	{
		//System.out.println("Criteria:"+Criteria+"\n depthLevel:"+depthLevel);
		int start=0;
		if(depthLevel>0)
		{
			start =1;
		}
		for(int j=0; j<inList.getLength(); j++)
		{
			if(j%2==1 || depthLevel==0){
				if(inList.item(j).getChildNodes().item(1)!=null) 
				{
					rowSizeCalculator(inList.item(j).getChildNodes(), depthLevel+1);
				}
				else
				{
				//	System.out.println("Criteria++");
					//System.out.println("inList.item("+j+").getNodeName(): "+ inList.item(j).getNodeName().trim());
					//System.out.println("inList.item("+j+").getTextContext(): "+ inList.item(j).getTextContent().trim());
					countUp();					
				}
			}
		}		
	}
	/**
	 * @Param NodeList the list of components, depth level of 0
	 * This method takes the text context from the XML files in tree format and hands them off to be put in a matrix
	 */
	private void XMLInputRecurr(NodeList inList, int depthLevel)
	{
		int start=0;
		if(depthLevel>0)
		{
			start =1;
		}
		for(int j=0; j<inList.getLength(); j++)
		{
			if(j%2==1 || depthLevel==0){ //For some reason, the tree is miss-shaped when it is initially passed in
										 //this is why the code looks so weird here. 		
//				for(int i =0; i<depthLevel;i++) //for depth tabs
//				{
//					System.out.print("\t");
//				}
//				System.out.println("inList.item("+j+").getNodeName(): "+ inList.item(j).getNodeName());
				if(inList.item(j).getChildNodes().item(1)!=null) 
				{
					XMLInputRecurr(inList.item(j).getChildNodes(), depthLevel+1);
				}
				else
				{
					String output = inList.item(j).getTextContent().trim();
//					for(int i =0; i<depthLevel;i++) //for depth tabs
//					{
//						System.out.print("\t");
//					}
//					System.out.println("inList.item("+j+").getTextContext(): "+ output);
					matrixHandoff(output);
				}
			}
		}
	}
	public double[][] getSearchSpace()
	{
		return searchSpace;
	}
}

