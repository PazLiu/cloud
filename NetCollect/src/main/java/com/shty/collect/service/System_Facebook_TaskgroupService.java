package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbFacebookSysTaskgroup;
import com.shty.collect.web.rest.dto.Syste_Facebook_Task_Attribute_Group;
import com.shty.collect.web.rest.dto.System_GroupAnd_AttributeDto;
import com.shty.collect.web.rest.dto.Task_StatusDto;

public interface System_Facebook_TaskgroupService {
	public List<System_GroupAnd_AttributeDto> getTaskgroupAll(String groupType);
	public List<System_GroupAnd_AttributeDto> getTaskgroupAll1();
	public List<System_GroupAnd_AttributeDto> getTaskgroupKeyWord();
	public int addGroup(TbFacebookSysTaskgroup group);
	public int updateGroup(TbFacebookSysTaskgroup group);
	public int deleteGroup(Long id);
	public int updateGroupStatus(Task_StatusDto task_statusdto);
	public List<Syste_Facebook_Task_Attribute_Group> getByidGroup(Long id);
	public List<Syste_Facebook_Task_Attribute_Group> getGroupByid(Long id);
	public List<Syste_Facebook_Task_Attribute_Group> select_Task(String task_static);
	public int updateGroup_task_static(Task_StatusDto statusDto);
}
