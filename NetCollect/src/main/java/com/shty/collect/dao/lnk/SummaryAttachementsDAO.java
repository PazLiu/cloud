package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.SummaryAttachements;

@Repository
public interface SummaryAttachementsDAO {

	public int addSumAttache(List<SummaryAttachements> summaryAttachements);

	public List<SummaryAttachements> getSummaryAttachementsByPeopleId(Long peopleId);

	// 删除
	public int deleteSummaryAttachements(Long peopleId);
}
