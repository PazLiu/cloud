package com.shty.collect.domain.lnk;

import java.sql.Timestamp;

public class ExperiencesAttachements {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String whos;

	private String title;

	private String staticimgurl;

	private String imgcode;

	private String imgname;

	private Long experiences_id;

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

	public String getWhos() {
		return whos;
	}

	public void setWhos(String whos) {
		this.whos = whos;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStaticimgurl() {
		return staticimgurl;
	}

	public void setStaticimgurl(String staticimgurl) {
		this.staticimgurl = staticimgurl;
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

	public Long getExperiences_id() {
		return experiences_id;
	}

	public void setExperiences_id(Long experiences_id) {
		this.experiences_id = experiences_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExperiencesAttachements [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", whos=");
		builder.append(whos);
		builder.append(", title=");
		builder.append(title);
		builder.append(", staticimgurl=");
		builder.append(staticimgurl);
		builder.append(", imgcode=");
		builder.append(imgcode);
		builder.append(", imgname=");
		builder.append(imgname);
		builder.append(", experiences_id=");
		builder.append(experiences_id);
		builder.append("]");
		return builder.toString();
	}

}
