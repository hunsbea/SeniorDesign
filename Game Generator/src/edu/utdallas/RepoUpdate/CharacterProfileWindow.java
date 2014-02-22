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
	private JPanel tagGrid, elementGrid;
	
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
		JLabel nameLabel = addProfileLabel("Name", ch.getName());
		
		//Attendance
		JLabel attendanceLabel = addProfileLabel("Attendance", ch.getProfile().getAttendance());
		
		//Availability
		JLabel availabilityLabel = addProfileLabel("Availability", ch.getProfile().getAvailability());
		
		//Communication
		JLabel communicationLabel = addProfileLabel("Communication", ch.getProfile().getCommunication());
		
		//Degrees
		for(int i=0;i<ch.getProfile().getDegrees().size();i++)
		{
			JLabel degreesLabel;
			if(i==0) { degreesLabel = addProfileLabel("Degrees", ch.getProfile().getDegrees().get(i)); }
			else { degreesLabel = addProfileLabel("", ch.getProfile().getDegrees().get(i)); }
		}
		
		//Demographics
		for(int i=0;i<ch.getProfile().getDemographics().size();i++)
		{
			JLabel demographicsLabel;
			if(i==0) { demographicsLabel = addProfileLabel("Demographics", ch.getProfile().getDemographics().get(i)); }
			else { demographicsLabel = addProfileLabel("", ch.getProfile().getDemographics().get(i)); }
		}
		
		//Skills
		for(int i=0;i<ch.getProfile().getSkills().size();i++)
		{
			JLabel skillsLabel;
			if(i==0) { skillsLabel = addProfileLabel("Skills", ch.getProfile().getSkills().get(i)); }
			else { skillsLabel = addProfileLabel("", ch.getProfile().getSkills().get(i)); }
		}
		
		//Teamwork
		JLabel teamworkLabel = addProfileLabel("Teamwork", ch.getProfile().getTeamwork());
		
		//Title
		JLabel titleLabel = addProfileLabel("Title", ch.getProfile().getTitle());
		
		//Experience
		JLabel experienceLabel = addProfileLabel("Experience", Integer.toString(ch.getProfile().getYearsOfExperience()));
        
		profilePanel.add(tagGrid, BorderLayout.WEST);
		profilePanel.add(elementGrid, BorderLayout.CENTER);
		JScrollPane scrollPane = new JScrollPane(profilePanel);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private JLabel addProfileLabel(String title, String text)
	{
		JLabel label = new JLabel(title);
		label.setFont(font);
		JTextField experienceField = new JTextField(text);
		experienceField.setBackground(Color.YELLOW);
		experienceField.setEditable(false);
		experienceField.setFont(font);
		((GridLayout)tagGrid.getLayout()).setRows(((GridLayout)tagGrid.getLayout()).getRows()+1);
		((GridLayout)elementGrid.getLayout()).setRows(((GridLayout)elementGrid.getLayout()).getRows()+1);
		tagGrid.add(label);
		elementGrid.add(experienceField);
		
		return label;
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