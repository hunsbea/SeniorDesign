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
import edu.utdallas.gamegenerator.Shared.ImageHelper;

public class BackgroundSelectWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1050, HEIGHT = 500;
	final JLabel pic = new JLabel();
	final JPanel wPanel = new JPanel(new GridLayout(0, 2));
	//final JPanel ePanel = new JPanel(new GridLayout(0, 1, 0, 0));
	final JPanel ePanel = new JPanel(new BorderLayout());
//	final JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 130, 80);
	final JComboBox<String> comboBox = new JComboBox<String>();
	String selectedPath = "";
	private String backPathString;
	
	public BackgroundSelectWindow(JFrame owner)
	{
		super(owner, "Background Selection", Dialog.DEFAULT_MODALITY_TYPE);
		setSize(WIDTH, HEIGHT);

		JPanel nPanel = new JPanel();
//		for(int i = 1; i <= 29; i++)
//		{
//			comboBox.addItem("Character_" + i);
//		}
//		comboBox.addItem("Hero-Villian");
		nPanel.add(comboBox);
		
		JScrollPane wPane = new JScrollPane();
		wPane.setPreferredSize(new Dimension(700, 700));
		wPane.add(wPanel);
		wPane.setViewportView(wPanel);
		
		comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	handleChangeBackgroundFolder();
            }
        });

		JPanel panel2 = new JPanel(new BorderLayout());
		JButton place = new JButton("Place");
		place.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				backPathString = selectedPath.substring(selectedPath.indexOf((String)comboBox.getSelectedItem()));
				setVisible(false);
			}
		});
		place.setPreferredSize(new Dimension(280, 40));
		panel2.add(place, BorderLayout.SOUTH);

		ePanel.add(pic,BorderLayout.CENTER);
		ePanel.add(panel2,BorderLayout.SOUTH);
		ePanel.setPreferredSize(new Dimension(325,650));

		add(ePanel, BorderLayout.EAST);
		add(wPane, BorderLayout.WEST);
		add(nPanel, BorderLayout.NORTH);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
		setResizable(false);
		
		handleChangeBackgroundFolder();
	}
	
	private void handleChangeBackgroundFolder()
	{
		if(comboBox.getSelectedItem() == null)
		{return;}
		final ArrayList<JLabel> jlabels = new ArrayList<JLabel>();
        String item = (String)comboBox.getSelectedItem();
        
        System.out.println(item);
        
    	File dir = new File("Office, Classroom/" + item + "/");
    	wPanel.removeAll();
    	
    	for (File child : dir.listFiles())
		{
    		if(child.isDirectory())
    		{
    			continue;
    		}
    		try {
	    		BufferedImage image = ImageHelper.getScaledImage(ImageIO.read(child), 0.43);
	    		
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
						BufferedImage img1 = ImageHelper.getScaledImage(ImageIO.read(new File(l.getName())), .43);
						pic.setIcon(new ImageIcon(img1));
			    		selectedPath = l.getName();
					} catch(Exception e4) {}
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
	}
	
	public void setBackgroundPathString(String backgroundString)
	{
		backPathString = backgroundString;
	}
	
	public String getNewBackgroundPath()
	{
		return backPathString;
	}
	
	public void setBackgroundFolderPath(String folderPath)
	{
		comboBox.removeAllItems();
		/*for(String charName : charNames)
		{
			comboBox.addItem(charName);
		}*/
		comboBox.addItem(folderPath);
		handleChangeBackgroundFolder();
	}
}