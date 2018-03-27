package com.shty.collect.domain;

import java.util.Date;

public class TbTwitterSysTask {

    private Long id;


    private String url;


    private Long taskattributeid;


    private String taskstatus;


    private String priority;


    private Long taskgroupid;


    private Date creattime;


    private String updatetime;
    
    private String taskname;

    public String getTaskName() {
		return taskname;
	}


	public void setTaskName(String taskname) {
		this.taskname = taskname;
	}


	public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public Long getTaskattributeid() {
        return taskattributeid;
    }


    public void setTaskattributeid(Long taskattributeid) {
        this.taskattributeid = taskattributeid;
    }


    public String getTaskstatus() {
        return taskstatus;
    }


    public void setTaskstatus(String taskstatus) {
        this.taskstatus = taskstatus;
    }

 
    public String getPriority() {
        return priority;
    }


    public void setPriority(String priority) {
        this.priority = priority;
    }


    public Long getTaskgroupid() {
        return taskgroupid;
    }


    public void setTaskgroupid(Long taskgroupid) {
        this.taskgroupid = taskgroupid;
    }


    public Date getCreattime() {
        return creattime;
    }


    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }


    public String getUpdatetime() {
        return updatetime;
    }


    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }
}