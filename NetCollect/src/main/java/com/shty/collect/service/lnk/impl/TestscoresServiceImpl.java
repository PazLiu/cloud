package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.TestscoresDAO;
import com.shty.collect.domain.lnk.Testscores;
import com.shty.collect.service.lnk.TestscoresService;

@Service
public class TestscoresServiceImpl implements TestscoresService {

	@Autowired
	private TestscoresDAO testscoresDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addTestscores(List<Testscores> testscores) {
		// TODO Auto-generated method stub
		return testscoresDAO.addTestscores(testscores);
	}

}
