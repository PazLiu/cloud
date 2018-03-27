package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbSystemNodeMapper;
import com.shty.collect.domain.TbSystemNode;
import com.shty.collect.service.System_NodeServiceI;

@Service
public class System_NodeServiceImpl implements System_NodeServiceI {
	
	@Autowired
	TbSystemNodeMapper tbSystemNodeMapper;
	
	/**
	 * 查询node表中的全部数据
	 */
	@Override
	public List<TbSystemNode> getAllnode() {
		// TODO Auto-generated method stub
		return tbSystemNodeMapper.getAllnode();
	}
	
	
	//添加node数据
	@Override
	public Integer addNode(TbSystemNode node) {
		// TODO Auto-generated method stub
		return tbSystemNodeMapper.addNode(node);
	}


	@Override
	public Integer updateNode(TbSystemNode node) {
		// TODO Auto-generated method stub
		return tbSystemNodeMapper.updateNode(node);
	}


	@Override
	public Integer deleteNode(Long id) {
		// TODO Auto-generated method stub
		return tbSystemNodeMapper.deleteNode(id);
	}


	@Override
	public TbSystemNode selectNodeip(String nodeIp) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return tbSystemNodeMapper.selectNodeip(nodeIp);
	}
	
}
