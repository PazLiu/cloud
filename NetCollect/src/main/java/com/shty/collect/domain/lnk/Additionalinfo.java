package com.shty.collect.domain.lnk;

import java.util.List;

public class Additionalinfo {

	private List<Additioninfo> additioninfo;

	public List<Additioninfo> getAdditioninfo() {
		return additioninfo;
	}

	public void setAdditioninfo(List<Additioninfo> additioninfo) {
		this.additioninfo = additioninfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Additionalinfo [additioninfo=");
		builder.append(additioninfo);
		builder.append("]");
		return builder.toString();
	}

}
