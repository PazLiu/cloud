package com.shty.collect.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbTwitterCount;
import com.shty.collect.domain.fb.FbTarget;
import com.shty.collect.service.System_Facebook_TaskServiceI;
import com.shty.collect.service.System_Facebook_TaskgroupService;
import com.shty.collect.service.fb.FbTarget_ServiceI;
import com.shty.collect.web.rest.dto.Task_StatusDto;
import com.smit.fb.http.FbHttpUtils;

/**
 * 任务Controller
 * 
 * @author Administrator
 *
 */
@Controller
public class System_Facebook_TaskController {
	@Resource
	System_Facebook_TaskServiceI system_Facebook_TaskServiceI;

	@Resource
	System_Facebook_TaskgroupService system_Facebook_TaskgroupService;

	@Resource
	FbTarget_ServiceI fbTarget_ServiceI;

	@RequestMapping(value = "addupdateTask")
	@ResponseBody
	public Map<String, String> addupdateTask(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "taskattributeid", required = true) String taskattributeid,
			@RequestParam(value = "taskgroupid", required = true) String taskgroupid,
			@RequestParam(value = "taskName", required = true) String taskName) {
		Map<String, String> map = new HashMap<>();
		TbFacebookSysTask tbFacebookSysTask = new TbFacebookSysTask();
		int status = 0;
		// id！=null 则为修改 如果为null则为添加
		if (!"".equals(id) && id != null) {
			tbFacebookSysTask.setId(Long.parseLong(id));
			tbFacebookSysTask.setTaskName(taskName);
			tbFacebookSysTask.setUrl(url);
			tbFacebookSysTask.setTaskattributeid(Long.parseLong(taskattributeid));
			tbFacebookSysTask.setTaskgroupid(Long.parseLong(taskgroupid));
			status = system_Facebook_TaskServiceI.updateTask(tbFacebookSysTask);
			if (status != 0) {
				map.put("result", "success");
			}
		} else {
			tbFacebookSysTask.setTaskName(taskName);
			tbFacebookSysTask.setUrl(url);
			tbFacebookSysTask.setTaskattributeid(Long.parseLong(taskattributeid));
			tbFacebookSysTask.setTaskgroupid(Long.parseLong(taskgroupid));
			tbFacebookSysTask.setTaskstatus("1");
			status = system_Facebook_TaskServiceI.addTask(tbFacebookSysTask);
			if (status != 0) {
				map.put("result", "success");
			}
		}
		return map;
	}

	@RequestMapping(value = "deleteTask")
	@ResponseBody
	public Map<String, String> deleteTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<>();
		int status = 0;
		for (int i = 0; i < id.length; i++) {
			status = system_Facebook_TaskServiceI.deleteTask(Long.parseLong(id[i]));
		}
		if (status != 0) {
			map.put("result", "success");
		}
		return map;
	}

	@RequestMapping(value = "select_taskUrl")
	@ResponseBody
	public TbFacebookSysTask select_taskUrl(@RequestParam(value = "url", required = true) String url) {
		TbFacebookSysTask tbFacebookSysTask = new TbFacebookSysTask();

		return tbFacebookSysTask;
	}

	/**
	 * 页面点击开始监控的方法 修改该分组的所有task任务的状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "MonitoringGroupTask")
	@ResponseBody
	public Map<String, String> MonitoringGroupTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Facebook_TaskServiceI.MonitoringGroupTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}

	/**
	 * 页面点击停止监控的方法 修改该分组的所有task任务的状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "StopMonitoringGroupTask")
	@ResponseBody
	public Map<String, String> StopMonitoringGroupTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Facebook_TaskServiceI.StopMonitoringGroupTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}

	/**
	 * 页面点击开始监控的方法 task任务的采集状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "MonitoringTask")
	@ResponseBody
	public Map<String, String> MonitoringTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Facebook_TaskServiceI.MonitoringTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}

	/**
	 * 页面点击停止监控的方法 task任务的采集状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "StopMonitoringTask")
	@ResponseBody
	public Map<String, String> StopMonitoringTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Facebook_TaskServiceI.StopMonitoringTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}

	/**
	 * 修改任务的状态
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "update_task_taskStatus")
	@ResponseBody
	public void update_task_taskStatus(Long id) {
		system_Facebook_TaskServiceI.update_task_taskStatus(id);
	}

	/**
	 * 页面树状图展示
	 */
	@RequestMapping(value = "getCountValue")
	@ResponseBody
	public List<TbTwitterCount> getCountValue() {
		List<TbTwitterCount> list = new ArrayList<>();
		for (int i = 6; i >= 0; i--) {
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, -i); // 设置为前一天
			dBefore = calendar.getTime(); // 得到前一天的时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
			String defaultStartDate = sdf.format(dBefore);
			TbTwitterCount tbTwitterCounts = system_Facebook_TaskServiceI.getFacebookCountValue(i);
			TbTwitterCount count = new TbTwitterCount();
			Integer number = 0;
			String date = defaultStartDate.substring(0, 10);
			if (tbTwitterCounts.getCount() != 0) {
				number = tbTwitterCounts.getCount();
			}
			count.setCount(number);
			count.setDateTime(date);
			list.add(count);
		}
		return list;
	}

	@RequestMapping(value = "getFacebookTask")
	@ResponseBody
	public TbFacebookSysTask getFacebookTask(String taskStatus) {
		TbFacebookSysTask tbFacebookSysTask = system_Facebook_TaskServiceI.getFacebookTask(taskStatus);
		if (tbFacebookSysTask != null) {
			Task_StatusDto status = new Task_StatusDto();
			status.setId(tbFacebookSysTask.getId());
			status.setTaskStatus("2");
			system_Facebook_TaskgroupService.updateGroup_task_static(status);
		}
		return tbFacebookSysTask;
	}

	@RequestMapping(value = "getFacebookthrid")
	@ResponseBody
	public List<TbFacebookSysTask> getFacebookthrid(@RequestParam(value = "third_id", required = true) int third_id) {
		List<TbFacebookSysTask> list_tbFacebookSysTask = system_Facebook_TaskServiceI.getFacebookthrid(third_id);
		for (TbFacebookSysTask tbFacebookSysTask : list_tbFacebookSysTask) {
			if (tbFacebookSysTask.getTaskstatus().equals("0")) {
				tbFacebookSysTask.setTaskstatus("未开始");
			} else if (tbFacebookSysTask.getTaskstatus().equals("1")) {
				tbFacebookSysTask.setTaskstatus("等待中");
			} else if (tbFacebookSysTask.getTaskstatus().equals("2")) {
				tbFacebookSysTask.setTaskstatus("正在运行");
			} else {
				tbFacebookSysTask.setTaskstatus("完成");
			}
		}
		return list_tbFacebookSysTask;
	}

}
