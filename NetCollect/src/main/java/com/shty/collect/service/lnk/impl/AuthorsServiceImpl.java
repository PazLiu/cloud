package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.AuthorsDAO;
import com.shty.collect.domain.lnk.Authors;
import com.shty.collect.service.lnk.AuthorsService;

@Service
public class AuthorsServiceImpl implements AuthorsService {

	@Autowired
	private AuthorsDAO authorsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addAuthors(List<Authors> authors) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return authorsDAO.addAuthors(authors);
	}

}
