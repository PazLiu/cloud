package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Volunteers;

@Repository
public interface VolunteersDAO {

	public int addVolunteers(List<Volunteers> volunteers);

	public List<Volunteers> getVolunteersByPeopleId(Long peopleId);

	// 删除
	public int deleteVolunteers(Long peopleId);

}
