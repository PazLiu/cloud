package com.shty.collect.domain.lnk;

public class Additionals extends BaseEntity {

	// organizations 里的 additionals

	private String otherOrg;

	public String getOtherOrg() {
		return otherOrg;
	}

	public void setOtherOrg(String otherOrg) {
		this.otherOrg = otherOrg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Additionals [otherOrg=");
		builder.append(otherOrg);
		builder.append("]");
		return builder.toString();
	}

}
