package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSystemRelation;
import com.shty.collect.web.rest.dto.System_Node_Socialacount_LinkaccountDto;

public interface System_RelationServiceI {
	public List<TbSystemRelation> select_Reation_nodeid(Long id);
	
	public List<TbSystemRelation> select_Reation_id(Long id);
	
	public List<System_Node_Socialacount_LinkaccountDto> select_ReationAll();
	
	public int insert_Reation(TbSystemRelation relation);
	
	public int update_Reation(TbSystemRelation relation);
	
	public int deleteReation(Long id);
}
