package edu.utdallas.gamegenerator.Character;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Profile")
public class Profile 
{
	private String profilePhoto;
	private String title;
	private int yearsOfExperience;
	private String communication;
	private String availability;
	private List<String> skills;
	private List<String> demographics;
	private List<String> degrees;
	private String teamwork;
	private String attendance;

    @XmlElement(name = "ProfilePhoto")
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
    @XmlElement(name = "Communication")
	public String getCommunication() {
		return communication;
	}
	public void setCommunication(String communication) {
		this.communication = communication;
	}
    @XmlElement(name = "Title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    @XmlElement(name = "YearsOfExperience")
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
    @XmlElement(name = "Availability")
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	@XmlElementWrapper(name = "Skills")
    @XmlElement(name = "Skill")
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	@XmlElementWrapper(name = "Demographics")
    @XmlElement(name = "Demographic")
	public List<String> getDemographics() {
		return demographics;
	}
	public void setDemographics(List<String> demographics) {
		this.demographics = demographics;
	}
	@XmlElementWrapper(name = "Degrees")
    @XmlElement(name = "Degree")
	public List<String> getDegrees() {
		return degrees;
	}
	public void setDegrees(List<String> degrees) {
		this.degrees = degrees;
	}
    @XmlElement(name = "Teamwork")
	public String getTeamwork() {
		return teamwork;
	}
	public void setTeamwork(String teamwork) {
		this.teamwork = teamwork;
	}
    @XmlElement(name = "Attendance")
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
}
