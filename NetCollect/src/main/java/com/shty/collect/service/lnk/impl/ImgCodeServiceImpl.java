package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.ImgCodeDAO;
import com.shty.collect.domain.lnk.ImgCode;
import com.shty.collect.service.lnk.ImgCodeService;

@Service
public class ImgCodeServiceImpl implements ImgCodeService {

	@Autowired
	private ImgCodeDAO imgCodeDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addImgCode(ImgCode imgCode) {
		// TODO Auto-generated method stub
		return imgCodeDao.addImgCode(imgCode);
	}

}
