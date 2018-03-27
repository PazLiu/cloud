package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.TeamerDAO;
import com.shty.collect.domain.lnk.Teamer;
import com.shty.collect.service.lnk.TeamerService;

@Service
public class TeamerServiceImpl implements TeamerService {

	@Autowired
	private TeamerDAO teamerDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addTeamer(List<Teamer> teamer) {
		// TODO Auto-generated method stub
		return teamerDAO.addTeamer(teamer);
	}

}
