package com.shty.collect.dao.fb;

import com.shty.collect.domain.fb.TimelineStory;

public interface TimelineStoryMapper {
	public int insert_TimelineStory(TimelineStory timelineStory);
	public TimelineStory select_insert_TimelineStory_id(String storyId);
}
