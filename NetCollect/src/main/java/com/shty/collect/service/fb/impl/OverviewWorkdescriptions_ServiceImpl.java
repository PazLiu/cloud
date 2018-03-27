package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.OverviewWorkdescriptionsMapper;
import com.shty.collect.domain.fb.OverviewWorkdescriptions;
import com.shty.collect.service.fb.OverviewWorkdescriptions_ServiceI;

@Service
public class OverviewWorkdescriptions_ServiceImpl implements OverviewWorkdescriptions_ServiceI {
	
	@Autowired
	OverviewWorkdescriptionsMapper overviewWorkdescriptionsMapper;

	@Override
	public void insert_OverviewWorkdescriptions(OverviewWorkdescriptions descriptions) {
		// TODO Auto-generated method stub
		overviewWorkdescriptionsMapper.insert_OverviewWorkdescriptions(descriptions);
	}

}
