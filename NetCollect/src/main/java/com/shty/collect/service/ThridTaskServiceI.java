package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSysThirdTask;
import com.shty.collect.web.rest.dto.LnkTaskDto;

public interface ThridTaskServiceI {

	public void insertOneTask(TbSysThirdTask task);

	public TbSysThirdTask getOneTask(String tasktype);

	public void updateTask(TbSysThirdTask task);

	public List<TbSysThirdTask> getAllThirdTask();

	public List<LnkTaskDto> getThirdLnkTask(String priority);
}
