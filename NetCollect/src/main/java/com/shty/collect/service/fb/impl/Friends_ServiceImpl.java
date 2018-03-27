package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.FriendsMapper;
import com.shty.collect.domain.fb.OverviewFamily;
import com.shty.collect.service.fb.Friends_ServiceI;


@Service
public class Friends_ServiceImpl implements Friends_ServiceI {

	@Autowired
	FriendsMapper friendsMapper;
	
	@Override
	public int insert_Friends(OverviewFamily overviewFamily) {
		// TODO Auto-generated method stub
		return friendsMapper.insert_Friends(overviewFamily);
	}

	@Override
	public void delete_Friends(Long FBTarget_id) {
		// TODO Auto-generated method stub
		friendsMapper.delete_Friends(FBTarget_id);
	}

}
