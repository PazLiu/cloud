package com.shty.collect.domain.lnk;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Overview {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String fullname;

	private String imgurl;

	private String headline;

	private String location;

	private String industry;

	private String current;

	private String past;

	private String education;

	private String twitter;

	private Websites websiteswebsites;

	private Object websites;

	private String email;

	private String imim;

	private ArrayList<String> im;

	private String phone;

	private String address;

	private String birthDate;

	private long people_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateCreatedTime() {
		return dateCreatedTime;
	}

	public void setDateCreatedTime(Timestamp dateCreatedTime) {
		this.dateCreatedTime = dateCreatedTime;
	}

	public Timestamp getDateUpdatedTime() {
		return dateUpdatedTime;
	}

	public void setDateUpdatedTime(Timestamp dateUpdatedTime) {
		this.dateUpdatedTime = dateUpdatedTime;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getPast() {
		return past;
	}

	public void setPast(String past) {
		this.past = past;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImim() {
		return imim;
	}

	public void setImim(String imim) {
		this.imim = imim;
	}

	public ArrayList<String> getIm() {
		return im;
	}

	public void setIm(ArrayList<String> im) {
		this.im = im;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone.toString();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public long getPeople_id() {
		return people_id;
	}

	public void setPeople_id(long people_id) {
		this.people_id = people_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Overview [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", fullname=");
		builder.append(fullname);
		builder.append(", imgurl=");
		builder.append(imgurl);
		builder.append(", headline=");
		builder.append(headline);
		builder.append(", location=");
		builder.append(location);
		builder.append(", industry=");
		builder.append(industry);
		builder.append(", current=");
		builder.append(current);
		builder.append(", past=");
		builder.append(past);
		builder.append(", education=");
		builder.append(education);
		builder.append(", twitter=");
		builder.append(twitter);
		builder.append(", websites=");
		builder.append(websites);
		builder.append(", email=");
		builder.append(email);
		builder.append(", imim=");
		builder.append(imim);
		builder.append(", im=");
		builder.append(im);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", address=");
		builder.append(address);
		builder.append(", people_id=");
		builder.append(people_id);
		builder.append("]");
		return builder.toString();
	}

	public Websites getWebsiteswebsites() {
		return websiteswebsites;
	}

	public void setWebsiteswebsites(Websites websiteswebsites) {
		this.websiteswebsites = websiteswebsites;
	}

	public void setWebsites(Object websites) {
		this.websites = websites;
	}

	public Object getWebsites() {
		return websites;
	}

}
