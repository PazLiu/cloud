package com.shty.collect.domain.lnk;

import java.util.List;

public class Follows {

	private List<Follow_people> follow_people;

	private List<Follow_school> follow_school;

	private List<Follow_channels> follow_channels;

	private List<Follow_company> follow_company;

	private List groupsList;

	/**
	 * @return the groupsList
	 */
	public List getGroupsList() {
		return groupsList;
	}

	/**
	 * @param groupsList
	 *            the groupsList to set
	 */
	public void setGroupsList(List groupsList) {
		this.groupsList = groupsList;
	}

	public List<Follow_people> getFollow_people() {
		return follow_people;
	}

	public void setFollow_people(List<Follow_people> follow_people) {
		this.follow_people = follow_people;
	}

	public List<Follow_school> getFollow_school() {
		return follow_school;
	}

	public void setFollow_school(List<Follow_school> follow_school) {
		this.follow_school = follow_school;
	}

	public List<Follow_channels> getFollow_channels() {
		return follow_channels;
	}

	public void setFollow_channels(List<Follow_channels> follow_channels) {
		this.follow_channels = follow_channels;
	}

	public List<Follow_company> getFollow_company() {
		return follow_company;
	}

	public void setFollow_company(List<Follow_company> follow_company) {
		this.follow_company = follow_company;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Follows [follow_people=");
		builder.append(follow_people);
		builder.append(", follow_school=");
		builder.append(follow_school);
		builder.append(", follow_channels=");
		builder.append(follow_channels);
		builder.append(", follow_company=");
		builder.append(follow_company);
		builder.append(", groupsList=");
		builder.append(groupsList);
		builder.append("]");
		return builder.toString();
	}

}
