package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.SummaryAttachementsDAO;
import com.shty.collect.domain.lnk.SummaryAttachements;
import com.shty.collect.service.lnk.SummaryAttachementsService;

@Service
public class SummaryAttachementsServiceImpl implements SummaryAttachementsService {

	@Autowired
	private SummaryAttachementsDAO summaryAttachementsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addSumAttache(List<SummaryAttachements> summaryAttachements) {
		// TODO Auto-generated method stub
		return summaryAttachementsDao.addSumAttache(summaryAttachements);
	}

}
