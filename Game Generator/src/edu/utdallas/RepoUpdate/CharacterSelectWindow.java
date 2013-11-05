package edu.utdallas.RepoUpdate;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class CharacterSelectWindow extends JFrame
{
	public static final int WIDTH = 1000, HEIGHT = 700;
	public CharacterSelectWindow()
	{
		super("Character Selection");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);

		JPanel nPanel = new JPanel();
		String[] comboStrings = {"Character 1", "Character 10", "Character 17"};
		final JComboBox comboBox = new JComboBox(comboStrings);
		nPanel.add(comboBox);
		
		JScrollPane wPane = new JScrollPane();
		//wPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//wPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		wPane.setPreferredSize(new Dimension(700, 700));
		JPanel wPanel = new JPanel(new GridLayout(0, 4));
		wPane.add(wPanel);
		wPane.setViewportView(wPanel);
		
		final ArrayList<JLabel> jlabels = new ArrayList<JLabel>();
		comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Object item = comboBox.getSelectedItem();
                if ("Character 1".equals(item)) {
                    System.out.println("char 1 is pressed");
                } else if ("Character 10".equals(item)) {
                	System.out.println("char 10 is pressed");
                }
            }
        });
		File dir = new File("Office, Classroom/Characters/character_1/");
		for (File child : dir.listFiles())
		{
			try {
				BufferedImage image = getScaledImage(ImageIO.read(child), 0.5);
				final JLabel l = new JLabel(new ImageIcon(image));
				jlabels.add(l);
				wPanel.add(l);
			} catch(Exception e) {}
		}
		
		for(final JLabel l : jlabels)
		{
			l.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					for(int i = 0; i < jlabels.size(); i++)
					{
						jlabels.get(i).setBorder(null);
					}
					l.setBorder(BorderFactory.createLoweredBevelBorder());
					
					//l.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				public void mouseEntered(MouseEvent e) {
				}
				public void mouseExited(MouseEvent e) {
				}
				public void mousePressed(MouseEvent e) {
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}

		JPanel ePanel = new JPanel(new GridLayout(0, 1, 0, 0));

		try {
			BufferedImage img = getScaledImage(ImageIO.read(new File("Office, Classroom/Characters/character_1/char1_REvil.png")), 1.2);
			JLabel pic = new JLabel(new ImageIcon(img));
			ePanel.add(pic);
		} catch(Exception e) {}

		JPanel panel2 = new JPanel(new BorderLayout());
		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 120);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(20, new JLabel("Smaller"));
        labels.put(180, new JLabel("Larger"));
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);
		panel2.add(slider, BorderLayout.CENTER);
		JButton place = new JButton("Place");
		place.setPreferredSize(new Dimension(280, 40));
		panel2.add(place, BorderLayout.SOUTH);

		ePanel.add(panel2);

		add(ePanel, BorderLayout.EAST);
		add(wPane, BorderLayout.WEST);
		add(nPanel, BorderLayout.NORTH);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
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


	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CharacterSelectWindow c = new CharacterSelectWindow();
				c.setVisible(true);
			}
		});
	}
}