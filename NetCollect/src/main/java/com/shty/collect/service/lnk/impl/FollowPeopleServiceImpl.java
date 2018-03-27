package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.FollowPeopleDAO;
import com.shty.collect.domain.lnk.Follow_people;
import com.shty.collect.service.lnk.FollowPeopleService;

@Service
public class FollowPeopleServiceImpl implements FollowPeopleService {

	@Autowired
	private FollowPeopleDAO followPeopleDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addFollowPeople(List<Follow_people> follow_people) {
		// TODO Auto-generated method stub
		return followPeopleDAO.addFollowPeople(follow_people);
	}

}
