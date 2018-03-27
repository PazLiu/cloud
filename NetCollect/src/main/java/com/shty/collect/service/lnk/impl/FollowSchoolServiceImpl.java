package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.FollowSchoolDAO;
import com.shty.collect.domain.lnk.Follow_school;
import com.shty.collect.service.lnk.FollowSchoolService;

@Service
public class FollowSchoolServiceImpl implements FollowSchoolService {

	@Autowired
	private FollowSchoolDAO followSchoolDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addFollowSchool(List<Follow_school> follow_school) {
		// TODO Auto-generated method stub
		return followSchoolDAO.addFollowSchool(follow_school);
	}

}
