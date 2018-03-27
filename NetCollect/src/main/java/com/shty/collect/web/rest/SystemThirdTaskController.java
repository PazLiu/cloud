package com.shty.collect.web.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbSysThirdTask;
import com.shty.collect.service.ThridTaskServiceI;
import com.shty.collect.web.rest.dto.LnkTaskDto;

@Controller
@RequestMapping(value = "/third")
public class SystemThirdTaskController {

	@Autowired
	ThridTaskServiceI thirdService;

	@RequestMapping(value = "insertTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> insertTask(@RequestParam(value = "tasktype", required = true) String tasktype,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "userid", required = true) String userid) {

		Map<String, String> map = new HashMap<String, String>();
		try {

			TbSysThirdTask task = new TbSysThirdTask();
			task.setKeyword(keyword);
			task.setTasktype(tasktype);
			task.setUrl(url);
			task.setUserid(userid);
			task.setTaskstatus("0");
			task.setCreattime(new Timestamp(System.currentTimeMillis()).toString());
			task.setUpdatetime(new Timestamp(System.currentTimeMillis()).toString());
			thirdService.insertOneTask(task);
			map.put("result", "success");
		} catch (Exception e) {
			map.put("result", "0");
		}
		return map;
	}

	@RequestMapping(value = "getThirdLnkTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LnkTaskDto> getThirdLnkTask(@RequestParam(value = "priority", required = true) String priority) {
		List<LnkTaskDto> tsTask = thirdService.getThirdLnkTask(priority);
		return tsTask;
	}

	@RequestMapping(value = "getThirdTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbSysThirdTask getThirdTask(@RequestParam(value = "tasktype", required = true) String tasktype) {
		TbSysThirdTask tsTask = thirdService.getOneTask(tasktype);
		if (tsTask != null) {
			tsTask.setTaskstatus("5");
			thirdService.updateTask(tsTask);
		}
		return tsTask;
	}

	@RequestMapping(value = "getThirdTaskResultByUserId", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TbSysThirdTask> getThirdTaskResultByUserId(
			@RequestParam(value = "userid", required = true) String userid) {

		List<TbSysThirdTask> list = new ArrayList<TbSysThirdTask>();

		return list;
		// TbSysThirdTask tsTask = thirdService.getOneTask();
		// if (tsTask != null) {
		// tsTask.setTaskstatus("5");
		// thirdService.updateTask(tsTask);
		// }
		// return tsTask;
	}

	@RequestMapping(value = "getAllThirdTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TbSysThirdTask> getAllThirdTask() {
		List<TbSysThirdTask> list = thirdService.getAllThirdTask();

		return list;
	}
}