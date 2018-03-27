package com.shty.collect.domain.lnk;

import java.sql.Timestamp;

public class EducationsAttachements {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String whos;

	private String title;

	private String staticimgurl;

	private String imgcode;

	private String imgname;

	private Long educations_id;

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

	public Long getEducations_id() {
		return educations_id;
	}

	public void setEducations_id(Long educations_id) {
		this.educations_id = educations_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EducationsAttachements [id=");
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
		builder.append(", educations_id=");
		builder.append(educations_id);
		builder.append("]");
		return builder.toString();
	}

}
