package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Follow_people;

@Repository
public interface FollowPeopleDAO {

	public int addFollowPeople(List<Follow_people> follow_people);

	public List<Follow_people> getFollowPeopleByPeopleId(Long peopleId);

	// 删除
	public int deleteFollowPeople(Long peopleId);

}
