package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.AdditionalinfoDAO;
import com.shty.collect.domain.lnk.Additioninfo;
import com.shty.collect.service.lnk.AdditionalinfoService;

@Service
public class AdditionalinfoServiceImpl implements AdditionalinfoService {

	@Autowired
	private AdditionalinfoDAO additionalinfoDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addAdditionalinfo(List<Additioninfo> additionalinfo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return additionalinfoDAO.addAdditionalinfo(additionalinfo);
	}

}
