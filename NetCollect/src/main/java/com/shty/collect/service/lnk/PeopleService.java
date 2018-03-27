package com.shty.collect.service.lnk;

import java.util.List;

import com.shty.collect.domain.lnk.People;
import com.shty.collect.domain.lnk.vo.PeopleShow;
import com.shty.collect.web.rest.dto.LinkedCountDayDto;
import com.shty.collect.web.rest.dto.LnkPeopleDto;

public interface PeopleService {

	public int addPeople(People people);

	public int countLnkid(String lnkid);

	public PeopleShow getPeopleById(Long id);

	public List<LnkPeopleDto> getAllPeople();

	public int getIdByLnkid(String lnkid);

	public LinkedCountDayDto getCountByDay(String number);

}
