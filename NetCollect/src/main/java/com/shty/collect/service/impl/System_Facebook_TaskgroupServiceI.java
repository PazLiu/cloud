package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbFacebookSysTaskgroupMapper;
import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbFacebookSysTaskgroup;
import com.shty.collect.service.System_Facebook_TaskgroupService;
import com.shty.collect.web.rest.dto.Syste_Facebook_Task_Attribute_Group;
import com.shty.collect.web.rest.dto.System_GroupAnd_AttributeDto;
import com.shty.collect.web.rest.dto.Task_StatusDto;

@Service
public class System_Facebook_TaskgroupServiceI implements System_Facebook_TaskgroupService {
	@Autowired
	TbFacebookSysTaskgroupMapper tbFacebookSysTaskgroupMapper;

	@Override
	public List<System_GroupAnd_AttributeDto> getTaskgroupAll(String groupType) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return tbFacebookSysTaskgroupMapper.getTaskgroupAll(groupType);
	}

	@Override
	public int addGroup(TbFacebookSysTaskgroup group) {
		// TODO Auto-generated method stub
		return tbFacebookSysTaskgroupMapper.addGroup(group);
	}

	@Override
	public int updateGroup(TbFacebookSysTaskgroup group) {
		// TODO Auto-generated method stub
		return tbFacebookSysTaskgroupMapper.updateByPrimaryKey(group);
	}

	@Override
	public int deleteGroup(Long id) {
		// TODO Auto-generated method stub
		return tbFacebookSysTaskgroupMapper.deleteGroup(id);
	}

	@Override
	public List<Syste_Facebook_Task_Attribute_Group> getByidGroup(Long id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return tbFacebookSysTaskgroupMapper.getByidGroup(id);
	}

	@Override
	public List<Syste_Facebook_Task_Attribute_Group> select_Task(String task_static) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return tbFacebookSysTaskgroupMapper.select_Task(task_static);
	}

	@Override
	public int updateGroup_task_static(Task_StatusDto statusDto) {
		// TODO Auto-generated method stub
		return tbFacebookSysTaskgroupMapper.updateGroup_task_static(statusDto);
	}

	@Override
	public List<System_GroupAnd_AttributeDto> getTaskgroupKeyWord() {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,1);
		return tbFacebookSysTaskgroupMapper.getTaskgroupKeyWord();
	}

	@Override
	public List<System_GroupAnd_AttributeDto> getTaskgroupAll1() {
		// TODO Auto-generated method stub
		return tbFacebookSysTaskgroupMapper.getTaskgroupAll1();
	}

	@Override
	public int updateGroupStatus(Task_StatusDto task_statusdto) {
		// TODO Auto-generated method stub
		return tbFacebookSysTaskgroupMapper.updateGroupStatus(task_statusdto);
	}

	@Override
	public List<Syste_Facebook_Task_Attribute_Group> getGroupByid(Long id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,0);
		return tbFacebookSysTaskgroupMapper.getGroupByid(id);
	}
	
}
