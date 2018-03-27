package com.shty.collect.web.rest.dto;

/**
 * Facebook 属性表
 * Facebook 分组表
 * Facebook 任务表
 * 三张表的Dto
 * @author Administrator
 *
 */
public class Syste_Facebook_Task_Attribute_Group {
	private int id;
	private String url;
	private String taskstatus;
	private String priority;
	private String weekchoice;
	private String startregiontime;
	private String endregiontime;
	private String downloadattribute;
	private String groupname;
	private String grouptype;
	private String keyword;
	private String overview;
	private String friends;
	private String timelin;
	private String comments;
	private String likes;
	private String photos;
	private String taskattributeid;
	private String taskgroupid;
	private String taskName;
	private long FBTarget_id;
	
	
	
	public long getFBTarget_id() {
		return FBTarget_id;
	}
	public void setFBTarget_id(long fBTarget_id) {
		FBTarget_id = fBTarget_id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskattributeid() {
		return taskattributeid;
	}
	public void setTaskattributeid(String taskattributeid) {
		this.taskattributeid = taskattributeid;
	}
	public String getTaskgroupid() {
		return taskgroupid;
	}
	public void setTaskgroupid(String taskgroupid) {
		this.taskgroupid = taskgroupid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
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
	public String getDownloadattribute() {
		return downloadattribute;
	}
	public void setDownloadattribute(String downloadattribute) {
		this.downloadattribute = downloadattribute;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getFriends() {
		return friends;
	}
	public void setFriends(String friends) {
		this.friends = friends;
	}
	public String getTimelin() {
		return timelin;
	}
	public void setTimelin(String timelin) {
		this.timelin = timelin;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	
	
	
}
