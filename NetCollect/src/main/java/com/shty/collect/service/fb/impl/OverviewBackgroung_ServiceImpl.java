package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.OverviewBackgroungMapper;
import com.shty.collect.domain.fb.OverviewBackgroung;
import com.shty.collect.domain.fb.OverviewBackgroungDescriptions;
import com.shty.collect.service.fb.OverviewBackgroung_ServiceI;


@Service
public class OverviewBackgroung_ServiceImpl implements OverviewBackgroung_ServiceI {
	
	@Autowired
	OverviewBackgroungMapper overviewBackgroungMapper;
	
	@Override
	public void delete_OverviewBackgroun(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewBackgroungMapper.delete_OverviewBackgroun(FBTarget_id);
	}

	@Override
	public int insert_OverviewBackgroun(OverviewBackgroung overviewBackgroung) {
		// TODO Auto-generated method stub
		return overviewBackgroungMapper.insert_OverviewBackgroun(overviewBackgroung);
	}


	@Override
	public int insert_OverviewBackgroungDescriptions(OverviewBackgroungDescriptions overviewBackgroungDescriptions) {
		// TODO Auto-generated method stub
		return overviewBackgroungMapper.insert_OverviewBackgroungDescriptions(overviewBackgroungDescriptions);
	}

}
