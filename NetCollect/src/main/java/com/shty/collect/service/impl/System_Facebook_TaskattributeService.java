package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.domain.TbFacebookSysTaskattribute;
import com.shty.collect.dao.TbFacebookSysTaskattributeMapper;

@Service
public class System_Facebook_TaskattributeService
		implements com.shty.collect.service.System_Facebook_TaskattributeService {
	
	@Autowired
	TbFacebookSysTaskattributeMapper TbFacebookSysTaskattributeMapper;
		
	@Override
	public List<TbFacebookSysTaskattribute> getAllattribute() {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskattributeMapper.getAllattribute();
	}

	@Override
	public void addAttribute(TbFacebookSysTaskattribute TbFacebookSysTaskattribute) {
		// TODO Auto-generated method stub
		TbFacebookSysTaskattributeMapper.addAttribute(TbFacebookSysTaskattribute);
	}

	@Override
	public void updateAttribute(TbFacebookSysTaskattribute TbFacebookSysTaskattribute) {
		// TODO Auto-generated method stub
		TbFacebookSysTaskattributeMapper.updateAttribute(TbFacebookSysTaskattribute);
	}

	@Override
	public int deleteAttribute(Long id) {
		// TODO Auto-generated method stub
		return TbFacebookSysTaskattributeMapper.deleteAttribute(id);
	}
	
}
