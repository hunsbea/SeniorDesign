package edu.utdallas.gamegenerator.View;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.utdallas.gamegenerator.Shared.CharacterAsset;
import edu.utdallas.gamegenerator.Shared.ImageHelper;

public class CharacterSelectWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1050, HEIGHT = 700;
	final JLabel pic = new JLabel();
	final JPanel wPanel = new JPanel(new GridLayout(0, 4));
	//final JPanel ePanel = new JPanel(new GridLayout(0, 1, 0, 0));
	final JPanel ePanel = new JPanel(new BorderLayout());
	final JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 130, 80);
	final JComboBox<String> comboBox = new JComboBox<String>();
	String selectedPath = "";
	private CharacterAsset charAsset;
	
	public CharacterSelectWindow(JFrame owner)
	{
		super(owner, "Character Selection", Dialog.DEFAULT_MODALITY_TYPE);
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
        labels.put(20, new JLabel("Smaller"));
        labels.put(120, new JLabel("Larger"));
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Slider Value: " + slider.getValue());
				double sValue = slider.getValue() / 100.0;
            	
				try {
					BufferedImage img1 = ImageHelper.getScaledImage(ImageIO.read(new File(selectedPath)), sValue);
					System.out.println(selectedPath);
					pic.setIcon(new ImageIcon(img1));
            	}catch(Exception e4) { e4.printStackTrace(); }
            }
        });
		panel2.add(slider, BorderLayout.CENTER);
		JButton place = new JButton("Place");
		place.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				charAsset = new CharacterAsset();
				String imgstrng = selectedPath.substring(selectedPath.indexOf((String)comboBox.getSelectedItem()));
				charAsset.setDisplayImage(imgstrng);
				charAsset.setFontFamily("Comic Sans MS");
				charAsset.setFontSize(15);
				charAsset.setHeight(pic.getIcon().getIconHeight());
				charAsset.setWidth(pic.getIcon().getIconWidth());
				charAsset.setX(300);
				charAsset.setX2(300 + charAsset.getWidth());
				charAsset.setY(50);
				charAsset.setY2(50 + charAsset.getHeight());
				charAsset.setOpacity(1);
				setVisible(false);
			}
		});
		place.setPreferredSize(new Dimension(280, 40));
		panel2.add(place, BorderLayout.SOUTH);

		//ePanel.add(pic);
		//ePanel.add(panel2);
		ePanel.add(pic,BorderLayout.CENTER);
		ePanel.add(panel2,BorderLayout.SOUTH);
		ePanel.setPreferredSize(new Dimension(325,650));

		add(ePanel, BorderLayout.EAST);
		add(wPane, BorderLayout.WEST);
		add(nPanel, BorderLayout.NORTH);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
		
		handleChangeCharacter();
	}
	
	private void handleChangeCharacter()
	{
		if(comboBox.getSelectedItem() == null)
		{return;}
		final ArrayList<JLabel> jlabels = new ArrayList<JLabel>();
        String item = (String)comboBox.getSelectedItem();
        
        System.out.println(item);
        
    	File dir = new File("Office, Classroom/Characters/" + item + "/");
    	wPanel.removeAll();
		
    	for (File child : dir.listFiles())
		{
    		if(child.isDirectory())
    		{
    			continue;
    		}
    		try {
    		BufferedImage image = ImageHelper.getScaledImage(ImageIO.read(child), 0.5);
    		
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
						BufferedImage img1 = ImageHelper.getScaledImage(ImageIO.read(new File(l.getName())), slider.getValue() / 100.0);
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
	
	public void setCharacterAsset(CharacterAsset characterAsset)
	{
		charAsset = characterAsset;
	}
	
	public CharacterAsset getNewCharacterAsset()
	{
		return charAsset;
	}
	
	public void setCharacterChoices(ArrayList<String> charNames)
	{
		comboBox.removeAllItems();
		for(String charName : charNames)
		{
			comboBox.addItem(charName);
		}
		handleChangeCharacter();
	}
}