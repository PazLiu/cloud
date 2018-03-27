package com.shty.collect.dao.fb;

import com.shty.collect.domain.fb.OverviewBackgroung;
import com.shty.collect.domain.fb.OverviewBackgroungDescriptions;

public interface OverviewBackgroungMapper {
	public void delete_OverviewBackgroun(Long FBTarget_id);
	public int insert_OverviewBackgroun(OverviewBackgroung overviewBackgroung);
	
	public int insert_OverviewBackgroungDescriptions(OverviewBackgroungDescriptions overviewBackgroungDescriptions);
}
