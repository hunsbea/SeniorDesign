package edu.utdallas.gamegenerator.Error;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.gamegenerator.Error.PreviewError.Severity;

public class GameErrorList extends ArrayList<PreviewError>
{
	private List<ActErrorList> actErrorLists;
	
	public boolean hasCriticalErrors() 
	{
		for(PreviewError e : this)
			if(e.getSeverity() == Severity.HIGH)
				return true;
		return false;
	}
}
