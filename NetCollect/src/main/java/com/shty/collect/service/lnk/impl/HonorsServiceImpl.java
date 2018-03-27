package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.HonorsDAO;
import com.shty.collect.domain.lnk.Honors;
import com.shty.collect.service.lnk.HonorsService;

@Service
public class HonorsServiceImpl implements HonorsService {

	@Autowired
	private HonorsDAO honorsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addHonors(List<Honors> honors) {
		// TODO Auto-generated method stub
		return honorsDAO.addHonors(honors);
	}

}
