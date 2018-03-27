package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSystemSociallimit;

public interface System_LimitServiceI {
	//查询限制表中的数据
	public List<TbSystemSociallimit> getAllLimit();
}
