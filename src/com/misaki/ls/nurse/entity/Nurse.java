package com.misaki.ls.nurse.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Nurse implements Serializable {
	private static final long serialVersionUID = -6195129013403169447L;
	
	private String nurseId;
	private String nurseName;
	private String nurseSex;
	private String nurseHeadImg;
	private Double nurseSalary; 
	private String nurseMobile;
	private Double nurseScore;
	private String nurseEducation;
	private Date nurseBirthday;
	private String nurseExperience;
	private String nurseArea;
	private Date lastModified;
	
	public Nurse() {
		this.nurseId = "nur" + UUID.randomUUID().toString().replace("-", "");
	}

	public String getNurseId() {
		return nurseId;
	}

	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}

	public String getNurseName() {
		return nurseName;
	}

	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}

	public String getNurseSex() {
		return nurseSex;
	}

	public void setNurseSex(String nurseSex) {
		this.nurseSex = nurseSex;
	}

	public String getNurseHeadImg() {
		return nurseHeadImg;
	}

	public void setNurseHeadImg(String nurseHeadImg) {
		this.nurseHeadImg = nurseHeadImg;
	}

	public Double getNurseSalary() {
		return nurseSalary;
	}

	public void setNurseSalary(Double nurseSalary) {
		this.nurseSalary = nurseSalary;
	}

	public String getNurseMobile() {
		return nurseMobile;
	}

	public void setNurseMobile(String nurseMobile) {
		this.nurseMobile = nurseMobile;
	}

	public Double getNurseScore() {
		return nurseScore;
	}

	public void setNurseScore(Double nurseScore) {
		this.nurseScore = nurseScore;
	}

	public String getNurseEducation() {
		return nurseEducation;
	}

	public void setNurseEducation(String nurseEducation) {
		this.nurseEducation = nurseEducation;
	}

	public Date getNurseBirthday() {
		return nurseBirthday;
	}

	public void setNurseBirthday(Date nurseBirthday) {
		this.nurseBirthday = nurseBirthday;
	}

	public String getNurseExperience() {
		return nurseExperience;
	}

	public void setNurseExperience(String nurseExperience) {
		this.nurseExperience = nurseExperience;
	}

	public String getNurseArea() {
		return nurseArea;
	}

	public void setNurseArea(String nurseArea) {
		this.nurseArea = nurseArea;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "Nurse [nurseId=" + nurseId + ", nurseName=" + nurseName
				+ ", nurseSex=" + nurseSex + ", nurseHeadImg=" + nurseHeadImg
				+ ", nurseSalary=" + nurseSalary + ", nurseMobile="
				+ nurseMobile + ", nurseScore=" + nurseScore
				+ ", nurseEducation=" + nurseEducation + ", nurseBirthday="
				+ nurseBirthday + ", nurseExperience=" + nurseExperience
				+ ", nurseArea=" + nurseArea + ", lastModified=" + lastModified
				+ "]";
	}
	
}
