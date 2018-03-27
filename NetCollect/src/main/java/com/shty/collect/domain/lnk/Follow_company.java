package com.shty.collect.domain.lnk;

public class Follow_company extends BaseEntity {

	private String imgurl;

	private String name;

	private String industry;

	private String lnkurl;

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
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
		builder.append("Follow_company [imgurl=");
		builder.append(imgurl);
		builder.append(", name=");
		builder.append(name);
		builder.append(", industry=");
		builder.append(industry);
		builder.append(", lnkurl=");
		builder.append(lnkurl);
		builder.append("]");
		return builder.toString();
	}

}
