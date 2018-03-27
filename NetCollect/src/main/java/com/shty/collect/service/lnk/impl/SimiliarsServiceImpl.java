package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.SimiliarsDAO;
import com.shty.collect.domain.lnk.Similiars;
import com.shty.collect.service.lnk.SimiliarsService;

@Service
public class SimiliarsServiceImpl implements SimiliarsService {

	@Autowired
	private SimiliarsDAO similiarsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addSimiliars(List<Similiars> similiars) {
		// TODO Auto-generated method stub
		return similiarsDao.addSimiliars(similiars);
	}

}
