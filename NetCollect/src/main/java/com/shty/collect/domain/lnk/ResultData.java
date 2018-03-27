package com.shty.collect.domain.lnk;

public class ResultData {

	private Integer statuscode;

	private Profile profile;

	/**
	 * @return the statuscode
	 */
	public Integer getStatuscode() {
		return statuscode;
	}

	/**
	 * @param statuscode
	 *            the statuscode to set
	 */
	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultData [statuscode=");
		builder.append(statuscode);
		builder.append(", profile=");
		builder.append(profile);
		builder.append("]");
		return builder.toString();
	}

}
