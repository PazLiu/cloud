package com.shty.collect.domain.lnk;

import java.sql.Timestamp;

public class Websites {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String webKey;

	private String webValue;

	private Long overview_id;

	private Long people_id;

	public Long getPeople_id() {
		return people_id;
	}

	public void setPeople_id(Long people_id) {
		this.people_id = people_id;
	}

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

	public String getWebKey() {
		return webKey;
	}

	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}

	public String getWebValue() {
		return webValue;
	}

	public void setWebValue(String webValue) {
		this.webValue = webValue;
	}

	public Long getOverview_id() {
		return overview_id;
	}

	public void setOverview_id(Long overview_id) {
		this.overview_id = overview_id;
	}

}
