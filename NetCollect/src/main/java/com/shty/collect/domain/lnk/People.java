package com.shty.collect.domain.lnk;

import java.sql.Timestamp;
import java.util.List;

public class People {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String collectionTime;

	private String lnkid;

	private String summary;

	private List<SummaryAttachements> attachements;

	private int digPerson;

	private String nodeid;

	private String urlpattern;

	private Long imgcode_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateCreatedTime() {
		return dateCreatedTime;
	}

	public void setDateCreatedTime(Timestamp dateCreatedTime) {
		this.dateCreatedTime = dateCreatedTime;
	}

	public Timestamp getDateUpdatedTime() {
		return dateUpdatedTime;
	}

	public void setDateUpdatedTime(Timestamp dateUpdatedTime) {
		this.dateUpdatedTime = dateUpdatedTime;
	}

	public String getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}

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

	public List<SummaryAttachements> getAttachements() {
		return attachements;
	}

	public void setAttachements(List<SummaryAttachements> attachements) {
		this.attachements = attachements;
	}

	public int getDigPerson() {
		return digPerson;
	}

	public void setDigPerson(int digPerson) {
		this.digPerson = digPerson;
	}

	public String getNodeid() {
		return nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	public String getUrlpattern() {
		return urlpattern;
	}

	public void setUrlpattern(String urlpattern) {
		this.urlpattern = urlpattern;
	}

	public Long getImgcode_id() {
		return imgcode_id;
	}

	public void setImgcode_id(Long imgcode_id) {
		this.imgcode_id = imgcode_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("People [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", lnkid=");
		builder.append(lnkid);
		builder.append(", summary=");
		builder.append(summary);
		builder.append(", attachements=");
		builder.append(attachements);
		builder.append(", digPerson=");
		builder.append(digPerson);
		builder.append(", nodeid=");
		builder.append(nodeid);
		builder.append(", urlpattern=");
		builder.append(urlpattern);
		builder.append(", imgcode_id=");
		builder.append(imgcode_id);
		builder.append("]");
		return builder.toString();
	}

}
