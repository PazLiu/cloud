package com.shty.collect.web.rest;

import java.text.ParseException;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbTwitterSysTask;
import com.shty.collect.domain.TbTwitterSysTaskgroup;
import com.shty.collect.service.System_Twitter_TaskAttributeServiceI;
import com.shty.collect.service.System_Twitter_TaskGroupServiceI;
import com.shty.collect.service.System_Twitter_TaskServiceI;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupAttrDto;
import com.twitter.controller.TwitterSearch;
import com.twitter.pojo.Search;
import com.twitter.pojo.Timeline;

@Controller
public class System_Twitter_TaskController {
	@Resource
	System_Twitter_TaskServiceI system_Twitter_TaskServiceI;
	@Resource
	System_Twitter_TaskGroupServiceI system_Twitter_TaskGroupServiceI;

	@RequestMapping(value = "addTwitterTask")
	@ResponseBody
	public Map<String, String> addTwitterTask(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "url", required = true) String url,
			@RequestParam(value = "taskattributeid", required = true) String taskattributeid,
			@RequestParam(value = "taskgroupid", required = true) String taskgroupid,
			@RequestParam(value = "taskName", required = true) String taskName) {
		Map<String, String> map = new HashMap<>();
		TbTwitterSysTask tbTwitterSysTask = new TbTwitterSysTask();
		int status = 0;
		// id！=null 则为修改 如果为null则为添加
		if (!"".equals(id) && id != null) {
			tbTwitterSysTask.setId(Long.parseLong(id));
			tbTwitterSysTask.setUrl(url);
			tbTwitterSysTask.setTaskattributeid(Long.parseLong(taskattributeid));
			tbTwitterSysTask.setTaskgroupid(Long.parseLong(taskgroupid));
			tbTwitterSysTask.setTaskName(taskName);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			tbTwitterSysTask.setUpdatetime(df.format(new Date()));
			status = system_Twitter_TaskServiceI.updateTwitterTask(tbTwitterSysTask);
			if (status != 0) {
				map.put("result", "success");
			}
		} else {
			tbTwitterSysTask.setUrl(url);
			tbTwitterSysTask.setTaskattributeid(Long.parseLong(taskattributeid));
			tbTwitterSysTask.setTaskgroupid(Long.parseLong(taskgroupid));
			tbTwitterSysTask.setTaskName(taskName);
			status = system_Twitter_TaskServiceI.addTwitterTask(tbTwitterSysTask);
			if (status != 0) {
				map.put("result", "success");
			}
		}
		return map;
	}

	@RequestMapping(value = "deteleTwitterTask")
	@ResponseBody
	public Map<String, String> deteleTwitterTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<>();
		int status = 0;
		for (int i = 0; i < id.length; i++) {
			status = system_Twitter_TaskServiceI.deteleTwitterTask(Long.parseLong(id[i]));
		}
		if (status != 0) {
			map.put("result", "success");
		}
		return map;
	}

	@RequestMapping(value = "monitoringTwitterGroupTask")
	@ResponseBody
	public Map<String, String> monitoringTwitterGroupTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Twitter_TaskServiceI.monitoringTwitterGroupTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}
    //开始搜索
	@RequestMapping(value = "searchTwitterGroupTask")
	@ResponseBody
	public Map<String, String> searchTwitterGroupTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		Long groupId = null;
		String keyWord = null;
		Long taskAttrId = null;
		String searchList = null;
		String searchUrl = null;
		String searchNickName = null;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				//status = system_Twitter_TaskServiceI.monitoringTwitterGroupTask(Long.parseLong(id[i]));
				List<TbTwitterSysTaskgroup> tbTwitterSysTaskgroups = system_Twitter_TaskGroupServiceI.getGroupSearchKeyWord(Long.parseLong(id[i]));
				for(TbTwitterSysTaskgroup tb:tbTwitterSysTaskgroups){
					groupId = tb.getId();
					System.out.println("groupid:"+groupId);
					keyWord = tb.getKeyword();
					System.out.println("keyWord:"+keyWord);
					taskAttrId = tb.getTaskattributeid();
					System.out.println("taskAttrId:"+taskAttrId);
					TwitterSearch twitterSearch = new TwitterSearch();
					ObjectMapper mapper = new ObjectMapper();
					try {						
						searchList = twitterSearch.searchByKeyWord(keyWord);
						if(searchList!=null){
						List<Search> search =mapper.readValue(searchList,
								new TypeReference<List<Search>>() {
								});
						for(Search t:search){
							searchUrl =t.getSearchUserURL();
							searchNickName = t.getSearchUserNickName();
							TbTwitterSysTask tbTwitterSysTask = new TbTwitterSysTask();
							tbTwitterSysTask.setTaskattributeid(taskAttrId);
							tbTwitterSysTask.setTaskgroupid(groupId);
							tbTwitterSysTask.setTaskName(searchNickName.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", ""));
							tbTwitterSysTask.setUrl(searchUrl);
							status = system_Twitter_TaskServiceI.addTwitterTask(tbTwitterSysTask);
						}
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}			
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}
	@RequestMapping(value = "stopMonitoringTwitterGroupTask")
	@ResponseBody
	public Map<String, String> stopMonitoringGroupTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Twitter_TaskServiceI.stopMonitoringTwitterGroupTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}

	@RequestMapping(value = "monitoringTwitterTask")
	@ResponseBody
	public Map<String, String> monitoringTwitterTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Twitter_TaskServiceI.monitoringTwitterTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}

	@RequestMapping(value = "stopMonitoringTwitterTask")
	@ResponseBody
	public Map<String, String> stopMonitoringTwitterTask(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Twitter_TaskServiceI.stopMonitoringTwitterTask(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}
    /*
     * 获取符合日期星期的任务
     * 更改任务状态为 2
     */
	@RequestMapping(value = "selectTwitterTaskUrl")
	@ResponseBody
	public System_Twitter_TaskGroupAttrDto selectTwitterTaskUrl(String taskStatus) {
		List<System_Twitter_TaskGroupAttrDto> tbTwitter = system_Twitter_TaskGroupServiceI
				.selectTwitterTaskUrl(taskStatus);
		System_Twitter_TaskGroupAttrDto result = new System_Twitter_TaskGroupAttrDto();
		for (System_Twitter_TaskGroupAttrDto system_Twitter_TaskGroupAttrDto : tbTwitter) {
			int startTime = Integer.parseInt(dateToStamp(system_Twitter_TaskGroupAttrDto.getStartRegionTime()));
			int endTime = Integer.parseInt(dateToStamp(system_Twitter_TaskGroupAttrDto.getEndRegionTime()));
			// 获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			int currentTime = Integer.parseInt(dateToStamp(df.format(new Date())));// 获取当前时间并转时间戳格式
			if (currentTime >= startTime && currentTime <= endTime) {
				String weekDate[] = system_Twitter_TaskGroupAttrDto.getWeekChoice().split(" ");
				for (int i = 0; i < weekDate.length; i++) {
					String currentWeek = null;
					try {
						currentWeek = Integer.toString(dateToWeek(df.format(new Date())));
					} catch (Exception e) {
						// TODO: handle exception
					}
					if (currentWeek.equals(weekDate[i])) {
						result.setId(system_Twitter_TaskGroupAttrDto.getId());
						result.setTaskStatus(system_Twitter_TaskGroupAttrDto.getTaskStatus());
						result.setWeekChoice(system_Twitter_TaskGroupAttrDto.getWeekChoice());
						result.setStartRegionTime(system_Twitter_TaskGroupAttrDto.getStartRegionTime());
						result.setEndRegionTime(system_Twitter_TaskGroupAttrDto.getEndRegionTime());
						result.setGroupName(system_Twitter_TaskGroupAttrDto.getGroupName());
						result.setGroupType(system_Twitter_TaskGroupAttrDto.getGroupType());
						result.setPriority(system_Twitter_TaskGroupAttrDto.getPriority());
						result.setKeyWord(system_Twitter_TaskGroupAttrDto.getKeyWord());
						result.setTaskAttributeId(system_Twitter_TaskGroupAttrDto.getTaskAttributeId());
						result.setTaskGroupId(system_Twitter_TaskGroupAttrDto.getTaskGroupId());
						result.setTaskName(system_Twitter_TaskGroupAttrDto.getTaskName());
						result.setURL(system_Twitter_TaskGroupAttrDto.getURL());
						TbTwitterSysTask tbTwitterSysTask = new TbTwitterSysTask();
						tbTwitterSysTask.setId((long) system_Twitter_TaskGroupAttrDto.getId());
						tbTwitterSysTask.setTaskstatus("2");
						system_Twitter_TaskServiceI.updateTwitterTaskStatusStatic(tbTwitterSysTask);
					}
				}
			} else {
				continue;
			}
			break;
		}

		return result;
	}

	/*
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) {
		String res = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = simpleDateFormat.parse(s);
			long ts = date.getTime();
			res = String.valueOf(ts).substring(0, 10);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	/*
	 * 将当前日期转星期
	 */
	public static int dateToWeek(String pTime) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(df.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
}
