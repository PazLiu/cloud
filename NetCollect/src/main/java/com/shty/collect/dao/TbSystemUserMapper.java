package com.shty.collect.dao;

import java.util.List;

import com.shty.collect.domain.TbSystemUser;

public interface TbSystemUserMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_user
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_user
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int insert(TbSystemUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_user
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int insertSelective(TbSystemUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_user
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	TbSystemUser selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_user
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int updateByPrimaryKeySelective(TbSystemUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_user
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int updateByPrimaryKey(TbSystemUser record);
	
	
	/**
	 * System_user登录的方法
	 * user  pwd
	 * System_user user
	 * @return
	 */
	public TbSystemUser Login(TbSystemUser user);
	
	/**
	 * System_user获取表中的全部数据
	 * @return
	 */
	public List<TbSystemUser> getAlluser();
	
	/**
	 * 添加一个用户
	 * @return
	 */
	public void adduser(TbSystemUser tb_user);
	
	
	/**
	 * update一条数据
	 * @return
	 */
	public void updateuser(TbSystemUser tb_user);
	
	
	/**
	 * 删除一条数据
	 * @return
	 */
	public void deleteUser(Long id);
}