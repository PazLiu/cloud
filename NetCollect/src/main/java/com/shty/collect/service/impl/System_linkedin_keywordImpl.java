package com.shty.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.TbLinkedinSysKeywordMapper;
import com.shty.collect.domain.TbLinkedinSysKeyword;
import com.shty.collect.service.System_linkedin_keywordI;
import com.shty.collect.web.rest.dto.LinkedinKeywordDto;

@Service
public class System_linkedin_keywordImpl implements System_linkedin_keywordI {

	@Autowired
	TbLinkedinSysKeywordMapper keywordMapper;

	@Override
	public List<LinkedinKeywordDto> getAllKeyword() {
		PageHelper.startPage(1, 0);
		return keywordMapper.getAllKeyword();
	}

	@Override
	public void addKeyword(TbLinkedinSysKeyword tb) {
		keywordMapper.insert(tb);

	}

	@Override
	public List<LinkedinKeywordDto> getKeywordByGroupId(int id) {
		PageHelper.startPage(1, 0);
		return keywordMapper.getKeywordByGroupId(id);
	}

	@Override
	public void updateKeywordGroup(int id) {

	}

	@Override
	public TbLinkedinSysKeyword getKeywordbyid(int id) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return keywordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateKeywordbyid(TbLinkedinSysKeyword tb) {
		// TODO Auto-generated method stub
		return keywordMapper.updateByPrimaryKey(tb);
	}

	@Override
	public int deletekeywordbyid(int id) {

		return keywordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TbLinkedinSysKeyword getOneTbLinkedinSysKeyword() {
		PageHelper.startPage(1, 1);
		return keywordMapper.selectOneKeyword();
	}

}
