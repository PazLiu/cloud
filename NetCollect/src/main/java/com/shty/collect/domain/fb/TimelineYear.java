package com.shty.collect.domain.fb;

public class TimelineYear {
	private Long id;
	private String timeyear;
	private String yearurl;
	private String nextpageurl;
	private int finished;
	private Long FBTarget_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTimeyear() {
		return timeyear;
	}
	public void setTimeyear(String timeyear) {
		this.timeyear = timeyear;
	}
	public String getYearurl() {
		return yearurl;
	}
	public void setYearurl(String yearurl) {
		this.yearurl = yearurl;
	}
	public String getNextpageurl() {
		return nextpageurl;
	}
	public void setNextpageurl(String nextpageurl) {
		this.nextpageurl = nextpageurl;
	}
	public int getFinished() {
		return finished;
	}
	public void setFinished(int finished) {
		this.finished = finished;
	}
	public Long getFBTarget_id() {
		return FBTarget_id;
	}
	public void setFBTarget_id(Long fBTarget_id) {
		FBTarget_id = fBTarget_id;
	}
	
	
}
