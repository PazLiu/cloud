package com.shty.collect.web.rest.dto;

public class LnkTaskDto {

	private int id;

	private String finished;

	private String fullname;

	private String url;

	private String taskstatus;

	private int keywordid;

	private String summary;

	private String creatTime;

	private String updateTime;

	private String lnkid;

	public String getLnkid() {
		return lnkid;
	}

	public void setLnkid(String lnkid) {
		this.lnkid = lnkid;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	public int getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(int keywordid) {
		this.keywordid = keywordid;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
