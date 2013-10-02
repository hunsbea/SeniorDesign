import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class Main {
	
	public static void main(String[] args) {
		//ResumeTests.run();
		ResumeModel resume = new ResumeModel();
		ResumeSettingsModel settings = new ResumeSettingsModel();
		
		try {
			resume = (ResumeModel) JAXBContext.newInstance(ResumeModel.class).createUnmarshaller().unmarshal(new File("resume.xml"));
			settings = (ResumeSettingsModel) JAXBContext.newInstance(ResumeSettingsModel.class).createUnmarshaller().unmarshal(new File("settings.xml"));
	 
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		ResumeView view = new ResumeView(resume, settings);
		view.setVisible(true);
	}

	public static void saveResume(ResumeModel resume) {
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(ResumeModel.class).createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(resume, new File("resume.xml"));
	    }
		catch (JAXBException e) {
	    	e.printStackTrace();
	    }
	}

	public static void saveSettings(ResumeSettingsModel settings) {
		try {
			Marshaller jaxbMarshaller = JAXBContext.newInstance(ResumeSettingsModel.class).createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(settings, new File("settings.xml"));
	    }
		catch (JAXBException e) {
	    	e.printStackTrace();
	    }
	}
}
