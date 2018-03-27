package com.shty.collect.service;

import com.shty.collect.domain.TbTwitterSysTask;

public interface System_Twitter_TaskServiceI {
	
	public TbTwitterSysTask getTwitterTask();
	int addTwitterTask(TbTwitterSysTask record);
	int updateTwitterTask(TbTwitterSysTask record);
	int deteleTwitterTask(Long id);
	int monitoringTwitterGroupTask(Long taskgroupid);	
	int stopMonitoringTwitterGroupTask(Long taskgroupid);		
	int monitoringTwitterTask(Long id);
	int stopMonitoringTwitterTask(Long id);
	int updateTwitterTaskStatusStatic(TbTwitterSysTask record);
}
