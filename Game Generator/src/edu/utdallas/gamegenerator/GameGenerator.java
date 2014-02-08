package edu.utdallas.gamegenerator;

import edu.utdallas.gamegenerator.Characters.Characters;
import edu.utdallas.gamegenerator.LearningAct.LearningAct;
import edu.utdallas.gamegenerator.LearningAct.LessonAct;
import edu.utdallas.gamegenerator.Lesson.Lesson;
import edu.utdallas.gamegenerator.Locale.Locale;
import edu.utdallas.gamegenerator.Search.Search;
import edu.utdallas.gamegenerator.Structure.Game;
import edu.utdallas.gamegenerator.Structure.Structure;
import edu.utdallas.gamegenerator.Subject.Subject;
import edu.utdallas.gamegenerator.Theme.Theme;
import edu.utdallas.gamegenerator.old.Challenge.Challenge;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.utdallas.gamegenerator.Layers.*;

/**
 * User: clocke
 * Date: 4/15/13
 * Time: 8:55 PM
 * hello world this is a test(this is another test)
 */
public class GameGenerator {
	public static void main(String[] args) {

		Search search = new Search();
		args=search.getAllFiles();

		if(args.length == 6) {
			Map<String, String> xmlFiles = new HashMap<String, String>();
			xmlFiles.put(CHARACTERS, args[0]);
			xmlFiles.put(LESSONS, args[1]);
			xmlFiles.put(CHALLENGES, args[2]);
			xmlFiles.put(LOCALE, args[3]);
			xmlFiles.put(SUBJECT, args[4]);
			xmlFiles.put(THEME, args[5]);

			GameGenerator gameGenerator = new GameGenerator();

			String exportFilename = search.getFileLocation();
			 //  String exportFilename = "C:\\Users\\Chris Mojica\\Documents\\2013\\Summer\\Git Output\\TestRunGame.xml";
			try {
				Layers layers = gameGenerator.loadXmlComponents(xmlFiles);
				Game game = gameGenerator.buildGame(layers);
				gameGenerator.exportGame(game, exportFilename);
			} catch (JAXBException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Please enter the file names of the layers:\n");
			System.out.println(
					"\tie: GameGenerator characters.xml lesson1.xml,lesson2xml challenge1.xml,challenge2.xml locale.xml subject.xml, theme.xml\n\n");
		}
	}

	/**
	 * Exports the Game object to xml
	 * @param game the Game object containing the game
	 * @throws JAXBException
	 */
	public void exportGame(Game game, String exportFilename) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		File file = new File(exportFilename);
		marshaller.marshal(game, file);
	}

	/**
	 * Builds the Game object from the layers
	 * @param layers a Layer object containing all game layers
	 * @return a Game object built from the layers
	 */
	public Game buildGame(Layers layers) {
		return layers.getStructure().createGame();
	}

	/**
	 * Loads the xml for each layer from a map of filenames
	 * @param xmlFiles a map of layer constants to filenames
	 * @return Layers object containing the loaded xml
	 * @throws JAXBException
	 */
	public Layers loadXmlComponents(Map<String, String> xmlFiles) throws JAXBException {
		Layers layers = new Layers();

		JAXBContext jaxbContext = null;
		File file = null;
		Unmarshaller unmarshaller = null;

		jaxbContext = JAXBContext.newInstance(Characters.class);
		file = new File("XMLrepo\\"+xmlFiles.get(CHARACTERS)+".xml");
		unmarshaller = jaxbContext.createUnmarshaller();
		layers.setCharacters((Characters) unmarshaller.unmarshal(file));

		jaxbContext = JAXBContext.newInstance(Subject.class);
		file = new File("XMLrepo\\"+xmlFiles.get(SUBJECT)+".xml");
		unmarshaller = jaxbContext.createUnmarshaller();
		layers.setSubject((Subject) unmarshaller.unmarshal(file));

		jaxbContext = JAXBContext.newInstance(Theme.class);
		file = new File("XMLrepo\\"+xmlFiles.get(THEME)+".xml");
		unmarshaller = jaxbContext.createUnmarshaller();
		layers.setTheme((Theme) unmarshaller.unmarshal(file));

		jaxbContext = JAXBContext.newInstance(Locale.class);
		file = new File("XMLrepo\\"+xmlFiles.get(LOCALE)+".xml");
		unmarshaller = jaxbContext.createUnmarshaller();
		layers.setLocale((Locale) unmarshaller.unmarshal(file));

		String[] lessonFiles = xmlFiles.get(LESSONS).split(",");

		jaxbContext = JAXBContext.newInstance(Lesson.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		List<Lesson> lessons = new ArrayList<Lesson>();
		for(int i = 0; i < lessonFiles.length; i++) {
			file = new File("XMLrepo\\"+lessonFiles[i]+".xml");
			Lesson lesson = ((Lesson) unmarshaller.unmarshal(file));
			lessons.add(lesson);
		}

		String[] challengeFiles = xmlFiles.get(CHALLENGES).split(",");

		jaxbContext = JAXBContext.newInstance(Challenge.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		List<Challenge> challenges = new ArrayList<Challenge>();
		for(int i = 0; i < challengeFiles.length; i++) {
			file = new File("XMLrepo\\"+challengeFiles[i]+".xml");
			Challenge challenge = ((Challenge) unmarshaller.unmarshal(file));
			challenges.add(challenge);
		}

		List<LearningAct> learningActs = new ArrayList<LearningAct>();
		for(int i = 0; i < lessons.size(); i++) {
			LearningAct learningAct = new LearningAct();
			List<LessonAct> lessonActs = new ArrayList<LessonAct>();
			LessonAct lessonAct = new LessonAct();
			lessonAct.setLessonScreens(lessons.get(i).getLessonScreens());
			lessonAct.setLessonChallenges(challenges.get(i).getLessonChallenges());
			lessonActs.add(lessonAct);
			learningAct.setLessonActs(lessonActs);
			learningActs.add(learningAct);
		}

		layers.setLearningActs(learningActs);

		layers.setStructure(new Structure());
		wireUpLayers(layers);

		return layers;
	}

	/**
	 * Injects dependent layers into each other
	 * @param layers a Layers object with all layers populated
	 */
	private void wireUpLayers(Layers layers) {
		layers.getLocale().setTheme(layers.getTheme());
		layers.getLocale().setLearningActs(layers.getLearningActs());
		layers.getLocale().setCharacters(layers.getCharacters());
		layers.getTheme().setSubject(layers.getSubject());
		layers.getTheme().setCharacters(layers.getCharacters());
		layers.getStructure().setLocale(layers.getLocale());
		layers.getStructure().setTheme(layers.getTheme());
	}


}
