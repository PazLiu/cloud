package com.shty.collect.domain.lnk;

import java.util.List;

public class HonorsOut {

	private List<Honors> honors;

	private Additionals additionals;

	public List<Honors> getHonors() {
		return honors;
	}

	public void setHonors(List<Honors> honors) {
		this.honors = honors;
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
		builder.append("HonorsOut [honors=");
		builder.append(honors);
		builder.append(", additionals=");
		builder.append(additionals);
		builder.append("]");
		return builder.toString();
	}

}
