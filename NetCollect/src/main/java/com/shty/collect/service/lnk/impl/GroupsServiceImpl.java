package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.GroupsDAO;
import com.shty.collect.domain.lnk.Groups;
import com.shty.collect.service.lnk.GroupsService;

@Service
public class GroupsServiceImpl implements GroupsService {

	@Autowired
	private GroupsDAO groupsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addGroups(List<Groups> groups) {
		// TODO Auto-generated method stub
		return groupsDAO.addGroups(groups);
	}

}
