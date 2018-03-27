package com.shty.collect.domain.lnk;

public class Follow_channels extends BaseEntity {

	private String imgurl;

	private String followers;

	private String name;

	private String title;

	private String lnkurl;

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getFollowers() {
		return followers;
	}

	public void setFollowers(String followers) {
		this.followers = followers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		builder.append("Follow_channels [imgurl=");
		builder.append(imgurl);
		builder.append(", followers=");
		builder.append(followers);
		builder.append(", name=");
		builder.append(name);
		builder.append(", title=");
		builder.append(title);
		builder.append(", lnkurl=");
		builder.append(lnkurl);
		builder.append("]");
		return builder.toString();
	}

}
