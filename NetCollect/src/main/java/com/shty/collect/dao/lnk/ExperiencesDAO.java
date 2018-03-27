package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Experiences;

@Repository
public interface ExperiencesDAO {

	public int addExperiences(Experiences experiences);

	public List<Experiences> getExperiencesByPeopleId(Long peopleId);

	// 删除
	public int deleteExperiences(Long peopleId);

}
