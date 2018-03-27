package com.shty.collect.web.rest.dto;

public class System_Twitter_TaskGroupDto {
	private int id;
	private String groupname;
	private String groupstatus;
	private String grouptype;
	private String priority;
	private String keyword;
	private String taskattributeid;
	private String weekchoice;
	private String startregiontime;
	private String endregiontime;
	private String updatetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupstatus() {
		return groupstatus;
	}
	public void setGroupstatus(String groupstatus) {
		this.groupstatus = groupstatus;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTaskattributeid() {
		return taskattributeid;
	}
	public void setTaskattributeid(String taskattributeid) {
		this.taskattributeid = taskattributeid;
	}
	public String getWeekchoice() {
		return weekchoice;
	}
	public void setWeekchoice(String weekchoice) {
		this.weekchoice = weekchoice;
	}
	public String getStartregiontime() {
		return startregiontime;
	}
	public void setStartregiontime(String startregiontime) {
		this.startregiontime = startregiontime;
	}
	public String getEndregiontime() {
		return endregiontime;
	}
	public void setEndregiontime(String endregiontime) {
		this.endregiontime = endregiontime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

}