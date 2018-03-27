package com.shty.collect.dao.fb;

import com.shty.collect.domain.fb.OverviewLivings;

public interface OverviewLivingsMapper {
	public void delete_OverviewLivings(Long FBTarget_id);
	public int insert_OverviewLivings(OverviewLivings overviewLivings);
}
