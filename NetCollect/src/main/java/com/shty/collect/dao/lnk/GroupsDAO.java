package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Groups;

@Repository
public interface GroupsDAO {

	public int addGroups(List<Groups> groups);

	public List<Groups> getGroupsByPeopleId(Long peopleId);

	// 删除
	public int deleteGroups(Long peopleId);

}
