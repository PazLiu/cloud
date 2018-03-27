package com.shty.collect.service.fb.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shty.collect.dao.fb.FBPhotoMapper;
import com.shty.collect.domain.fb.FBPhoto;
import com.shty.collect.service.fb.FBPhoto_ServiceI;


@Service
public class FBPhoto_ServiceImpl implements FBPhoto_ServiceI {
	
	@Autowired
	FBPhotoMapper fBPhotoMapper;

	@Override
	public int insert_FBPhoto(FBPhoto photo) {
		// TODO Auto-generated method stub
		return fBPhotoMapper.insert_FBPhoto(photo);
	}


}
