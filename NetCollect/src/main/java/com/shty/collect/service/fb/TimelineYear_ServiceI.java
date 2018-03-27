package com.shty.collect.service.fb;

import java.util.List;

import com.shty.collect.domain.fb.TimelineYear;

public interface TimelineYear_ServiceI {
	public int insert_TimelineYear(TimelineYear timelineYear);
	public void delete_TimelineYear(Long FBTarget_id);
	public List<TimelineYear> getAllTimeline(Long FBTarget_id);
	public void update_TimelineYear(TimelineYear timelineYear);
	public void update_TimelineYearYes(TimelineYear timelineYear);
	public TimelineYear select_TimelineYear_timeyear(TimelineYear timeyear);
}
