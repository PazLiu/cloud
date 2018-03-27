package com.shty.collect.dao;

import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.domain.TbTwitterSysTask;

public interface TbSystemImportExeclMapper {

	int insertImportTask(TbTwitterSysTask twitterSysTask);
	int insertImportFacebookTask(TbFacebookSysTask tbFacebookSysTask);
	int insertImportAccount(TbSystemSocialaccount tbSystemSocialaccount);
}
