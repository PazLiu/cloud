package com.shty.collect.service.lnk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.TbSystemNodeMapper;
import com.shty.collect.service.lnk.LnkAccountService;
import com.shty.collect.web.rest.dto.CollectAccountDto;

@Service
public class LnkAccountServiceImpl implements LnkAccountService {

	@Autowired
	TbSystemNodeMapper node;

	@Override
	public CollectAccountDto getLinkaccount() {

		// PageHelper.startPage(1, 1);
		// return node.selectNodeAccount();
		return null;
	}

}
