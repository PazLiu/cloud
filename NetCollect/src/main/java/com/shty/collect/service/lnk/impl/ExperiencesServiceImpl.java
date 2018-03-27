package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.ExperiencesDAO;
import com.shty.collect.domain.lnk.Experiences;
import com.shty.collect.service.lnk.ExperiencesService;

@Service
public class ExperiencesServiceImpl implements ExperiencesService {

	@Autowired
	private ExperiencesDAO experiencesDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addExperiences(Experiences experiences) {
		// TODO Auto-generated method stub
		return experiencesDao.addExperiences(experiences);
	}

}
