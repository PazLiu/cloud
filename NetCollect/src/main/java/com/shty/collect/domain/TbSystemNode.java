package com.shty.collect.domain;

import java.util.Date;

public class TbSystemNode {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_system_node.id
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_system_node.nodeIp
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	private String nodeip;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_system_node.physicalIp
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	private String physicalip;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_system_node.isused
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	private Byte isused;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_system_node.creatTime
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	private Date creattime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column tb_system_node.updateTime
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	private Date updatetime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_system_node.id
	 * @return  the value of tb_system_node.id
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_system_node.id
	 * @param id  the value for tb_system_node.id
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_system_node.nodeIp
	 * @return  the value of tb_system_node.nodeIp
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public String getNodeip() {
		return nodeip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_system_node.nodeIp
	 * @param nodeip  the value for tb_system_node.nodeIp
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public void setNodeip(String nodeip) {
		this.nodeip = nodeip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_system_node.physicalIp
	 * @return  the value of tb_system_node.physicalIp
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public String getPhysicalip() {
		return physicalip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_system_node.physicalIp
	 * @param physicalip  the value for tb_system_node.physicalIp
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public void setPhysicalip(String physicalip) {
		this.physicalip = physicalip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_system_node.isused
	 * @return  the value of tb_system_node.isused
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public Byte getIsused() {
		return isused;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_system_node.isused
	 * @param isused  the value for tb_system_node.isused
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public void setIsused(Byte isused) {
		this.isused = isused;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_system_node.creatTime
	 * @return  the value of tb_system_node.creatTime
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public Date getCreattime() {
		return creattime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_system_node.creatTime
	 * @param creattime  the value for tb_system_node.creatTime
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column tb_system_node.updateTime
	 * @return  the value of tb_system_node.updateTime
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column tb_system_node.updateTime
	 * @param updatetime  the value for tb_system_node.updateTime
	 * @mbg.generated  Wed Jun 07 10:53:40 CST 2017
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}