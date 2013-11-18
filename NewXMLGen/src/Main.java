import java.util.ArrayList;
import java.util.Random;

//The purpose of this program is to generate random, complete XML files that meet the current (new) specification
public class Main 
{
	public static Random rand = new Random();
	public static int minNumActs = 3;
	public static int minNumScenes = 2;
	public static int minNumScreens = 3;
	public static int minNumChars = 1;
	public static int minNumRewards = 1;
	public static int randRange = 5;
	public static String[] characters = new String[30];
	
	public static void main(String[] args) 
	{
		//initialize character names, these are later linked to image directories
		for(int i = 0; i < characters.length; i++)
			characters[i] = "character_" + (i+1);
		
		//can't make up my mind, enums or Strings
		
		Game game = new Game();
		
		//create protagonist
		game.player = new PlayerCharacter();
		game.player.characterId = rand.nextInt(characters.length) + 1;
		game.player.name = characters[game.player.characterId - 1];
		game.player.behavior = (Character.Behavior)randEnum(Character.Behavior.class);
		game.player.modelType = Character.MetaModelType.PROTAGONIST;
		int numRewards = rand.nextInt(randRange) + minNumRewards;
		game.player.rewards = new ArrayList<Reward>(numRewards);
		for(int i = 0; i < numRewards; i++)
			game.player.rewards.add(randReward());
		game.player.profile = new Profile();
		game.player.profile.photoImagePath = "profile_pic.jpg";
		game.player.profile.title = (Profile.Title)randEnum(Profile.Title.class);
		game.player.profile.skills = new ArrayList<Profile.Skill>(3);
		game.player.profile.skills.add((Profile.Skill)randEnum(Profile.Skill.class));
		game.player.profile.skills.add((Profile.Skill)randEnum(Profile.Skill.class));
		game.player.profile.skills.add((Profile.Skill)randEnum(Profile.Skill.class));
		game.player.profile.yearsOfExperience = rand.nextInt(20);
		game.player.profile.communication = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
		game.player.profile.leadership = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
		game.player.profile.teamwork = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
		game.player.profile.availability = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
		game.player.profile.attendance = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
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
			c.modelType = (Character.MetaModelType)randEnum(Character.MetaModelType.class);
			c.behavior = (Character.Behavior)randEnum(Character.Behavior.class);
			numRewards = rand.nextInt(randRange) + minNumRewards;
			c.rewards = new ArrayList<Reward>(numRewards);
			for(int j = 0; j < numRewards; j++)
				c.rewards.add(randReward());
			c.profile = new Profile();
			c.profile.photoImagePath = "profile_pic.jpg";
			c.profile.title = (Profile.Title)randEnum(Profile.Title.class);
			c.profile.skills = new ArrayList<Profile.Skill>(3);
			c.profile.skills.add((Profile.Skill)randEnum(Profile.Skill.class));
			c.profile.skills.add((Profile.Skill)randEnum(Profile.Skill.class));
			c.profile.skills.add((Profile.Skill)randEnum(Profile.Skill.class));
			c.profile.yearsOfExperience = rand.nextInt(20);
			c.profile.communication = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
			c.profile.leadership = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
			c.profile.teamwork = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
			c.profile.availability = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
			c.profile.attendance = (Profile.Proficiency)randEnum(Profile.Proficiency.class);
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
					
					//possibly generate a challenge for this screen
					sc.hasChallenge = rand.nextInt(2) > 0;
					if(sc.hasChallenge)
					{
						//always a QuizChallenge type for now
						QuizChallenge qc = new QuizChallenge();
						qc.purpose = "This will teach you that one thing";
						qc.rewardScheme = (QuizChallenge.RewardScheme)randEnum(QuizChallenge.RewardScheme.class);
						
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
							qc.timer.transitionSpeed = (Timer.TransitionSpeed)randEnum(Timer.TransitionSpeed.class);
							qc.timer.presentationSpeed = (Timer.PresentationSpeed)randEnum(Timer.PresentationSpeed.class);
							qc.timer.hintSpeed = (Timer.HintSpeed)randEnum(Timer.HintSpeed.class);
							qc.timer.animationSpeed = (Timer.AnimationSpeed)randEnum(Timer.AnimationSpeed.class);
							qc.timer.animationEffectSpeed = (Timer.AnimationEffectSpeed)randEnum(Timer.AnimationEffectSpeed.class);
						}
						
						//IMPORTANT: When the challenge is written to XML, by default it will not
						//  write subclass properties even though the challenge has properties of
						//  the subclass QuizChallenge. Need to tell JAXB to write a QuizChallenge
						sc.challenge = qc;

						//possibly compete against your nemesis in this quiz
						qc.isCompetitive = rand.nextInt(2) > 0;
						if(qc.isCompetitive)
						{
							//TODO: add nemesis as character in quiz
							//what else? this isn't specified completely enough
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
	
	//TODO: these methods
	public CharacterElement characterById(int id)
	{
		return null;
	}
	
	public CharacterElement randCharacter()
	{
		return null;
	}
	
	public GenericElement randGeneric()
	{
		return null;
	}
	
	public EducationElement randEducation()
	{
		return null;
	}
	
	public PropElement randProp()
	{
		return null;
	}
	
	// randomly choose an enum constant from the given enumeration
	public static Enum randEnum(Class enu)
	{
		Enum[] consts = (Enum[])enu.getEnumConstants();
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
	}
}
