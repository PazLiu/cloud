package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.OverviewDAO;
import com.shty.collect.domain.lnk.Overview;
import com.shty.collect.service.lnk.OverviewService;

@Service
public class OverviewServiceImpl implements OverviewService {

	@Autowired
	private OverviewDAO overviewDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addOverview(Overview overview) {
		// TODO Auto-generated method stub
		return overviewDao.addOverview(overview);
	}

}
