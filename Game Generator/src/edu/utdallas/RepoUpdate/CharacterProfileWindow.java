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
	
	public CharacterProfileWindow(JFrame owner, Character ch)
	{
		super(owner, ch.getName()+"'s Profile", Dialog.DEFAULT_MODALITY_TYPE);
		setSize(WIDTH, HEIGHT);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);

		JPanel tagGrid = new JPanel(new GridLayout());
		JPanel elementGrid = new JPanel(new GridLayout());
		JPanel profilePanel = new JPanel(new BorderLayout());
        
		profilePanel.add(tagGrid, BorderLayout.WEST);
		profilePanel.add(elementGrid, BorderLayout.EAST);
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