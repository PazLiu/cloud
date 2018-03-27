package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.People;
import com.shty.collect.web.rest.dto.LinkedCountDayDto;
import com.shty.collect.web.rest.dto.LnkPeopleDto;

@Repository
public interface PeopleDAO {

	public int addPeople(People people);

	public int countLnkid(String lnkid);

	public People getPeopleById(Long id);

	// 删除
	public int deletePeople(Long id);

	public List<LnkPeopleDto> getAllPeople();

	public int getIdByLnkid(String lnkid);

	public LinkedCountDayDto getCountByDay(String number);

}
