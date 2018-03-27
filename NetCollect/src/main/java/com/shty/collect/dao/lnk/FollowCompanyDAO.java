package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Follow_company;

@Repository
public interface FollowCompanyDAO {

	public int addFollowCompany(List<Follow_company> follow_company);

	public List<Follow_company> getFollowCompanyByPeopleId(Long peopleId);

	// 删除
	public int deleteFollowCompany(Long peopleId);

}
