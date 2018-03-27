package com.shty.collect.domain.lnk;

import java.util.List;

public class Recommendations {

	private List<Given> given;

	private List<Received> recieved;

	/**
	 * @return the given
	 */
	public List<Given> getGiven() {
		return given;
	}

	/**
	 * @param given
	 *            the given to set
	 */
	public void setGiven(List<Given> given) {
		this.given = given;
	}

	/**
	 * @return the recieved
	 */
	public List<Received> getRecieved() {
		return recieved;
	}

	/**
	 * @param recieved
	 *            the recieved to set
	 */
	public void setRecieved(List<Received> recieved) {
		this.recieved = recieved;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recommendations [given=");
		builder.append(given);
		builder.append(", recieved=");
		builder.append(recieved);
		builder.append("]");
		return builder.toString();
	}

}
