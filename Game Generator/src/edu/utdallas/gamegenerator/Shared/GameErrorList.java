package edu.utdallas.gamegenerator.Shared;

import java.util.ArrayList;

public class GameErrorList extends ArrayList<String>
{
	private boolean hasCriticalErrors = false;

	public boolean hasCriticalErrors() 
	{
		return hasCriticalErrors;
	}

	public void setHasCriticalErrors(boolean hasCriticalErrors) 
	{
		this.hasCriticalErrors = hasCriticalErrors;
	}
}
