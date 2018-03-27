package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.TbTwitterSysTaskattributeMapper;
import com.shty.collect.domain.TbTwitterSysTaskattribute;
import com.shty.collect.service.System_Twitter_TaskAttributeServiceI;

@Service
public class System_Twitter_TaskAttributeServiceImpl implements System_Twitter_TaskAttributeServiceI {
	@Autowired
	TbTwitterSysTaskattributeMapper tbTwitterSysTaskattributeMapper;

	@Override
	public List<TbTwitterSysTaskattribute> getTwitterAllAttribute() {
		return tbTwitterSysTaskattributeMapper.getTwitterAllAttribute();
	}
	
	@Override
	public void addTwitterAllAttribute(TbTwitterSysTaskattribute  tbTwitterSysTaskattribute){
		tbTwitterSysTaskattributeMapper.addTwitterAllAttribute(tbTwitterSysTaskattribute);
	}
	@Override
	public void updateTwitterAllAttribute(TbTwitterSysTaskattribute tbTwitterSysTaskattribute){
		tbTwitterSysTaskattributeMapper.updateTwitterAllAttribute(tbTwitterSysTaskattribute);
	}
	@Override
	public int deleteTwitterAllAttribute(Long id){
		return tbTwitterSysTaskattributeMapper.deleteByPrimaryKey(id);
	}
}
