package com.shty.collect.domain.lnk;

import java.sql.Timestamp;

public class Connections {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String lnkid;

	private String fullname;

	private String fmt_name;

	private String fmt_summary;

	private String fmt_location;

	private String fmt_industry;

	private String fmt_current;

	private String fmt_past;

	private String fmt_education;

	private String lnkurl;

	private int finished;

	private String fmt_headline;

	private int peekType;

	private int referenceType;

	private String referenceValue;

	private String imgurl;

	private String imgcode;

	private String imgname;

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

	public String getLnkid() {
		return lnkid;
	}

	public void setLnkid(String lnkid) {
		this.lnkid = lnkid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFmt_name() {
		return fmt_name;
	}

	public void setFmt_name(String fmt_name) {
		this.fmt_name = fmt_name;
	}

	public String getFmt_summary() {
		return fmt_summary;
	}

	public void setFmt_summary(String fmt_summary) {
		this.fmt_summary = fmt_summary;
	}

	public String getFmt_location() {
		return fmt_location;
	}

	public void setFmt_location(String fmt_location) {
		this.fmt_location = fmt_location;
	}

	public String getFmt_industry() {
		return fmt_industry;
	}

	public void setFmt_industry(String fmt_industry) {
		this.fmt_industry = fmt_industry;
	}

	public String getFmt_current() {
		return fmt_current;
	}

	public void setFmt_current(String fmt_current) {
		this.fmt_current = fmt_current;
	}

	public String getFmt_past() {
		return fmt_past;
	}

	public void setFmt_past(String fmt_past) {
		this.fmt_past = fmt_past;
	}

	public String getFmt_education() {
		return fmt_education;
	}

	public void setFmt_education(String fmt_education) {
		this.fmt_education = fmt_education;
	}

	public String getLnkurl() {
		return lnkurl;
	}

	public void setLnkurl(String lnkurl) {
		this.lnkurl = lnkurl;
	}

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public String getFmt_headline() {
		return fmt_headline;
	}

	public void setFmt_headline(String fmt_headline) {
		this.fmt_headline = fmt_headline;
	}

	public int getPeekType() {
		return peekType;
	}

	public void setPeekType(int peekType) {
		this.peekType = peekType;
	}

	public int getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(int referenceType) {
		this.referenceType = referenceType;
	}

	public String getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgcode() {
		return imgcode;
	}

	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public long getPeople_id() {
		return people_id;
	}

	public void setPeople_id(long people_id) {
		this.people_id = people_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Connections [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", lnkid=");
		builder.append(lnkid);
		builder.append(", fullname=");
		builder.append(fullname);
		builder.append(", lnkurl=");
		builder.append(lnkurl);
		builder.append(", finished=");
		builder.append(finished);
		builder.append(", fmt_headline=");
		builder.append(fmt_headline);
		builder.append(", peekType=");
		builder.append(peekType);
		builder.append(", referenceType=");
		builder.append(referenceType);
		builder.append(", referenceValue=");
		builder.append(referenceValue);
		builder.append(", imgurl=");
		builder.append(imgurl);
		builder.append(", imgcode=");
		builder.append(imgcode);
		builder.append(", imgname=");
		builder.append(imgname);
		builder.append(", people_id=");
		builder.append(people_id);
		builder.append("]");
		return builder.toString();
	}

}
