package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbLinkedinSysTaskgroupMapper;
import com.shty.collect.domain.TbLinkedinSysTaskgroup;
import com.shty.collect.service.System_linkedin_keywordGroupI;
import com.shty.collect.web.rest.dto.LnkKeywordGroupDto;

@Service
public class System_linkedin_keywordGroupImpl implements System_linkedin_keywordGroupI {

	@Autowired
	TbLinkedinSysTaskgroupMapper lnkGroup;

	@Override
	public List<LnkKeywordGroupDto> getAllKeywordGroup() {
		PageHelper.startPage(1, 0);
		return lnkGroup.getAllKeywordGroup();
	}

	@Override
	public int addLinkedinSysTaskgroup(TbLinkedinSysTaskgroup tbg) {
		// TODO Auto-generated method stub
		return lnkGroup.insert(tbg);
	}

	@Override
	public TbLinkedinSysTaskgroup getKeywordById(Long id) {
		PageHelper.startPage(1, 1);
		return lnkGroup.selectByPrimaryKey(id);
	}

	@Override
	public int updateLinkedinSysTaskgroup(TbLinkedinSysTaskgroup tbg) {
		return lnkGroup.updateByPrimaryKey(tbg);
	}

}
