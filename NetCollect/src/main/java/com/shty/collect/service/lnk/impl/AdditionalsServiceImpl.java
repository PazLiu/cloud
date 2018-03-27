package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.AdditionalsDAO;
import com.shty.collect.domain.lnk.Additionals;
import com.shty.collect.service.lnk.AdditionalsService;

@Service
public class AdditionalsServiceImpl implements AdditionalsService {

	@Autowired
	private AdditionalsDAO additionalsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addAdditionals(Additionals additionals) {

		PageHelper.startPage(1, 1);
		return additionalsDAO.addAdditionals(additionals);
	}

}
