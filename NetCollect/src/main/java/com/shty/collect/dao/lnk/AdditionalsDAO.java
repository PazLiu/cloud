package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Additionals;

@Repository
public interface AdditionalsDAO {

	public int addAdditionals(Additionals additionals);

	public List<Additionals> getAdditionalsByPeopleId(Long peopleId);

	// 删除
	public int deleteAdditionals(Long peopleId);

}
