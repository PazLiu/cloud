package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.EndorsementsDAO;
import com.shty.collect.domain.lnk.Endorsements;
import com.shty.collect.service.lnk.EndorsementsService;

@Service
public class EndorsementsServiceImpl implements EndorsementsService {

	@Autowired
	private EndorsementsDAO endorsementsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addEndorsements(List<Endorsements> endorsements) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 0);
		return endorsementsDao.addEndorsements(endorsements);
	}

}
