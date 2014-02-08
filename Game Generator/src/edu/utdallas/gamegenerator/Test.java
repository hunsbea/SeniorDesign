package edu.utdallas.gamegenerator;

import edu.utdallas.gamegenerator.Characters.Characters;
import edu.utdallas.gamegenerator.LearningAct.LearningAct;
import edu.utdallas.gamegenerator.Lesson.Lesson;
import edu.utdallas.gamegenerator.Locale.Locale;
import edu.utdallas.gamegenerator.Structure.Game;
import edu.utdallas.gamegenerator.Structure.Scene;
import edu.utdallas.gamegenerator.Structure.Screen;
import edu.utdallas.gamegenerator.Structure.Structure;
import edu.utdallas.gamegenerator.Subject.Subject;
import edu.utdallas.gamegenerator.Theme.Theme;
import edu.utdallas.gamegenerator.old.Challenge.Challenge;

import javax.xml.bind.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: clocke
 * Date: 3/3/13
 * Time: 10:10 PM
 */
public class Test {
    private static Characters characters;
    private static Subject subject;
    private static LearningAct learningAct;
    private static Locale locale;
    private static Theme theme;
    private static Structure structure = new Structure();
    private static Game game;

    public static void main(String[] args) {
//        TestObjects testObjects = new TestObjects();
//        testObjects.getTheme().getIntro();
//        List<ScreenNode> blah2 = testObjects.getTheme().getIntro();
//        testObjects.getLocale().getAct(0);
//        Game game = testObjects.getStructure().createGame();

        try {
//            readXml();
            createXsd();
//            createXml(testObjects);
//            readXmlGenerated();
//            test();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        theme.setCharacters(characters);
//        theme.setSubject(subject);
//
//        List<LearningAct> learningObjectives = new ArrayList<LearningAct>();
//        learningObjectives.add(learningAct);
//
//        locale.setCharacters(characters);
//        locale.setLearningActs(learningObjectives);
//        locale.setTheme(theme);
//
//        structure.setTheme(theme);
//        structure.setLocale(locale);
//        game = structure.createGame();
//
//        try {
//            writeGameXml();
//            createXsd();
//            createXml(testObjects);
//            readXmlGenerated();
//            test();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println();
    }

    private static void writeGameXml() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Game.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("Game.xml");
        marshaller.marshal(game, file);

    }

    private static void createXsd() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Characters.class);
        SchemaOutputResolver sor = new MySchemaOutputResolver("Characters.xsd");
        jaxbContext.generateSchema(sor);

        jaxbContext = JAXBContext.newInstance(Theme.class);
        sor = new MySchemaOutputResolver("Theme.xsd");
        jaxbContext.generateSchema(sor);

//        jaxbContext = JAXBContext.newInstance(LearningAct.class);
//        sor = new MySchemaOutputResolver("LearningAct.xsd");
//        jaxbContext.generateSchema(sor);

        jaxbContext = JAXBContext.newInstance(Lesson.class);
        sor = new MySchemaOutputResolver("Lesson.xsd");
        jaxbContext.generateSchema(sor);

        jaxbContext = JAXBContext.newInstance(Challenge.class);
        sor = new MySchemaOutputResolver("Challenge.xsd");
        jaxbContext.generateSchema(sor);

        jaxbContext = JAXBContext.newInstance(Locale.class);
        sor = new MySchemaOutputResolver("Locale.xsd");
        jaxbContext.generateSchema(sor);

        jaxbContext = JAXBContext.newInstance(Subject.class);
        sor = new MySchemaOutputResolver("Subject.xsd");
        jaxbContext.generateSchema(sor);

    }

    private static void test() throws JAXBException {
        Scene scene = new Scene();
        List<Screen> screens = new ArrayList<Screen>();
        scene.setScreens(screens);

        JAXBContext jaxbContext = JAXBContext.newInstance(Scene.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("Scene.xml");
        marshaller.marshal(scene, file);

        file = new File("Scene.xml");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Scene scene2 = (Scene) unmarshaller.unmarshal(file);
        System.out.println(scene2);
    }

    private static void readXml() throws JAXBException {
        JAXBContext jaxbContext = null;
        File file = null;
        Unmarshaller unmarshaller = null;

        jaxbContext = JAXBContext.newInstance(Characters.class);
        file = new File("Characters.xml");
        unmarshaller = jaxbContext.createUnmarshaller();
        characters = (Characters) unmarshaller.unmarshal(file);

        jaxbContext = JAXBContext.newInstance(Subject.class);
        file = new File("Subject.xml");
        unmarshaller = jaxbContext.createUnmarshaller();
        subject = (Subject) unmarshaller.unmarshal(file);

        jaxbContext = JAXBContext.newInstance(Theme.class);
        file = new File("Theme.xml");
        unmarshaller = jaxbContext.createUnmarshaller();
        theme = (Theme) unmarshaller.unmarshal(file);

        jaxbContext = JAXBContext.newInstance(Locale.class);
        file = new File("Locale.xml");
        unmarshaller = jaxbContext.createUnmarshaller();
        locale = (Locale) unmarshaller.unmarshal(file);

        jaxbContext = JAXBContext.newInstance(LearningAct.class);
        file = new File("LearningAct.xml");
        unmarshaller = jaxbContext.createUnmarshaller();
        learningAct = (LearningAct) unmarshaller.unmarshal(file);

        System.out.println();
    }

    private static void createXml(TestObjects testObjects) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Characters.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("Characters.xml");
        marshaller.marshal(testObjects.getCharacters(), file);

        file = new File("LearningObjectiveOut.xml");
        jaxbContext = JAXBContext.newInstance(LearningAct.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(testObjects.getLearningAct(), file);

        file = new File("ThemeOut.xml");
        jaxbContext = JAXBContext.newInstance(Theme.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(testObjects.getTheme(), file);

        file = new File("LocaleOut.xml");
        jaxbContext = JAXBContext.newInstance(Locale.class);
        marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(testObjects.getLocale(), file);
    }

    private static void readXmlGenerated() throws JAXBException {
//        jaxbContext = JAXBContext.newInstance(Characters.class);
//        file = new File("E:\\Development\\Java\\GameGenerator\\xml\\Characters.xml");
//        unmarshaller = jaxbContext.createUnmarshaller();
//        characters = (Characters) unmarshaller.unmarshal(file);
//
//        jaxbContext = JAXBContext.newInstance(Subject.class);
//        file = new File("E:\\Development\\Java\\GameGenerator\\xml\\Subject.xml");
//        unmarshaller = jaxbContext.createUnmarshaller();
//        subject = (Subject) unmarshaller.unmarshal(file);

        JAXBContext jaxbContext = JAXBContext.newInstance(Theme.class);
        File file = new File("ThemeOut.xml");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        theme = (Theme) unmarshaller.unmarshal(file);

//        jaxbContext = JAXBContext.newInstance(Locale.class);
//        file = new File("E:\\Development\\Java\\GameGenerator\\xml\\LocaleOut.xml");
//        unmarshaller = jaxbContext.createUnmarshaller();
//        locale = (Locale) unmarshaller.unmarshal(file);
//
//        jaxbContext = JAXBContext.newInstance(LearningAct.class);
//        file = new File("E:\\Development\\Java\\GameGenerator\\xml\\LearningObjectiveOut.xml");
//        unmarshaller = jaxbContext.createUnmarshaller();
//        learningAct = (LearningAct) unmarshaller.unmarshal(file);

        System.out.println();
    }
}
