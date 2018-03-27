package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbTwitterCount;

public interface System_Facebook_TaskServiceI {
	public int addTask(TbFacebookSysTask tbFacebookSysTask);
	public int addthirdTask(TbFacebookSysTask tbFacebookSysTask);
	public int updateTask(TbFacebookSysTask tbFacebookSysTask);
	public int deleteTask(Long id);
	public int MonitoringGroupTask(Long Groupid);
	public int StopMonitoringGroupTask(Long Groupid);
	public int MonitoringTask(Long id);
	public int StopMonitoringTask(Long id);
	public int update_task_taskStatus(Long id);
	public TbFacebookSysTask select_taskUrl(String url);
	public TbTwitterCount getFacebookCountValue(Integer number);
	public TbFacebookSysTask getFacebookTask(String taskStatus);
	public List<TbFacebookSysTask> getFacebookthrid(int third_id);
}
