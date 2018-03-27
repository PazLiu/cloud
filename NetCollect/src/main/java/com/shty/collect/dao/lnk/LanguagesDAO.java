package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Languages;

@Repository
public interface LanguagesDAO {

	public int addLanguages(List<Languages> languages);

	public List<Languages> getLanguagesByPeopleId(Long peopleId);

	// 删除
	public int deleteLanguages(Long peopleId);

}
