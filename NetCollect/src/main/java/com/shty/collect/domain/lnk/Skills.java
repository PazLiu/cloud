package com.shty.collect.domain.lnk;

import java.sql.Timestamp;
import java.util.List;

public class Skills {

	private Long id;

	private Timestamp dateCreatedTime;

	private Timestamp dateUpdatedTime;

	private String skillname;

	private String lnkid;

	private Long people_id;

	private List<Endorsements> endorsements;

	public List<Endorsements> getEndorsements() {
		return endorsements;
	}

	public void setEndorsements(List<Endorsements> endorsements) {
		this.endorsements = endorsements;
	}

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

	public String getSkillname() {
		return skillname;
	}

	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}

	public String getLnkid() {
		return lnkid;
	}

	public void setLnkid(String lnkid) {
		this.lnkid = lnkid;
	}

	public Long getPeople_id() {
		return people_id;
	}

	public void setPeople_id(Long people_id) {
		this.people_id = people_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Skills [id=");
		builder.append(id);
		builder.append(", dateCreatedTime=");
		builder.append(dateCreatedTime);
		builder.append(", dateUpdatedTime=");
		builder.append(dateUpdatedTime);
		builder.append(", skillname=");
		builder.append(skillname);
		builder.append(", lnkid=");
		builder.append(lnkid);
		builder.append(", people_id=");
		builder.append(people_id);
		builder.append(", endorsements=");
		builder.append(endorsements);
		builder.append("]");
		return builder.toString();
	}

}
