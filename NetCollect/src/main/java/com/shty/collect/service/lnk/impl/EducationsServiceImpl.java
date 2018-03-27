package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.EducationsDAO;
import com.shty.collect.domain.lnk.Educations;
import com.shty.collect.service.lnk.EducationsService;

@Service
public class EducationsServiceImpl implements EducationsService {

	@Autowired
	private EducationsDAO educationsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addEducations(Educations educations) {
		// TODO Auto-generated method stub
		return educationsDao.addEducations(educations);
	}

}
