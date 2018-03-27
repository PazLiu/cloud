package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.web.rest.dto.System_socialAccountDto;

public interface System_AccountServiceI {
	/**
	 * 查询所有的account数据
	 * 
	 * @return
	 */
	public List<System_socialAccountDto> getAllaccount();
	
	public List<System_socialAccountDto> getAllaccountIsuesdTo();
	
	public List<System_socialAccountDto> getAllaccountIsuesd(Long id);

	public Integer addAccount(TbSystemSocialaccount tbSystemSocialaccount);

	public Integer updateAccount(TbSystemSocialaccount tbSystemSocialaccount);

	public Integer deletesocialAccount(Long id);
	
	public TbSystemSocialaccount select_socialAcount_id(Long id);
	
	public TbSystemSocialaccount select_type_facebook(String accountType);
	
	public List<TbSystemSocialaccount> getAllAccountName();
}