package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbTwitterSysTaskattribute;

public interface System_Twitter_TaskAttributeServiceI {
	public List<TbTwitterSysTaskattribute> getTwitterAllAttribute();
	public void addTwitterAllAttribute(TbTwitterSysTaskattribute  tbTwitterSysTaskattribute);
	public void updateTwitterAllAttribute(TbTwitterSysTaskattribute tbTwitterSysTaskattribute);
	public int deleteTwitterAllAttribute(Long id);
}
