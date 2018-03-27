package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbTwitterSysTaskgroup;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupAttrDto;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupDto;

public interface System_Twitter_TaskGroupServiceI {
	public List<System_Twitter_TaskGroupDto> getAllGroupAttribute();
	public List<System_Twitter_TaskGroupDto> getAllGroupAttributeSearch();
	public List<TbTwitterSysTaskgroup> getGroupSearchKeyWord(Long id);
	int addGroupAttribute(TbTwitterSysTaskgroup record);
	int updateGroupAttribute(TbTwitterSysTaskgroup record);
	int deleteGroupAttribute(Long id);
	public List<System_Twitter_TaskGroupAttrDto>getGroupDetailByid(Long id);
	public List<System_Twitter_TaskGroupAttrDto>selectTwitterTaskUrl(String taskStatus);
}
