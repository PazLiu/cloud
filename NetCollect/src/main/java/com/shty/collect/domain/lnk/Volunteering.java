package com.shty.collect.domain.lnk;

import java.util.List;

public class Volunteering {

	private List<Volunteers> Volunteers;

	private List<Interests> interests;

	public List<Volunteers> getVolunteers() {
		return Volunteers;
	}

	public void setVolunteers(List<Volunteers> volunteers) {
		Volunteers = volunteers;
	}

	public List<Interests> getInterests() {
		return interests;
	}

	public void setInterests(List<Interests> interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Volunteering [Volunteers=");
		builder.append(Volunteers);
		builder.append(", interests=");
		builder.append(interests);
		builder.append("]");
		return builder.toString();
	}

}
