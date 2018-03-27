package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.ExperiencesAttachementsDAO;
import com.shty.collect.domain.lnk.ExperiencesAttachements;
import com.shty.collect.service.lnk.ExperiencesAttachementsService;

@Service
public class ExperiencesAttachementsServiceImpl implements ExperiencesAttachementsService {

	@Autowired
	private ExperiencesAttachementsDAO experiencesAttachementsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addExpAttache(List<ExperiencesAttachements> experiencesAttachements) {
		// TODO Auto-generated method stub

		return experiencesAttachementsDao.addExpAttache(experiencesAttachements);
	}

}
