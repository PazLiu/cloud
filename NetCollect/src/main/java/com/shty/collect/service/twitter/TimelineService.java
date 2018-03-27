package com.shty.collect.service.twitter;

import com.shty.collect.domain.twitter.Timeline;

public interface TimelineService {
	
	public void insertNew(Timeline timeline);

	public String findMaxPubTime(String userID);
	public Timeline findMaxValues(String profileId);
	public Timeline findMinValues(String profileId);
}
