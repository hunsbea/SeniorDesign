package edu.utdallas.gamegenerator.Search;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import edu.utdallas.RepoUpdate.BackgroundSelectWindow;
import edu.utdallas.RepoUpdate.ScenePanel;
import edu.utdallas.RepoUpdate.CharacterSelectWindow;
import edu.utdallas.RepoUpdate.Updates;
import edu.utdallas.gamegenerator.Shared.*;
import edu.utdallas.gamegenerator.Structure.*;

import Jama.Matrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;


public class InputWizard implements ActionListener {
/**
 * @Authors Kaleb Breault, Alex Hunsberger, Zayed Alfalasi, Abdulla Alfalasi, Jacob Dahleen
 * This class makes a GUI interface for entering input and previewing XML games
 * implements ActionListener so a subclass for it is not needed. 
 */
	
	private static int WIDTH = 1280;
	private static int HEIGHT = 481;
	private Matrix[] componentInputs;
	private boolean submitClicked = false;
 	private JFrame window = new JFrame();
 	private JPanel mainPannel;
 	private JMenuBar menuBar;
 	private JMenu menu;
 	private JMenu fileMenu;
 	private JMenuItem openFileItem;
 	private JMenuItem addToRepo;
 	private JMenuItem remakeRepo;
 	private JMenuItem saveToRepo;
 	private static String label1 = "Preview after generating: ";
 	private JTree actTree;
 	private ScenePanel scenePanel;
 	//JD character selection class parameters
 	private CharacterSelectWindow characterSelectWindow;
 	private CharacterAsset characterSelectAsset;
 	private BackgroundSelectWindow backgroundSelectWindow;
 	private String backgroundSelectPath;
 	public enum gameLevel{GAME, ACT, SCENE, SCREEN, CHALLENGE};
 	private gameLevel selectedLevel = null;
 	private JButton characterButton;
 	private JButton backgroundButton;
 	private Scene lastSelectedScene = null;
 	private ScreenNode lastSelectedScreen = null;
 	//JD end
 	
 	private Game game;
 	
 	private String playerGender= "none"; 
  	private String playerAge= "none";	
  	private String playerDress= "none";  	
   	private String gameTheme= "none";	
 	private String gameSubject= "none";  	
 	private String gameSetting= "none";   	
 	private String gameDifficulty= "none"; 
 	private JFileChooser saveFileChooser;
 	private String gameSavePath = "C:\\";
 	private static final int wizardRowSize = 10; //row size for wizard
 	private String gameGradeLevel = "none";
	private Updates updater;
	private String charBaseDir = "Office, Classroom\\Characters\\";
	private Double xtraXposition = 180.00;
	private int imgtrack = 0;
	private int selectedValue = 1;
	CharacterAsset c;
	final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	public InputWizard(Matrix[] input)
	{
		componentInputs = input;
		initializeComponentInputs();
        window.setSize(WIDTH, HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int nextOpenRow =0; // next available row slot
        final String none = "no";
        mainPannel = new JPanel(new GridLayout(wizardRowSize,1));
// making menu        
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        openFileItem = new JMenuItem("Open Game File");
        openFileItem.setActionCommand("openFile");
        openFileItem.setMnemonic(KeyEvent.VK_O);
        openFileItem.addActionListener(this);
        fileMenu.add(openFileItem);
        menuBar.add(fileMenu);
        menu = new JMenu("Repository Tools");
        menu.setMnemonic(KeyEvent.VK_R);
        menuBar.add(menu);
        addToRepo = new JMenuItem("Add game to repository", KeyEvent.VK_A);
        addToRepo.setActionCommand("addToRepo");
        addToRepo.addActionListener(this);
        remakeRepo = new JMenuItem("Remake the repository", KeyEvent.VK_R);
        remakeRepo.addActionListener(this);
        remakeRepo.setActionCommand("remakeRepo");
        menu.add(addToRepo);
        menu.add(remakeRepo);
        saveToRepo = new JMenuItem ("Save Game File", KeyEvent.VK_S);
        saveToRepo.addActionListener(this);
        saveToRepo.setActionCommand("saveToRepo");
        fileMenu.add(saveToRepo);
        //JD
        characterSelectWindow = new CharacterSelectWindow(window);
        characterSelectWindow.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if(characterSelectWindow.getNewCharacterAsset() == null)
				{
					return;
				}
				else
				{
					List<Asset> currentAssets = lastSelectedScreen.getAssets();
					currentAssets.add(characterSelectWindow.getNewCharacterAsset());
					lastSelectedScreen.setAssets(currentAssets);
					displayScreen(lastSelectedScene, lastSelectedScreen);
				}
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        backgroundSelectWindow = new BackgroundSelectWindow(window);
        backgroundSelectWindow.addWindowListener(new WindowListener(){
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				if(backgroundSelectWindow.getNewBackgroundPath() == null)
				{
					return;
				}
				else
				{
					lastSelectedScene.setBackground(backgroundSelectWindow.getNewBackgroundPath());
					scenePanel.loadBackground(lastSelectedScene.getBackground());
				}
			}
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        // create tree-structure for viewing Acts/Scenes
        actTree = new JTree();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("No game file selected");
        DefaultTreeModel model = new DefaultTreeModel(rootNode);
        actTree.setModel(model);
        //DefaultMutableTreeNodes are not JComponents and therefore cannot individually have actionListeners
        // that was a TERRIBLE design decision, because now all I have to work with are the names of the nodes 
        // in this one global tree selection listener
        actTree.addTreeSelectionListener(new TreeSelectionListener() 
        {
            public void valueChanged(TreeSelectionEvent e) 
            {
            	if(game == null) { return; } // don't try to display an empty game
            	DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) actTree.getLastSelectedPathComponent();
            	if (selectedNode != null && selectedNode.isLeaf()) //a screen
            	{
                	String screenName = selectedNode.toString();
                	System.out.println(""+screenName);
            		String sceneName = selectedNode.getParent().toString();
            		System.out.println(""+sceneName);
            		String actName = selectedNode.getParent().getParent().toString();
            		//JD
            		characterButton.setEnabled(true);
            		backgroundButton.setEnabled(false);
            		selectedLevel = gameLevel.SCREEN;
            		lastSelectedScene = getScene(actName, sceneName);
            		lastSelectedScreen = getScreen(actName, sceneName, screenName);
            		//JD end
            		displayScreen(getScene(actName, sceneName),getScreen(actName, sceneName, screenName));
            	}
            	else if (selectedNode != null && selectedNode.getLastChild() != null && selectedNode.getLastChild().isLeaf()) //a scene painted
            	{
            		String sceneName = selectedNode.toString();
            		String actName = selectedNode.getParent().toString();
          			Scene s = getScene(actName, sceneName);
          			characterButton.setEnabled(false);
            		backgroundButton.setEnabled(true);
          			selectedLevel = gameLevel.SCENE;
          			lastSelectedScene = getScene(actName, sceneName);
          			scenePanel.clear();
          			System.out.println("calling clear scene node\n");
          			scenePanel.loadBackground(s.getBackground());
            	}
            	else if(selectedNode != null && selectedNode.isRoot()) //game level
            	{
            		ArrayList<CharacterAsset> UNIQchars = new ArrayList<CharacterAsset>();
            		
            		ArrayList<CharacterAsset> chars = null;
            		
            		chars = getCharacters();
            		
            		scenePanel.clear();
            		System.out.println("calling clear rootnode\n");
            		UNIQchars.clear();
            		xtraXposition = 180.00;
            		System.out.println("uniq chars before putting in size "+UNIQchars.size());
            		characterButton.setEnabled(false);
            		backgroundButton.setEnabled(false);
            		selectedLevel = gameLevel.GAME;
            		for (CharacterAsset ca : chars){
            			
            			//create a copy as not to modify the original coordinates
            			CharacterAsset c = (CharacterAsset)(Asset)ca.clone();
            			
            			//make all images a standard width
            			int desiredWidth = 180;
            			double mFactor = desiredWidth / c.getWidth();
            			c.setWidth(c.getWidth() * mFactor);
            			c.setHeight(c.getHeight() * mFactor);
            			
            			if(UNIQchars.size() == 0){
            				UNIQchars.add(c);
    						c.setLocX(0);
    						c.setLocY(0);
            				scenePanel.loadAssetToRoot(c, charBaseDir);
            				
            			}
            			else{	
            				int i =0;
            				
            				while(true){
            					StringTokenizer st = new StringTokenizer(c.getDisplayImage(), "\\");
            					String firstPart = st.nextToken();
            					StringTokenizer st2 = new StringTokenizer(UNIQchars.get(i).getDisplayImage(), "\\");
            					String firstPartU = st2.nextToken();
            					
            					
            					if(!firstPart.equals(firstPartU) && i == UNIQchars.size()-1)
            					{
            						UNIQchars.add(c);
            						try{
            						
            						c.setLocX(xtraXposition);
            						c.setLocY(0);
            						
            						scenePanel.loadAssetToRoot(c, charBaseDir);
            						xtraXposition = xtraXposition+ xtraXposition;
            						
            						} catch(Exception ex) {}
            						break;
            					}
            					if (firstPart.equals(firstPartU)){
               						
            						break;
            						
            					}
            					i++;
            					if(i == UNIQchars.size()){
     
            						break;
            					}
            					
            				}
            			}

            		}
            		
            		
            	}//end else if
            	else {
            		characterButton.setEnabled(false);
            		backgroundButton.setEnabled(false);
            		selectedLevel = gameLevel.ACT;
            	}
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(actTree);
        
        // create tabbed layout
        
        JPanel generateTab = new JPanel(new BorderLayout());
        JPanel previewTab = new JPanel(new BorderLayout());
        tabbedPane.addTab("Generate", null, generateTab);
        tabbedPane.addTab("Preview", null, previewTab);
        JPanel browsePanel = new JPanel(new BorderLayout()); // browse/click on Acts/Scenes
        browsePanel.add(scrollPane);
        
        JPanel toolbarPanel = new JPanel(new GridLayout(3,2,0,0));
        //JPanel toolbarPanel = new JPanel(new GridLayout(6,1));
        //JSplitPane splitTreePane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,browsePanel, toolbarPanel);
        //JButton characterButton = new JButton("Character");
        characterButton = new JButton("Character");
        characterButton.addActionListener(this);
        characterButton.setEnabled(false);
        characterButton.setActionCommand("charactersToolbar");
        toolbarPanel.add(characterButton);
        JButton propButton = new JButton("Prop");
        toolbarPanel.add(propButton);
        JButton speechButton = new JButton("Speech");
        toolbarPanel.add(speechButton);
        JButton buttonButton = new JButton("Button");
        toolbarPanel.add(buttonButton);
        JButton soundButton = new JButton("Sound");
        toolbarPanel.add(soundButton);
        backgroundButton = new JButton("Background");
        backgroundButton.addActionListener(this);
        backgroundButton.setEnabled(false);
        backgroundButton.setActionCommand("backgroundToolbar");
        toolbarPanel.add(backgroundButton);
        //splitTreePane.setBottomComponent(toolbarPanel);
        browsePanel.add(toolbarPanel, BorderLayout.SOUTH);
        toolbarPanel.setPreferredSize(new Dimension(0, 80));
        
        scenePanel = new ScenePanel(); // view/edit the Scene selected in the browse panel
        scenePanel.setLayout(null);
        JSplitPane splitPreviewPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, browsePanel, scenePanel);
        splitPreviewPane.setEnabled(false);
        previewTab.add(splitPreviewPane);
        
        
        
        //Jcheckbox
        JCheckBox tickBox = new JCheckBox(label1);
        tickBox.setHorizontalTextPosition(SwingConstants.LEFT);
        ActionListener actionListener = new ActionListener() {

        	@Override
        public void actionPerformed(ActionEvent actionEvent) {

            AbstractButton absB = (AbstractButton) actionEvent.getSource();

            boolean slct = absB.getModel().isSelected();

             selectedValue = (slct ? 0 : 1);
            
            System.out.println(selectedValue);


        }

          };
        	  tickBox.addActionListener(actionListener);
        	 // tickBox.setMnemonic(KeyEvent.VK_S);


    //gradeButtons    
    ButtonGroup gradeGroup = new ButtonGroup();
 		JRadioButton primaryButton = new JRadioButton("Primary School");
 		primaryButton.setActionCommand("primary");
 		primaryButton.addActionListener(this);
 		JRadioButton secondaryButton = new JRadioButton("Secondary School");
 		secondaryButton.setActionCommand("secondary");
 		secondaryButton.addActionListener(this);
 		JRadioButton highButton = new JRadioButton("High School");
 		highButton.setActionCommand("high");
 		highButton.addActionListener(this);
 		JRadioButton collegeButton= new JRadioButton("College");
 		collegeButton.setActionCommand("college");
 		collegeButton.addActionListener(this);
 		JRadioButton jobTrainingButton= new JRadioButton("Job Training");
 		jobTrainingButton.setActionCommand("jobTraining");
 		jobTrainingButton.addActionListener(this);
 		JRadioButton noGradePreference = new JRadioButton("No Preference",true);
 		noGradePreference.setActionCommand(none+" grade");
 		noGradePreference.addActionListener(this);
 		gradeGroup.add(primaryButton);
 		gradeGroup.add(secondaryButton);
 		gradeGroup.add(highButton);
 		gradeGroup.add(collegeButton);
 		gradeGroup.add(jobTrainingButton);
 		gradeGroup.add(noGradePreference);
 		
 		JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel gradeLabel = new JLabel("Indended grade level:");
    	gradePanel.add(gradeLabel);
 		gradePanel.add(primaryButton);
 		gradePanel.add(secondaryButton);
 		gradePanel.add(highButton);
 		gradePanel.add(collegeButton);
 		gradePanel.add(jobTrainingButton);
 		gradePanel.add(noGradePreference);
 	mainPannel.add(gradePanel,nextOpenRow++);
    //GenderButtons    
        ButtonGroup genderGroup = new ButtonGroup();
        	JRadioButton maleButton = new JRadioButton("Male");
            maleButton.setActionCommand("Male");
            maleButton.addActionListener(this);
        	JRadioButton femaleButton = new JRadioButton("Female");
            femaleButton.setActionCommand("Female");
            femaleButton.addActionListener(this);
        	JRadioButton noGenderPreference = new JRadioButton("No Preference",true);
            noGenderPreference.setActionCommand(none+" gender");
            noGenderPreference.addActionListener(this);
        	genderGroup.add(maleButton);
        	genderGroup.add(femaleButton);
        	genderGroup.add(noGenderPreference);
        	JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	JLabel genderLabel = new JLabel("Player Gender:");
        	genderPanel.add(genderLabel);
        	genderPanel.add(maleButton);
        	genderPanel.add(femaleButton);
        	genderPanel.add(noGenderPreference);
        mainPannel.add(genderPanel,nextOpenRow++);
        //ageButtons    
        ButtonGroup ageGroup = new ButtonGroup();
        	JRadioButton youngButton = new JRadioButton("Young");
            youngButton.setActionCommand("Young");
            youngButton.addActionListener(this);
        	JRadioButton oldButton = new JRadioButton("Old");
        	oldButton.setActionCommand("Old");
        	oldButton.addActionListener(this);
        	JRadioButton noAgePreference = new JRadioButton("No Preference",true);
            noAgePreference.setActionCommand(none+" age");
            noAgePreference.addActionListener(this);
        	ageGroup.add(youngButton);
        	ageGroup.add(oldButton);
        	ageGroup.add(noAgePreference);
        	JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	JLabel ageLabel = new JLabel("Character age:");
        	agePanel.add(ageLabel);
        	agePanel.add(youngButton);
        	agePanel.add(oldButton);
        	agePanel.add(noAgePreference);
        mainPannel.add(agePanel,nextOpenRow++);
        
        //dressButtons    
        ButtonGroup dressGroup = new ButtonGroup();
        	JRadioButton casualButton = new JRadioButton("Casual Dress");
        	casualButton.setActionCommand("Casual");
        	casualButton.addActionListener(this);
        	JRadioButton fancyButton = new JRadioButton("Fancy Dress");
        	fancyButton.setActionCommand("Fancy");
        	fancyButton.addActionListener(this);
        	JRadioButton noDressPreference = new JRadioButton("No Preference",true);
        	noDressPreference.setActionCommand(none+" dress");
        	noDressPreference.addActionListener(this);
        	dressGroup.add(casualButton);
        	dressGroup.add(fancyButton );
        	dressGroup.add(noDressPreference);
        	JPanel dressPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	JLabel dressLabel = new JLabel("Character dress:");
        	dressPanel.add(dressLabel);
        	dressPanel.add(casualButton);
        	dressPanel.add(fancyButton );
        	dressPanel.add(noDressPreference);
        mainPannel.add(dressPanel,nextOpenRow++);
        //Theme Buttons
     	ButtonGroup themeGroup = new ButtonGroup();
     		JRadioButton goobleButton = new JRadioButton("Gooble");
     		goobleButton.setActionCommand("Gooble");
     		goobleButton.addActionListener(this);
     		JRadioButton dreamButton = new JRadioButton("Dream");
     		dreamButton.setActionCommand("Dream");
     		dreamButton.addActionListener(this);
     		JRadioButton virtualTourButton = new JRadioButton("Virtual Tour");
     		virtualTourButton.setActionCommand("VirtualTour");
     		virtualTourButton.addActionListener(this);
     		JRadioButton workplaceButton= new JRadioButton("Workplace");
     		workplaceButton.setActionCommand("Workplace");
     		workplaceButton.addActionListener(this);
     		JRadioButton otherButton= new JRadioButton("Other");
     		otherButton.setActionCommand("Other");
     		otherButton.addActionListener(this);
     		JRadioButton noThemePreference = new JRadioButton("No Preference",true);
     		noThemePreference.setActionCommand(none+" theme");
     		noThemePreference.addActionListener(this);
     		themeGroup.add(goobleButton);
     		themeGroup.add(dreamButton);
     		themeGroup.add(virtualTourButton);
     		themeGroup.add(workplaceButton);
     		themeGroup.add(otherButton);
     		themeGroup.add(noThemePreference);
     		JPanel themePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel themeLabel = new JLabel("Game Theme:");
        	themePanel.add(themeLabel);
     		themePanel.add(goobleButton);
     		themePanel.add(dreamButton);
     		themePanel.add(virtualTourButton);
     		themePanel.add(workplaceButton);
     		themePanel.add(otherButton);
     		themePanel.add(noThemePreference);
     	mainPannel.add(themePanel,nextOpenRow++);
      //SubjectButtons
        ButtonGroup subjectGroup = new ButtonGroup();
    		JRadioButton englishButton = new JRadioButton("English");
    		englishButton.setActionCommand("English");
    		englishButton.addActionListener(this);
    		JRadioButton mathButton = new JRadioButton("Math");
    		mathButton.setActionCommand("Math");
    		mathButton.addActionListener(this);
    		JRadioButton scienceButton = new JRadioButton("Science");
    		scienceButton.setActionCommand("Science");
    		scienceButton.addActionListener(this);
    		JRadioButton socialstudiesButton = new JRadioButton("Social Studies");
    		socialstudiesButton.setActionCommand("Social Studies");
    		socialstudiesButton.addActionListener(this);
    		JRadioButton literatureButton = new JRadioButton("Literature");
    		literatureButton.setActionCommand("Literature");
    		literatureButton.addActionListener(this);
    		JRadioButton professionalButton = new JRadioButton("Professional");
    		professionalButton.setActionCommand("Professional");
    		professionalButton.addActionListener(this);
    		JRadioButton noSubjectPreference = new JRadioButton("No Preference",true);
    		noSubjectPreference.setActionCommand(none+" subject");
    		noSubjectPreference.addActionListener(this);
    		subjectGroup.add(englishButton);
    		subjectGroup.add(mathButton);
    		subjectGroup.add(scienceButton);
    		subjectGroup.add(socialstudiesButton);
    		subjectGroup.add(literatureButton);
    		subjectGroup.add(professionalButton);
    		subjectGroup.add(noSubjectPreference);
    		JPanel subjectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel subjectLabel = new JLabel("Game subject:");
           	subjectPanel.add(subjectLabel);
    		subjectPanel.add(englishButton);
    		subjectPanel.add(mathButton);
    		subjectPanel.add(scienceButton);
    		subjectPanel.add(socialstudiesButton);
    		subjectPanel.add(literatureButton);
    		subjectPanel.add(professionalButton);
    		subjectPanel.add(noSubjectPreference);
    	mainPannel.add(subjectPanel,nextOpenRow++);
    //Setting Buttons	
     	ButtonGroup settingGroup = new ButtonGroup();
     		JRadioButton professionalSettingButton = new JRadioButton("Professional");
     		professionalSettingButton.setActionCommand("ProfessionalSetting");
     		professionalSettingButton.addActionListener(this);
     		JRadioButton casualSettingButton = new JRadioButton("Casual");
     		casualSettingButton.setActionCommand("CasualSetting");
     		casualSettingButton.addActionListener(this);
     		JRadioButton naturalSettingButton = new JRadioButton("Natural");
     		naturalSettingButton.setActionCommand("NaturalSetting");
     		naturalSettingButton.addActionListener(this);
     		JRadioButton educationalSettingButton= new JRadioButton("Educational");
     		educationalSettingButton.setActionCommand("EducationalSetting");
     		educationalSettingButton.addActionListener(this);
     		JRadioButton nonterrestrialSettingButton= new JRadioButton("Non-terrestrial");
     		nonterrestrialSettingButton.setActionCommand("Non-terrestrialSetting");
     		nonterrestrialSettingButton.addActionListener(this);
     		JRadioButton noSettingPreference = new JRadioButton("No Preference",true);
     		noSettingPreference.setActionCommand(none+" setting");
     		noSettingPreference.addActionListener(this);
     		settingGroup.add(professionalSettingButton);
     		settingGroup.add(casualSettingButton);
     		settingGroup.add(naturalSettingButton);
     		settingGroup.add(educationalSettingButton);
     		settingGroup.add(nonterrestrialSettingButton);
 			settingGroup.add(noSettingPreference);
 			JPanel settingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
 			JLabel settingLabel = new JLabel("Game Setting:");
 			settingPanel.add(settingLabel);
 			settingPanel.add(professionalSettingButton);
 			settingPanel.add(casualSettingButton);
 			settingPanel.add(naturalSettingButton);
 			settingPanel.add(educationalSettingButton);
 			settingPanel.add(nonterrestrialSettingButton);
 			settingPanel.add(noSettingPreference);
 		mainPannel.add(settingPanel,nextOpenRow++);
        //difficulty Buttons
     	ButtonGroup difficultyGroup = new ButtonGroup();
     		JRadioButton easyButton = new JRadioButton("Easy");
     		easyButton.setActionCommand("Easy");
     		easyButton.addActionListener(this);
     		JRadioButton mediumButton = new JRadioButton("Medium");
     		mediumButton.setActionCommand("Medium");
     		mediumButton.addActionListener(this);
     		JRadioButton hardButton = new JRadioButton("Hard");
     		hardButton.setActionCommand("Hard");
     		hardButton.addActionListener(this);
     		JRadioButton noDifficultyPreference = new JRadioButton("No Preference",true);
     		noDifficultyPreference.setActionCommand(none+" difficulty");
     		noDifficultyPreference.addActionListener(this);
     		difficultyGroup.add(easyButton);
     		difficultyGroup.add(mediumButton);
     		difficultyGroup.add(hardButton);
     		difficultyGroup.add(noDifficultyPreference);
     		JPanel difficultyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel difficultyLabel = new JLabel("Challenge difficulty:");
        	difficultyPanel.add(difficultyLabel);
     		difficultyPanel.add(easyButton);
     		difficultyPanel.add(mediumButton);
     		difficultyPanel.add(hardButton);
     		difficultyPanel.add(noDifficultyPreference);
     	mainPannel.add(difficultyPanel,nextOpenRow++);
     	JPanel previewCheckPanel = new JPanel(new GridLayout(1,1));
     	previewCheckPanel.add(tickBox);
        mainPannel.add(previewCheckPanel, nextOpenRow++);
        //ADD MORE BUTTON SETS HERE IN FUTURE IF DESIRED
     	
     	
     	//Submit Button on bottom
     	JPanel submitPanel = new JPanel(new GridLayout(1,3));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setActionCommand("Submit");
        submitPanel.add(submitButton);
        mainPannel.add(submitPanel, nextOpenRow++);
        generateTab.add(mainPannel);
        window.add(tabbedPane,BorderLayout.CENTER);
        window.setJMenuBar(menuBar);
        window.setVisible(true); // this needs to happen last to avoid blank window on start-up
	}
	
	private ArrayList<CharacterAsset> getCharacters()
	{
		ArrayList<CharacterAsset> chars = new ArrayList<CharacterAsset>();
		
		List<Act> acts = game.getActs();
		for(Act a : acts)
		{
			List<Scene> scenes = a.getScenes();
			for(Scene s : scenes)
			{
				List<ScreenNode> screens = s.getScreens();
				for(ScreenNode scr : screens)
				{
					List<Asset> assets = scr.getAssets();
					for(Asset as : assets)
					{
						if(as instanceof CharacterAsset)
						{
							chars.add((CharacterAsset)as);
						}
					}
				}
			}
		}
		
		return chars;
	}
	
	private ArrayList<String> getUniqueCharacterNames(ArrayList<CharacterAsset> chars)
	{
		ArrayList<String> charStrings = new ArrayList<String>();
		for(CharacterAsset charAsset : chars)
		{
			String filePath = charAsset.getDisplayImage();
			String charName = filePath.substring(0, filePath.indexOf('\\'));
			
			if(!charStrings.contains(charName))
			{
				charStrings.add(charName);
			}
		}
		
		return charStrings;
	}
	
	//sets all the component Inputs to 0
	private void initializeComponentInputs()
	{
		for(int x=0; x<componentInputs.length;x++)
		{
			componentInputs[x]= initializeMatrix(componentInputs[x], 1);
		}
		
	}
	// read in and return an XML game file given the path to the file
	private Game readGameFile(File gameFile)
	{
		Game game1 = null;
		
		try {
			game1 = (Game)JAXBContext.newInstance(Game.class).createUnmarshaller().unmarshal(gameFile);
		}
		catch (JAXBException e) {
			e.printStackTrace();
			System.out.println("Unable to open " + gameFile);
		}
		
		return game1;
	}
	// C40 handle loading an XML game into the preview window
	private void loadGame()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select a game XML file");
		int retval = chooser.showOpenDialog(null);
		
		if(retval == JFileChooser.APPROVE_OPTION)
		{
            File file = chooser.getSelectedFile();
            game = readGameFile(file);
            displayGame(game, file.getName());
            
            scenePanel.clear();
    		System.out.println("calling clear loadgame\n");
        } 
		else 
		{
            System.out.println("Open command cancelled by user.");
        }
	}
	// divide game into Acts and Scenes translating to java swing TreeNodes
	// file name is required because it will be the name of the root node
	private void displayGame(Game game1, String name)
	{
		((DefaultMutableTreeNode) actTree.getModel().getRoot()).removeAllChildren();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(name);
        ((DefaultTreeModel) actTree.getModel()).setRoot(rootNode);
		
		List<Act> acts = game1.getActs();
		for(int i = 0; i < acts.size(); i++)
		{
			DefaultMutableTreeNode actNode = new DefaultMutableTreeNode("Act " + (i + 1));
			
			List<Scene> scenes = acts.get(i).getScenes();
			for(int j = 0; j < scenes.size(); j++)
			{
				DefaultMutableTreeNode sceneNode = new DefaultMutableTreeNode("Scene " + (j + 1));
				actNode.add(sceneNode);
				List<ScreenNode> screens = scenes.get(j).getScreens();
				for(int k = 0; k < screens.size(); k++)
				{
					DefaultMutableTreeNode screenNode = new DefaultMutableTreeNode("Screen " + (k + 1));
					sceneNode.add(screenNode);
				}
			}
			
			rootNode.add(actNode);
		}
        actTree.expandRow(0);
	}

	private Scene getScene(String act, String scene)
	{
		Scanner sc = new Scanner(act);
		sc.next();
		int actNum = sc.nextInt();
		sc = new Scanner(scene);
		sc.next();
		int sceneNum = sc.nextInt();
		
		return game.getActs().get(actNum - 1).getScenes().get(sceneNum - 1);
	}
	
	private ScreenNode getScreen(String act, String scene, String screen)
	{
		Scanner sc = new Scanner(act);
		sc.next();
		int actNum = sc.nextInt();
		sc = new Scanner(scene);
		sc.next();
		int sceneNum = sc.nextInt();
		sc = new Scanner(screen);
		sc.next();
		int screenNum = sc.nextInt();
		
		return game.getActs().get(actNum - 1).getScenes().get(sceneNum - 1).getScreens().get(screenNum - 1);
	}
	//paint the scene in all of its glory
	private void displayScreen(Scene scene, ScreenNode screen)
	{
		List<Asset> assets = screen.getAssets();
		if(assets != null){
			scenePanel.loadAssets(assets);
			//assets.
		//scenePanel.loadAssetsToRoot(assets);
		}
		else
			System.out.println("assets null");

		scenePanel.loadBackground(scene.getBackground());
	}
	
	private void RootCharacters(ScreenNode screen)
	{
		List<Asset> assets = screen.getAssets();
		if(assets != null)
			scenePanel.loadAssetsToRoot(assets);
		else
			System.out.println("assets null");

	}
	// sets all the values of the matrix to the given value. 
	private Matrix initializeMatrix(Matrix inputMatrix, double initValue)
	{
		double[][] inputMatrixArray = inputMatrix.getArray();
		for(int y=0; y<inputMatrixArray.length;y++)
		{
			for(int z=0; z<inputMatrixArray[y].length;z++)
			{
				inputMatrixArray[y][z] = initValue;
			}
		}
		
		return inputMatrix;		
	}
	//same as above, only with an array instead of a matrix 
	private double[][] initializeArray(double[][] inputMatrixArray, double initValue)
	{
		for(int y=0; y<inputMatrixArray.length;y++)
		{
			for(int z=0; z<inputMatrixArray[y].length;z++)
			{
				inputMatrixArray[y][z] = initValue;
			}
		}
		
		return inputMatrixArray;		
	}
	//prints the given matrix
	private void printMatrix(Matrix  inputMatrix)
	{
		double[][] inputArray = inputMatrix.getArray();
		for(int x=0; x < inputArray.length; x++)
		{
			for (int y =0; y <  inputArray[x].length; y++)
			{
				System.out.printf("%.2f ",inputArray[x][y]);
			}
			System.out.println("");
		}
	}
	public Matrix[] getWizardInputs() {
		while(!submitClicked)
		{
			//WAIT!!!! waits for the user to hit submit once called
			// Sleep to avoid running at full CPU usage
			try { Thread.sleep(200); } catch(Exception e) { }
		}
		return componentInputs;
	}
	public void previewGame(File filename){
		System.out.println(selectedValue);
    	  if(selectedValue == 0){
  		  tabbedPane.setSelectedIndex(1);
          
  		  //We are literally guessing how long it will take to write to disk.
  		  //TODO: sync filesystem write
  		  //note: 2 seconds was a sufficient amount of time for my computer
  		  try {Thread.sleep(3000);}catch(Exception e){}
          game = readGameFile(filename);
          displayGame(game, filename.getName());
  		  
  	  }
	}
	// a way to weight the options for each component against each other (ie age v gender) on which is more important
	// add a third input to this method called weight and replace the optionTotal in the assignment statement with it. 
	private Matrix optionMatrix(int optionNumber, int optionTotal)
	{
		double[][] outputArray = new double[optionTotal][optionTotal];
		outputArray = initializeArray(outputArray,1);
		for(int x=0; x<optionTotal; x++)
		{
			if(x!=optionNumber-1)
			{
				outputArray[optionNumber-1][x]=optionTotal; //replace optionTotal here with weight
				outputArray[x][optionNumber-1]=(1.0 / optionTotal); //replace optionTotal here with weight
			}							
		}						
		return new Matrix(outputArray);
	}
	//called once after the submit button was clicked and a valid location is given.
	private void distributeInputs() {
		//start with character component
		int socialRating = 0;
		int professionalRating =0;
		int educationalRating =0;
		System.out.println("distributing Inputs");
		switch(gameGradeLevel){
		//There is probably a better way to do this, with lists or whatnot but this was the quick
		//implementation that I(Kaleb) decided to go with
		case "primary":
			componentInputs[4].setMatrix(6,9,6,9,optionMatrix(1,4));
			educationalRating++;
			break;
		case "secondary":
			componentInputs[4].setMatrix(6,9,6,9,optionMatrix(2,4));
			educationalRating++;
			break;
		case "high":
			componentInputs[4].setMatrix(6,9,6,9,optionMatrix(3,4));
			educationalRating++;
			break;
		case "college":
			componentInputs[4].setMatrix(6,9,6,9,optionMatrix(4,4));
			educationalRating++;
			break;
		case "jobTraining":
			professionalRating++;
			break;
		case "no grade":
			socialRating++;
		break;
		}
		switch(playerAge){
		case "Young":
			componentInputs[0].setMatrix(0,1,0,1,optionMatrix(1,2));
			break;
		case "Old":
			componentInputs[0].setMatrix(0,1,0,1,optionMatrix(2,2));
			break;
		case "none":
			break;
		default:
			break;
		}
		switch(playerGender){
		case "Male":
			componentInputs[0].setMatrix(2,3,2,3,optionMatrix(1,2));
			break;
		case "Female":
			componentInputs[0].setMatrix(2,3,2,3,optionMatrix(2,2));
			break;
		case "none":
			break;
		default:
			break;
		}
		switch(playerDress){
		case "Casual":
			componentInputs[0].setMatrix(4,5,4,5,optionMatrix(1,2));
			//			socialRating++;
			break;
		case "Fancy":
			componentInputs[0].setMatrix(4,5,4,5,optionMatrix(2,2));
			break;
		case "none":
			break;
		default:
			break;
		}
		switch(gameTheme){
		case "Gooble":
			componentInputs[5].setMatrix(0,4,0,4,optionMatrix(1,5));
			educationalRating++;
			break;
		case "Dream":
			componentInputs[5].setMatrix(0,4,0,4,optionMatrix(2,5));
			socialRating++;
			break;
		case "VirtualTour":
			componentInputs[5].setMatrix(0,4,0,4,optionMatrix(3,5));
			professionalRating++;
			break;
		case "Workplace":
			componentInputs[5].setMatrix(0,4,0,4,optionMatrix(4,5));
			socialRating++;
			break;
		case "Other":
			componentInputs[5].setMatrix(0,4,0,4,optionMatrix(5,5));
			break;
		case "none":
			break;
		default:
			System.out.println("Unanticipated Input for gameTheme " + gameTheme);
			break;
		}
		switch(gameSubject)
		{
		//Subject
		case "English":
			componentInputs[4].setMatrix(0,5,0,5,optionMatrix(3,6));
			educationalRating++;
			break;
		case "Math":
			componentInputs[4].setMatrix(0,5,0,5,optionMatrix(1,6));
			educationalRating++;
			break;
		case "Science":
			componentInputs[4].setMatrix(0,5,0,5,optionMatrix(5,6));
			educationalRating++;
			break;
		case "Social Studies":
			componentInputs[4].setMatrix(0,5,0,5,optionMatrix(4,6));
			educationalRating++;
			break;
		case "Literature":
			componentInputs[4].setMatrix(0,5,0,5,optionMatrix(2,6));
			educationalRating++;
			break;
		case "Professional":
			componentInputs[4].setMatrix(0,5,0,5,optionMatrix(6,6));
			professionalRating++;
			break;
		case "none":
			break;
		default:
			System.out.println("Unanticipated Input for gameSubject " + gameSubject);
			break;
		}
		switch(gameSetting){
		//Setting
		case "Professional":
			professionalRating+=2;
			componentInputs[3].setMatrix(3,7,3,7,optionMatrix(1,5));
			break;
		case "Casual":
			socialRating+=2;
			componentInputs[3].setMatrix(3,7,3,7,optionMatrix(2,5));
			break;
		case "Natural":
			componentInputs[3].setMatrix(3,7,3,7,optionMatrix(3,5));
			break;
		case "Educational":
			componentInputs[3].setMatrix(3,7,3,7,optionMatrix(4,5));
			educationalRating+=2;
			break;
		case "Non-terrestrial":
			componentInputs[3].setMatrix(3,7,3,7,optionMatrix(5,5));
			break;
		case "none":
			break;
		default:
			System.out.println("Unanticipated Input for gameSetting " + gameSetting);
			break;
		}
		switch(gameDifficulty){
		//Difficulty
		case "Easy":
			componentInputs[2].setMatrix(0,2,0,2,optionMatrix(1,3));
			componentInputs[2].setMatrix(3,5,3,5,optionMatrix(1,3));
			break;
		case "Medium":
			componentInputs[2].setMatrix(0,2,0,2,optionMatrix(2,3));
			componentInputs[2].setMatrix(3,5,3,5,optionMatrix(2,3));
			break;
		case "Hard":
			componentInputs[2].setMatrix(0,2,0,2,optionMatrix(3,3));
			componentInputs[2].setMatrix(3,5,3,5,optionMatrix(3,3));
			break;
		case "none":
			break;
		default:
			System.out.println("Unanticipated Input for gameDifficulity " + gameDifficulty);
			break;
		}
		System.out.println("social: "+ socialRating + " Professional: "+ professionalRating + " Educational: " + educationalRating);

		if(!(socialRating == professionalRating && professionalRating == educationalRating))
		{
			if(socialRating>professionalRating && socialRating>educationalRating)
			{
				componentInputs[2].setMatrix(6,8,6,8,optionMatrix(1,3));
				componentInputs[1].setMatrix(0,2,0,2,optionMatrix(1,3));

			}
			if(professionalRating>=socialRating && professionalRating>educationalRating)
			{
				componentInputs[3].setMatrix(0,2,0,2,optionMatrix(2,3));
				componentInputs[2].setMatrix(6,8,6,8,optionMatrix(2,3));
				componentInputs[1].setMatrix(0,2,0,2,optionMatrix(3,3));

			}
			if(educationalRating>=socialRating && educationalRating>=professionalRating)
			{
				componentInputs[2].setMatrix(6,8,6,8,optionMatrix(3,3));
				componentInputs[1].setMatrix(0,2,0,2,optionMatrix(2,3));
			}
		}
	}	
	public void printStrings()
	{
		System.out.println(gameGradeLevel);
		System.out.println(playerGender);
		System.out.println(playerAge);
		System.out.println(playerDress);
		System.out.println(gameTheme);
		System.out.println(gameSetting);
		System.out.println(gameDifficulty);
		System.out.println(gameSubject);
	}
	public String getFileLocation()
	{
		return gameSavePath;
	}
	public void checkForXML(String input)
	{			
		if(!input.contains("."))
		{
			gameSavePath = input+".xml";
		}
		else{ 
			String extension = input.substring(input.lastIndexOf(".") + 1, input.length());
			if (!extension.equalsIgnoreCase("XML")) {
				gameSavePath= input.substring(0,input.lastIndexOf("."))+".xml";
			}
		}
	}
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand()) 
		{
		case "Submit":
			printStrings();
			distributeInputs();
			System.out.println("Submit Clicked");
			saveFileChooser = new JFileChooser("OutputGames//");
			int returnValue = saveFileChooser.showSaveDialog(saveFileChooser);
			if(returnValue==JFileChooser.APPROVE_OPTION)
			{
				File file = saveFileChooser.getSelectedFile();
				gameSavePath=file.getAbsolutePath(); 
				checkForXML(gameSavePath);
				System.out.println("Game Save Path: "+gameSavePath);
				submitClicked = true;
				previewGame(file);
			}
			else if(returnValue == JFileChooser.CANCEL_OPTION)
			{
				System.out.println("Save cancelled by user. \n Returning.");
			}		
			break;
		case "openFile":
			loadGame();
			break;
		case "charactersToolbar":
			//JD call method to pass an asset to the character select window.
			characterSelectAsset = null;
			characterSelectWindow.setCharacterAsset(characterSelectAsset);
			ArrayList<CharacterAsset> chars = new ArrayList<CharacterAsset>();
			List<Asset> assets = lastSelectedScreen.getAssets();
			for(Asset as : assets)
			{
				if(as instanceof CharacterAsset)
				{
					chars.add((CharacterAsset)as);
				}
			}
			ArrayList<String> charNamesInScreen = getUniqueCharacterNames(chars);
			ArrayList<String> charNamesInGame = getUniqueCharacterNames(getCharacters());
			ArrayList<String> availableChars = new ArrayList<String>();
			for(String charName : charNamesInGame)
			{
				if(!charNamesInScreen.contains(charName))
				{
					availableChars.add(charName);
				}
			}
			if(availableChars.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "All characters are currently in this Screen, none to add");
			}
			else
			{
				characterSelectWindow.setCharacterChoices(availableChars);
				characterSelectWindow.setVisible(true);
			}
			//JD end
			break;
		case "backgroundToolbar":
			backgroundSelectPath = null;
			String currentBackgroundPath = lastSelectedScene.getBackground();
			currentBackgroundPath = currentBackgroundPath.substring(0, currentBackgroundPath.lastIndexOf('\\'));
			backgroundSelectWindow.setBackgroundPathString(backgroundSelectPath);
			backgroundSelectWindow.setBackgroundFolderPath(currentBackgroundPath);
			backgroundSelectWindow.setVisible(true);
			break;
			
		case "addToRepo":
			File parent = new File("New Games\\");
			saveFileChooser = new JFileChooser(parent);
			saveFileChooser.setDialogTitle("Choose the game folder");
			saveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			updater= new Updates();
			int returnValue2 = saveFileChooser.showOpenDialog(saveFileChooser);
			String gameName;
			if(returnValue2==JFileChooser.APPROVE_OPTION)
			{
				File file = saveFileChooser.getSelectedFile();
				gameName= file.getAbsolutePath();
				
//				if(gameName.contains("New Games"))
//				{ 
//				System.out.println("Game name: "+ gameName.substring(gameName.lastIndexOf('\\')+1,gameName.length()));				
//				updater.addGame(gameName.substring(gameName.lastIndexOf('\\')+1,gameName.length()));
//				}
				System.out.println("Game name: "+ gameName.substring(gameName.lastIndexOf('\\')+1,gameName.length()));
				updater.addGame(gameName.substring(gameName.lastIndexOf('\\')+1,gameName.length()));
			}
			else if(returnValue2 == JFileChooser.CANCEL_OPTION)
			{
				System.out.println("Open cancelled by user. /n Returning.");
			}
			
			break;
		case "remakeRepo":
			updater = new Updates();
			updater.remakeRepo();
			break;
			// Grade 
		case "primary":
			gameGradeLevel = "primary";
			break;
		case "secondary":
			gameGradeLevel = "secondary";
			break;
		case "high":
			gameGradeLevel = "high";
			break;
		case "college":
			gameGradeLevel = "college";
			break;
		case "jobTraining":
			gameGradeLevel = "jobTraining";
			break;
		case "no grade":
			gameGradeLevel = "none";
		break;
//Gender			
		case "Male": 
			playerGender = "Male";
			break;
		case "Female":
			playerGender = "Female";
			break;
		case "no gender":
			playerGender = "none";
//Age					
		case "Young":
			playerAge = "Young";
			break;
		case "Old":
			playerAge = "Old";
			break;
		case "no age":
			playerAge = "none";
			break;
//Dress
		case "Casual":
			playerDress = "Casual";
			break;
		case "Fancy":
			playerDress = "Fancy";
			break;
		case "no dress":
			playerDress = "none";
			break;
//Theme
		case "Gooble":
			gameTheme = "Gooble";
			break;
		case "Dream":
			gameTheme = "Dream";
			break;
		case "VirtualTour":
			gameTheme = "VirtualTour";
			break;
		case "Workplace":
			gameTheme = "Workplace";
			break;
		case "Other":
			gameTheme = "Other";
			break;
		case "no theme":
			gameTheme = "none";
			break;
//Subject
		case "English":
			gameSubject = "English";
			break;
		case "Math":
			gameSubject = "Math";
			break;
		case "Science":
			gameSubject = "Science";
			break;
		case "Social Studies":
			gameSubject = "Social Studies";
			break;
		case "Literature":
			gameSubject = "Literature";
			break;
		case "Professional":
			gameSubject = "Professional";
			break;
		case "no subject":
			gameSubject = "none";
			break;
//Setting
		case "ProfessionalSetting":
			gameSetting = "Professional";
			break;
		case "CasualSetting":
			gameSetting = "Casual";
			break;
		case "NaturalSetting":
			gameSetting = "Natural";
			break;
		case "EducationalSetting":
			gameSetting = "Educational";
			break;
		case "Non-terrestrialSetting":
			gameSetting = "Non-terrestrial";
			break;
		case "no setting":
			gameSetting = "none";
			break;
//Difficulty
		case "Easy":
			gameDifficulty = "Easy";
			break;
		case "Medium":
			gameDifficulty = "Medium";
			break;
		case "Hard":
			gameDifficulty = "Hard";
			break;
		case "no difficulty":
			gameDifficulty = "none";
			break;
		default:
		System.out.println("Unanticipated Input in ActionPerformed:" + e.getActionCommand());
		break;
		}
	}
	public JMenuItem getSaveToRepo() {
		return saveToRepo;
	}
	public void setSaveToRepo(JMenuItem saveToRepo) {
		this.saveToRepo = saveToRepo;
	}
		
}
