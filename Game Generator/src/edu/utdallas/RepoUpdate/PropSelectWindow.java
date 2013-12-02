package edu.utdallas.RepoUpdate;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
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

import edu.utdallas.gamegenerator.Shared.ImageAsset;

public class PropSelectWindow extends JDialog
{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1150, HEIGHT = 700;
	private String imageBaseDir = "Office, Classroom\\";
	final JLabel pic = new JLabel();
	final JPanel wPanel = new JPanel(new GridLayout(0, 4));
	//final JPanel ePanel = new JPanel(new GridLayout(0, 1, 0, 0));
	final JPanel ePanel = new JPanel(new BorderLayout());
	final JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 130, 80);
	final JComboBox<String> comboBox = new JComboBox<String>();
	String selectedPath = "";
	private ImageAsset imgAsset;
	
	public PropSelectWindow(JFrame owner)
	{
		super(owner, "Prop Selection", Dialog.DEFAULT_MODALITY_TYPE);
		setSize(WIDTH, HEIGHT);
		setResizable(false);

		JPanel nPanel = new JPanel();
		
		File dir = new File("Office, Classroom/Props/SetDecoration/");
		for (File child : dir.listFiles())
		{
    		if(child.isDirectory())
    		{
    			comboBox.addItem(child.getName());
    		}
		}
		nPanel.add(comboBox);
		
		JScrollPane wPane = new JScrollPane();
		wPane.setPreferredSize(new Dimension(700, 700));
		wPane.add(wPanel);
		wPane.setViewportView(wPanel);
		
		comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	handleChangePropCatagory();
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
					BufferedImage img1 = getScaledImage(ImageIO.read(new File(selectedPath)), sValue);
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
				imgAsset = new ImageAsset();
				String imgstrng = selectedPath.substring(selectedPath.indexOf(imageBaseDir)+imageBaseDir.length());
				imgAsset.setDisplayImage(imgstrng);
				imgAsset.setFontFamily("Comic Sans MS");
				imgAsset.setFontSize(15);
				imgAsset.setHeight(pic.getIcon().getIconHeight());
				imgAsset.setWidth(pic.getIcon().getIconWidth());
				imgAsset.setLocX(300);
				imgAsset.setLocX2(300 + imgAsset.getWidth());
				imgAsset.setLocY(50);
				imgAsset.setLocY2(50 + imgAsset.getHeight());
				imgAsset.setOpacity(1);
				setVisible(false);
			}
		});
		place.setPreferredSize(new Dimension(280, 40));
		panel2.add(place, BorderLayout.SOUTH);

		ePanel.add(pic,BorderLayout.CENTER);
		ePanel.add(panel2,BorderLayout.SOUTH);
		ePanel.setPreferredSize(new Dimension(325+100,650));

		add(ePanel, BorderLayout.EAST);
		add(wPane, BorderLayout.WEST);
		add(nPanel, BorderLayout.NORTH);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
		
		handleChangePropCatagory();
	}
	
	private void handleChangePropCatagory()
	{
		if(comboBox.getSelectedItem() == null)
		{return;}
		final ArrayList<JLabel> jlabels = new ArrayList<JLabel>();
        String folder = (String)comboBox.getSelectedItem();
        
        System.out.println(folder);
        
    	File dir = new File("Office, Classroom/Props/SetDecoration/" + folder + "/");
    	wPanel.removeAll();
		
    	for (File child : dir.listFiles())
		{
    		if(child.isDirectory())
    		{
    			continue;
    		}
    		try {
    			BufferedImage image = ImageIO.read(child);
	    		BufferedImage scaledImage = getScaledImage(image, (150f)/((float)image.getWidth()));
	    		
	    		final JLabel l = new JLabel(new ImageIcon(scaledImage));
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
					handleGridClick(l, jlabels);
				}
				public void mouseReleased(MouseEvent e) {
				}
			});
		}
    	handleGridClick(jlabels.get(0), jlabels);
	}
	
	private void handleGridClick(JLabel label, ArrayList<JLabel> jlabels)
	{
		for(int i = 0; i < jlabels.size(); i++)
		{
			jlabels.get(i).setBorder(null);
		}
		label.setBorder(BorderFactory.createLoweredBevelBorder());
		try {
			BufferedImage img1 = getScaledImage(ImageIO.read(new File(label.getName())), slider.getValue() / 100.0);
			pic.setIcon(new ImageIcon(img1));
    		selectedPath = label.getName();
		} catch(Exception e4) {}
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
	
	public void setImageAsset(ImageAsset imageAsset)
	{
		imgAsset = imageAsset;
	}
	
	public ImageAsset getNewImageAsset()
	{
		return imgAsset;
	}
	
	public void setImageChoices(ArrayList<String> ImageFolders)
	{
		comboBox.removeAllItems();
		for(String imageFolder : ImageFolders)
		{
			comboBox.addItem(imageFolder);
		}
		handleChangePropCatagory();
	}
}