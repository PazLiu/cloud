package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbSystemHttpMapper;
import com.shty.collect.domain.TbSystemHttp;
import com.shty.collect.service.TbSystemHttpServiceI;

@Service
public class TbSystemHttpServiceImpl implements TbSystemHttpServiceI {

	@Autowired
	TbSystemHttpMapper tbSystemHttpMapper;

	@Override
	public List<TbSystemHttp> getAllHttp() {
		// TODO Auto-generated method stub
		return tbSystemHttpMapper.getAllHttp();
	}

	@Override
	public int insertHttp(TbSystemHttp TbSystemHttp) {
		// TODO Auto-generated method stub
		return tbSystemHttpMapper.insertHttp(TbSystemHttp);
	}

	@Override
	public int updateHttp(TbSystemHttp TbSystemHttp) {
		// TODO Auto-generated method stub
		return tbSystemHttpMapper.updateHttp(TbSystemHttp);
	}

	@Override
	public int deleteHttp(Long id) {
		// TODO Auto-generated method stub
		return tbSystemHttpMapper.deleteHttp(id);
	}

	@Override
	public TbSystemHttp getByidHttp(Long id) {
		PageHelper.startPage(1, 1);
		// TODO Auto-generated method stub
		return tbSystemHttpMapper.getByidHttp(id);
	}

}
