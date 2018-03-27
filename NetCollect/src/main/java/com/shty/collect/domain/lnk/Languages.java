package com.shty.collect.domain.lnk;

public class Languages extends BaseEntity {

	private String language;

	private String proficiency;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Languages [language=");
		builder.append(language);
		builder.append(", proficiency=");
		builder.append(proficiency);
		builder.append("]");
		return builder.toString();
	}

}
