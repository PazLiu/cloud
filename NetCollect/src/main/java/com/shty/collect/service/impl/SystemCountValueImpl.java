package com.shty.collect.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbCountMapper;
import com.shty.collect.domain.TbTwitterCount;
import com.shty.collect.service.SystemCountValueServiceI;

@Service
public class SystemCountValueImpl implements SystemCountValueServiceI {
	@Autowired
	TbCountMapper tbCountMapper;

	@Override
	public TbTwitterCount getTwitterCountByDate(Integer number){
		PageHelper.startPage(1, 0);
		return tbCountMapper.getTwitterCountByDate(number);
	}
}
