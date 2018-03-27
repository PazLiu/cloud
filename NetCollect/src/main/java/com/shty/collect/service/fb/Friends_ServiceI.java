package com.shty.collect.service.fb;

import com.shty.collect.domain.fb.OverviewFamily;

public interface Friends_ServiceI {
	public int insert_Friends(OverviewFamily overviewFamily);
	public void delete_Friends(Long FBTarget_id);
}
