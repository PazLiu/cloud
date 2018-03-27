package com.shty.collect.domain.lnk;

import java.util.List;

public class OrganizationsOut {

	private List<Organizations> organizations;

	private Additionals additionals;

	public List<Organizations> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organizations> organizations) {
		this.organizations = organizations;
	}

	public Additionals getAdditionals() {
		return additionals;
	}

	public void setAdditionals(Additionals additionals) {
		this.additionals = additionals;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrganizationsOut [organizations=");
		builder.append(organizations);
		builder.append(", additionals=");
		builder.append(additionals);
		builder.append("]");
		return builder.toString();
	}

}
