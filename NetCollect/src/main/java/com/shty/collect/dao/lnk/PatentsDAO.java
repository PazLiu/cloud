package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Patents;

@Repository
public interface PatentsDAO {

	public int addPatents(Patents patents);

	public List<Patents> getPatentsByPeopleId(Long peopleId);

	// 删除
	public int deletePatents(Long peopleId);

}
