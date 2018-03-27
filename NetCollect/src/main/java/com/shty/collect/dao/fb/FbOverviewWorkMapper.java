package com.shty.collect.dao.fb;

import com.shty.collect.domain.fb.FbOverviewWork;

public interface FbOverviewWorkMapper {
	
	public int insert_FbOverview(FbOverviewWork fbOverviewWork);
	
	public int delete_FbOverviewWork(Long FBTarget_id);
}
