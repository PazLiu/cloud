package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.WebsitesDAO;
import com.shty.collect.domain.lnk.Websites;
import com.shty.collect.service.lnk.WebsitesService;

@Service
public class WebsitesServiceImpl implements WebsitesService {

	@Autowired
	private WebsitesDAO websitesDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addWebsites(Websites websites) {
		// TODO Auto-generated method stub
		return websitesDao.addWebsites(websites);
	}

}
