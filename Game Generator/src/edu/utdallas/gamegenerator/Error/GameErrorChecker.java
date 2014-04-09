package edu.utdallas.gamegenerator.Error;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.ws.Response;

import edu.utdallas.gamegenerator.Challenge.Challenge;
import edu.utdallas.gamegenerator.Challenge.Introduction;
import edu.utdallas.gamegenerator.Challenge.Item;
import edu.utdallas.gamegenerator.Challenge.MultipleChoiceItem;
import edu.utdallas.gamegenerator.Challenge.Option;
import edu.utdallas.gamegenerator.Challenge.QuizChallenge;
import edu.utdallas.gamegenerator.Challenge.Stem;
import edu.utdallas.gamegenerator.Challenge.Summary;
import edu.utdallas.gamegenerator.Character.Character;
import edu.utdallas.gamegenerator.Character.Profile;
import edu.utdallas.gamegenerator.Error.PreviewError.Level;
import edu.utdallas.gamegenerator.Error.PreviewError.Severity;
import edu.utdallas.gamegenerator.Search.InputWizard;
import edu.utdallas.gamegenerator.Shared.Asset;
import edu.utdallas.gamegenerator.Shared.ButtonAsset;
import edu.utdallas.gamegenerator.Shared.CharacterAsset;
import edu.utdallas.gamegenerator.Shared.ConversationBubbleAsset;
import edu.utdallas.gamegenerator.Shared.ImageAsset;
import edu.utdallas.gamegenerator.Shared.ImageHelper;
import edu.utdallas.gamegenerator.Shared.InformationBoxAsset;
import edu.utdallas.gamegenerator.Shared.ThoughtBubbleAsset;
import edu.utdallas.gamegenerator.Structure.*;

public class GameErrorChecker 
{
	// Check entire Game hierarchy for errors and return a list of errors
	public static GameErrorList checkErrors(final Game game, final int panelWidth, final int panelHeight)
	{
		//TODO: don't save if no game file open
		GameErrorList errors = new GameErrorList();
		
		//Check for Game-level errors
		if(game == null)
		{
			errors.add(new PreviewError(Level.GAME, Severity.HIGH, "No <Game> detected in XML file") {
				public void fixError() { } // can't fix this
			});
		}
		else
		{
			if(game.getName() == null)
			{
				errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Name> property of the game is not specified") {
					public void fixError() { game.setName("Game 1"); }
				});
			}
			
			// Check for global character errors
			if(game.getCharacters() == null || game.getCharacters().size() == 0)
			{
				errors.add(new PreviewError(Level.GAME, Severity.HIGH, "No <Characters> detected in Game") {
					public void fixError() { } //TODO
				});
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
						errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Name> property of Character found in position " + (i+1) + " is not specified") {
							public void fixError() { } //TODO
						});
						cName = "[in position " + (i+1) + "]";
					}
					if(isNullOrEmpty(characters.get(i).getBehavior()))
					{
						errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Behavior> property of Character " + cName + " is not specified") {
							public void fixError() { } //TODO
						});
					}
					if(characters.get(i).getCharacterID() == 0)
					{
						errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <CharacterID> property of Character " + cName + " is not specified") {
							public void fixError() { } //TODO
						});
					}
					if(characters.get(i).getProfile() == null)
					{
						errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Profile> property of Character " + cName + " is not specified") {
							public void fixError() { } //TODO
						});
					}
					// Check valid profile properties
					else
					{
						Profile profile = characters.get(i).getProfile();
						if(profile.getDemographics() == null || profile.getDemographics().size() == 0)
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Demographics> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						if(profile.getDegrees() == null || profile.getDegrees().size() == 0)
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Degrees> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						if(profile.getSkills() == null || profile.getSkills().size() == 0)
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Skills> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						if(isNullOrEmpty(profile.getAttendance()))
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Attendance> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						if(isNullOrEmpty(profile.getAvailability()))
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Availability> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						if(isNullOrEmpty(profile.getCommunication()))
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Communication> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						if(isNullOrEmpty(profile.getTeamwork()))
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Teamwork> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						if(isNullOrEmpty(profile.getTitle()))
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <Title> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}
						//TODO: 0 might actually be real data
						/*if(profile.getYearsOfExperience() == 0)
						{
							errors.add(new PreviewError(Level.GAME, Severity.LOW, "The <YearsOfExperience> property of Character " + cName + " profile is missing or empty") {
								public void fixError() { } //TODO
							});
						}*/
					}
				}
			}

			// Check for Act-level errors
			//TODO Acts wrapper check needed, or if this is not possible may need to change error to be more general
			if(game.getActs() == null || game.getActs().size() == 0)
			{
				errors.add(new PreviewError(Level.ACT, Severity.HIGH, "No <Acts> detected in Game") {
					public void fixError() { } //TODO
				});
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
						errors.add(new PreviewError(Level.ACT, Severity.LOW, "The <Name> property of Act found in position " + (i+1) + " is not specified") {
							public void fixError() { } //TODO
						});
						aName = "Act [in position " + (i+1) + "]";
					}
					
					//Check for Scene-level errors
					//TODO Scenes wrapper check needed, or if this is not possible may need to change error to be more general
					List<Scene> scenes = acts.get(i).getScenes();
					if(scenes == null || scenes.size() == 0)
					{
						errors.add(new PreviewError(Level.SCENE, Severity.HIGH, "No <Scenes> detected for " + aName) {
							public void fixError() { } //TODO
						});
					}
					else
					{
						for(int j = 0; j < scenes.size(); j++)
						{
							// Need a way to refer to which Scene has an error
							String sName = aName + " " + scenes.get(j).getName();
							if(isNullOrEmpty(aName))
							{
								errors.add(new PreviewError(Level.SCENE, Severity.LOW, "The <Name> property of Scene found in position " + (j+1) + " is not specified") {
									public void fixError() { } //TODO
								});
								sName = aName + " " + "Scene [in position " + (j+1) + "]";
							}
							if(scenes.get(j).getBackground() == null)
							{
								errors.add(new PreviewError(Level.SCENE, Severity.LOW, "The <Background> property of " + sName + " is not specified") {
									public void fixError() { } //TODO
								});
							}
							//TODO: should we warn about no background audio?
							//TODO: Ryan 4/8 10AM "Don't think so, Longstreet never specified that backgeound music is mandatory"
							//Check for Screen-level errors
							List<Screen> screens = scenes.get(j).getScreens();
							if(screens == null || screens.size() == 0)
							{
								errors.add(new PreviewError(Level.SCREEN, Severity.HIGH, "No <Screens> detected for " + sName) {
									public void fixError() { } //TODO
								});
							}
							else
							{
								
								for(int k = 0; k < screens.size(); k++)
								{
									// Need a way to refer to which Screen has an error
									String srName = sName + " " + screens.get(k).getName();
									if(isNullOrEmpty(srName))
									{
										errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "The <Name> property of Screen found in position " + (k+1) + " is not specified") {
											public void fixError() { } //TODO
										});
										srName = sName + " " + "Screen [in position " + (k+1) + "]";
									}
									
									//TODO: check challenges
									//Challenge checking
									//Need a way to check if their are two Challenges in a screen. First thought was
									//getting a list and checking if size was > 1 but the Screen class doesn't return
									//Challenges as lists cause it assumes their is only one. 
									final Challenge challenge = screens.get(k).getChallenge();
									if(challenge != null) 
									{
										if(challenge instanceof QuizChallenge)
										{
											QuizChallenge qChallenge = (QuizChallenge) challenge;
											
											if(qChallenge.getLayout() == null)
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "Layout in Challenge in " + srName + " is missing or empty") {
													public void fixError() { } //TODO
												});
											}
											
											List<Item> items = qChallenge.getItems();
											//Catches C_13 & C_14
											if(items == null || items.size() == 0) 
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "There are no Questions in the Challenge in " + srName) {
													public void fixError() { } //TODO
												});
											} 
											else 
											{
												for (int u = 0; u < items.size(); u++)
												{
													if(items.get(u) instanceof MultipleChoiceItem)
													{
														MultipleChoiceItem mcItem = (MultipleChoiceItem) items.get(u);
														
														List<Option> options = mcItem.getOptions();
														//Catches C_15 & C_16
														if(options == null || options.size() == 0) 
														{
															errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "There are no answers to choose from in Question " + (u+1) +" in " + srName) {
																public void fixError() { } //TODO
															});
														}
														else
														{
															for(Option o : options)
															{
																if(o == null || isNullOrEmpty(o.getText()))
																{
																	errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "Option in Challenge Question in " + srName + " has no text") {
																		public void fixError() { } //TODO
																	});
																}
															}
														}
														if(options.size() == 1)
														{
															errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "Only one response in Challenge Question in " + srName) {
																public void fixError() { } //TODO
															});
														}
														else if(options.size() > 6)
														{
															errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "More than 6 responses in Challenge Question in " + srName) {
																public void fixError() { } //TODO
															});
														}
														
														Stem stem = mcItem.getStem();
														if(stem == null)
														{
															errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "No STEM Collection in Challenge in " + srName) {
																public void fixError() { } //TODO
															});
														}
														else
														{
															if(stem.getStemQuestion() == null)
															{
																errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "No STEM question in Challenge in " + srName) {
																	public void fixError() { } //TODO
																});
															}
															else if(isNullOrEmpty(stem.getStemQuestion().getText()))
															{
																errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "STEM Question with empty Text field in Challenge in " + srName) {
																	public void fixError() { } //TODO
																});
															}
															if(stem.getStemText() == null)
															{
																errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "No STEM text in Challenge in " + srName) {
																	public void fixError() { } //TODO
																});
															}
															else if(isNullOrEmpty(stem.getStemText().getText()))
															{
																errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "STEM Text with empty Text field in Challenge in " + srName) {
																	public void fixError() { } //TODO
																});
															}
														}
													} 
													else 
													{
														//TODO Item has no type
													}
												}
											}
											
											List<Summary> summaries = qChallenge.getSummaries();
											if(summaries == null || summaries.size() == 0)
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "No Summaries in Challenge in " + srName) {
													public void fixError() { } //TODO
												});
											}
											
											Introduction intro = qChallenge.getIntro();
											if(intro == null)
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "No Introduction in Challenge in " + srName) {
													public void fixError() { } //TODO
												});
											}
											
										} 
										else 
										{
											//TODO May need enum for Level.CHALLENGE?
											errors.add(new PreviewError(Level.SCREEN, Severity.MEDIUM, "The xsi:type is not specified for the challenge of Screen found in position " + (k+1)) {
												public void fixError() { } //TODO
											});
										}
										
									} else {
										//TODO At this point the screen could either have no challenge at all or a null
										//challenge tag correct? One needing an error, the other being a normal occurrence
										//Any ways to distinguish
									}
									
									//Check for Asset-level errors
									final List<Asset> assets = screens.get(k).getAssets();
									if(assets == null || assets.size() == 0)
									{
										errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "No <Assets> detected for " + srName) {
											public void fixError() { } //TODO
										});
									}
									else
									{
										for(int m = 0; m < assets.size(); m++)
										{
											final Asset asset = assets.get(m);
											
											// Need a way to refer to which Asset has an error
											String asName = srName + " " + "Asset [in position " + (m+1) + "]";
											
											if(asset.getWidth() <= 0)
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "The <Width> property of " + asName + " is zero or not specified") {
													public void fixError() { asset.setWidth(100); } //TODO
												});
											}
											if(asset.getHeight() <= 0)
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "The <Height> property of " + asName + " is zero or not specified") {
													public void fixError() { asset.setHeight(100); } //TODO
												});
											}

											if(asset.getX() > panelWidth)				// too far right
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.LOW, asName + " is not visible, right of the coordinate system") {
													public void fixError() { asset.setX(panelWidth - 100); } //TODO
												});
											}
											if(asset.getX() + asset.getWidth() <= 0)	// too far left
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.LOW, asName + " is not visible, left of the coordinate system") {
													public void fixError() { asset.setX(100); } //TODO
												});
											}
											if(asset.getY() > panelHeight)				// too far down
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.LOW, asName + " is not visible, below the coordinate system") {
													public void fixError() { asset.setY(panelHeight - 100); } //TODO
												});
											}
											if(asset.getY() + asset.getHeight() <= 0)	// too far up
											{
												errors.add(new PreviewError(Level.SCREEN, Severity.LOW, asName + " is not visible, above the coordinate system") {
													public void fixError() { asset.setY(100); } //TODO
												});
											}
											
											if(asset instanceof CharacterAsset || asset instanceof ImageAsset)
											{
												if(isNullOrEmpty(asset.getDisplayImage()))
												{
													errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "The <DisplayImage> property of " + asName + " is not specified") {
														public void fixError() { asset.setDisplayImage("error_button.png"); }
													});
												}
												else if(asset instanceof CharacterAsset) {
													String baseDir = "Office, Classroom\\Characters\\";
													try{
														BufferedImage image = ImageIO.read(new File(baseDir + asset.getDisplayImage()));
														int width = image.getWidth();
														final double desiredWidth = asset.getWidth();
														double scaleFactor = desiredWidth / width;
														BufferedImage scaledImage = ImageHelper.getScaledImage(image, scaleFactor);
													} catch (IOException ex) {
														errors.add(new PreviewError( Level.SCREEN, Severity.MEDIUM, "The <DisplayImage> property of " + asName + " is missing from the repository") {
															public void fixError() { asset.setDisplayImage("error_button.png"); }
														});
													}
												} else if(assets.get(m) instanceof ImageAsset) {
													String baseDir = "Office, Classroom\\";
													try{
														BufferedImage image = ImageIO.read(new File(baseDir + assets.get(m).getDisplayImage()));
														int width = image.getWidth();
														final double desiredWidth = asset.getWidth();
														double scaleFactor = desiredWidth / width;
														BufferedImage scaledImage = ImageHelper.getScaledImage(image, scaleFactor);
													} catch (IOException ex) {
														errors.add(new PreviewError( Level.SCREEN, Severity.MEDIUM, "The <DisplayImage> property of " + asName + " is missing from the repository") {
															public void fixError() {} //TODO this fix error is below
														});
														asset.setDisplayImage("error_button.png");
													}
												}
											}
											if(asset instanceof ButtonAsset || asset instanceof ThoughtBubbleAsset || asset instanceof ConversationBubbleAsset || asset instanceof InformationBoxAsset)
											{
												if(isNullOrEmpty(asset.getName()))
												{
													errors.add(new PreviewError(Level.SCREEN, Severity.LOW, asName + " contains no text, will be filled with null") {
														public void fixError() { asset.setName("null"); }
													});
												}
												if(asset instanceof ThoughtBubbleAsset)
												{
													ThoughtBubbleAsset tbAsset = (ThoughtBubbleAsset)asset;
													if(tbAsset.getPointDirection() == null)
													{
														errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "ThoughtBubble in " + asName + " requires a PointDirection field") {
															public void fixError() { } //TODO
														});
													}
												}
												if(asset instanceof ConversationBubbleAsset)
												{
													ConversationBubbleAsset cbAsset = (ConversationBubbleAsset)asset;
													if(cbAsset.getPointDirection() == null)
													{
														errors.add(new PreviewError(Level.SCREEN, Severity.LOW, "ConversationBubble in " + asName + " requires a PointDirection field") {
															public void fixError() { } //TODO
														});
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
		}
		return errors;
	}
	private static boolean isNullOrEmpty(String s)
	{
		return s == null || s.equals("");
	}
}
