package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.ExperiencesAttachements;

@Repository
public interface ExperiencesAttachementsDAO {

	public int addExpAttache(List<ExperiencesAttachements> experiencesAttachements);

	public List<ExperiencesAttachements> getExperiencesAttachementsByPeopleId(Long experienceId);

	// 删除
	public int deleteExpAttache(Long peopleId);
}
