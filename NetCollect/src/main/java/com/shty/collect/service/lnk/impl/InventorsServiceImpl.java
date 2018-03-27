package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.InventorsDAO;
import com.shty.collect.domain.lnk.Inventors;
import com.shty.collect.service.lnk.InventorsService;

@Service
public class InventorsServiceImpl implements InventorsService {

	@Autowired
	private InventorsDAO inventorsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addInventors(List<Inventors> inventors) {
		// TODO Auto-generated method stub
		return inventorsDAO.addInventors(inventors);
	}

}
