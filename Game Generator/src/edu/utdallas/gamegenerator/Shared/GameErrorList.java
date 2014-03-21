package edu.utdallas.gamegenerator.Shared;

import java.util.ArrayList;

import edu.utdallas.gamegenerator.Shared.GameError.Severity;

public class GameErrorList extends ArrayList<GameError>
{
	public boolean hasCriticalErrors() 
	{
		for(GameError e : this)
			if(e.getSeverity() == Severity.HIGH)
				return true;
		return false;
	}
}
