package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbFacebookSysTaskattribute;


public interface System_Facebook_TaskattributeService {
	public List<TbFacebookSysTaskattribute> getAllattribute();
	public void addAttribute(TbFacebookSysTaskattribute TbFacebookSysTaskattribute);
	public void updateAttribute(TbFacebookSysTaskattribute TbFacebookSysTaskattribute);
	public int deleteAttribute(Long id);
}
