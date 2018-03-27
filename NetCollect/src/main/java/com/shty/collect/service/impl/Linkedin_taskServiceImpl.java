package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbLinkedinSysTaskMapper;
import com.shty.collect.dao.lnk.PeopleDAO;
import com.shty.collect.domain.TbLinkedinSysTask;
import com.shty.collect.service.Linkedin_taskServiceI;
import com.shty.collect.web.rest.dto.LnkTaskDto;

@Service
public class Linkedin_taskServiceImpl implements Linkedin_taskServiceI {

	@Autowired
	TbLinkedinSysTaskMapper linkedinMapper;

	@Autowired
	private PeopleDAO peopleDao;

	@Override
	public TbLinkedinSysTask getTask() {
		PageHelper.startPage(1, 1);
		return linkedinMapper.getOneTask();
	}

	@Override
	public List<TbLinkedinSysTask> getAllTask() {

		PageHelper.startPage(1, 0);
		return linkedinMapper.getAllTask();
	}

	@Override
	public void updateTask(TbLinkedinSysTask tbLinkedinSysTask) {
		linkedinMapper.updateByPrimaryKey(tbLinkedinSysTask);

	}

	@Override
	public void addTask(TbLinkedinSysTask tbLinkedinSysTask) {
		linkedinMapper.insert(tbLinkedinSysTask);

	}

	@Override
	public List<LnkTaskDto> getTaskById(int id) {
		PageHelper.startPage(1, 0);
		List<LnkTaskDto> list = linkedinMapper.getTaskById(id);
		for (LnkTaskDto l : list) {
			try {
				int pid = 0;
				if (l.getLnkid() != null) {
					PageHelper.startPage(1, 1);
					pid = peopleDao.getIdByLnkid(l.getLnkid());
					if (pid != 0)
						l.setId(pid);
				}
			} catch (Exception e) {

			}
		}
		return list;

	}

	@Override
	public void insertLnkTask(TbLinkedinSysTask tbLinkedinSysTask) {

		linkedinMapper.insert(tbLinkedinSysTask);
	}

	@Override
	public TbLinkedinSysTask getTaskByurlpattern(String urlpattern) {
		PageHelper.startPage(1, 1);
		return linkedinMapper.getTaskByurlpattern(urlpattern);
	}
}