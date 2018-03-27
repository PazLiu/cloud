package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.VolunteersDAO;
import com.shty.collect.domain.lnk.Volunteers;
import com.shty.collect.service.lnk.VolunteersService;

@Service
public class VolunteersServiceImpl implements VolunteersService {

	@Autowired
	private VolunteersDAO volunteersDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addVolunteers(List<Volunteers> volunteers) {
		// TODO Auto-generated method stub
		return volunteersDAO.addVolunteers(volunteers);
	}

}
