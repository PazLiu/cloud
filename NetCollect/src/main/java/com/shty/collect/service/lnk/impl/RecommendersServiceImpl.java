package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.RecommendersDAO;
import com.shty.collect.domain.lnk.Recommenders;
import com.shty.collect.service.lnk.RecommendersService;

@Service
public class RecommendersServiceImpl implements RecommendersService {

	@Autowired
	private RecommendersDAO recommendersDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addRecommenders(List<Recommenders> recommenders) {
		// TODO Auto-generated method stub
		return recommendersDao.addRecommenders(recommenders);
	}

}
