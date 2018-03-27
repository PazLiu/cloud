package com.shty.collect.web.rest.dto;

public class LinkedinKeywordDto {

	private Integer id;

	private Integer finished;

	private String groupName;

	private String keyword;

	private String createtime;

	private String updatatime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFinished() {
		return finished;
	}

	public void setFinished(Integer finished) {
		this.finished = finished;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatatime() {
		return updatatime;
	}

	public void setUpdatatime(String updatatime) {
		this.updatatime = updatatime;
	}

}
