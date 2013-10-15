package edu.utdallas.gamegenerator.Structure;

import edu.utdallas.gamegenerator.Locale.Locale;
import edu.utdallas.gamegenerator.Shared.*;
import edu.utdallas.gamegenerator.Theme.Theme;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: clocke
 * Date: 2/17/13
 * Time: 6:52 PM
 */
public class Structure {
    private Theme theme;
    private Locale locale;
    private List<Act> acts;
    private Game game;

    /**
     * Builds the game from the injected layers
     * @return a Game object representing the created game
     */
    public Game createGame() {
        acts = new ArrayList<Act>();
        acts.add(createActFromScreens(theme.getIntro()));
        for(int i = 0; i < locale.getLearningActs().size(); i++) {
            acts.add(createActFromScreens(locale.getAct(i)));
        }
        acts.add(createActFromScreens(theme.getOutro()));
        game = new Game();
        game.setActs(acts);

        wireUpActs(acts);
        nameEverything();
        convertAssetsAndBehaviors();

        return game;
    }

    /**
     * Wires the transition behaviors between acts together.
     * It will search for transition behaviors with a null transitionId and set it to
     * the next act's first scene's first screen's id
     * @param acts a list of Acts
     */
    private void wireUpActs(List<Act> acts) {
        for(int i = 0; i < acts.size() - 1; i++) {
            Act act = acts.get(i);
            UUID nextActId = acts.get(i+1).getScenes().get(0).getScreens().get(0).getId();
            for(Scene scene : act.getScenes()) {
                ScreenNode screenNode = scene.getScreens().get(0);
                if(screenNode.getAssets() != null) {
                    for(Asset asset : screenNode.getAssets()) {
                        if(asset.getBehaviors() != null) {
                            for(Behavior behavior : asset.getBehaviors()) {
                                if(behavior != null &&
                                        BehaviorType.TRANSITION_BEHAVIOR.equals(behavior.getBehaviorType()) &&
                                        behavior.getTransitionId() == null) {
                                    behavior.setTransitionId(nextActId);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Names all acts, scenes, and screens in the game
     */
    private void nameEverything() {
        game.setName("Game");
        for(int a = 0; a < game.getActs().size(); a++) {
            Act act = game.getActs().get(a);
            act.setName("Act" + a);
            for(int b = 0; b < act.getScenes().size(); b++) {
                Scene scene = act.getScenes().get(b);
                scene.setName("Act" + a + " Scene" + b);
                for(int c = 0; c < scene.getScreens().size(); c++) {
                    ScreenNode screen = scene.getScreens().get(c);
                    screen.setName("Act" + a + " Scene" + b + " Screen" + c);
                }
            }
        }
    }

    /**
     * Converts every asset into it a new object of the correct type
     */
    private void convertAssetsAndBehaviors() {
        for(int a = 0; a < game.getActs().size(); a++) {
            Act act = game.getActs().get(a);
            for(int b = 0; b < act.getScenes().size(); b++) {
                Scene scene = act.getScenes().get(b);
                for(int c = 0; c < scene.getScreens().size(); c++) {
                    ScreenNode screen = scene.getScreens().get(c);
                    for(int d = 0; d < screen.getAssets().size(); d++) {
                        Asset asset = screen.getAssets().get(d);
                        Asset newAsset = null;
                        if("ImageAsset".equals(asset.getType())) {
                            newAsset = new ImageAsset(asset);
                        } else if ("ButtonAsset".equals(asset.getType())) {
                            newAsset = new ButtonAsset(asset);
                        } else if ("CharacterAsset".equals(asset.getType())) {
                            newAsset = new CharacterAsset(asset);
                        } else if ("InformationBoxAsset".equals(asset.getType())) {
                            newAsset = new InformationBoxAsset(asset);
                        }
                        if(newAsset != null) {
                            screen.getAssets().set(d, newAsset);
                        }
                        if(newAsset.getBehaviors() != null) {
                            for(int e = 0; e < newAsset.getBehaviors().size(); e++) {
                                Behavior behavior = newAsset.getBehaviors().get(e);
                                Behavior newBehavior = null;
                                if(BehaviorType.TRANSITION_BEHAVIOR == behavior.getBehaviorType()) {
                                    newBehavior = new TransitionBehavior(behavior);
                                } else if (BehaviorType.END_GAME_BEHAVIOR == behavior.getBehaviorType()) {
                                    newBehavior = new EndGameBehavior(behavior);
                                }
                                if(newBehavior != null) {
                                    newAsset.getBehaviors().set(e, newBehavior);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    /**
     * Creates an Act object from a list of ScreenNode
     * @param screenNodes a list of ScreenNode
     * @return an Act object containing all ScreenNodes from the list
     */
    private Act createActFromScreens(List<ScreenNode> screenNodes) {
        Act act = new Act();
        List<Scene> scenes = new ArrayList<Scene>();
        for(int i = 0; i < screenNodes.size(); i++) {
            Scene scene = new Scene();
            scene.setScreens(screenNodes.subList(i,i+1));
            scene.setBackground(screenNodes.get(i).getBackground());
            scenes.add(scene);
        }
        act.setScenes(scenes);
        return act;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }
}
