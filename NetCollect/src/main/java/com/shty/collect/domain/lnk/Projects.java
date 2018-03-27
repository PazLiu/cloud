package com.shty.collect.domain.lnk;

import java.util.List;

public class Projects extends BaseEntity {

	private String name;

	private String date;

	private String url;

	private List<Teamer> teamer;

	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Teamer> getTeamer() {
		return teamer;
	}

	public void setTeamer(List<Teamer> teamer) {
		this.teamer = teamer;
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
		builder.append("Projects [name=");
		builder.append(name);
		builder.append(", date=");
		builder.append(date);
		builder.append(", url=");
		builder.append(url);
		builder.append(", teamer=");
		builder.append(teamer);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
