package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.TimelineStoryimgCodeListMapper;
import com.shty.collect.domain.fb.TimelineStoryimgCodeList;
import com.shty.collect.service.fb.TimelineStoryimgCodeList_ServiceI;


@Service
public class TimelineStoryimgCodeList_ServiceImpl implements TimelineStoryimgCodeList_ServiceI {
	
	@Autowired
	TimelineStoryimgCodeListMapper timelineStoryimgCodeListMapper;
	
	@Override
	public int insert_TimelineStoryimgCodeList(TimelineStoryimgCodeList timelineStoryimgCodeList) {
		// TODO Auto-generated method stub
		return timelineStoryimgCodeListMapper.insert_TimelineStoryimgCodeList(timelineStoryimgCodeList);
	}

	@Override
	public int insert_timelineStoryimgUrlList(TimelineStoryimgCodeList timelineStoryimgCodeList) {
		// TODO Auto-generated method stub
		return timelineStoryimgCodeListMapper.insert_timelineStoryimgUrlList(timelineStoryimgCodeList);
	}

	@Override
	public int insert_timelineStoryimgPath(TimelineStoryimgCodeList timelineStoryimgCodeList) {
		// TODO Auto-generated method stub
		return timelineStoryimgCodeListMapper.insert_timelineStoryimgPath(timelineStoryimgCodeList);
	}

}
