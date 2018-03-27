package com.shty.collect.dao.lnk;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Overview;

@Repository
public interface OverviewDAO {

	public int addOverview(Overview overview);

	public Overview getOverviewByPeopleId(Long peopleId);

	// 删除
	public int deleteOverview(Long peopleId);

}
