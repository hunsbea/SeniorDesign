package edu.utdallas.gamegenerator.Shared;

import java.util.ArrayList;
import java.util.List;

import edu.utdallas.gamegenerator.Character.Character;
import edu.utdallas.gamegenerator.Search.InputWizard;
import edu.utdallas.gamegenerator.Structure.*;

public class GameErrorChecker 
{
	// Check entire Game hierarchy for errors and return a list of errors
	public static GameErrorList checkErrors(Game game)
	{
		//TODO: don't save if no game file open
		GameErrorList errors = new GameErrorList();
		
		//Check for Game-level errors
		if(game == null)
		{
			errors.add("No <Game> detected in XML file");
			errors.setHasCriticalErrors(true);
		}
		else
		{
			if(game.getName() == null)
			{
				errors.add("The <Name> property of the game is not specified");
			}
			
			// Check for global character errors
			if(game.getCharacters() == null || game.getCharacters().size() == 0)
			{
				errors.add("No <Characters> detected in Game");
				errors.setHasCriticalErrors(true);
			}
			else
			{
				List<Character> characters = game.getCharacters();
				for(int i = 0; i < characters.size(); i++)
				{
					// Need a way to refer to which Character has an error
					String cName = characters.get(i).getName();
					if(isNullOrEmpty(cName))
					{
						errors.add("The <Name> property of Character found in position " + (i+1) + " is not specified");
						cName = "[in position " + (i+1) + "]";
					}
					if(isNullOrEmpty(characters.get(i).getBehavior()))
					{
						errors.add("The <Behavior> property of Character " + cName + " is not specified");
					}
					if(characters.get(i).getCharacterID() == 0)
					{
						errors.add("The <CharacterID> property of Character " + cName + " is not specified");
					}
					if(characters.get(i).getProfile() == null)
					{
						errors.add("The <Profile> property of Character " + cName + " is not specified");
					}
					// Check valid profile properties
					else
					{
						//TODO: check valid profile properties
					}
				}
			}

			// Check for Act-level errors
			if(game.getActs() == null || game.getActs().size() == 0)
			{
				errors.add("No <Acts> detected in Game");
				errors.setHasCriticalErrors(true);
			}
			else
			{
				List<Act> acts = game.getActs();
				for(int i = 0; i < acts.size(); i++)
				{
					// Need a way to refer to which Act has an error
					String aName = acts.get(i).getName();
					if(isNullOrEmpty(aName))
					{
						errors.add("The <Name> property of Act found in position " + (i+1) + " is not specified");
						aName = "Act [in position " + (i+1) + "]";
					}
					
					//Check for Scene-level errors
					List<Scene> scenes = acts.get(i).getScenes();
					if(scenes == null || scenes.size() == 0)
					{
						errors.add("No <Scenes> detected for " + aName);
						errors.setHasCriticalErrors(true);
					}
					else
					{
						for(int j = 0; j < scenes.size(); j++)
						{
							// Need a way to refer to which Scene has an error
							String sName = aName + " " + scenes.get(j).getName();
							if(isNullOrEmpty(aName))
							{
								errors.add("The <Name> property of Scene found in position " + (j+1) + " is not specified");
								sName = aName + " " + "Scene [in position " + (j+1) + "]";
							}
							if(scenes.get(j).getBackground() == null)
							{
								errors.add("The <Background> property of " + sName + " is not specified");
							}
							//TODO: should we warn about no background audio?
							
							//Check for Screen-level errors
							List<Screen> screens = scenes.get(j).getScreens();
							if(screens == null || screens.size() == 0)
							{
								errors.add("No <Screens> detected for " + aName + " " + sName);
								errors.setHasCriticalErrors(true);
							}
							else
							{
								//TODO: check challenges
								for(int k = 0; k < screens.size(); k++)
								{
									// Need a way to refer to which Screen has an error
									String srName = sName + " " + screens.get(k).getName();
									if(isNullOrEmpty(srName))
									{
										errors.add("The <Name> property of Screen found in position " + (k+1) + " is not specified");
										srName = sName + " " + "Screen [in position " + (k+1) + "]";
									}
									
									//Check for Asset-level errors
									List<Asset> assets = screens.get(k).getAssets();
									if(assets == null || assets.size() == 0)
									{
										errors.add("No <Assets> detected for " + srName);
									}
									else
									{
										for(int m = 0; m < assets.size(); m++)
										{
											// Need a way to refer to which Asset has an error
											String asName = srName + " " + "Asset [in position " + (m+1) + "]";
											
											if(assets.get(m).getWidth() == 0)
											{
												errors.add("The <Width> property of " + asName + " is zero or not specified");
											}
											if(assets.get(m).getHeight() == 0)
											{
												errors.add("The <Height> property of " + asName + " is zero or not specified");
											}
											if(assets.get(m).getX() > InputWizard.WIDTH - 150					// too far right
													|| assets.get(m).getX() + assets.get(m).getWidth() <= 0		// too far left
													|| assets.get(m).getY() > InputWizard.HEIGHT				// too far down
													|| assets.get(m).getY() + assets.get(m).getHeight() <= 0)	// too far up
											{
												errors.add(asName + " is not visible in the coordinate system");
											}
											
											if(assets.get(m) instanceof CharacterAsset || assets.get(m) instanceof ImageAsset)
											{
												if(isNullOrEmpty(assets.get(m).getDisplayImage()))
												{
													errors.add("The <DisplayImage> property of " + asName + " is not specified");
												}
											}
											if(assets.get(m) instanceof ButtonAsset || assets.get(m) instanceof ThoughtBubbleAsset || assets.get(m) instanceof ConversationBubbleAsset)
											{
												if(isNullOrEmpty(assets.get(m).getName()))
												{
													errors.add(asName + " contains no text");
												}
											}
										}
									}
								}
							}
							
						}
					}
				}
			}
		}
		return errors;
	}
	private static boolean isNullOrEmpty(String s)
	{
		return s == null || s.equals("");
	}
}
