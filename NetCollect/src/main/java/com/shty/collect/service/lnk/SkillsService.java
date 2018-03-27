package com.shty.collect.service.lnk;

import java.util.List;

import com.shty.collect.domain.lnk.Skills;
import com.shty.collect.domain.lnk.SomebodyEndor;

public interface SkillsService {

	public int addSkills(Skills skills);

	public Skills getSkillBySkillId(Long skillId);

	public List<Skills> getPeopleSkillById(Long peopleId);

	public List<SomebodyEndor> getSomebodyEndor(Long peopleId, int type);

}
