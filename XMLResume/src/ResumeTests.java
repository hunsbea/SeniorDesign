import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ResumeTests {
	
	public static void run() {
		ResumeModel resume = new ResumeModel();
		resume.name = "Dr. Ima Coder";
		resume.photoPath = "ima_coder1.png";
		resume.title = "Instructor";
		resume.skills = new ArrayList<String>();
		resume.skills.add("Software Engineering"); 
		resume.skills.add("Project Management");
		resume.skills.add("Capstone Project courses");
		resume.yearsOfExp = "5";
		resume.communication = "Excellent";
		resume.leadership = "Excellent";
		resume.teamwork = "Excellent";
		resume.demographics = new ArrayList<String>();
		resume.demographics.add("Female");
		resume.demographics.add("Caucasian");
		resume.availability = "Excellent";
		resume.attendance = "Excellent";
		resume.degrees = new ArrayList<String>();
		resume.degrees.add("B.Sc. Computer Science, Stanford University");
		resume.degrees.add("Ph.D. Computer Science, Stanford University");
		
		ResumeSettingsModel settings = new ResumeSettingsModel();
		settings.colorR = 255;
		settings.colorG = 200;
		settings.colorB = 255;
		settings.fontName = "Arial";
		settings.fontStyle = Font.ITALIC;
		settings.fontSize = 14;
		
		try {
			// RESUME CONTENT
			File file = new File("resume.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ResumeModel.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller.marshal(resume, file);
			jaxbMarshaller.marshal(resume, System.out);

			// RESUME SETTINGS
			File file2 = new File("settings.xml");
			JAXBContext jaxbContext2 = JAXBContext.newInstance(ResumeSettingsModel.class);
			Marshaller jaxbMarshaller2 = jaxbContext2.createMarshaller();
	 
			jaxbMarshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 
			jaxbMarshaller2.marshal(settings, file2);
			jaxbMarshaller2.marshal(settings, System.out);
 
	    } catch (JAXBException e) {
	    	e.printStackTrace();
	    }
	
		try {
			File file = new File("resume.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ResumeModel.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ResumeModel resume2 = (ResumeModel) jaxbUnmarshaller.unmarshal(file);
			System.out.println(resume2);

			File file2 = new File("settings.xml");
			JAXBContext jaxbContext2 = JAXBContext.newInstance(ResumeSettingsModel.class);
	 
			Unmarshaller jaxbUnmarshaller2 = jaxbContext2.createUnmarshaller();
			ResumeSettingsModel settings2 = (ResumeSettingsModel) jaxbUnmarshaller2.unmarshal(file2);
			System.out.println(settings2);
	 
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
