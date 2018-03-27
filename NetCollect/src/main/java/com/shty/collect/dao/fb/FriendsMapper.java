package com.shty.collect.dao.fb;

import com.shty.collect.domain.fb.OverviewFamily;

public interface FriendsMapper {
	public int insert_Friends(OverviewFamily overviewFamily);
	public void delete_Friends(Long FBTarget_id);
}
