package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.TbSystemSociallimitMapper;
import com.shty.collect.domain.TbSystemSociallimit;
import com.shty.collect.service.System_LimitServiceI;

@Service
public class System_LimitServiceImpl implements System_LimitServiceI {
	@Autowired
	TbSystemSociallimitMapper tbSystemSociallimitMapper;

	@Override
	public List<TbSystemSociallimit> getAllLimit() {
		// TODO Auto-generated method stub
		return tbSystemSociallimitMapper.getAllLimit();
	}
}
