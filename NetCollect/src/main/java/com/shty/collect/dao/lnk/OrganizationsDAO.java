package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Organizations;

@Repository
public interface OrganizationsDAO {

	public int addOrganizations(List<Organizations> organizations);

	public List<Organizations> getOrganizationsByPeopleId(Long peopleId);

	// 删除
	public int deleteOrganizations(Long peopleId);

}
