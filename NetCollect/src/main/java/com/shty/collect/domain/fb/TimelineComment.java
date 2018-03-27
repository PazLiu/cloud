package com.shty.collect.domain.fb;

public class TimelineComment {
	private Long id;
	private String fbid;
	private String fburl;
	private String fmt_name;
	private String fmt_headline;
	private String imgurl;
	private String imgPath;
	private String imgcode;
	private int referenceKey;
	private String referenceValue;
	private Long FBStory_id;
	private String content;
	private String pubdate;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFbid() {
		return fbid;
	}
	public void setFbid(String fbid) {
		this.fbid = fbid;
	}
	public String getFburl() {
		return fburl;
	}
	public void setFburl(String fburl) {
		this.fburl = fburl;
	}
	public String getFmt_name() {
		return fmt_name;
	}
	public void setFmt_name(String fmt_name) {
		this.fmt_name = fmt_name;
	}
	public String getFmt_headline() {
		return fmt_headline;
	}
	public void setFmt_headline(String fmt_headline) {
		this.fmt_headline = fmt_headline;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgcode() {
		return imgcode;
	}
	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}
	public int getReferenceKey() {
		return referenceKey;
	}
	public void setReferenceKey(int referenceKey) {
		this.referenceKey = referenceKey;
	}
	public String getReferenceValue() {
		return referenceValue;
	}
	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}
	public Long getFBStory_id() {
		return FBStory_id;
	}
	public void setFBStory_id(Long fBStory_id) {
		FBStory_id = fBStory_id;
	}
	
}
