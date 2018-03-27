package com.shty.collect.dao.twitter;

import com.shty.collect.domain.twitter.Timeline;

public interface TimelineMapper {
	public void insertNew(Timeline timeline);
	public String findMaxPubTime(String userID);
	public Timeline findMaxValues(String profileId);
	public Timeline findMinValues(String profileId);
}
