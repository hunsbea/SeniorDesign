package edu.utdallas.RepoUpdate;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Updates {
	
	String[] gameComponents = {"Characters", "Lesson", "Challenge", "Locale", "Subject", "Theme"};
	String[] gameComponentsClose = {"</Characters>", "</Lesson>", "</Challenge>", "</Locale>", "</Subject>", "</Theme>"};
	String[] repoGameComponents = {"characters", "lesson", "challenge", "locale", "subject", "theme"};
	String[] elementsOpen= {"<character>", "<Lessons>", "<Challenges>", "<Locales>", "<Subjects>", "<Themes>"};
	String[] elementsClose= {"</character>", "</Lessons>", "</Challenges>", "</Locales>", "</Subjects>", "</Themes>"};
	String[] Games= {"MathGame", "OfficeGame", "SpaceGame", "VocabGame", "HistoryGame"};
	Scanner input = new Scanner(System.in);
	int gameNumb;
	int checkCount=0;
	
	public Updates(){
		
	}
	
	public void addGame(String newGamename){
		
		for(int x=0; x<gameComponents.length; x++)
		{
			gameNumb=-1;			
			this.addtoXMLMetadata(newGamename, x);
			this.addToXMLRepo(newGamename, x);
		
		}//end for

	}//end addGame
	
	public void remakeRepo(){

		String textlocation;
		
		for (int x=0; x<gameComponents.length; x++){
			
			try{
				
			
			textlocation="New Games//"+Games[0]+"//"+ gameComponents[x]+"MetaData.xml";
			File copyGame = new File(textlocation);
			this.checkFile(copyGame);
			
			File original = new File("XMLmetadata//"+ repoGameComponents[x]+".xml");
			this.checkCreateFile(original);
			
			Scanner copy = new Scanner(copyGame);
			PrintWriter writeNewFile = new PrintWriter(original);
			
			writeNewFile.write(elementsOpen[x]+"\n");
			writeNewFile.write("\t<"+gameComponents[x]+" id=\"0\">\n");
			
			while (copy.hasNext()){
				writeNewFile.write(copy.nextLine()+"\n");
			}//end while
			writeNewFile.write(gameComponentsClose[x]+"\n");
			writeNewFile.write(elementsClose[x]+"\n");
			
			
			this.addToXMLRepo(Games[0], x);
			
			copy.close();
			writeNewFile.close();
			
			}//end try
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}//end catch 
		
		}//end for
		
		
		for(int y=1; y<Games.length; y++){
			
			this.addGame(Games[y]);
			
		}//end for
		
		
		
	}//end makeRepo
	
	private void addToXMLRepo(String newGamename, int x){
		try{
			
			String textlocation;
			textlocation="New Games//"+newGamename+"//"+ gameComponents[x]+".xml";			
			File fileAddition = new File(textlocation);
			this.checkFile(fileAddition);
			Scanner addFile = new Scanner(fileAddition);
			
			String newfilelocation="XMLrepo//"+gameComponents[x]+gameNumb+".xml";
			File check = new File(newfilelocation);
			this.checkCreateFile(check);
						
			PrintWriter writeNewFile = new PrintWriter(newfilelocation);
			while (addFile.hasNext()){
				writeNewFile.write(addFile.nextLine()+"\n");
			}//end while
	    
			System.out.println(gameComponents[x]+gameNumb+ " XMLrepo Added.\n");
			addFile.close();
			writeNewFile.close();
		}//end try
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}//end catch 
	}//end addToXMLRepo
	
	
	private void addtoXMLMetadata(String newGamename, int x){
		
		String textlocation;
		String nextInputLine;
		try{
			textlocation="New Games//"+newGamename+"//"+gameComponents[x]+"MetaData.xml";				
			File dataAddition = new File(textlocation);
			this.checkFile(dataAddition);
			
			File original = new File("XMLmetadata//"+ repoGameComponents[x]+".xml");
			this.checkFile(original);
			
			File temp = new File("XMLmetadata//temp.xml");
			if (temp.exists()){
				temp.setWritable(true);
				temp.delete();
			}
			
			
			Scanner copy = new Scanner(original);
			
			
			PrintWriter writeTemp = new PrintWriter("XMLmetadata//temp.xml");
			while (copy.hasNext()){
				writeTemp.write(copy.nextLine()+"\n");
			}//end while
			
			copy.close();
			writeTemp.close();
			
			original.setWritable(true);
			original.delete();
			
			//File temp = new File("XMLmetadata//temp.xml");
						
			String updatedfileloc="XMLmetadata//"+ repoGameComponents[x]+".xml"; 
			
			File check = new File(updatedfileloc);
			this.checkCreateFile(check);
						
			Scanner fileinput =new Scanner(temp);
			PrintWriter output = new PrintWriter(updatedfileloc);
			//System.out.println(fileinput.hasNext());
			while (fileinput.hasNext()){
				nextInputLine=fileinput.nextLine();
				//System.out.println(nextInputLine);
				
				if (nextInputLine.contains(gameComponents[x]+" id=")){
					gameNumb++;
				}
				//System.out.println("-"+nextInputLine + elements[x]);
				//System.out.println(nextInputLine.compareTo(elements[x]));
				
				if (nextInputLine.compareTo(elementsClose[x])==0){
					gameNumb++;
					Scanner addition = new Scanner(dataAddition);
					//addition.nextLine();
					output.write("<"+gameComponents[x]+" id=\""+ gameNumb+ "\">\n");
					while(addition.hasNext()){
						output.write(addition.nextLine()+"\n");
					}//end while
					output.write("</"+gameComponents[x]+">\n");
					addition.close();
				//All the addition has been outputed.
				}//end if
				
				output.write(nextInputLine+"\n");
				//System.out.println(fileinput.nextLine());
				
				temp.setWritable(true);
				temp.delete();
			}//end while
			
			System.out.println(repoGameComponents[x]+" XMLmetadata Updated.");
			output.close();
			fileinput.close();
			
		}//end try
		
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}//end catch
	}
	private void checkFile(File validate){
		checkCount++;
		if (validate.exists()==false){
			System.out.println("Could not find " + validate.getPath()+". Please check your files and try again."+ "\n ...Incomplete Update Terminated...");
			System.exit(checkCount);
		}
	}
	private void checkCreateFile(File check){
		if (check.exists()){
			System.out.println("You are trying to create a file that already exists: "+ check.getPath()+ "\n ...Incomplete Update Terminated...");
			System.exit(0);
		}
	}
}
