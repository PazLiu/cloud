package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.PublicationsDAO;
import com.shty.collect.domain.lnk.Publications;
import com.shty.collect.service.lnk.PublicationsService;

@Service
public class PublicationsServiceImpl implements PublicationsService {

	@Autowired
	private PublicationsDAO publicationsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addPublications(Publications publications) {
		// TODO Auto-generated method stub
		return publicationsDAO.addPublications(publications);
	}

}
