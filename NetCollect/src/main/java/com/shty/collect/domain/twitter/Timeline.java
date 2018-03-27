package com.shty.collect.domain.twitter;

import java.util.List;

public class Timeline {
	private String id;
	private String twid;
	private String twContent;
	private String twImgURL;
	private String twImgName;
	private String twUserID;
	private String profileID;
	private String twUserName;
	private String twUserAvatarURL;
	private int twImgURLSize;
	private int Tweet;
	private String twPubTime;
	private String twPositionValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTwid() {
		return twid;
	}

	public void setTwid(String twid) {
		this.twid = twid;
	}

	public String getTwContent() {
		return twContent;
	}

	public void setTwContent(String twContent) {
		this.twContent = twContent;
	}

	public String getTwImgURL() {
		return twImgURL;
	}

	public void setTwImgURL(String twImgURL) {
		this.twImgURL = twImgURL;
	}

	public String getTwImgName() {
		return twImgName;
	}

	public void setTwImgName(String twImgName) {
		this.twImgName = twImgName;
	}

	public String getProfileID() {
		return profileID;
	}

	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}

	public String getTwUserID() {
		return twUserID;
	}

	public void setTwUserID(String twUserID) {
		this.twUserID = twUserID;
	}

	public String getTwUserName() {
		return twUserName;
	}

	public void setTwUserName(String twUserName) {
		this.twUserName = twUserName;
	}

	public String getTwUserAvatarURL() {
		return twUserAvatarURL;
	}

	public void setTwUserAvatarURL(String twUserAvatarURL) {
		this.twUserAvatarURL = twUserAvatarURL;
	}

	public int getTweet() {
		return Tweet;
	}

	public void setTweet(int tweet) {
		Tweet = tweet;
	}

	public String getTwPubTime() {
		return twPubTime;
	}

	public void setTwPubTime(String twPubTime) {
		this.twPubTime = twPubTime;
	}

	public String getTwPositionValue() {
		return twPositionValue;
	}

	public void setTwPositionValue(String twPositionValue) {
		this.twPositionValue = twPositionValue;
	}

	public int getTwImgURLSize() {
		return twImgURLSize;
	}

	public void setTwImgURLSize(int twImgURLSize) {
		this.twImgURLSize = twImgURLSize;
	}
	
}
