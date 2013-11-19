import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

//The purpose of this program is to generate random, complete XML files that meet the current (new) specification
public class Main 
{
	public static Random rand = new Random();
	public static int minNumActs = 2;
	public static int minNumScenes = 2;
	public static int minNumScreens = 2;
	public static int minNumChars = 3;
	public static int minNumRewards = 1;
	public static int minElemWidth = 40;
	public static int elemWidthRange = 200;
	public static int randRange = 2;
	public static String[] characters = new String[30];
	public static String[] charPoses = { "StandSmileOpen", "StandSmileClosed", "StandOpen", "LEvil", "RPointUp", 
			"LPointNo", "WalkRSurprised", "RPointDown", "SitCheerChair", "SitAlmostRaiseChair", "SitSad" };
	public static String[] prop = { "Desk_1", "Coat_Rack_1", "Cabinet_C", "fence_1", "ATM_1_C", "Door_2_L", "bench long" };
	public static String[] generic = { "button", "TextBubble", "InformationBoxLarge", "Caption Box", "Regular Speech" };
	public static String[] education = { "BlackBoard", "BulletinBoard", "Compscreen" };
	public static int gameWidth = 1000, gameHeight = 500;
	public static Game game;
	public static double charWidthPerHeight = 0.6;
	
	public static void main(String[] args) 
	{
		//initialize character names, these are later linked to image directories
		for(int i = 0; i < characters.length; i++)
			characters[i] = "character_" + (i+1);
		
		//can't make up my mind, enums or Strings
		
		game = new Game();
		
		//create protagonist
		game.player = new PlayerCharacter();
		game.player.characterId = rand.nextInt(characters.length) + 1;
		game.player.name = characters[game.player.characterId - 1];
		game.player.behavior = randEnum(Character.Behavior.class);
		game.player.modelType = Character.MetaModelType.PROTAGONIST;
		int numRewards = rand.nextInt(randRange) + minNumRewards;
		game.player.rewards = new ArrayList<Reward>(numRewards);
		for(int i = 0; i < numRewards; i++)
			game.player.rewards.add(randReward());
		game.player.profile = new Profile();
		game.player.profile.photoImagePath = "profile_pic.jpg";
		game.player.profile.title = randEnum(Profile.Title.class);
		game.player.profile.skills = new ArrayList<Profile.Skill>(3);
		game.player.profile.skills.add(randEnum(Profile.Skill.class));
		game.player.profile.skills.add(randEnum(Profile.Skill.class));
		game.player.profile.skills.add(randEnum(Profile.Skill.class));
		game.player.profile.yearsOfExperience = rand.nextInt(20);
		game.player.profile.communication = randEnum(Profile.Proficiency.class);
		game.player.profile.leadership = randEnum(Profile.Proficiency.class);
		game.player.profile.teamwork = randEnum(Profile.Proficiency.class);
		game.player.profile.availability = randEnum(Profile.Proficiency.class);
		game.player.profile.attendance = randEnum(Profile.Proficiency.class);
		game.player.profile.demographics = new ArrayList<String>(2);
		game.player.profile.demographics.add("Male");
		game.player.profile.demographics.add("Caucasian");
		game.player.profile.degrees = new ArrayList<String>(1);
		game.player.profile.degrees.add("B.S. Computer Science, NorthEastern University");
		
		int numNonPlayers = rand.nextInt(randRange) + minNumChars;
		game.nonPlayers = new ArrayList<NonPlayerCharacter>(numNonPlayers);
		for(int i = 0; i < numNonPlayers; i++)
		{
			NonPlayerCharacter c = new NonPlayerCharacter();
			c.characterId = rand.nextInt(characters.length) + 1;
			while(c.characterId == game.player.characterId) //prevent duplicate protagonist id
				c.characterId = rand.nextInt(characters.length) + 1;
			c.name = characters[c.characterId - 1];
			c.modelType = randEnum(Character.MetaModelType.class);
			c.behavior = randEnum(Character.Behavior.class);
			numRewards = rand.nextInt(randRange) + minNumRewards;
			c.rewards = new ArrayList<Reward>(numRewards);
			for(int j = 0; j < numRewards; j++)
				c.rewards.add(randReward());
			c.profile = new Profile();
			c.profile.photoImagePath = "profile_pic.jpg";
			c.profile.title = randEnum(Profile.Title.class);
			c.profile.skills = new ArrayList<Profile.Skill>(3);
			c.profile.skills.add(randEnum(Profile.Skill.class));
			c.profile.skills.add(randEnum(Profile.Skill.class));
			c.profile.skills.add(randEnum(Profile.Skill.class));
			c.profile.yearsOfExperience = rand.nextInt(20);
			c.profile.communication = randEnum(Profile.Proficiency.class);
			c.profile.leadership = randEnum(Profile.Proficiency.class);
			c.profile.teamwork = randEnum(Profile.Proficiency.class);
			c.profile.availability = randEnum(Profile.Proficiency.class);
			c.profile.attendance = randEnum(Profile.Proficiency.class);
			c.profile.demographics = new ArrayList<String>(2);
			c.profile.demographics.add("Male");
			c.profile.demographics.add("Caucasian");
			c.profile.degrees = new ArrayList<String>(1);
			c.profile.degrees.add("B.S. Computer Science, NorthEastern University");
			game.nonPlayers.add(c);
		}

		//populate this game's acts
		int numActs = rand.nextInt(randRange) + minNumActs;
		game.acts = new ArrayList<Act>(numActs);
		for(int i = 0; i < numActs; i++)
		{
			Act a = new Act();
			a.purpose = "The purpose of this act to teach you the learning objective";
			a.learningObjective = "In this act, you will learn A, B, and C";
			a.currentScene = 0;
			
			//populate this act's scenes
			int numScenes = rand.nextInt(randRange) + minNumScenes;
			a.scenes = new ArrayList<Scene>(numScenes);
			for(int j = 0; j < numScenes; j++)
			{
				Scene s = new Scene();
				s.purpose = "Is this really different from the leaning objective?";
				s.learningObjective = "In this Scene, you will learn A.X, B.Y and C.Z";
				s.backgroundImagePath = "exampleBackground1.png";
				s.backgroundMusicPath = "exampleAudio1.midi";
				
				//populate this scene's screens
				int numScreens = rand.nextInt(randRange) + minNumScreens;
				s.screens = new ArrayList<Screen>(numScreens);
				for(int k = 0; k < numScreens; k++)
				{
					Screen sc = new Screen();
					
					//generate some GameElements for this Screen
					sc.elements = randElements();
					
					//possibly generate a challenge for this screen
					sc.hasChallenge = rand.nextInt(2) > 0;
					if(sc.hasChallenge)
					{
						//always a QuizChallenge type for now
						QuizChallenge qc = new QuizChallenge();

						//generate some GameElements for this Quiz
						sc.elements = randElements();
						
						qc.purpose = "This will teach you that one thing";
						qc.rewardScheme = randEnum(QuizChallenge.RewardScheme.class);
						qc.style = randEnum(QuizChallenge.Style.class);
						qc.reward = randReward();
						
						qc.name = "challenge " + k;
						qc.hints = new ArrayList<Hint>(3);
						qc.hints.add(new Hint("Think outside the box"));
						qc.hints.add(new Hint("The answer is right under your nose"));
						qc.hints.add(new Hint("It's easier than you think"));
						
						//possibly generate a timer for this quiz
						qc.isTimed = rand.nextInt(2) > 0;
						if(qc.isTimed)
						{
							qc.timer = new Timer();
							qc.timer.transitionSpeed = randEnum(Timer.TransitionSpeed.class);
							qc.timer.presentationSpeed = randEnum(Timer.PresentationSpeed.class);
							qc.timer.hintSpeed = randEnum(Timer.HintSpeed.class);
							qc.timer.animationSpeed = randEnum(Timer.AnimationSpeed.class);
							qc.timer.animationEffectSpeed = randEnum(Timer.AnimationEffectSpeed.class);
						}
						
						//IMPORTANT: When the challenge is written to XML, by default it will not
						//  write subclass properties even though the challenge has properties of
						//  the subclass QuizChallenge. Need to tell JAXB to write a QuizChallenge
						sc.challenge = qc;

						//possibly compete against your nemesis in this quiz
						qc.isCompetitive = rand.nextInt(2) > 0;
						if(qc.isCompetitive)
						{
							//TODO: how do you know which character is your nemesis?
						}

						//possibly quiz is proctored and another character must be present to give the quiz
						qc.isProctored = rand.nextInt(2) > 0;
						if(qc.isProctored)
						{
							//TODO: do we actually need special code for this? or is a proctor just another character
						}
						
						//create quiz questions
						qc.questions = new ArrayList<Question>(3);
						for(int m = 0; m < 3; m++)
						{
							Question q = new Question();
							q.questionText = "What is your name?";
							
							q.responses = new ArrayList<Response>(3);
							for(int n = 0; n < 3; n++)
							{
								Response r = new Response();
								r.answer = "Bob is my name";
								r.feedback = "Yes, that's correct!";
								r.evaluation = Response.Evaluation.UNDEFINED;
								q.responses.add(r);
							}
							
							qc.questions.add(q);
						}
					}
					
					s.screens.add(sc);
				}
				
				a.scenes.add(s);
			}
			
			game.acts.add(a);
		}
		
		writeToXML(game);

	}
	
	//generate some of each type of element
	public static ArrayList<GameElement> randElements()
	{
		int numProps = rand.nextInt(3);
		int numChars = rand.nextInt(3);
		int numGeneric = rand.nextInt(2);
		int numEducation = rand.nextInt(2);
		boolean mainCharPresent = rand.nextInt(2) > 0;
		
		int total = numProps + numChars + numGeneric + numEducation + (mainCharPresent ? 1 : 0);
		ArrayList<GameElement> elems = new ArrayList<GameElement>(total);
		
		if(mainCharPresent)
			elems.add(characterById(game.player.characterId));
		
		for(int i = 0; i < numProps; i++)
			elems.add(randProp());
		
		for(int i = 0; i < numChars; i++)
			elems.add(randCharacter());
		
		for(int i = 0; i < numGeneric; i++)
			elems.add(randGeneric());
		
		for(int i = 0; i < numEducation; i++)
			elems.add(randEducation());
		
		return elems;
	}
	
	public static String randPose()
	{
		return charPoses[rand.nextInt(charPoses.length)];
	}
	
	//A point well within the boundary of the game screen
	public static Point randPoint()
	{
		return new Point(rand.nextInt(gameWidth-200), rand.nextInt(gameHeight-200));
	}
	
	public static Dimension randSize()
	{
		int width = rand.nextInt(elemWidthRange) + minElemWidth;
		int height = (int)(width / charWidthPerHeight);
		return new Dimension(width, height);
	}
	
	public static CharacterElement characterById(int id)
	{
		//start with base GameElement
		CharacterElement c = fillBaseElement(new CharacterElement());
		c.imagePath = "character_" + id + "/char" + id + "_" + randPose() + ".png";
		c.characterId = id;
		c.animation = randAnimation();
		c.sound = randSound();
		c.name = "characterName";
		return c;
	}
	
	public static Animation randAnimation()
	{
		Point p = randPoint();
		Animation a = new Animation();
		a.movement = randEnum(Animation.Movement.class);
		a.timing = randEnum(Animation.Timing.class);
		a.path = randEnum(Animation.Path.class);
		a.loop = rand.nextInt(2) > 0;
		a.moveToX = p.x;
		a.moveToY = p.y;
		return a;
	}
	
	public static Sound randSound()
	{
		Sound s = new Sound();
		s.type = randEnum(Sound.Type.class);
		s.audioFilePath = "exampleSoundEffect.midi";
		return s;
	}
	
	public static CharacterElement randCharacter()
	{
		//randomly pick one of the available characters
		int id = game.nonPlayers.get(rand.nextInt(game.nonPlayers.size())).characterId;
		
		return characterById(id);
	}
	
	public static <T extends GameElement> T fillBaseElement(T e)
	{
		Point p = randPoint();
		Dimension d = randSize();
		e.animation = randAnimation();
		e.sound = randSound();
		e.name = "elementName";
		e.x = p.x;
		e.y = p.y;
		e.width = d.width;
		e.height = d.height;
		return e;
	}
	
	public static GenericElement randGeneric()
	{
		GenericElement g = fillBaseElement(new GenericElement());
		g.type = randEnum(GenericElement.GenericType.class);
		g.imagePath = generic[rand.nextInt(generic.length)] + ".png";
		g.color = Color.GRAY;
		g.text = "This text is inside of this generic element. I wonder what I will be displayed in.";
		return g;
	}
	
	public static EducationElement randEducation()
	{
		EducationElement e = fillBaseElement(new EducationElement());
		e.type = randEnum(EducationElement.EducationType.class);
		e.imagePath = education[rand.nextInt(education.length)] + ".png";
		return e;
	}
	
	public static PropElement randProp()
	{
		PropElement p = fillBaseElement(new PropElement());
		p.type = randEnum(PropElement.PropType.class);
		p.imagePath = prop[rand.nextInt(prop.length)] + ".png";
		return p;
	}
	
	// randomly choose an enum constant from the given enumeration
	public static <T extends Enum<T>> T randEnum(Class<T> enu)
	{
		T[] consts = enu.getEnumConstants();
		return consts[rand.nextInt(consts.length)];
	}
	
	public static Reward randReward()
	{
		int type = rand.nextInt(4);
		Reward r = new Reward();
		
		switch(type)
		{
		case 1:
			r.certificate = (Reward.Certificate)randEnum(Reward.Certificate.class);
			break;
		case 2:
			r.trophy = (Reward.Trophy)randEnum(Reward.Trophy.class);
			break;
		case 3:
			r.promotion = true;
			break;
		default:
			r.demotion = true;
			break;
		}
		
		return r;
	}
	
	public static void writeToXML(Game g)
	{
		try 
		{
			File file = new File("GAME.XML");
			JAXBContext jaxbContext = JAXBContext.newInstance(Game.class, QuizChallenge.class, GenericElement.class,
					PropElement.class, CharacterElement.class, EducationElement.class, NonPlayerCharacter.class,
					PlayerCharacter.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(g, file);
			//jaxbMarshaller.marshal(g, System.out);
		  }
		  catch (Exception e) 
		  {
				e.printStackTrace();
		  }
	}
}
