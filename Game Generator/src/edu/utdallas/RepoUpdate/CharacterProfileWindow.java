package edu.utdallas.RepoUpdate;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.*;

import java.io.*;

import javax.imageio.ImageIO;
import java.util.*;

import edu.utdallas.gamegenerator.Character.Character;

public class CharacterProfileWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 450, HEIGHT = 300;
	public static final String soundFolder = "AudioAssetRepository\\";
	public static final String effectsFolder = "effects\\sound effects from WavSource\\";
	public static final String musicFolder = "music\\";
	private String soundFolderString;
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