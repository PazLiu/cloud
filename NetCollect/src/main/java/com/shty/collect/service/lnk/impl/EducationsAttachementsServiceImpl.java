package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.EducationsAttachementsDAO;
import com.shty.collect.domain.lnk.EducationsAttachements;
import com.shty.collect.service.lnk.EducationsAttachementsService;

@Service
public class EducationsAttachementsServiceImpl implements EducationsAttachementsService {

	@Autowired
	private EducationsAttachementsDAO educationsAttachementsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addEduAttache(List<EducationsAttachements> educationsAttachements) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return educationsAttachementsDao.addEduAttache(educationsAttachements);
	}

}
