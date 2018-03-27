package com.shty.collect.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shty.collect.domain.TbTwitterSysTaskgroup;
import com.shty.collect.service.System_Twitter_TaskGroupServiceI;
import com.shty.collect.web.rest.dto.Syste_Facebook_Task_Attribute_Group;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupAttrDto;
import com.shty.collect.web.rest.dto.System_Twitter_TaskGroupDto;

@Controller
public class System_Twitter_TaskGroupController {
	@Resource
	System_Twitter_TaskGroupServiceI system_Twitter_TaskGroupServiceI;

	@RequestMapping(value = "getAllGroupAttribute")
	@ResponseBody
	public List<System_Twitter_TaskGroupDto> getAllGroupAttribute() {

		return system_Twitter_TaskGroupServiceI.getAllGroupAttribute();
	}
	//搜索页面获取数据
	@RequestMapping(value = "getAllGroupAttributeSearch")
	@ResponseBody
	public List<System_Twitter_TaskGroupDto> getAllGroupAttributeSearch() {

		return system_Twitter_TaskGroupServiceI.getAllGroupAttributeSearch();
	}

	@RequestMapping(value = "addGroupAttribute")
	@ResponseBody
	public Map<String, String> addGroupAttribute(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "groupname", required = true) String groupname,
			@RequestParam(value = "keyword", required = true) String keyword,
			@RequestParam(value = "priority", required = true) String priority,
			@RequestParam(value = "attribute", required = true) String attribute) {
		Map<String, String> map = new HashMap<String, String>();
        TbTwitterSysTaskgroup tbTwitterSysTaskgroup = new TbTwitterSysTaskgroup();
        if (null != id && !"".equals(id)) {
        	tbTwitterSysTaskgroup.setId(Long.parseLong(id));
        	tbTwitterSysTaskgroup.setGroupname(groupname);
        	tbTwitterSysTaskgroup.setGroupstatus("待采集");
			if (keyword != null && !"".equals(keyword)) {
				tbTwitterSysTaskgroup.setKeyword(keyword);
				tbTwitterSysTaskgroup.setGrouptype("搜索");
			} else {
				tbTwitterSysTaskgroup.setKeyword("");
				tbTwitterSysTaskgroup.setGrouptype("监控");
			}
			tbTwitterSysTaskgroup.setPriority(Integer.parseInt(priority));
			tbTwitterSysTaskgroup.setTaskattributeid(Long.parseLong(attribute));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			tbTwitterSysTaskgroup.setUpdatetime(df.format(new Date()));
			int i = system_Twitter_TaskGroupServiceI.updateGroupAttribute(tbTwitterSysTaskgroup);
			if (!"".equals(i) && i != 0) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		} else {
			tbTwitterSysTaskgroup.setGroupname(groupname);
			tbTwitterSysTaskgroup.setGroupstatus("待采集");
			if (keyword != null && !"".equals(keyword)) {
				tbTwitterSysTaskgroup.setKeyword(keyword);
				tbTwitterSysTaskgroup.setGrouptype("搜索");
			} else {
				tbTwitterSysTaskgroup.setKeyword("");
				tbTwitterSysTaskgroup.setGrouptype("监控");
			}
			tbTwitterSysTaskgroup.setPriority(Integer.parseInt(priority));
			tbTwitterSysTaskgroup.setTaskattributeid(Long.parseLong(attribute));
			int i = system_Twitter_TaskGroupServiceI.addGroupAttribute(tbTwitterSysTaskgroup);
			if (!"".equals(i) && i != 0) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		}
		return map;
	}
	
	@RequestMapping(value = "deleteGroupAttribute")
	@ResponseBody
	public Map<String, String> deleteGroupAttribute(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Twitter_TaskGroupServiceI.deleteGroupAttribute(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}

		return map;
	}
	
	@RequestMapping(value = "getGroupDetailByid")
	@ResponseBody
	public List<System_Twitter_TaskGroupAttrDto> getGroupDetailByid(
			@RequestParam(value = "id", required = true) Long id) {
		return system_Twitter_TaskGroupServiceI.getGroupDetailByid(id);
	}
}
