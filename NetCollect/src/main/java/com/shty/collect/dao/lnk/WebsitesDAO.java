package com.shty.collect.dao.lnk;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Websites;

@Repository
public interface WebsitesDAO {

	public int addWebsites(Websites websites);

	public Websites getWebsites(Long overview_id);

	// 删除
	public int deleteWebsites(Long peopleId);

}
