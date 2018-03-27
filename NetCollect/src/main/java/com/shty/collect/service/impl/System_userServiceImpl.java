package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbSystemUserMapper;
import com.shty.collect.domain.TbSystemUser;
import com.shty.collect.service.System_userServiceI;


@Service
public class System_userServiceImpl implements System_userServiceI {
	
	@Autowired
	TbSystemUserMapper tbSystemUserMapper;

	@Override
	public TbSystemUser Login(TbSystemUser user) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		TbSystemUser result = tbSystemUserMapper.Login(user);
		return result;
	}

	@Override
	public List<TbSystemUser> getAlluser() {
		// TODO Auto-generated method stub
		return tbSystemUserMapper.getAlluser();
	}

	@Override
	public void adduser(TbSystemUser tb_user) {
		// TODO Auto-generated method stub
		tbSystemUserMapper.adduser(tb_user);
	}

	@Override
	public void updateuser(TbSystemUser tb_user) {
		// TODO Auto-generated method stub
		tbSystemUserMapper.updateuser(tb_user);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		tbSystemUserMapper.deleteUser(id);
	}


}
