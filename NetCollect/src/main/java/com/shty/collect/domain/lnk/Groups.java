package com.shty.collect.domain.lnk;

public class Groups extends BaseEntity {

	private String groupname;

	private String lnkurl;

	private String groupsnummember;

	private String imgurl;

	private String imgcode;

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getLnkurl() {
		return lnkurl;
	}

	public void setLnkurl(String lnkurl) {
		this.lnkurl = lnkurl;
	}

	public String getGroupsnummember() {
		return groupsnummember;
	}

	public void setGroupsnummember(String groupsnummember) {
		this.groupsnummember = groupsnummember;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Groups [groupname=");
		builder.append(groupname);
		builder.append(", lnkurl=");
		builder.append(lnkurl);
		builder.append(", groupsnummember=");
		builder.append(groupsnummember);
		builder.append(", imgurl=");
		builder.append(imgurl);
		builder.append("]");
		return builder.toString();
	}

	public String getImgcode() {
		return imgcode;
	}

	public void setImgcode(String imgcode) {
		this.imgcode = imgcode;
	}

}
