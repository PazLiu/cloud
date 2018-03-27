package com.shty.collect.domain.lnk;

public class Course extends BaseEntity {

	private String institution;

	private String course;

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Course [institution=");
		builder.append(institution);
		builder.append(", course=");
		builder.append(course);
		builder.append("]");
		return builder.toString();
	}

}
