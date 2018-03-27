package com.shty.collect.domain.lnk;

public class Authors extends BaseEntity {

	private String fullname;

	private String lnkurl;

	private String fmt_headline;

	private String imgurl;

	private int finished;

	private int peekType;

	private int referenceType;

	private String imgcode;

	private String imgname;

	private Long publications_id;

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getLnkurl() {
		return lnkurl;
	}

	public void setLnkurl(String lnkurl) {
		this.lnkurl = lnkurl;
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

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public int getPeekType() {
		return peekType;
	}

	public void setPeekType(int peekType) {
		this.peekType = peekType;
	}

	public int getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(int referenceType) {
		this.referenceType = referenceType;
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

	public Long getPublications_id() {
		return publications_id;
	}

	public void setPublications_id(Long publications_id) {
		this.publications_id = publications_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Authors [fullname=");
		builder.append(fullname);
		builder.append(", lnkurl=");
		builder.append(lnkurl);
		builder.append(", fmt_headline=");
		builder.append(fmt_headline);
		builder.append(", imgurl=");
		builder.append(imgurl);
		builder.append(", finished=");
		builder.append(finished);
		builder.append(", peekType=");
		builder.append(peekType);
		builder.append(", referenceType=");
		builder.append(referenceType);
		builder.append(", imgcode=");
		builder.append(imgcode);
		builder.append(", imgname=");
		builder.append(imgname);
		builder.append(", publications_id=");
		builder.append(publications_id);
		builder.append("]");
		return builder.toString();
	}

}
