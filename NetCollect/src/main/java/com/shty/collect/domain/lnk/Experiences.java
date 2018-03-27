package com.shty.collect.domain.lnk;

import java.sql.Timestamp;
import java.util.List;

public class Experiences {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String header;

	private String company;

	private String date;

	private String locality;

	private String description;

	private List<ExperiencesAttachements> attachements;

	private Long people_id;

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

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ExperiencesAttachements> getAttachements() {
		return attachements;
	}

	public void setAttachements(List<ExperiencesAttachements> attachements) {
		this.attachements = attachements;
	}

	public Long getPeople_id() {
		return people_id;
	}

	public void setPeople_id(Long people_id) {
		this.people_id = people_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Experiences [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", header=");
		builder.append(header);
		builder.append(", company=");
		builder.append(company);
		builder.append(", date=");
		builder.append(date);
		builder.append(", locality=");
		builder.append(locality);
		builder.append(", description=");
		builder.append(description);
		builder.append(", attachements=");
		builder.append(attachements);
		builder.append(", people_id=");
		builder.append(people_id);
		builder.append("]");
		return builder.toString();
	}

}
