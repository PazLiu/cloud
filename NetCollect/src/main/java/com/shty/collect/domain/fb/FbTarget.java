package com.shty.collect.domain.fb;

public class FbTarget {
	private Long id;
	
	private Long task_id;
	
	private String urlpattern="";
	
	private byte[] fmt_headline;
	
	private byte[] fmt_name;
	
	private byte[] overview;
	
	private String imgpath;
	
	private String fullName;
	
	private String friendsurl;
	
	private String photoUrl;

	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getFriendsurl() {
		return friendsurl;
	}

	public void setFriendsurl(String friendsurl) {
		this.friendsurl = friendsurl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrlpattern() {
		return urlpattern;
	}

	public void setUrlpattern(String urlpattern) {
		this.urlpattern = urlpattern;
	}

	public byte[] getFmt_headline() {
		return fmt_headline;
	}

	public void setFmt_headline(byte[] fmt_headline) {
		this.fmt_headline = fmt_headline;
	}

	public byte[] getFmt_name() {
		return fmt_name;
	}

	public void setFmt_name(byte[] fmt_name) {
		this.fmt_name = fmt_name;
	}

	public byte[] getOverview() {
		return overview;
	}

	public void setOverview(byte[] overview) {
		this.overview = overview;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}
