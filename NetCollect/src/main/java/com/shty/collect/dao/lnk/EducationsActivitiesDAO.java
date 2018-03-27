package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.EducationsActivities;

@Repository
public interface EducationsActivitiesDAO {

	public int addEducationsActivities(List<EducationsActivities> educationsActivities);

	public List<EducationsActivities> getEducationsActivitiesByPeopleId(Long educationId);

	// 删除
	public int deleteEducationsActivities(Long peopleId);

}
