package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.OverviewLivingsMapper;
import com.shty.collect.domain.fb.OverviewLivings;
import com.shty.collect.service.fb.OverviewLivings_ServiceI;

@Service
public class OverviewLivings_ServiceImpl implements OverviewLivings_ServiceI {

	@Autowired
	OverviewLivingsMapper overviewLivingsMapper;
	
	
	@Override
	public void delete_OverviewLivings(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewLivingsMapper.delete_OverviewLivings(FBTarget_id);
	}


	@Override
	public int insert_OverviewLivings(OverviewLivings overviewLivings) {
		// TODO Auto-generated method stub
		return overviewLivingsMapper.insert_OverviewLivings(overviewLivings);
	}
	
}
