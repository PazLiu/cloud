package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.OverviewSkillsMapper;
import com.shty.collect.domain.fb.OverviewSkills;
import com.shty.collect.service.fb.OverviewSkills_ServiceI;


@Service
public class OverviewSkills_ServiceImpl implements OverviewSkills_ServiceI {

	@Autowired
	OverviewSkillsMapper overviewSkillsMapper;
	
	
	@Override
	public void insert_overviewSkills(OverviewSkills overviewSkills) {
		// TODO Auto-generated method stub
		overviewSkillsMapper.insert_overviewSkills(overviewSkills);
	}

	@Override
	public void delete_overviewSkills(Long FBTarget_id) {
		// TODO Auto-generated method stub
		overviewSkillsMapper.delete_overviewSkills(FBTarget_id);
	}

}
