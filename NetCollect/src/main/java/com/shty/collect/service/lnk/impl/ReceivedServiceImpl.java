package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.ReceivedDAO;
import com.shty.collect.domain.lnk.Received;
import com.shty.collect.service.lnk.ReceivedService;

@Service
public class ReceivedServiceImpl implements ReceivedService {

	@Autowired
	private ReceivedDAO receivedDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addReceived(Received received) {
		// TODO Auto-generated method stub
		return receivedDao.addReceived(received);
	}

}
