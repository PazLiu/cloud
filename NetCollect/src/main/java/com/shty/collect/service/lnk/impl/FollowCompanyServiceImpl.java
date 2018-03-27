package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.FollowCompanyDAO;
import com.shty.collect.domain.lnk.Follow_company;
import com.shty.collect.service.lnk.FollowCompanyService;

@Service
public class FollowCompanyServiceImpl implements FollowCompanyService {

	@Autowired
	private FollowCompanyDAO followCompanyDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addFollowCompany(List<Follow_company> follow_company) {
		// TODO Auto-generated method stub
		return followCompanyDAO.addFollowCompany(follow_company);
	}

}
