package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Projects;

@Repository
public interface ProjectsDAO {

	public int addProjects(Projects projects);

	public List<Projects> getProjectsByPeopleId(Long peopleId);

	// 删除
	public int deleteProjects(Long peopleId);

}
