package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbSystemSocialaccountMapper;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.web.rest.dto.System_socialAccountDto;

@Service
public class System_AccountServiceImpl implements System_AccountServiceI {

	@Autowired
	TbSystemSocialaccountMapper tbSystemSocialaccountMapper;

	@Override
	public List<System_socialAccountDto> getAllaccount() {
		// TODO Auto-generated method stub
		return tbSystemSocialaccountMapper.getAllaccount();
	}

	@Override
	public Integer addAccount(TbSystemSocialaccount tbSystemSocialaccount) {
		// TODO Auto-generated method stub
		return tbSystemSocialaccountMapper.addAccount(tbSystemSocialaccount);
	}

	@Override
	public Integer updateAccount(TbSystemSocialaccount tbSystemSocialaccount) {
		// TODO Auto-generated method stub
		return tbSystemSocialaccountMapper.updateAccount(tbSystemSocialaccount);
	}

	@Override
	public Integer deletesocialAccount(Long id) {
		// TODO Auto-generated method stub
		return tbSystemSocialaccountMapper.deletesocialAccount(id);
	}

	@Override
	public TbSystemSocialaccount select_socialAcount_id(Long id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return tbSystemSocialaccountMapper.select_socialAcount_id(id);
	}

	@Override
	public List<System_socialAccountDto> getAllaccountIsuesd(Long id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 0);
		return tbSystemSocialaccountMapper.getAllaccountIsuesd(id);
	}

	@Override
	public List<System_socialAccountDto> getAllaccountIsuesdTo() {
		// TODO Auto-generated method stub
		return tbSystemSocialaccountMapper.getAllaccountIsuesdTo();
	}

	@Override
	public TbSystemSocialaccount select_type_facebook(String accountType) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return tbSystemSocialaccountMapper.select_type_facebook(accountType);
	}

	@Override
	public List<TbSystemSocialaccount> getAllAccountName() {
		return tbSystemSocialaccountMapper.getAllAccountName();
	}

}
