package com.shty.collect.web.rest;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.fb.test.FacebookTest;
import com.shty.collect.fb.test.Facebook_Status;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.service.System_NodeServiceI;
import com.shty.collect.service.System_RelationServiceI;
import com.shty.collect.service.System_Twitter_TaskServiceI;
import com.shty.collect.service.TbSystemHttpServiceI;
import com.shty.collect.service.twitter.ProfileService;
import com.shty.collect.service.twitter.TimelineService;
import com.shty.collect.twitter.task.TwitterNodeStatus;
import com.shty.collect.twitter.task.TwitterTask;

@Controller
public class System_Twitter_nodeController {
	@Resource
	private ProfileService profileService;
	@Resource
	private TimelineService timelineService;
	@Resource
	private System_Twitter_TaskServiceI system_Twitter_TaskServiceI;
	@Resource
	private System_NodeServiceI system_NodeServiceI;
	@Resource
	private System_RelationServiceI system_RelationServiceI;
	@Resource
	private System_AccountServiceI system_AccountServiceI;
	@Resource
	private TbSystemHttpServiceI tbSystemHttpServiceI;

	@RequestMapping(value = "startTwitterNode", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> startTwitterNode() throws Exception {
		Map<String, String> map = new HashMap<>();
		TwitterTask twitterTask = new TwitterTask(profileService, timelineService, system_Twitter_TaskServiceI,
				system_NodeServiceI, system_RelationServiceI, system_AccountServiceI, tbSystemHttpServiceI);
		if (TwitterNodeStatus.getNodeStatus().equals("100")) {
			System.out.println("节点未启动，启动节点！");
			twitterTask.twitter_status = false;
			twitterTask.start();
			if (twitterTask != null) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		} else {
			map.put("result", "success");
		}
		return map;
	}
	
	@RequestMapping(value = "stopTwitterNode", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> stopTwitterNode() throws Exception {
		Map<String, String> map = new HashMap<>();
		TwitterTask twitterTask = new TwitterTask(profileService, timelineService, system_Twitter_TaskServiceI,
				system_NodeServiceI, system_RelationServiceI, system_AccountServiceI, tbSystemHttpServiceI);
		if (!TwitterNodeStatus.getNodeStatus().equals("100")) {
			System.out.println("节点启动，关闭节点！");
			twitterTask.twitter_status = true;
			twitterTask.interrupt();// 退出阻塞状态
			twitterTask.join();
			if (twitterTask != null) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		} else {
			map.put("result", "success");
		}
		return map;
	}
	
	
	

	/**
	 * 节点状态查询
	 */
	@RequestMapping(value = "twitterNodeStatus", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> twitterNodeStatus() {
		Map<String, String> map = new HashMap<>();
		String nodeStatus = TwitterNodeStatus.getNodeStatus();
		if (nodeStatus.equals("100")) {
			nodeStatus = "未启动";
		} else if (nodeStatus.equals("200")) {
			nodeStatus = "空闲";
		} else if (nodeStatus.equals("300")) {
			nodeStatus = "采集中";
		} else if (nodeStatus.equals("500")) {
			nodeStatus = "采集完成";
		} else if (nodeStatus.equals("400")) {
			nodeStatus = "采集出错";
		}
		map.put("result", nodeStatus);
		return map;
	}
}
