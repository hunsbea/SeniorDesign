package edu.utdallas.gamegenerator.Search;

/* Boundary Class-
 * 
 * 
 * 1) Initializes SearchInput - Creates a LinkedList of desired criteria.
 * 2) Initializes SearchAlgo - Uses SearchComponents List and SearchInput List for SearchAlgo
 * 3) Returns the Search Results as an array. 
 * 
 * 
 * 
 * */
public class Search {
	
	
	//private SearchInput searchInput;
	private SearchAlgo searchAlgo;
	private String[] allFiles = new String[6];
	

	public Search(){
		///////////////////////////
//		System.out.println("Test -1, before SearchAlgoCalled");
		searchAlgo = new SearchAlgo();//searchInput.getSearchCriteria(), searchInput.getInputList());
		///////////////////////////
//		System.out.println("Test0, after SearchAlgoCalled");
		
		allFiles=searchAlgo.searchResults();
		printAllFiles();

	}
	public String getFileLocation()
	{
		return searchAlgo.getFileLocation();
	}
	public void printAllFiles()
	{
		System.out.println(allFiles[0]);
		System.out.println(allFiles[1]);
		System.out.println(allFiles[2]);		
		System.out.println(allFiles[3]);
		System.out.println(allFiles[4]);
		System.out.println(allFiles[5]);
	}
	public String[] getAllFiles(){
		
		return allFiles;
	}

}
