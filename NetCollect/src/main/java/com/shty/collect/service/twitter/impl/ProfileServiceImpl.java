package com.shty.collect.service.twitter.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.twitter.ProfileMapper;
import com.shty.collect.domain.twitter.TwitterProfile;
import com.shty.collect.service.twitter.ProfileService;


@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileMapper profileDao;

	@Override
	public void insertNew(TwitterProfile profile) {
		profileDao.insertNew(profile);
	}

	@Override
	public int findProfileByNickName(String userNickName) {
		return profileDao.findProfileByNickName(userNickName);

	}

	@Override
	public String findProfileID(String userNickName) {	
		PageHelper.startPage(1,1);
		return profileDao.findProfileID(userNickName);
	}

}
