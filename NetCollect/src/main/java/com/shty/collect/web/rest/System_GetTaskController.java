package com.shty.collect.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbLinkedinSysTask;
import com.shty.collect.service.Linkedin_taskServiceI;
import com.shty.collect.service.lnk.PeopleService;
import com.shty.collect.web.rest.dto.LinkedCountDayDto;
import com.shty.collect.web.rest.dto.LnkTaskDto;

@Controller
public class System_GetTaskController {
	@Autowired
	Linkedin_taskServiceI linkedin_taskServiceI;

	@Autowired
	PeopleService peopleservice;

	@RequestMapping(value = "getTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbLinkedinSysTask getLinkedinTask() {
		try {
			return linkedin_taskServiceI.getTask();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "getTaskByid", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LnkTaskDto> getTaskByid(@RequestParam(value = "id", required = true) int id) {
		try {
			return linkedin_taskServiceI.getTaskById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "getCountByDay", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public LinkedCountDayDto getCountByDay(@RequestParam(value = "number", required = true) String number) {
		return peopleservice.getCountByDay(number);
	}

	@RequestMapping(value = "getAllCountByDay", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LinkedCountDayDto> getAllCountByDay() {

		List<LinkedCountDayDto> list = new ArrayList<>();
		for (int i = 6; i >= 0; i--) {
			list.add(peopleservice.getCountByDay(Integer.toString(i)));
		}
		return list;
	}
}
