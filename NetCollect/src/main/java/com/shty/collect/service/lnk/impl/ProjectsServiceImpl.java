package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.ProjectsDAO;
import com.shty.collect.domain.lnk.Projects;
import com.shty.collect.service.lnk.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	private ProjectsDAO projectsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addProjects(Projects projects) {
		// TODO Auto-generated method stub
		return projectsDAO.addProjects(projects);
	}

}
