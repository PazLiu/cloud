package com.shty.collect.service.fb;

import com.shty.collect.domain.fb.TimelineStory;

public interface TimelineStory_ServiceI {
	public int insert_TimelineStory(TimelineStory timelineStory);
	public TimelineStory select_insert_TimelineStory_id(String storyId);
}
