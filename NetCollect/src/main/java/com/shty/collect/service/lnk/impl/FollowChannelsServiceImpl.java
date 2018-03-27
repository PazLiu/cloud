package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shty.collect.dao.lnk.FollowChannelsDAO;
import com.shty.collect.domain.lnk.Follow_channels;
import com.shty.collect.service.lnk.FollowChannelsService;

@Service
public class FollowChannelsServiceImpl implements FollowChannelsService {

	@Autowired
	private FollowChannelsDAO followChannelsDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addFollowChannels(List<Follow_channels> follow_channels) {
		// TODO Auto-generated method stub
		return followChannelsDAO.addFollowChannels(follow_channels);
	}

}
