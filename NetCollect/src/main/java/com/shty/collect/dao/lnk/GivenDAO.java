package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Given;

@Repository
public interface GivenDAO {

	public int addGiven(List<Given> given);

	public List<Given> getGivenByPeopleId(Long peopleId);

	// 删除
	public int deleteGiven(Long peopleId);

}
