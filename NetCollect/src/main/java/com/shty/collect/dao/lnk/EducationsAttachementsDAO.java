package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.EducationsAttachements;

@Repository
public interface EducationsAttachementsDAO {

	public int addEduAttache(List<EducationsAttachements> educationsAttachements);

	public List<EducationsAttachements> getEducationsAttachementsByPeopleId(Long educationId);

	// 删除
	public int deleteEducationsAttachements(Long peopleId);
}
