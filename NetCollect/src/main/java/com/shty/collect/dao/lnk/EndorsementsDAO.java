package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Endorsements;

@Repository
public interface EndorsementsDAO {

	public int addEndorsements(List<Endorsements> endorsements);

	public List<Endorsements> getEndorsementsBySkillsId(Long skillsId);

	public List<Endorsements> getEndorsementsBySkillsIdLimitTen(Long skillsId);

	// 删除
	public int deleteEndorsements(Long peopleId);

}
