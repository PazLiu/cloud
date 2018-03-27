package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Educations;

@Repository
public interface EducationsDAO {

	public int addEducations(Educations educations);

	public List<Educations> getEducationsByPeopleId(Long peopleId);

	// 删除
	public int deleteEducations(Long peopleId);

}
