package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Follow_school;

@Repository
public interface FollowSchoolDAO {

	public int addFollowSchool(List<Follow_school> follow_school);

	public List<Follow_school> getFollowSchoolByPeopleId(Long peopleId);

	// 删除
	public int deleteFollowSchool(Long peopleId);

}
