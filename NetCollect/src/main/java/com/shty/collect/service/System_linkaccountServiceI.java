package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSystemLinkaccount;


public interface System_linkaccountServiceI {
	
	public List<TbSystemLinkaccount> getAllLinkaccount();
	
	TbSystemLinkaccount selectByPrimaryKey(Long id);
	
	List<TbSystemLinkaccount> selectAll(int page,int size);

	int deleteByPrimaryKey(Long id);

	int insert(TbSystemLinkaccount record);

	int insertSelective(TbSystemLinkaccount record);

	int updateByPrimaryKeySelective(TbSystemLinkaccount record);

	int updateByPrimaryKey(TbSystemLinkaccount record);
}
