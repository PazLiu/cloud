package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Interests;

@Repository
public interface InterestsDAO {

	public int addInterests(List<Interests> interests);

	public List<Interests> getInterestsByPeopleId(Long peopleId);

	// 删除
	public int deleteInterests(Long peopleId);

}
