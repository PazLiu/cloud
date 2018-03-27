package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.fb.FbOverviewWorkMapper;
import com.shty.collect.dao.fb.FbTargetMapper;
import com.shty.collect.domain.fb.FbOverviewWork;
import com.shty.collect.domain.fb.FbTarget;
import com.shty.collect.service.fb.FbOverviewWork_ServiceI;
import com.shty.collect.service.fb.FbTarget_ServiceI;


@Service
public class FbOverviewWork_ServiceImpl implements FbOverviewWork_ServiceI {

	@Autowired
	FbOverviewWorkMapper fbOverviewMapper;

	@Override
	public int insert_FbOverview(FbOverviewWork fbOverviewWork) {
		// TODO Auto-generated method stub
		return fbOverviewMapper.insert_FbOverview(fbOverviewWork);
	}

	@Override
	public int delete_FbOverviewWork(Long FBTarget_id) {
		// TODO Auto-generated method stub
		return fbOverviewMapper.delete_FbOverviewWork(FBTarget_id);
	}
	
	
}
