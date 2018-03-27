package com.shty.collect.web.rest;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbTwitterSysTask;
import com.shty.collect.service.System_Twitter_TaskServiceI;

@Controller
public class System_Twitter_GetTask {
	
	@Resource
	System_Twitter_TaskServiceI system_Twitter_TaskServiceI;

	@RequestMapping(value = "getTwitterTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbTwitterSysTask getTwitterTask() {
		return system_Twitter_TaskServiceI.getTwitterTask();
	};
}
