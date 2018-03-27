package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbSystemLinkaccountMapper;
import com.shty.collect.domain.TbSystemLinkaccount;
import com.shty.collect.service.System_linkaccountServiceI;

@Service
public class System_linkaccountServiceImpl implements System_linkaccountServiceI{
	@Autowired
	TbSystemLinkaccountMapper system_linkaccount_Map;

	@Override
	public TbSystemLinkaccount selectByPrimaryKey(Long id) {
		PageHelper.startPage(1, 1);
		return system_linkaccount_Map.selectByPrimaryKey(id);
	}

	@Override
	public List<TbSystemLinkaccount> selectAll(int page,int size) {
		PageHelper.startPage(page, size);
		return system_linkaccount_Map.selectAll();
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return system_linkaccount_Map.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(TbSystemLinkaccount record) {
		// TODO Auto-generated method stub
		return system_linkaccount_Map.insert(record);
	}

	@Override
	public int insertSelective(TbSystemLinkaccount record) {
		// TODO Auto-generated method stub
		return system_linkaccount_Map.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TbSystemLinkaccount record) {
		// TODO Auto-generated method stub
		return system_linkaccount_Map.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TbSystemLinkaccount record) {
		// TODO Auto-generated method stub
		return system_linkaccount_Map.updateByPrimaryKey(record);
	}

	@Override
	public List<TbSystemLinkaccount> getAllLinkaccount() {
		// TODO Auto-generated method stub
		return system_linkaccount_Map.getAllLinkaccount();
	}
	
}
