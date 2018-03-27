package com.shty.collect.domain.lnk;

import java.sql.Timestamp;
import java.util.List;

public class Received {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String headline;

	private String companyname;

	private List<Recommenders> recommenders;

	public List<Recommenders> getRecommenders() {
		return recommenders;
	}

	public void setRecommenders(List<Recommenders> recommenders) {
		this.recommenders = recommenders;
	}

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

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
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
		builder.append("Received [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", headline=");
		builder.append(headline);
		builder.append(", companyname=");
		builder.append(companyname);
		builder.append(", recommenders=");
		builder.append(recommenders);
		builder.append(", people_id=");
		builder.append(people_id);
		builder.append("]");
		return builder.toString();
	}

}
