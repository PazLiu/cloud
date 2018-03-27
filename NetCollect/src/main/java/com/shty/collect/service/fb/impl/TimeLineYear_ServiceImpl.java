package com.shty.collect.service.fb.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.fb.TimelineYearMapper;
import com.shty.collect.domain.fb.TimelineYear;
import com.shty.collect.service.fb.TimelineYear_ServiceI;


@Service
public class TimeLineYear_ServiceImpl implements TimelineYear_ServiceI {
	
	@Autowired
	TimelineYearMapper timelineyearmapper;
	
	@Override
	public int insert_TimelineYear(TimelineYear timelineYear) {
		// TODO Auto-generated method stub
		return timelineyearmapper.insert_TimelineYear(timelineYear);
	}

	@Override
	public void delete_TimelineYear(Long FBTarget_id) {
		// TODO Auto-generated method stub
		timelineyearmapper.delete_TimelineYear(FBTarget_id);
	}

	@Override
	public List<TimelineYear> getAllTimeline(Long FBTarget_id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		List<TimelineYear> list = new ArrayList<>();
		list = timelineyearmapper.getAllTimeline(FBTarget_id);
		return list;
	}

	@Override
	public void update_TimelineYear(TimelineYear timelineYear) {
		// TODO Auto-generated method stub
		timelineyearmapper.update_TimelineYear(timelineYear);
	}

	@Override
	public void update_TimelineYearYes(TimelineYear timelineYear) {
		// TODO Auto-generated method stub
		timelineyearmapper.update_TimelineYearYes(timelineYear);
	}

	@Override
	public TimelineYear select_TimelineYear_timeyear(TimelineYear timeyear) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return timelineyearmapper.select_TimelineYear_timeyear(timeyear);
	}
	
}
