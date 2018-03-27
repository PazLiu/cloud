package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Skills;
import com.shty.collect.domain.lnk.SomebodyEndor;

@Repository
public interface SkillsDAO {

	public int addSkills(Skills skills);

	public List<Skills> getSkillsByPeopleId(Long peopleId);

	public Skills getSkillBySkillId(Long skillId);

	public List<SomebodyEndor> getSomebodyEndor(Long peopleId);

	// 删除
	public int deleteSkills(Long peopleId);

}
