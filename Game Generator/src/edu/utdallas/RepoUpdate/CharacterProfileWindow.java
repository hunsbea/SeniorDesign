package edu.utdallas.RepoUpdate;

import java.awt.*;
import java.awt.image.*;

import javax.swing.*;

import edu.utdallas.gamegenerator.Character.Character;

public class CharacterProfileWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = 480;
	private final Font font;
	
	public CharacterProfileWindow(JFrame owner, Character ch)
	{
		super(owner, ch.getName()+"'s Profile", Dialog.DEFAULT_MODALITY_TYPE);
		setSize(WIDTH, HEIGHT);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
		setBackground(Color.YELLOW);
		font = new Font("Comic Sans MS", Font.BOLD, 15);

		JPanel tagGrid = new JPanel(new GridLayout());
		tagGrid.setBackground(Color.YELLOW);
		JPanel elementGrid = new JPanel(new GridLayout());
		elementGrid.setBackground(Color.YELLOW);
		JPanel profilePanel = new JPanel(new BorderLayout());
		((BorderLayout)profilePanel.getLayout()).setHgap(2);
		
		//name
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(font);
		JTextField nameField = new JTextField(ch.getName());
		nameField.setBackground(Color.YELLOW);
		nameField.setEditable(false);
		nameField.setFont(font);
		tagGrid.add(nameLabel);
		elementGrid.add(nameField);
		
		//Attendance
		JLabel attendanceLabel = new JLabel("Attendance");
		attendanceLabel.setFont(font);
		JTextField attendanceField = new JTextField(ch.getProfile().getAttendance());
		attendanceField.setBackground(Color.YELLOW);
		attendanceField.setEditable(false);
		attendanceField.setFont(font);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(attendanceLabel);
		elementGrid.add(attendanceField);
		
		//Availability
		JLabel availabilityLabel = new JLabel("Availability");
		availabilityLabel.setFont(font);
		JTextField availabilityField = new JTextField(ch.getProfile().getAvailability());
		availabilityField.setBackground(Color.YELLOW);
		availabilityField.setEditable(false);
		availabilityField.setFont(font);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(availabilityLabel);
		elementGrid.add(availabilityField);
		
		//Communication
		JLabel communicationLabel = new JLabel("Communication");
		communicationLabel.setFont(font);
		JTextField communicationField = new JTextField(ch.getProfile().getCommunication());
		communicationField.setBackground(Color.YELLOW);
		communicationField.setEditable(false);
		communicationField.setFont(font);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(communicationLabel);
		elementGrid.add(communicationField);
		
		//Degrees
		for(int i=0;i<ch.getProfile().getDegrees().size();i++)
		{
			JLabel degreesLabel;
			if(i==0){
				degreesLabel = new JLabel("Degrees");
			}
			else
			{
				degreesLabel = new JLabel("");
			}
			degreesLabel.setFont(font);
			JTextField degreeField = new JTextField(ch.getProfile().getDegrees().get(i));
			degreeField.setBackground(Color.YELLOW);
			degreeField.setEditable(false);
			degreeField.setFont(font);
			((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
			((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
			tagGrid.add(degreesLabel);
			elementGrid.add(degreeField);
		}
		
		//Demographics
		for(int i=0;i<ch.getProfile().getDemographics().size();i++)
		{
			JLabel demographicsLabel;
			if(i==0){
				demographicsLabel = new JLabel("Demographics");
			}
			else
			{
				demographicsLabel = new JLabel("");
			}
			demographicsLabel.setFont(font);
			JTextField demographicField = new JTextField(ch.getProfile().getDemographics().get(i));
			demographicField.setBackground(Color.YELLOW);
			demographicField.setEditable(false);
			demographicField.setFont(font);
			((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
			((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
			tagGrid.add(demographicsLabel);
			elementGrid.add(demographicField);
		}
		
		//Skills
		for(int i=0;i<ch.getProfile().getSkills().size();i++)
		{
			JLabel skillsLabel;
			if(i==0){
				skillsLabel = new JLabel("Skills");
			}
			else
			{
				skillsLabel = new JLabel("");
			}
			skillsLabel.setFont(font);
			JTextField skillField = new JTextField(ch.getProfile().getSkills().get(i));
			skillField.setBackground(Color.YELLOW);
			skillField.setEditable(false);
			skillField.setFont(font);
			((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
			((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
			tagGrid.add(skillsLabel);
			elementGrid.add(skillField);
		}
		
		//Teamwork
		JLabel teamworkLabel = new JLabel("Teamwork");
		teamworkLabel.setFont(font);
		JTextField teamworkField = new JTextField(ch.getProfile().getTeamwork());
		teamworkField.setBackground(Color.YELLOW);
		teamworkField.setEditable(false);
		teamworkField.setFont(font);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(teamworkLabel);
		elementGrid.add(teamworkField);
		
		//Title
		JLabel titleLabel = new JLabel("Title");
		titleLabel.setFont(font);
		JTextField titleField = new JTextField(ch.getProfile().getTitle());
		titleField.setBackground(Color.YELLOW);
		titleField.setEditable(false);
		titleField.setFont(font);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(titleLabel);
		elementGrid.add(titleField);
		
		//Experience
		JLabel experienceLabel = new JLabel("Experience");
		experienceLabel.setFont(font);
		JTextField experienceField = new JTextField(Integer.toString(ch.getProfile().getYearsOfExperience()));
		experienceField.setBackground(Color.YELLOW);
		experienceField.setEditable(false);
		experienceField.setFont(font);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(experienceLabel);
		elementGrid.add(experienceField);
        
		profilePanel.add(tagGrid, BorderLayout.WEST);
		profilePanel.add(elementGrid, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(profilePanel);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public static BufferedImage getScaledImage(BufferedImage orig, double scale)
	{
		int origW = orig.getWidth();
		int origH = orig.getHeight();
		double newW = origW * scale;
		double newH = origH * scale;

		BufferedImage resized = new BufferedImage((int)newW, (int)newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(orig, 0, 0, (int)newW, (int)newH, 0, 0, orig.getWidth(), orig.getHeight(), null);
		g.dispose();

		return resized;
	}
}