package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbSysThirdTaskMapper;
import com.shty.collect.dao.lnk.PeopleDAO;
import com.shty.collect.domain.TbSysThirdTask;
import com.shty.collect.service.ThridTaskServiceI;
import com.shty.collect.web.rest.dto.LnkTaskDto;

@Service
public class ThirdTaskServiceImpl implements ThridTaskServiceI {

	@Autowired
	TbSysThirdTaskMapper tbSysThirdTaskMapper;

	@Autowired
	private PeopleDAO peopleDao;

	@Override
	public void insertOneTask(TbSysThirdTask task) {

		tbSysThirdTaskMapper.insert(task);

	}

	@Override
	public TbSysThirdTask getOneTask(String tasktype) {
		PageHelper.startPage(1, 1);
		return tbSysThirdTaskMapper.setelctOneTask(tasktype);
	}

	@Override
	public void updateTask(TbSysThirdTask task) {
		tbSysThirdTaskMapper.updateByPrimaryKey(task);
	}

	@Override
	public List<TbSysThirdTask> getAllThirdTask() {
		PageHelper.startPage(1, 0);
		return tbSysThirdTaskMapper.getAllThirdTask();
	}

	@Override
	public List<LnkTaskDto> getThirdLnkTask(String priority) {
		PageHelper.startPage(1, 0);

		List<LnkTaskDto> list = tbSysThirdTaskMapper.getThirdLnkTask(priority);
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
}
