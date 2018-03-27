package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.OverviewEducationMapper;
import com.shty.collect.domain.fb.OverviewEducation;
import com.shty.collect.domain.fb.OverviewEducationDescriptions;
import com.shty.collect.service.fb.OverviewEducation_ServiceI;

@Service
public class OverviewEducation_ServiceImpl implements OverviewEducation_ServiceI {

	@Autowired 
	OverviewEducationMapper overviewEducationMapper;
	
	@Override
	public int insert_OverviewEducation(OverviewEducation overviewEducation) {
		// TODO Auto-generated method stub
		return overviewEducationMapper.insert_OverviewEducation(overviewEducation);
	}

	@Override
	public void insert_OverviewEducationDescriptions(OverviewEducationDescriptions overviewEducationDescriptions) {
		// TODO Auto-generated method stub
		overviewEducationMapper.insert_OverviewEducationDescriptions(overviewEducationDescriptions);
	}

	@Override
	public void delete_OverviewEducation(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewEducationMapper.delete_OverviewEducation(FBTarget_id);
	}

}
