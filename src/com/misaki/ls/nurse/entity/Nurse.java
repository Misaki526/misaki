package com.misaki.ls.nurse.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.apache.solr.client.solrj.beans.Field;

public class Nurse implements Serializable {
	private static final long serialVersionUID = -6195129013403169447L;
	
	@Field("id")
	private String nurseId;
	@Field("nurse_name")
	private String nurseName;
	@Field("nurse_sex")
	private String nurseSex;
	@Field("nurse_headImg")
	private String nurseHeadImg;
	@Field("nurse_salary")
	private Double nurseSalary; 
	@Field("nurse_mobile")
	private String nurseMobile;
	@Field("nurse_score")
	private Double nurseScore;
	@Field("nurse_education")
	private String nurseEducation;
	@Field("nurse_birthday")
	private Date nurseBirthday;
	@Field("nurse_experience")
	private String nurseExperience;
	@Field("nurse_area")
	private String nurseArea;
	@Field("last_modified")
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
