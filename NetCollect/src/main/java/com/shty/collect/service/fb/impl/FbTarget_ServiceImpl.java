package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.fb.FbTargetMapper;
import com.shty.collect.domain.fb.FbTarget;
import com.shty.collect.service.fb.FbTarget_ServiceI;


@Service
public class FbTarget_ServiceImpl implements FbTarget_ServiceI {

	@Autowired
	FbTargetMapper fbTargetMapper;
	
	@Override
	public FbTarget getFbTarget_urlpattern(String urlpattern) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1,1);
		return fbTargetMapper.getFbTarget_urlpattern(urlpattern);
	}

	@Override
	public int update_FbTarget_urlpattern(FbTarget fbTarget) {
		// TODO Auto-generated method stub
		return fbTargetMapper.update_FbTarget_urlpattern(fbTarget);
	}

	@Override
	public int insert_FbTarget_urlpattern(FbTarget fbTarget) {
		// TODO Auto-generated method stub
		return fbTargetMapper.insert_FbTarget_urlpattern(fbTarget);
	}

	@Override
	public int update_FbTarget_friendsurl(FbTarget fbTarget) {
		// TODO Auto-generated method stub
		return fbTargetMapper.update_FbTarget_friendsurl(fbTarget);
	}

	@Override
	public int update_FbTarget_photoUrl(FbTarget fbTarget) {
		// TODO Auto-generated method stub
		return fbTargetMapper.update_FbTarget_photoUrl(fbTarget);
	}

}
