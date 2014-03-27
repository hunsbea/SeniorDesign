package edu.utdallas.gamegenerator.View;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import edu.utdallas.gamegenerator.Character.Character;

public class CharacterProfileWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = 480;
	private final Font font;
	private JPanel tagGrid, elementGrid;
	private Border bottomOfLabel = BorderFactory.createMatteBorder(0, 0, 5, 0, Color.black);
	
	public CharacterProfileWindow(JFrame owner, Character ch)
	{
		super(owner, ch.getName()+"'s Profile", Dialog.DEFAULT_MODALITY_TYPE);
		setSize(WIDTH, HEIGHT);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
		setBackground(Color.YELLOW);
		font = new Font("Comic Sans MS", Font.BOLD, 15);

		tagGrid = new JPanel(new GridLayout(0,1));
		tagGrid.setBackground(Color.YELLOW);
		elementGrid = new JPanel(new GridLayout(0,1));
		elementGrid.setBackground(Color.YELLOW);
		JPanel profilePanel = new JPanel(new BorderLayout());
		((BorderLayout)profilePanel.getLayout()).setHgap(2);
		
		//Name
		JLabel nameLabel = addProfileLabel("Name", ch.getName(), true);
		nameLabel.setBorder(bottomOfLabel);
		
		//Attendance
		JLabel attendanceLabel = addProfileLabel("Attendance", ch.getProfile().getAttendance(), true);
		attendanceLabel.setBorder(bottomOfLabel);
		
		//Availability
		JLabel availabilityLabel = addProfileLabel("Availability", ch.getProfile().getAvailability(), true);
		availabilityLabel.setBorder(bottomOfLabel);
		
		//Communication
		JLabel communicationLabel = addProfileLabel("Communication", ch.getProfile().getCommunication(), true);
		communicationLabel.setBorder(bottomOfLabel);
		
		//Degrees
		for(int i=0;i<ch.getProfile().getDegrees().size();i++)
		{
			JLabel degreesLabel;
			if(i==0) { degreesLabel = addProfileLabel("Degrees", ch.getProfile().getDegrees().get(i), false); }
			else if (i+1==(ch.getProfile().getDegrees().size())) { degreesLabel = addProfileLabel("", ch.getProfile().getDegrees().get(i), true);
				degreesLabel.setBorder(bottomOfLabel);
			}
			else { degreesLabel = addProfileLabel("", ch.getProfile().getDegrees().get(i), false);	}
		}
		
		//Demographics
		for(int i=0;i<ch.getProfile().getDemographics().size();i++)
		{
			JLabel demographicsLabel;
			if(i==0) { demographicsLabel = addProfileLabel("Demographics", ch.getProfile().getDemographics().get(i), false); }
			else if (i+1==(ch.getProfile().getDemographics().size())) { demographicsLabel = addProfileLabel("", ch.getProfile().getDemographics().get(i), true);
				demographicsLabel.setBorder(bottomOfLabel);
			}
			else { demographicsLabel = addProfileLabel("", ch.getProfile().getDemographics().get(i), false); }
		}
		
		//Skills
		for(int i=0;i<ch.getProfile().getSkills().size();i++)
		{
			JLabel skillsLabel;
			if(i==0) { skillsLabel = addProfileLabel("Skills", ch.getProfile().getSkills().get(i), false); }
			else if (i+1==(ch.getProfile().getSkills().size())) { skillsLabel = addProfileLabel("", ch.getProfile().getSkills().get(i), true);
				skillsLabel.setBorder(bottomOfLabel);
			}
			else { skillsLabel = addProfileLabel("", ch.getProfile().getSkills().get(i), false); }
		}
		
		//Teamwork
		JLabel teamworkLabel = addProfileLabel("Teamwork", ch.getProfile().getTeamwork(), true);
		teamworkLabel.setBorder(bottomOfLabel);
		
		//Title
		JLabel titleLabel = addProfileLabel("Title", ch.getProfile().getTitle(), true);
		titleLabel.setBorder(bottomOfLabel);
		
		//Experience
		JLabel experienceLabel = addProfileLabel("Experience", Integer.toString(ch.getProfile().getYearsOfExperience()), true);
        experienceLabel.setBorder(bottomOfLabel);
		
		profilePanel.add(tagGrid, BorderLayout.WEST);
		profilePanel.add(elementGrid, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(profilePanel);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private JLabel addProfileLabel(String title, String text, boolean isLastEntry)
	{
		JLabel label = new JLabel(title);
		label.setFont(font);
		JTextField experienceField = new JTextField(text);
		experienceField.setBackground(Color.YELLOW);
		experienceField.setEditable(false);
		experienceField.setFont(font);
		if (isLastEntry)
			experienceField.setBorder(bottomOfLabel);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(label);
		elementGrid.add(experienceField);
		
		return label;
	}
}