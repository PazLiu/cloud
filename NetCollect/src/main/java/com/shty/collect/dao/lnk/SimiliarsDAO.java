package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Similiars;

@Repository
public interface SimiliarsDAO {

	public int addSimiliars(List<Similiars> similiars);

	public List<Similiars> getSimiliarsByPeopleId(Long peopleId);

	// 删除
	public int deleteSimiliars(Long peopleId);

}
