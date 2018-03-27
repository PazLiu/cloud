package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSystemNode;

public interface System_NodeServiceI {
	//查询node表中的全部数据
	public List<TbSystemNode> getAllnode();
	
	//添加node数据
	public Integer addNode(TbSystemNode node);
	
	//修改node数据
	public Integer updateNode(TbSystemNode node);
	
	//删除node数据
	public Integer deleteNode(Long id);
	
	public TbSystemNode selectNodeip(String nodeIp);
}
