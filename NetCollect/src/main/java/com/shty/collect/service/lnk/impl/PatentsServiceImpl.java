package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.PatentsDAO;
import com.shty.collect.domain.lnk.Patents;
import com.shty.collect.service.lnk.PatentsService;

@Service
public class PatentsServiceImpl implements PatentsService {

	@Autowired
	private PatentsDAO patentsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addPatents(Patents patents) {
		// TODO Auto-generated method stub
		return patentsDAO.addPatents(patents);
	}

}
