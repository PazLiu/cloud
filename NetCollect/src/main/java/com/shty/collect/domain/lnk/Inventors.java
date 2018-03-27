package com.shty.collect.domain.lnk;

public class Inventors extends BaseEntity {

	private String fullname;

	private String lnkurl;

	private int finished;

	private String fmt_headline;

	private int peekType;

	private int referenceType;

	private String imgurl;

	private String imgcode;

	private String imgname;

	private Long patents_id;

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

	public int getFinished() {
		return finished;
	}

	public void setFinished(int finished) {
		this.finished = finished;
	}

	public String getFmt_headline() {
		return fmt_headline;
	}

	public void setFmt_headline(String fmt_headline) {
		this.fmt_headline = fmt_headline;
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

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
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

	public Long getPatents_id() {
		return patents_id;
	}

	public void setPatents_id(Long patents_id) {
		this.patents_id = patents_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Inventors [fullname=");
		builder.append(fullname);
		builder.append(", lnkurl=");
		builder.append(lnkurl);
		builder.append(", finished=");
		builder.append(finished);
		builder.append(", fmt_headline=");
		builder.append(fmt_headline);
		builder.append(", peekType=");
		builder.append(peekType);
		builder.append(", referenceType=");
		builder.append(referenceType);
		builder.append(", imgurl=");
		builder.append(imgurl);
		builder.append(", imgcode=");
		builder.append(imgcode);
		builder.append(", imgname=");
		builder.append(imgname);
		builder.append(", patents_id=");
		builder.append(patents_id);
		builder.append("]");
		return builder.toString();
	}

}
