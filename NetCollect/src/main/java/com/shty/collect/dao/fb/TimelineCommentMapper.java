package com.shty.collect.dao.fb;

import com.shty.collect.domain.fb.TimelineComment;

public interface TimelineCommentMapper {
	public int insert_TimelineComment(TimelineComment timelineComment);
	public void inset_TimelineLike(TimelineComment timelineComment);
}
