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
	private Clip currentClip;
	
	public SoundSelectWindow(JFrame owner)
	{
		super(owner, "Sound Selection", Dialog.DEFAULT_MODALITY_TYPE);
		setSize(WIDTH, HEIGHT);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);

		String[] data = { "Sound1.wav", "Sound2.wav", "Sound3.wav",
			"Sound4.wav", "Sound5.wav", "Sound6.wav", "Sound7.wav",
			"Sound8.wav", "Sound9.wav", "Sound10.wav", "Sound11.wav",
			"Sound12.wav", "Sound13.wav", "Sound14.wav", "Sound15.wav" };

        list = new JList<String>(data);
        
        addWindowListener(new WindowListener() {

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
				stopAudio();
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

		JScrollPane listPane = new JScrollPane(list);
		add(listPane, BorderLayout.CENTER);

		JPanel flow = new JPanel(new FlowLayout());

		JButton preview = new JButton("Preview");
		try {
			BufferedImage img = getScaledImage(ImageIO.read(new File("Office, Classroom/Asst Bitstrips and Composite images/play.png")), 0.5);
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
				playAudio(soundFolder + soundFolderString + selected);
			}
		});
		JButton stop = new JButton("Stop");
		stop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stopAudio();
			}
		});
		try {
			BufferedImage img = getScaledImage(ImageIO.read(new File("Office, Classroom/Asst Bitstrips and Composite images/stop.png")), 1.0);
			stop.setIcon(new ImageIcon(img));
		} catch(Exception e) {}
		stop.setPreferredSize(new Dimension(100, 30));
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				soundPathString = soundFolder + soundFolderString + list.getSelectedValue().toString();
				stopAudio();
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
	
	public void playAudio(String path)
	{
		stopAudio();
		try {
		    File yourFile = new File(path);
		    System.out.println(path);
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    final Clip clip;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    currentClip = clip;

		    clip.addLineListener(new LineListener()
			{
				@Override
				public void update(LineEvent event)
				{
					if (event.getType() == LineEvent.Type.STOP) {
						clip.close();
					}
				}
        	});

		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void stopAudio()
	{
		if(!(currentClip == null) && currentClip.isRunning())
		{
			currentClip.stop();
			currentClip.close();
		}
	}
}