package com.shty.collect.domain.lnk;

import java.util.List;

public class Patents extends BaseEntity {

	private String applicationnumber;

	private String title;

	private List<Inventors> inventors;

	private String date;

	private String description;

	public String getApplicationnumber() {
		return applicationnumber;
	}

	public void setApplicationnumber(String applicationnumber) {
		this.applicationnumber = applicationnumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Inventors> getInventors() {
		return inventors;
	}

	public void setInventors(List<Inventors> inventors) {
		this.inventors = inventors;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Patents [applicationnumber=");
		builder.append(applicationnumber);
		builder.append(", title=");
		builder.append(title);
		builder.append(", inventors=");
		builder.append(inventors);
		builder.append(", date=");
		builder.append(date);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
