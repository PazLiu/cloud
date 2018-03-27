package com.shty.collect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbTwitterSysTaskMapper;
import com.shty.collect.domain.TbTwitterSysTask;
import com.shty.collect.service.System_Twitter_TaskServiceI;

@Service
public class System_Twitter_TaskServiceImpl implements System_Twitter_TaskServiceI {
	@Autowired
	TbTwitterSysTaskMapper tbTwitterSysTaskMapper;

	@Override
	public TbTwitterSysTask getTwitterTask() {
		PageHelper.startPage(1, 0);
		return tbTwitterSysTaskMapper.getTwitterTask();
	}
	@Override
	public int addTwitterTask(TbTwitterSysTask record){
		return tbTwitterSysTaskMapper.addTwitterTask(record);
	}
	@Override
	public int updateTwitterTask(TbTwitterSysTask record){
		return tbTwitterSysTaskMapper.updateTwitterTask(record);
	}
	@Override
	public int deteleTwitterTask(Long id){
		return tbTwitterSysTaskMapper.deteleTwitterTask(id);
	}
	@Override
	public int monitoringTwitterGroupTask(Long taskgroupid){
		return tbTwitterSysTaskMapper.monitoringTwitterGroupTask(taskgroupid);
	}
	@Override
	public int stopMonitoringTwitterGroupTask(Long taskgroupid){
		return tbTwitterSysTaskMapper.stopMonitoringTwitterGroupTask(taskgroupid);
	}
	@Override
	public int monitoringTwitterTask(Long id){
		return tbTwitterSysTaskMapper.monitoringTwitterTask(id);
	}
	@Override
	public int stopMonitoringTwitterTask(Long id){
		return tbTwitterSysTaskMapper.stopMonitoringTwitterTask(id);
	}
	@Override
	public int updateTwitterTaskStatusStatic(TbTwitterSysTask record){
		return tbTwitterSysTaskMapper.updateTwitterTaskStatusStatic(record);
	}
	
}
