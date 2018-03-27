package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.LanguagesDAO;
import com.shty.collect.domain.lnk.Languages;
import com.shty.collect.service.lnk.LanguagesService;

@Service
public class LanguagesServiceImpl implements LanguagesService {

	@Autowired
	private LanguagesDAO languagesDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addLanguages(List<Languages> languages) {
		// TODO Auto-generated method stub
		return languagesDAO.addLanguages(languages);
	}

}
