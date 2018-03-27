package com.shty.collect.domain.lnk;

import java.sql.Timestamp;

public class EducationsActivities {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String activityname;

	private String activityurl;

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

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getActivityurl() {
		return activityurl;
	}

	public void setActivityurl(String activityurl) {
		this.activityurl = activityurl;
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
		builder.append("EducationsActivities [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", activityname=");
		builder.append(activityname);
		builder.append(", activityurl=");
		builder.append(activityurl);
		builder.append(", educations_id=");
		builder.append(educations_id);
		builder.append("]");
		return builder.toString();
	}

}
