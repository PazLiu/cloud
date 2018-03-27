package com.shty.collect.domain.lnk;

public class Follow_school extends BaseEntity {

	private String imgurl;

	private String school;

	private String location;

	private String lnkurl;

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLnkurl() {
		return lnkurl;
	}

	public void setLnkurl(String lnkurl) {
		this.lnkurl = lnkurl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Follow_school [imgurl=");
		builder.append(imgurl);
		builder.append(", school=");
		builder.append(school);
		builder.append(", location=");
		builder.append(location);
		builder.append(", lnkurl=");
		builder.append(lnkurl);
		builder.append("]");
		return builder.toString();
	}

}
