package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Additioninfo;

@Repository
public interface AdditionalinfoDAO {

	public int addAdditionalinfo(List<Additioninfo> additionalinfo);

	public List<Additioninfo> getAdditionalinfoByPeopleId(Long peopleId);

	// 删除
	public int deleteAdditionalinfo(Long peopleId);

}
