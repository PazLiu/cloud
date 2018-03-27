package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Honors;

@Repository
public interface HonorsDAO {

	public int addHonors(List<Honors> honors);

	public List<Honors> getHonorsByPeopleId(Long peopleId);

	// 删除
	public int deleteHonors(Long peopleId);

}
