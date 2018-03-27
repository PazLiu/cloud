package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Testscores;

@Repository
public interface TestscoresDAO {

	public int addTestscores(List<Testscores> testscores);

	public List<Testscores> getTestscoresByPeopleId(Long peopleId);

	// 删除
	public int deleteTestscores(Long peopleId);

}
