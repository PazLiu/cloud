package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.OrganizationsDAO;
import com.shty.collect.domain.lnk.Organizations;
import com.shty.collect.service.lnk.OrganizationsService;

@Service
public class OrganizationsServiceImpl implements OrganizationsService {

	@Autowired
	private OrganizationsDAO organizationsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addOrganizations(List<Organizations> organizations) {
		// TODO Auto-generated method stub
		return organizationsDAO.addOrganizations(organizations);
	}

}
