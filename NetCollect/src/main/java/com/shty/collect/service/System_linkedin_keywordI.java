package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbLinkedinSysKeyword;
import com.shty.collect.web.rest.dto.LinkedinKeywordDto;

public interface System_linkedin_keywordI {

	public List<LinkedinKeywordDto> getAllKeyword();

	public List<LinkedinKeywordDto> getKeywordByGroupId(int id);

	public void addKeyword(TbLinkedinSysKeyword tb);

	public TbLinkedinSysKeyword getKeywordbyid(int id);

	public int updateKeywordbyid(TbLinkedinSysKeyword tb);

	public int deletekeywordbyid(int id);

	public void updateKeywordGroup(int id);

	public TbLinkedinSysKeyword getOneTbLinkedinSysKeyword();
}
