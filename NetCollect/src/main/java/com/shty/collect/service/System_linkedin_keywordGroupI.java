package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbLinkedinSysTaskgroup;
import com.shty.collect.web.rest.dto.LnkKeywordGroupDto;

public interface System_linkedin_keywordGroupI {

	List<LnkKeywordGroupDto> getAllKeywordGroup();

	TbLinkedinSysTaskgroup getKeywordById(Long id);

	int addLinkedinSysTaskgroup(TbLinkedinSysTaskgroup tbg);

	int updateLinkedinSysTaskgroup(TbLinkedinSysTaskgroup tbg);
}
