package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbSystemRelationMapper;
import com.shty.collect.domain.TbSystemRelation;
import com.shty.collect.service.System_RelationServiceI;
import com.shty.collect.web.rest.dto.System_Node_Socialacount_LinkaccountDto;

@Service
public class System_RelationServiceImpl implements System_RelationServiceI {

	@Autowired
	TbSystemRelationMapper tbSystemRelationMapper;
	
	@Override
	public List<TbSystemRelation> select_Reation_nodeid(Long id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return tbSystemRelationMapper.select_Reation_nodeid(id);
	}

	@Override
	public List<System_Node_Socialacount_LinkaccountDto> select_ReationAll() {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return tbSystemRelationMapper.select_ReationAll();
	}

	@Override
	public int insert_Reation(TbSystemRelation relation) {
		// TODO Auto-generated method stub
		return tbSystemRelationMapper.insert_Reation(relation);
	}

	@Override
	public int update_Reation(TbSystemRelation relation) {
		// TODO Auto-generated method stub
		return tbSystemRelationMapper.update_Reation(relation);
	}

	@Override
	public List<TbSystemRelation> select_Reation_id(Long id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return tbSystemRelationMapper.select_Reation_id(id);
	}

	@Override
	public int deleteReation(Long id) {
		// TODO Auto-generated method stub
		return tbSystemRelationMapper.deleteReation(id);
	}

}
