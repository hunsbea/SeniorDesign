package edu.utdallas.RepoUpdate;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class CharacterSelectWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1000, HEIGHT = 700;
	final JLabel pic = new JLabel();
	final JPanel wPanel = new JPanel(new GridLayout(0, 4));
	final JPanel ePanel = new JPanel(new GridLayout(0, 1, 0, 0));
	final JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 200, 120);
	final JComboBox<String> comboBox = new JComboBox<String>();
	String selectedPath = "";
	
	public CharacterSelectWindow()
	{
		super("Character Selection");
		setSize(WIDTH, HEIGHT);

		JPanel nPanel = new JPanel();
		for(int i = 1; i <= 29; i++)
		{
			comboBox.addItem("Character_" + i);
		}
		comboBox.addItem("Hero-Villian");
		nPanel.add(comboBox);
		
		JScrollPane wPane = new JScrollPane();
		wPane.setPreferredSize(new Dimension(700, 700));
		wPane.add(wPanel);
		wPane.setViewportView(wPanel);
		
		comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	handleChangeCharacter();
            }
        });

		JPanel panel2 = new JPanel(new BorderLayout());
		slider.setMajorTickSpacing(10);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(30, new JLabel("Smaller"));
        labels.put(180, new JLabel("Larger"));
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Slider Value: " + slider.getValue());
				double sValue = slider.getValue() / 100.0;
            	
				try {
					BufferedImage img1 = getScaledImage(ImageIO.read(new File(selectedPath)), sValue);
					System.out.println(selectedPath);
					pic.setIcon(new ImageIcon(img1));
            	}catch(Exception e4) { e4.printStackTrace(); }
            }
        });
		panel2.add(slider, BorderLayout.CENTER);
		JButton place = new JButton("Place");
		place.setPreferredSize(new Dimension(280, 40));
		panel2.add(place, BorderLayout.SOUTH);

		ePanel.add(pic);
		ePanel.add(panel2);

		add(ePanel, BorderLayout.EAST);
		add(wPane, BorderLayout.WEST);
		add(nPanel, BorderLayout.NORTH);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
		
		handleChangeCharacter();
	}
	
	private void handleChangeCharacter()
	{
		final ArrayList<JLabel> jlabels = new ArrayList<JLabel>();
        String item = (String)comboBox.getSelectedItem();
        
        System.out.println(item);
        
    	File dir = new File("Office, Classroom/Characters/" + item + "/");
    	wPanel.removeAll();
		
    	for (File child : dir.listFiles())
		{
    		try {
    		BufferedImage image = getScaledImage(ImageIO.read(child), 0.5);
    		
    		final JLabel l = new JLabel(new ImageIcon(image));
    		l.setName(child.getPath().toString());
			jlabels.add(l);
			wPanel.add(l);
			wPanel.validate();
			wPanel.repaint();
		} catch(Exception e1) {}
		
		}
    	for(final JLabel l : jlabels)
		{
			l.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
				}
				public void mouseEntered(MouseEvent e) {
				}
				public void mouseExited(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
					for(int i = 0; i < jlabels.size(); i++)
					{
						jlabels.get(i).setBorder(null);
					}
					l.setBorder(BorderFactory.createLoweredBevelBorder());
					try {
						BufferedImage img1 = getScaledImage(ImageIO.read(new File(l.getName())), slider.getValue() / 100.0);
						pic.setIcon(new ImageIcon(img1));
			    		selectedPath = l.getName();
					} catch(Exception e4) {}
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
			;
		}
        
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