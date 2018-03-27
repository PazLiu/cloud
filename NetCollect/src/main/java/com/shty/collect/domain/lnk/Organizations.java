package com.shty.collect.domain.lnk;

public class Organizations extends BaseEntity {

	private String organization;

	private String positions;

	private String date;

	private String notes;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getPositions() {
		return positions;
	}

	public void setPositions(String positions) {
		this.positions = positions;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Organizations [organization=");
		builder.append(organization);
		builder.append(", positions=");
		builder.append(positions);
		builder.append(", date=");
		builder.append(date);
		builder.append(", notes=");
		builder.append(notes);
		builder.append("]");
		return builder.toString();
	}

}
