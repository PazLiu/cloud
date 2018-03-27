package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.OverviewFamilyMapper;
import com.shty.collect.domain.fb.OverviewFamily;
import com.shty.collect.service.fb.OverviewFamily_ServiceI;


@Service
public class OverviewFamily_ServiceImpl implements OverviewFamily_ServiceI {
	@Autowired
	OverviewFamilyMapper overviewFamilyMapper;

	@Override
	public void insert_OverviewFamily(OverviewFamily overviewFamily) {
		// TODO Auto-generated method stub
		overviewFamilyMapper.insert_OverviewFamily(overviewFamily);
	}

	@Override
	public void delete_OverviewFamily(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewFamilyMapper.delete_OverviewFamily(FBTarget_id);
	}
}
