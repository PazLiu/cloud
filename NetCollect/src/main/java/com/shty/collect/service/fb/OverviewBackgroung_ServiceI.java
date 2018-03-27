package com.shty.collect.service.fb;

import com.shty.collect.domain.fb.OverviewBackgroung;
import com.shty.collect.domain.fb.OverviewBackgroungDescriptions;

public interface OverviewBackgroung_ServiceI {
	public void delete_OverviewBackgroun(Long FBTarget_id);
	public int insert_OverviewBackgroun(OverviewBackgroung overviewBackgroung);
	
	public int insert_OverviewBackgroungDescriptions(OverviewBackgroungDescriptions overviewBackgroungDescriptions);
}
