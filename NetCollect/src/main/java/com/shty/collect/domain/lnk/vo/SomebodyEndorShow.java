package com.shty.collect.domain.lnk.vo;

import java.util.List;

public class SomebodyEndorShow {

	private String lnkid;

	private String imgname;

	private String peoplename;

	private List<String> skills;

	public String getLnkid() {
		return lnkid;
	}

	public void setLnkid(String lnkid) {
		this.lnkid = lnkid;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public String getPeoplename() {
		return peoplename;
	}

	public void setPeoplename(String peoplename) {
		this.peoplename = peoplename;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

}
