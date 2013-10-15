package edu.utdallas.gamegenerator;

import edu.utdallas.gamegenerator.Characters.*;
import edu.utdallas.gamegenerator.Characters.Characters;
import edu.utdallas.gamegenerator.LearningAct.Challenge.ChallengeOption;
import edu.utdallas.gamegenerator.LearningAct.Challenge.ChallengeOptionType;
import edu.utdallas.gamegenerator.LearningAct.Challenge.Reward;
import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacter;
import edu.utdallas.gamegenerator.LearningAct.Character.LearningActCharacterType;
import edu.utdallas.gamegenerator.LearningAct.LearningAct;
import edu.utdallas.gamegenerator.LearningAct.LessonAct;
import edu.utdallas.gamegenerator.LearningAct.Prop.GameButton;
import edu.utdallas.gamegenerator.LearningAct.Prop.GameText;
import edu.utdallas.gamegenerator.LearningAct.Prop.TextType;
import edu.utdallas.gamegenerator.LearningAct.Screen.*;
import edu.utdallas.gamegenerator.Locale.Locale;
import edu.utdallas.gamegenerator.Locale.LocaleScreen;
import edu.utdallas.gamegenerator.Locale.ObjectMovement;
import edu.utdallas.gamegenerator.Locale.ObjectMovementType;
import edu.utdallas.gamegenerator.Shared.*;
import edu.utdallas.gamegenerator.Structure.Structure;
import edu.utdallas.gamegenerator.Subject.Subject;
import edu.utdallas.gamegenerator.Theme.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: clocke
 * Date: 2/28/13
 * Time: 7:56 PM
 */
public class TestObjects {
    private Characters characters;
    private LearningAct learningAct;
    private Locale locale;
    private Structure structure;
    private Subject subject;
    private Theme theme;

    public TestObjects() {
        createNPC();
        createLearningObjective();
        createSubject();
        createTheme();
        createLocale();
        createStructure();
    }

    private void createLocale() {
        Map<ScreenType, LocaleScreen> localeScreenMap = new HashMap<ScreenType, LocaleScreen>();
        LocaleScreen localeScreen = new LocaleScreen();

        localeScreenMap.put(ScreenType.LESSON_STORY_INTRO, localeScreen);
        localeScreenMap.put(ScreenType.LESSON_STORY_OUTRO, localeScreen);
        localeScreenMap.put(ScreenType.LESSON, localeScreen);
        localeScreenMap.put(ScreenType.CHALLENGE, localeScreen);
        localeScreenMap.put(ScreenType.FAILURE, localeScreen);

        localeScreen.setBackground("locale screen background");



        locale = new Locale();
        Map<ScreenType, String> backgrounds = new HashMap<ScreenType, String>();
        backgrounds.put(ScreenType.CHALLENGE, "");
        backgrounds.put(ScreenType.NEUTRAL, "");
        backgrounds.put(ScreenType.FAILURE, "");
        backgrounds.put(ScreenType.LESSON, "");
        backgrounds.put(ScreenType.SUCCESS, "");
        backgrounds.put(ScreenType.LESSON_STORY_INTRO, "");
        backgrounds.put(ScreenType.LESSON_STORY_OUTRO, "");

        Map<ScreenType, Map<ButtonLocationType, SharedButton>> buttons = new HashMap<ScreenType, Map<ButtonLocationType, SharedButton>>();
        Map<ButtonLocationType, SharedButton> buttonButtons = new HashMap<ButtonLocationType, SharedButton>();
        buttonButtons.put(ButtonLocationType.NEXT, new SharedButton("",10,10,10,10, null));
        buttonButtons.put(ButtonLocationType.CHALLENGE_1, new SharedButton("",10,10,10,10, null));
        buttonButtons.put(ButtonLocationType.CHALLENGE_2, new SharedButton("",10,10,10,10, null));
        buttons.put(ScreenType.CHALLENGE, buttonButtons);
        Map<ScreenType, List<GameObject>> gameObjectsMap = new HashMap<ScreenType, List<GameObject>>();

        List<GameObject> gameObjects = getGameObjects();
        gameObjectsMap.put(ScreenType.CHALLENGE, gameObjects);


        List<LearningAct> learningActs = new ArrayList<LearningAct>();
        learningActs.add(learningAct);
        locale.setLearningActs(learningActs);

        Map<ScreenType, Map<LearningActCharacterType, SharedCharacter>> localeCharactersMap = new HashMap<ScreenType, Map<LearningActCharacterType, SharedCharacter>>();
        Map<LearningActCharacterType, SharedCharacter> localeCharacters = new HashMap<LearningActCharacterType, SharedCharacter>();
        List<ObjectMovement> movements = new ArrayList<ObjectMovement>();
        movements.add(new ObjectMovement(ObjectMovementType.WALK_ONTO_SCREEN, 1, 1, 5, 5));
        localeCharacters.put(LearningActCharacterType.HERO, new SharedCharacter(200, 200, 100, 100, null, LearningActCharacterType.HERO,
                movements));
        movements = new ArrayList<ObjectMovement>();
        movements.add(new ObjectMovement(ObjectMovementType.WALK_ONTO_SCREEN, 1, 1, 5, 5));
        localeCharacters.put(LearningActCharacterType.VILLIAN, new SharedCharacter(200, 200, 100, 100, null, LearningActCharacterType.VILLIAN,
                movements));
        movements = new ArrayList<ObjectMovement>();
        movements.add(new ObjectMovement(ObjectMovementType.WALK_ONTO_SCREEN, 1, 1, 5, 5));
        localeCharacters.put(LearningActCharacterType.ALT, new SharedCharacter(200, 200, 100, 100, null, LearningActCharacterType.ALT,
                movements));
        localeCharactersMap.put(ScreenType.LESSON, localeCharacters);

        Map<TextType, SharedInformationBox> informationBoxMap = new HashMap<TextType, SharedInformationBox>();
        SharedInformationBox informationBox = new SharedInformationBox(100, 100, 100, 100, "", "hero");
        informationBoxMap.put(TextType.HERO, informationBox);


        locale.setCharacters(characters);
        locale.setTheme(theme);

        localeScreen.setButtons(buttonButtons);
        localeScreen.setCharacters(localeCharacters);
        localeScreen.setGameObjects(gameObjects);


        localeScreen.setInformationBoxes(informationBoxMap);

        locale.setLocaleScreens(localeScreenMap);
    }

    private List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<GameObject>();
        gameObjects.add(new GameObject(100,100,100,100,""));
        return gameObjects;
    }

    private void createLearningObjective() {
        learningAct = new LearningAct();
        List<LessonAct> lessonActs = new ArrayList<LessonAct>();
        LessonAct lessonAct = new LessonAct();
        List<LessonScreen> learningObjectiveScreens = new ArrayList<LessonScreen>();
        LessonScreen learningObjectiveLesson = new LessonScreen();
        learningObjectiveLesson.setInformationBoxes(getGameTexts());
        learningObjectiveLesson.setButtons(getGameButtons());
        learningObjectiveLesson.setCharacters(getLearningObjectiveCharacters());
        learningObjectiveScreens.add(learningObjectiveLesson);
        learningObjectiveScreens.add(learningObjectiveLesson);
        lessonAct.setLessonScreens(learningObjectiveScreens);
        lessonActs.add(lessonAct);

        List<ChallengeScreen> challenges = new ArrayList<ChallengeScreen>();
        ChallengeScreen challenge = new ChallengeScreen();
        challenge.setButtons(null);
        challenge.setCharacters(getLearningObjectiveCharacters());
        challenge.setInformationBoxes(getGameTexts());
        challenge.setTimer(0);
        BaseScreen screen = new FailureScreen();
        screen.setCharacters(getLearningObjectiveCharacters());
        screen.setButtons(getGameButtons());
        screen.setInformationBoxes(getGameTexts());
        List<BaseScreen> baseScreens1 = new ArrayList<BaseScreen>();
        baseScreens1.add(screen);
        BaseScreen screen1 = screen.clone();
        screen1.getButtons().get(0).setTransitionType(TransitionType.NEXT_CHALLENGE);
        baseScreens1.add(screen);
        List<ChallengeOption> challengeOptions = new ArrayList<ChallengeOption>();
        challengeOptions.add(new ChallengeOption(ChallengeOptionType.BUTTON, "additional screens", new Reward(), TransitionType.ADDITIONAL, baseScreens1, ButtonLocationType.CHALLENGE_1));
        challengeOptions.add(new ChallengeOption(ChallengeOptionType.BUTTON, "end of story", null, TransitionType.END_OF_STORY, null, ButtonLocationType.CHALLENGE_2));
        challenge.setChallengeOptions(challengeOptions);
        challenge.setButtons(getGameButtons());
        challenges.add(challenge);
        challenges.add(challenge);
        lessonAct.setLessonChallenges(challenges);
        learningAct.setLessonActs(lessonActs);

    }

    private void createTheme() {
        theme = new Theme();
        List<ThemeScreen> themeScreens = new ArrayList<ThemeScreen>();
        ThemeScreen screen = new ThemeScreen();
        screen.setBackground("");
        Map<ButtonLocationType, SharedButton> buttons = new HashMap<ButtonLocationType, SharedButton>();
        Behavior behavior = new Behavior();
        buttons.put(ButtonLocationType.NEXT, new SharedButton("button intro 1",10, 30, 350, 350, new Behavior(BehaviorType.TRANSITION_BEHAVIOR)));
        screen.setButtons(buttons);
        List<GameObject> gameObjects = getGameObjects();
        screen.setGameObjects(gameObjects);
        Map<LearningActCharacterType, SharedCharacter> themeCharacters = new HashMap<LearningActCharacterType, SharedCharacter>();
        List<ObjectMovement> movements = new ArrayList<ObjectMovement>();
        movements.add(new ObjectMovement(ObjectMovementType.WALK_ONTO_SCREEN, 1, 1, 5, 5));
        themeCharacters.put(LearningActCharacterType.HERO, new SharedCharacter(200, 200, 100, 100, null, LearningActCharacterType.HERO,
                movements));
        movements = new ArrayList<ObjectMovement>();
        movements.add(new ObjectMovement(ObjectMovementType.WALK_OFF_SCREEN, 1, 1, 5, 5));
        themeCharacters.put(LearningActCharacterType.VILLIAN, new SharedCharacter(200, 200, 100, 100, null, LearningActCharacterType.HERO,
                movements));
        movements = new ArrayList<ObjectMovement>();
        movements.add(new ObjectMovement(ObjectMovementType.WALK_ONTO_SCREEN, 1, 1, 5, 5));
        themeCharacters.put(LearningActCharacterType.ALT, new SharedCharacter(200, 200, 100, 100, null, LearningActCharacterType.HERO,
                movements));
        screen.setThemeCharacters(themeCharacters);
        themeScreens.add(screen);

        List<ThemeStory> themeStories = new ArrayList<ThemeStory>();
        ThemeStory themeStory = new ThemeStory();
        List<ThemeStoryScreenIntro> themeStoryScreenIntros = new ArrayList<ThemeStoryScreenIntro>();
        ThemeStoryScreenIntro screenIntro = new ThemeStoryScreenIntro();

        List<LearningActCharacter> learningActCharacters = getLearningObjectiveCharacters();
        screenIntro.setCharacters(learningActCharacters);

        List<GameButton> gameButtons = getGameButtons();
        screenIntro.setButtons(gameButtons);

        List<GameText> gameTexts = getGameTexts();
        screenIntro.setInformationBoxes(gameTexts);

        themeStoryScreenIntros.add(screenIntro);
        themeStoryScreenIntros.add(screenIntro);
        themeStory.setIntro(themeStoryScreenIntros);

        gameButtons = getGameButtons();
        gameButtons.get(0).setTransitionType(TransitionType.NEXT_ACT);
        List<ThemeStoryScreenOutro> themeStoryScreenOutros = new ArrayList<ThemeStoryScreenOutro>();
        ThemeStoryScreenOutro themeStoryScreenOutro = new ThemeStoryScreenOutro();
        themeStoryScreenOutro.setCharacters(learningActCharacters);
        themeStoryScreenOutro.setButtons(gameButtons);
        themeStoryScreenOutro.setInformationBoxes(gameTexts);
        themeStoryScreenOutros.add(themeStoryScreenOutro);
        themeStory.setOutro(themeStoryScreenOutros);

        themeStories.add(themeStory);
        theme.setThemeStories(themeStories);
        theme.setIntroScreens(themeScreens);
        theme.setOutroScreens(themeScreens);
        theme.setSubject(subject);
        theme.setCharacters(characters);
    }

    private List<GameText> getGameTexts() {
        List<GameText> gameTexts = new ArrayList<GameText>();
        GameText gameText = new GameText();
        gameText.setTextType(TextType.HERO);
        gameText.setText("Hi");

        gameTexts.add(gameText);
        return gameTexts;
    }

    private List<GameButton> getGameButtons() {
        List<GameButton> gameButtons = new ArrayList<GameButton>();
        GameButton gameButton = new GameButton();
        gameButton.setReward(new Reward(500));
        gameButton.setButtonLocationType(ButtonLocationType.NEXT);
        gameButton.setText("Next");
        gameButton.setTransitionType(TransitionType.NEXT_SCREEN);

        gameButtons.add(gameButton);
        return gameButtons;
    }

    private List<LearningActCharacter> getLearningObjectiveCharacters() {
        List<LearningActCharacter> learningActCharacters = new ArrayList<LearningActCharacter>();
        LearningActCharacter character = new LearningActCharacter();
        character.setCharacterType(LearningActCharacterType.HERO);
        character.setMovementType(ObjectMovementType.WALK_ONTO_SCREEN);
        character.setTimer(100);
        learningActCharacters.add(character);
        return learningActCharacters;
    }

    private void createStructure() {
        structure = new Structure();
        structure.setLocale(locale);
        structure.setTheme(theme);
    }

    private void createNPC() {
        characters = new Characters();
        GameCharacter hero = new GameCharacter();
        hero.setName("Sir Solvesalot");
        hero.setDirectory("character_22");
        hero.setPrefix("char22");
        hero.getCharacterAsset(CharacterAssetType.STAND_SMILE_CLOSED);

        GameCharacter villian = new GameCharacter();
        villian.setName("Calcatron");
        villian.setDirectory("character_21");
        villian.setPrefix("char21");
        villian.getCharacterAsset(CharacterAssetType.STAND_SMILE_CLOSED);

        GameCharacter alt = new GameCharacter();
        alt.setName("TI-83+");
        alt.setDirectory("character_15");
        alt.setPrefix("char15");
        alt.getCharacterAsset(CharacterAssetType.STAND_SMILE_CLOSED);

        characters.setHero(hero);
        characters.setVillain(villian);
        characters.setAlt(alt);
    }

    private void createSubject() {
        subject = new Subject();
        subject.setSubject("Algebra");
        subject.setIntroText("Algebra Adventure");
    }

    public Characters getCharacters() {
        return characters;
    }

    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    public LearningAct getLearningAct() {
        return learningAct;
    }

    public void setLearningAct(LearningAct learningAct) {
        this.learningAct = learningAct;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
