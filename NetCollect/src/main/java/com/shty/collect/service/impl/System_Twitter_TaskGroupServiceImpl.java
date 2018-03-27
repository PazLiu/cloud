package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbTwitterSysTaskgroupMapper;
import com.shty.collect.domain.TbTwitterSysTaskgroup;
import com.shty.collect.service.System_Twitter_TaskGroupServiceI;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupAttrDto;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupDto;

@Service
public class System_Twitter_TaskGroupServiceImpl implements System_Twitter_TaskGroupServiceI {
	@Autowired
	TbTwitterSysTaskgroupMapper tbTwitterSysTaskgroupMapper;

	@Override
	public List<System_Twitter_TaskGroupDto> getAllGroupAttribute() {

		return tbTwitterSysTaskgroupMapper.getAllGroupAttribute();
	}
	@Override
	public List<System_Twitter_TaskGroupDto> getAllGroupAttributeSearch(){
		return tbTwitterSysTaskgroupMapper.getAllGroupAttributeSearch();
	}
	@Override
	public List<TbTwitterSysTaskgroup> getGroupSearchKeyWord(Long id){
		PageHelper.startPage(1, 0);
		return tbTwitterSysTaskgroupMapper.getGroupSearchKeyWord(id);
	}
	@Override
	public int addGroupAttribute(TbTwitterSysTaskgroup record){
		return tbTwitterSysTaskgroupMapper.addGroupAttribute(record);
	}
	@Override
	public int updateGroupAttribute(TbTwitterSysTaskgroup record){
		return tbTwitterSysTaskgroupMapper.updateGroupAttribute(record);
	}
	@Override
	public int deleteGroupAttribute(Long id){
		return tbTwitterSysTaskgroupMapper.deleteGroupAttribute(id);
	}
	@Override
	public List<System_Twitter_TaskGroupAttrDto>getGroupDetailByid(Long id){
		PageHelper.startPage(1, 0);
		return tbTwitterSysTaskgroupMapper.getGroupDetailByid(id);
	}
	@Override
	public List<System_Twitter_TaskGroupAttrDto>selectTwitterTaskUrl(String taskStatus){
		PageHelper.startPage(1, 0);
		return tbTwitterSysTaskgroupMapper.selectTwitterTaskUrl(taskStatus);
	}
	

}
