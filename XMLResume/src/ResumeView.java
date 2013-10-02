import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ResumeView extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 750, HEIGHT = 700;
	private Color userColor;
	private Font userFont;
	private ResumeModel resume1;
	private ResumeSettingsModel settings1;
	private JPanel labelPanel;
	private JPanel infoPanel;
	
	public ResumeView(ResumeModel resume, ResumeSettingsModel settings) {
		super("Resume");
		setSize(WIDTH, HEIGHT);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width/2 - WIDTH/2, d.height/2 - HEIGHT/2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		resume1 = resume;
		settings1 = settings;
		
		userColor = new Color(settings.colorR, settings.colorG, settings.colorB);
		getContentPane().setBackground(userColor);
		userFont = new Font(settings.fontName, settings.fontStyle, settings.fontSize);
		setFont(userFont);
		
		// 2 columns total, the label panel being skinnier than the info panel
		labelPanel = new JPanel(new GridLayout(0, 1));
		labelPanel.setMaximumSize(new Dimension(200, 2000));
		infoPanel = new JPanel(new GridLayout(0, 1));
		
		// Write all object properties back to XML on close
		WindowListener saveOnExitListener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	Main.saveResume(resume1);
            	Main.saveSettings(settings1);
        }};        
        addWindowListener(saveOnExitListener);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu color = new JMenu("Color");
        JMenuItem color1 = new JMenuItem("Color1");
        color1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		settings1.colorR = 200;
        		settings1.colorG = 255;
        		settings1.colorB = 255;
        	}
        });
        color.add(color1);
        JMenuItem color2 = new JMenuItem("Color2");
        color2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		settings1.colorR = 255;
        		settings1.colorG = 200;
        		settings1.colorB = 255;
        	}
        });
        color.add(color2);
        JMenuItem color3 = new JMenuItem("Color3");
        color3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		settings1.colorR = 255;
        		settings1.colorG = 255;
        		settings1.colorB = 200;
        	}
        });
        color.add(color3);
        menuBar.add(color);
        JMenu font = new JMenu("Font");
        JMenuItem font1 = new JMenuItem("Font1");
        font1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		settings1.fontName = "Arial";
        		settings1.fontStyle = Font.ITALIC;
        		settings1.fontSize = 14;
        	}
        });
        font.add(font1);
        JMenuItem font2 = new JMenuItem("Font2");
        font2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		settings1.fontName = "Calibri";
        		settings1.fontStyle = Font.BOLD;
        		settings1.fontSize = 14;
        	}
        });
        font.add(font2);
        JMenuItem font3 = new JMenuItem("Font3");
        font3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		settings1.fontName = "Consolas";
        		settings1.fontStyle = Font.PLAIN;
        		settings1.fontSize = 11;
        	}
        });
        font.add(font3);
        menuBar.add(font);
        setJMenuBar(menuBar);
		
		//Begin display resume
        registerField("name", addField("Name ", resume.name));
		
		//display photo
		JLabel photoLabel1 = new JLabel("Resume Photo ");
		photoLabel1.setOpaque(true);
		photoLabel1.setBackground(userColor);
		photoLabel1.setFont(userFont);
		labelPanel.add(photoLabel1);
		JLabel photoLabel2 = new JLabel(new ImageIcon(resume.photoPath));
		photoLabel2.setOpaque(true);
		photoLabel2.setBackground(userColor);
		photoLabel2.setFont(userFont);
		infoPanel.add(photoLabel2);
		
		registerField("title", addField("Title ", resume.title));
		
		String skills = "";
		for(String c : resume.skills) {
			skills += c + ", ";
		}
		addField("Skills ", skills);

		registerField("yearsOfExp", addField("Years of Experience ", resume.yearsOfExp));
		registerField("communication", addField("Communication ", resume.communication));
		registerField("leadership", addField("Leadership ", resume.leadership));
		registerField("teamwork", addField("Teamwork ", resume.teamwork));
		
		String demog = "";
		for(String g : resume.demographics) {
			demog += g + ", ";
		}
		addField("Demographics ", demog);

		registerField("availability", addField("Availability ", resume.availability));
		registerField("attendance", addField("Attendance ", resume.attendance));

		String degrees = "";
		for(String e : resume.degrees) {
			degrees += e + ", ";
		}
		addField("Degrees ", degrees);
		
		add(labelPanel, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);
	}
	
	private JTextField addField(String label, String text) {
		JLabel jlabel = new JLabel(label);
		jlabel.setOpaque(true);
		jlabel.setBackground(userColor);
		jlabel.setFont(userFont);
		labelPanel.add(jlabel);
		JTextField field = new JTextField();
		field.setFont(userFont);
		field.setText(text);
		infoPanel.add(field);
		return field;
	}

	private void registerField(final String fieldName, final JTextField field) {
		
		field.getDocument().addDocumentListener(new DocumentListener() {
        	public void changedUpdate(DocumentEvent e) { updateField(fieldName, field.getText()); }
        	public void removeUpdate(DocumentEvent e) { updateField(fieldName, field.getText()); }
        	public void insertUpdate(DocumentEvent e) { updateField(fieldName, field.getText()); }
        });
	}

	private void updateField(String fieldName, String value) {
		try {
			Field f = resume1.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(resume1, value);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
