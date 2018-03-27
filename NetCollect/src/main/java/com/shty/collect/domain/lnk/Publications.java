package com.shty.collect.domain.lnk;

import java.util.List;

public class Publications extends BaseEntity {

	private String title;

	private String publisher;

	private String date;

	private String description;

	private List<Authors> authors;

	private String linkurl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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

	public List<Authors> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Authors> authors) {
		this.authors = authors;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Publications [title=");
		builder.append(title);
		builder.append(", publisher=");
		builder.append(publisher);
		builder.append(", date=");
		builder.append(date);
		builder.append(", description=");
		builder.append(description);
		builder.append(", authors=");
		builder.append(authors);
		builder.append(", linkurl=");
		builder.append(linkurl);
		builder.append("]");
		return builder.toString();
	}

}
