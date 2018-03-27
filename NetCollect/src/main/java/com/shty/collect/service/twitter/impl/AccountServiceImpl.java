package com.shty.collect.service.twitter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.twitter.AccountMapper;
import com.shty.collect.domain.twitter.Account;
import com.shty.collect.service.twitter.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountMapper accountDao;
	public List<Account> findAll(){
		return accountDao.findAll();
	}

}
