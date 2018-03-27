package com.shty.collect.dao;

import java.util.List;

import com.shty.collect.domain.TbSystemNode;

public interface TbSystemNodeMapper  {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_node
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public Integer deleteNode(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_node
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int insert(TbSystemNode record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_node
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int insertSelective(TbSystemNode record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_node
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	TbSystemNode selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_node
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int updateByPrimaryKeySelective(TbSystemNode record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_system_node
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	int updateByPrimaryKey(TbSystemNode record);
	
	//查询node表中的全部数据
	public List<TbSystemNode> getAllnode();
	
	//添加node数据
	public Integer addNode(TbSystemNode node);
	
	//修改node数据
	public Integer updateNode(TbSystemNode node);
	
	public TbSystemNode selectNodeip(String nodeIp);
	
}