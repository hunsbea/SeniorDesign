package edu.utdallas.gamegenerator.Search;

import java.util.LinkedList;

/*
 * 
 * 
 * //CURRENTLY NOT BEING USED. USED BEFORE INPUT WIZARD WAS CREATED.
 * 
 * 
 * 
 */



//Entity Class- Creates the list of search criteria available. Has a validate feature to ensure the CriteriaInput is search-able. 
public class SearchCriteriaList {
	private String Criteria_1="Criteria_1";
	private String Criteria_2="Criteria_2";
	private String Criteria_3="Criteria_3";
	private String Criteria_4="Criteria_4";
	private String Criteria_5="Criteria_5";
	private String Criteria_6="Criteria_6";
	private LinkedList<String> criteriaList;
	
	public SearchCriteriaList(){
		criteriaList= new LinkedList<String>();
		criteriaList.add(Criteria_1);
		criteriaList.add(Criteria_2);
		criteriaList.add(Criteria_3);
		criteriaList.add(Criteria_4);
		criteriaList.add(Criteria_5);
		criteriaList.add(Criteria_6);
	}
	public LinkedList<String> getCriteriaList(){
		return criteriaList;
	}
	public Boolean componentValidate(String input){
		return criteriaList.contains(input);
	}
	

}
