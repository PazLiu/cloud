package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbLinkedinSysTask;
import com.shty.collect.web.rest.dto.LnkTaskDto;

public interface Linkedin_taskServiceI {

	public TbLinkedinSysTask getTask();

	public List<TbLinkedinSysTask> getAllTask();

	public void updateTask(TbLinkedinSysTask tbLinkedinSysTask);

	public void addTask(TbLinkedinSysTask tbLinkedinSysTask);

	public List<LnkTaskDto> getTaskById(int id);

	public void insertLnkTask(TbLinkedinSysTask tbLinkedinSysTask);

	public TbLinkedinSysTask getTaskByurlpattern(String urlpattern);

}
