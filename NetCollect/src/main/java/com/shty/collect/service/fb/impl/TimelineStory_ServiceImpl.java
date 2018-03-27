package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.fb.TimelineStoryMapper;
import com.shty.collect.domain.fb.TimelineStory;
import com.shty.collect.service.fb.TimelineStory_ServiceI;


@Service
public class TimelineStory_ServiceImpl implements TimelineStory_ServiceI {

	@Autowired
	TimelineStoryMapper timelineStoryMapper;
	
	@Override
	public int insert_TimelineStory(TimelineStory timelineStory) {
		// TODO Auto-generated method stub
		return timelineStoryMapper.insert_TimelineStory(timelineStory);
	}

	@Override
	public TimelineStory select_insert_TimelineStory_id(String storyId) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return timelineStoryMapper.select_insert_TimelineStory_id(storyId);
	}

}
