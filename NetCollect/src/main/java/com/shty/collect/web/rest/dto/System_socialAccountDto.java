package com.shty.collect.web.rest.dto;

import java.util.Date;

public class System_socialAccountDto {
	/**
	 *Long		 账号ID
	 *String	 账号name
	 *String	 账号密码
	 *String	 账号类型{(1,facebook),(2,Linkdin),(3.twitter)}
	 *Byte		 是否分配
	 *Long		 账号限制表ID
	 *String	 创建时间
	 *String	 修改时间
	 *String	 睡眠时间
	 *Integer	 睡眠次数
	 */
	private Long id;
	private String accountname;
	private String password;
	private String accounttype;
	//数据库出来的是否分配
	private String isused;
	//用于返回的http
	private String httpip;
	//用于返回的http
	private String httpid;
	
	private Long limittableid;
	//数据库出来的时间
	private Date creattime;
	private Date updatetime;
	//用于返回的时间
	private String datecreattime;
	private String dateupdatetime;
	private String sleeptime;
	private Integer limitnumber;
	
	public String getHttpid() {
		return httpid;
	}

	public void setHttpid(String httpid) {
		this.httpid = httpid;
	}
	public String getIsused() {
		return isused;
	}
	
	public String getHttpip() {
		return httpip;
	}

	public void setHttpip(String httpip) {
		this.httpip = httpip;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreattime() {
		return creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getDatecreattime() {
		return datecreattime;
	}

	public void setDatecreattime(String datecreattime) {
		this.datecreattime = datecreattime;
	}

	public String getDateupdatetime() {
		return dateupdatetime;
	}

	public void setDateupdatetime(String dateupdatetime) {
		this.dateupdatetime = dateupdatetime;
	}

	
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public Long getLimittableid() {
		return limittableid;
	}
	public void setLimittableid(Long limittableid) {
		this.limittableid = limittableid;
	}
	public String getSleeptime() {
		return sleeptime;
	}
	public void setSleeptime(String sleeptime) {
		this.sleeptime = sleeptime;
	}
	public Integer getLimitnumber() {
		return limitnumber;
	}
	public void setLimitnumber(Integer limitnumber) {
		this.limitnumber = limitnumber;
	}
	
	
	
	
}
