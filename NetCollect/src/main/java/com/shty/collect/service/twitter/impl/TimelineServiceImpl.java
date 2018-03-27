package com.shty.collect.service.twitter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.twitter.TimelineMapper;
import com.shty.collect.domain.twitter.Timeline;
import com.shty.collect.service.twitter.TimelineService;

@Service
public class TimelineServiceImpl implements TimelineService {
	@Autowired
	private TimelineMapper TimelineDao;

	@Override
	public void insertNew(Timeline timeline) {
		TimelineDao.insertNew(timeline);
	}
	@Override
	public String findMaxPubTime(String userID) {
		return TimelineDao.findMaxPubTime(userID);

	}
	@Override
	public Timeline findMaxValues(String profileId){
		PageHelper.startPage(1, 1);
		return TimelineDao.findMaxValues(profileId);
	}
	@Override
	public Timeline findMinValues(String profileId){
		PageHelper.startPage(1, 1);
		return TimelineDao.findMinValues(profileId);
	}
}
