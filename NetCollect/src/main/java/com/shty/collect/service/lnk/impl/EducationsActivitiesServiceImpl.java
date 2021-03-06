package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.EducationsActivitiesDAO;
import com.shty.collect.domain.lnk.EducationsActivities;
import com.shty.collect.service.lnk.EducationsActivitiesService;

@Service
public class EducationsActivitiesServiceImpl implements EducationsActivitiesService {

	@Autowired
	private EducationsActivitiesDAO educationsActivitiesDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addEducationsActivities(List<EducationsActivities> educationsActivities) {

		return educationsActivitiesDao.addEducationsActivities(educationsActivities);

	}

}
