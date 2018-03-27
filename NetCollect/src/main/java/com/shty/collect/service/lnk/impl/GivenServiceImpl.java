package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.GivenDAO;
import com.shty.collect.domain.lnk.Given;
import com.shty.collect.service.lnk.GivenService;

@Service
public class GivenServiceImpl implements GivenService {

	@Autowired
	private GivenDAO givenDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addGiven(List<Given> given) {
		// TODO Auto-generated method stub
		return givenDao.addGiven(given);
	}

}
