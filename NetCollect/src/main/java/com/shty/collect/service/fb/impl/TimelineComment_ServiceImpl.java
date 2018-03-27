package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.TimelineCommentMapper;
import com.shty.collect.domain.fb.OverviewFamily;
import com.shty.collect.domain.fb.TimelineComment;
import com.shty.collect.service.fb.TimelineComment_ServiceI;

@Service
public class TimelineComment_ServiceImpl implements TimelineComment_ServiceI {

	@Autowired
	TimelineCommentMapper timelineCommentMapper;
	
	@Override
	public int insert_TimelineComment(TimelineComment timelineComment) {
		// TODO Auto-generated method stub
		return timelineCommentMapper.insert_TimelineComment(timelineComment);
	}

	@Override
	public void inset_TimelineLike(TimelineComment timelineComment) {
		// TODO Auto-generated method stub
		timelineCommentMapper.inset_TimelineLike(timelineComment);
	}

}
