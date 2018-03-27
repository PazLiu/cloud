package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.CertificationsDAO;
import com.shty.collect.domain.lnk.Certifications;
import com.shty.collect.service.lnk.CertificationsService;

@Service
public class CertificationsServiceImpl implements CertificationsService {

	@Autowired
	private CertificationsDAO certificationsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addCertifications(List<Certifications> certifications) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return certificationsDAO.addCertifications(certifications);
	}

}
