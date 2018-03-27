package com.shty.collect.service.twitter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.twitter.TargetMapper;
import com.shty.collect.domain.twitter.Target;
import com.shty.collect.service.twitter.TargetService;

@Service
public class TargetServiceImpl implements TargetService{
	@Autowired
	private TargetMapper targetDao;
	public List<Target> findAll(){
		return targetDao.findAll();
	}

}
