package edu.utdallas.gamegenerator.View;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import java.io.*;

import javax.imageio.ImageIO;

import edu.utdallas.gamegenerator.Shared.AudioPlayer;
import edu.utdallas.gamegenerator.Shared.ImageHelper;

import java.util.*;

public class SoundSelectWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 450, HEIGHT = 300;
	public static final String soundFolder = "AudioAssetRepository\\";
	public static final String effectsFolder = "effects\\sound effects from WavSource\\";
	public static final String musicFolder = "music\\";
	private String soundFolderString;
	private String soundPathString;
	private JList<String> list;
	private ListSelectionModel select;
	
	public SoundSelectWindow(JFrame owner)
	{
		super(owner, "Sound Selection", Dialog.DEFAULT_MODALITY_TYPE);
		setSize(WIDTH, HEIGHT);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);

        list = new JList<String>();
        
        addWindowListener(new WindowListener() {
			public void windowActivated(WindowEvent e) { }
			public void windowClosed(WindowEvent e) { }
			public void windowClosing(WindowEvent e) { }
			public void windowDeactivated(WindowEvent e) 
			{
				AudioPlayer.stopAudio();
			}
			public void windowDeiconified(WindowEvent e) { }
			public void windowIconified(WindowEvent e) { }
			public void windowOpened(WindowEvent e) { }
        });

		JScrollPane listPane = new JScrollPane(list);
		add(listPane, BorderLayout.CENTER);

		JPanel flow = new JPanel(new FlowLayout());

		JButton preview = new JButton("Preview");
		try {
			BufferedImage img = ImageHelper.getScaledImage(ImageIO.read(new File("Office, Classroom/Asst Bitstrips and Composite images/play.png")), 0.5);
			preview.setIcon(new ImageIcon(img));
		} catch(Exception e) {}
		preview.setPreferredSize(new Dimension(100, 30));
		preview.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int selectedIndex = list.getSelectedIndex();
				if(selectedIndex == -1)
				{
					return;
				}
				String selected = list.getSelectedValue().toString();
				AudioPlayer.playAudio(soundFolder + soundFolderString + selected);
			}
		});
		JButton stop = new JButton("Stop");
		stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AudioPlayer.stopAudio();
			}
		});
		try {
			BufferedImage img = ImageHelper.getScaledImage(ImageIO.read(new File("Office, Classroom/Asst Bitstrips and Composite images/stop.png")), 1.0);
			stop.setIcon(new ImageIcon(img));
		} catch(Exception e) {}
		stop.setPreferredSize(new Dimension(100, 30));
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				soundPathString = soundFolder + soundFolderString + list.getSelectedValue().toString();
				AudioPlayer.stopAudio();
				setVisible(false);
			}
		});
		add.setPreferredSize(new Dimension(100, 30));

		flow.add(preview);
		flow.add(stop);
		flow.add(add);
		add(flow, BorderLayout.SOUTH);
		
		soundFolderString = effectsFolder;
		handleChangeSoundFolder();
	}
	private void handleChangeSoundFolder()
	{
		ArrayList<String> soundFiles = new ArrayList<String>();
		File dir = new File(soundFolder + soundFolderString);
    	
    	for (File child : dir.listFiles())
		{
    		if(child.isDirectory())
    		{
    			continue;
    		}
    		soundFiles.add(child.getName());
		}
    	
    	String[] soundFilesArray = new String[soundFiles.size()];
    	for(int i = 0; i < soundFiles.size(); i++)
    	{
    		soundFilesArray[i] = soundFiles.get(i);
    	}
    	list.setListData(soundFilesArray);
    	select = list.getSelectionModel();
		select.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}
	
	public void setSoundPathString(String soundString)
	{
		soundPathString = soundString;
	}
	
	public String getNewSoundPath()
	{
		return soundPathString;
	}
	
	public void setSoundFolderPath(String folderPath)
	{
		soundFolderString = folderPath;
		handleChangeSoundFolder();
	}
}