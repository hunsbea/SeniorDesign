package edu.utdallas.gamegenerator;

import edu.utdallas.gamegenerator.Characters.Characters;
import edu.utdallas.gamegenerator.LearningAct.LearningAct;
import edu.utdallas.gamegenerator.Locale.Locale;
import edu.utdallas.gamegenerator.Structure.Structure;
import edu.utdallas.gamegenerator.Subject.Subject;
import edu.utdallas.gamegenerator.Theme.Theme;

import java.util.List;

/**
 * User: clocke
 * Date: 4/15/13
 * Time: 8:59 PM
 */
public class Layers {
    public static String CHARACTERS = "Characters";
    public static String LOCALE = "Locale";
    public static String SUBJECT = "Subject";
    public static String THEME = "Theme";
    public static String STRUCTURE = "Structure";
    public static String LESSONS = "Lessons";
    public static String CHALLENGES = "Challenges";

    private Characters characters;
    private List<LearningAct> learningActs;
    private Locale locale;
    private Subject subject;
    private Structure structure;
    private Theme theme;

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public List<LearningAct> getLearningActs() {
        return learningActs;
    }

    public void setLearningActs(List<LearningAct> learningActs) {
        this.learningActs = learningActs;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
