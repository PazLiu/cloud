package com.shty.collect.domain.lnk;

import java.util.List;

public class Summary {

	private String summary;

	private List<SummaryAttachements> attachements;

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the attachements
	 */
	public List<SummaryAttachements> getAttachements() {
		return attachements;
	}

	/**
	 * @param attachements
	 *            the attachements to set
	 */
	public void setAttachements(List<SummaryAttachements> attachements) {
		this.attachements = attachements;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Summary [summary=");
		builder.append(summary);
		builder.append(", attachements=");
		builder.append(attachements);
		builder.append("]");
		return builder.toString();
	}

}
