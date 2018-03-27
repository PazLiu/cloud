package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbFacebookSysTaskMapper;
import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbTwitterCount;
import com.shty.collect.service.System_Facebook_TaskServiceI;

@Service
public class System_Facebook_ServiceImpl implements System_Facebook_TaskServiceI {
	@Autowired
	TbFacebookSysTaskMapper TbFacebookSysTaskMapper;

	@Override
	public int addTask(TbFacebookSysTask tbFacebookSysTask) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.addTask(tbFacebookSysTask);
	}

	@Override
	public int updateTask(TbFacebookSysTask tbFacebookSysTask) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.updateTask(tbFacebookSysTask);
	}

	@Override
	public int deleteTask(Long id) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.deleteTask(id);
	}

	@Override
	public int MonitoringGroupTask(Long Groupid) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.MonitoringGroupTask(Groupid);
	}

	@Override
	public int MonitoringTask(Long id) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.MonitoringTask(id);
	}

	@Override
	public int StopMonitoringGroupTask(Long Groupid) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.StopMonitoringGroupTask(Groupid);
	}

	@Override
	public int StopMonitoringTask(Long id) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.StopMonitoringTask(id);
	}

	@Override
	public int update_task_taskStatus(Long id) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.update_task_taskStatus(id);
	}

	@Override
	public TbFacebookSysTask select_taskUrl(String url) {
		// TODO Auto-generated method stub
		PageHelper.startPage(0, 1);
		return TbFacebookSysTaskMapper.select_taskUrl(url);
	}

	@Override
	public TbTwitterCount getFacebookCountValue(Integer number) {
		// TODO Auto-generated method stub
		PageHelper.startPage(0, 1);
		return TbFacebookSysTaskMapper.getFacebookCountValue(number);
	}

	@Override
	public TbFacebookSysTask getFacebookTask(String taskStatus) {
		// TODO Auto-generated method stub
		PageHelper.startPage(0, 1);
		return TbFacebookSysTaskMapper.getFacebookTask(taskStatus);
	}

	@Override
	public List<TbFacebookSysTask> getFacebookthrid(int third_id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 0);
		return TbFacebookSysTaskMapper.getFacebookthrid(third_id);
	}

	@Override
	public int addthirdTask(TbFacebookSysTask tbFacebookSysTask) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskMapper.addthirdTask(tbFacebookSysTask);
	}


	
}
