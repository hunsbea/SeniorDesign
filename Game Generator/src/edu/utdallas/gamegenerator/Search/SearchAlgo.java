package edu.utdallas.gamegenerator.Search;

import java.util.LinkedList;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
/*Control Class
 */
public class SearchAlgo {
	private String xmlCharacters;
	private String xmlLessons;
	private String xmlChallenges;
	private String xmlLocale;
	private String xmlSubject;
	private String xmlTheme;
	private String[] allFiles = new String[6];
	private String[] gameComponents = { "Characters", "Lesson", "Challenge", "Locale", "Subject", "Theme"};
	private int[] allFileNumbers = new int[6];
	private InputWizard inputs;
	
	public SearchAlgo()//LinkedList<String> CriteriaList, LinkedList<String>inputedCriteriaList)
	{

		xmlCharacters="Characters0";
		xmlLessons="Lesson0";
		xmlChallenges="Challenge0";
		xmlLocale="Locale0";
		xmlSubject="Subject0";
		xmlTheme="Theme0";
		Matrix[] componentInputs = new Matrix[6];
		//SearchInput input;
		SearchSpace[] searchSpaces = new SearchSpace[6];
		Matrix[] componentInputSearchSpace=new Matrix[6];
		///////////////////////////
//		System.out.println("Test1, Start of SearchAlgo");
		for(int x=0; x<componentInputs.length; x++)
		{
		searchSpaces[x]= new SearchSpace(gameComponents[x]); 	
		//searchSpace which should be from the metadata tags	
		componentInputSearchSpace[x]= new Matrix(searchSpaces[x].getSearchSpace());
		//changes the SearchSpace array into a Matrix object
		}	
		
		for(int x=0; x<componentInputs.length; x++)
		{
		componentInputs[x]= new Matrix(new double[searchSpaces[x].getNumberOfCriteria()][searchSpaces[x].getNumberOfCriteria()]);
		}//end of loop with x
		//REPLACES ABOVE LOOP WITH WIZARD INPUTS
		componentInputs = getWizardInputs(componentInputs);
		printAllMatrixes(componentInputs);
		//AHP Matrix Math
		for(int x=gameComponents.length-1; x>=0; x--)
		{
			System.out.println("Matrcies for "+ gameComponents[x]);
			System.out.println("Search Input");
			printMatrix(componentInputs[x]);//brings the input into this class
//OLDCODE- this code uses the JAMA Matrix library to get the eigenvector matrix. However due to inconsistent results, it has been scrapped.  
//			EigenvalueDecomposition eigenDecomp= componentInputs[x].eig();
//creates new object that contains the eigenvector
//			Matrix weightedMatrix = eigenDecomp.getV();//makes the eigenvector matrix of the input
//			System.out.println("Real Eigenvalues");
//			printArray(eigenDecomp.getRealEigenvalues());
//			System.out.println("Imiganary Eigenvalues");
//			printArray(eigenDecomp.getImagEigenvalues());
			System.out.println("Weighted Matrix / Eigenvector");
			Matrix weightedMatrix = eigenvectorCalculation(componentInputs[x]);
			printMatrix(weightedMatrix);
			System.out.println("Component Metadata Input");
			printMatrix(componentInputSearchSpace[x]);
			Matrix criteriaScore = componentInputSearchSpace[x].times(weightedMatrix);
			//multiplies the weighted score matrix by the input matrix.
			printMatrix(criteriaScore);
			allFileNumbers[x]= getLargestValue(criteriaScore, x);
			allFiles[x]=gameComponents[x]+allFileNumbers[x];			
			System.out.println(allFiles[x]);
		
		}// end of loop with x 

		//Get rid of this when SearchInput is working. 
//		allFiles[0]=xmlCharacters;
//		allFiles[1]=xmlLessons;
//		allFiles[2]=xmlChallenges;
//		allFiles[3]=xmlLocale;
//		allFiles[4]=xmlSubject;
//		allFiles[5]=xmlTheme;
	}
	/*
	 * FAKE EIGENVECTOR CALCULATION
	 * The original eigenvector calculation through the library returned poor, unreadable
	 * results with no documentation. This prompted me (Kaleb) to find and employ this 
	 * alternative method. Its basicly weighting the matrix and putting into a 1 dimensional 
	 * matrix.
	 */
	private Matrix eigenvectorCalculation(Matrix inputMatrix)
	{
		double[][] inputArray = inputMatrix.getArray();
		double[][] outputArray = new double[inputArray.length][1];
		int rowLength = inputMatrix.getRowDimension();
		double[] rowSums = new double[inputArray.length];
		for(int y=0; y<inputArray.length;y++)
		{
			for(int x=0; x<inputArray[y].length; x++)
			{
				rowSums[x]+=inputArray[x][y];
			}
		}
		double rowSumsTotal =0;
		for(int x=0; x< rowSums.length; x++)
		{
			rowSums[x]= Math.pow(rowSums[x],1.0/rowSums.length);
			rowSumsTotal += rowSums[x];
		}
		for(int x=0; x< rowSums.length; x++)
		{
			rowSums[x]/=rowSumsTotal;
		}
		for(int x=0; x< rowSums.length; x++)
		{
			outputArray[x][0] = rowSums[x];
		}
				return new Matrix(outputArray) ;
	}
	
	private void printArray(double[] input)
	{
		for(int x =0; x<input.length;x++ )
		{
			System.out.println(x+": "+input[x]);
		}
	}
	//Creates, calls, and returns the input wizard inputs
	private Matrix[] getWizardInputs(Matrix[] componentInputs)
	{
		inputs = new InputWizard(componentInputs);
		
		
		return inputs.getWizardInputs();
	}
	private void printAllMatrixes(Matrix[] componentInputs) 
	{
		for(int x=0; x<componentInputs.length;x++)
		{
			System.out.println(gameComponents[x]);
			printMatrix(componentInputs[x]);
		}
		
	}
	public String getFileLocation()
	{
		return inputs.getFileLocation();	
	}
	public int getLargestValue(Matrix in, int componentNumber)
	{
		double[][] inputArray = in.getArray();
		double largestValue=inputArray[0][0];
		int largestIndex=0;
		for(int x = 0; x<inputArray.length; x++)
		{	
			if(inputArray[x][0]>largestValue)
			{
				largestValue = inputArray[x][0];
				largestIndex = x;
			}
			if(inputArray[x][0]==largestValue && (componentNumber==1 || componentNumber ==2) && x==allFileNumbers[4])
			{
				System.out.println("Adjusting "+gameComponents[componentNumber]+" "+x+" to Subject "+allFileNumbers[4]);
				largestValue = inputArray[x][0];
				largestIndex = x;
			}
		}//end of loop with x
		return largestIndex;
	}
	public Matrix getLastColumn(Matrix inputMatrix)
	{
		double[][] inputArray = inputMatrix.getArray();
		double[][] outputArray = new double[inputArray[0].length][1];
		for(int x =0; x<inputArray[0].length;x++ )
		{
			outputArray[x][0]=inputArray[x][0];//inputArray.length-1];
		}
//		System.out.println("Eigenvector alone");
//		printMatrix(new Matrix (outputArray));
		return new Matrix(outputArray);
	}
	public void printMatrix(Matrix  inputMatrix)
	{
		double[][] inputArray = inputMatrix.getArray();
		for(int x=0; x < inputArray.length; x++)
		{
			for (int y =0; y <  inputArray[x].length; y++)
			{
				System.out.printf("%.3f ",inputArray[x][y]);
			}
			System.out.println("");
		}
	}
	// Getter Methods
	public String getCharacters(){
		return xmlCharacters;
	}
	public String getLessons(){
		return xmlLessons;
	}
	public String getChallenges(){
		return xmlChallenges;
	}
	public String getLocale(){
		return xmlLocale;
	}
	public String getSubject(){
		return xmlSubject;
	}
	public String getTheme(){
		return xmlTheme;
	}
	public String[] searchResults(){
		return allFiles;
	}
}
