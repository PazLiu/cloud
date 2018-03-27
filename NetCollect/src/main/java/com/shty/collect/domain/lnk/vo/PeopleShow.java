package com.shty.collect.domain.lnk.vo;

import java.util.List;

import com.shty.collect.domain.lnk.Connections;
import com.shty.collect.domain.lnk.Educations;
import com.shty.collect.domain.lnk.Experiences;
import com.shty.collect.domain.lnk.Given;
import com.shty.collect.domain.lnk.Others;
import com.shty.collect.domain.lnk.Overview;
import com.shty.collect.domain.lnk.Received;
import com.shty.collect.domain.lnk.Similiars;
import com.shty.collect.domain.lnk.Skills;

public class PeopleShow {

	private String lnkid;

	private String summary;

	// private byte[] imgcode;

	private String collectionTime;

	private String imgname;

	private Overview overview;

	private List<Skills> skills;

	private List<Experiences> experiences;

	private List<Educations> educations;

	private List<Similiars> similiars;

	private List<Connections> connections;

	private List<Given> given;

	private List<Received> received;

	private Others others;

	public String getLnkid() {
		return lnkid;
	}

	public void setLnkid(String lnkid) {
		this.lnkid = lnkid;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	// public byte[] getImgcode() {
	// return imgcode;
	// }
	//
	// public void setImgcode(byte[] imgcode) {
	// this.imgcode = imgcode;
	// }

	public String getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}

	public Overview getOverview() {
		return overview;
	}

	public void setOverview(Overview overview) {
		this.overview = overview;
	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public List<Experiences> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experiences> experiences) {
		this.experiences = experiences;
	}

	public List<Educations> getEducations() {
		return educations;
	}

	public void setEducations(List<Educations> educations) {
		this.educations = educations;
	}

	public String getImgname() {
		return imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

	public List<Similiars> getSimiliars() {
		return similiars;
	}

	public void setSimiliars(List<Similiars> similiars) {
		this.similiars = similiars;
	}

	public List<Connections> getConnections() {
		return connections;
	}

	public void setConnections(List<Connections> connections) {
		this.connections = connections;
	}

	public List<Given> getGiven() {
		return given;
	}

	public void setGiven(List<Given> given) {
		this.given = given;
	}

	public List<Received> getReceived() {
		return received;
	}

	public void setReceived(List<Received> received) {
		this.received = received;
	}

	public Others getOthers() {
		return others;
	}

	public void setOthers(Others others) {
		this.others = others;
	}

}
