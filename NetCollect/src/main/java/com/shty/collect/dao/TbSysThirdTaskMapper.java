package com.shty.collect.dao;

import java.util.List;

import com.shty.collect.domain.TbSysThirdTask;
import com.shty.collect.web.rest.dto.LnkTaskDto;

public interface TbSysThirdTaskMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_sys_third_task
	 * 
	 * @mbg.generated Mon Sep 04 09:18:24 CST 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_sys_third_task
	 * 
	 * @mbg.generated Mon Sep 04 09:18:24 CST 2017
	 */
	int insert(TbSysThirdTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_sys_third_task
	 * 
	 * @mbg.generated Mon Sep 04 09:18:24 CST 2017
	 */
	int insertSelective(TbSysThirdTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_sys_third_task
	 * 
	 * @mbg.generated Mon Sep 04 09:18:24 CST 2017
	 */
	TbSysThirdTask selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_sys_third_task
	 * 
	 * @mbg.generated Mon Sep 04 09:18:24 CST 2017
	 */
	int updateByPrimaryKeySelective(TbSysThirdTask record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table tb_sys_third_task
	 * 
	 * @mbg.generated Mon Sep 04 09:18:24 CST 2017
	 */
	int updateByPrimaryKey(TbSysThirdTask record);

	TbSysThirdTask setelctOneTask(String tasktype);

	List<TbSysThirdTask> getAllThirdTask();

	List<LnkTaskDto> getThirdLnkTask(String priority);
}