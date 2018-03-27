package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.InterestsDAO;
import com.shty.collect.domain.lnk.Interests;
import com.shty.collect.service.lnk.InterestsService;

@Service
public class InterestsServiceImpl implements InterestsService {

	@Autowired
	private InterestsDAO interestsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addInterests(List<Interests> interests) {
		// TODO Auto-generated method stub
		return interestsDAO.addInterests(interests);
	}

}
