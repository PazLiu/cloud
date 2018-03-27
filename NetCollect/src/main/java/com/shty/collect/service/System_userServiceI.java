package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSystemUser;

public interface System_userServiceI {
	/**
	 * System_user登录的方法
	 * user  pwd
	 * System_user user
	 * @return
	 */
	public TbSystemUser Login(TbSystemUser user);
	
	/**
	 * 获取用户表中的全部数据
	 * @return
	 */
	public List<TbSystemUser> getAlluser();
	
	/**
	 * 添加一条数据
	 * @return
	 */
	public void adduser(TbSystemUser tb_user);
	
	/**
	 * 修改一条数据
	 * @return
	 */
	public void updateuser(TbSystemUser tb_user);
	
	/**
	 * 删除一条数据
	 * @return
	 */
	public void deleteUser(Long id);
}
