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

import com.shty.collect.domain.TbFacebookSysTaskgroup;
import com.shty.collect.service.System_Facebook_TaskgroupService;
import com.shty.collect.service.fb.FbTarget_ServiceI;
import com.shty.collect.service.impl.System_Facebook_TaskattributeService;
import com.shty.collect.web.rest.dto.Syste_Facebook_Task_Attribute_Group;
import com.shty.collect.web.rest.dto.System_GroupAnd_AttributeDto;
import com.shty.collect.web.rest.dto.Task_StatusDto;

/**
 * Facebook 分组Controller 分别有增删改查四个方法
 * 
 * @author Administrator
 *
 */
@Controller
public class System_Facebook_TaskgroupController {

	@Resource
	System_Facebook_TaskgroupService system_Facebook_TaskgroupService;

	@Resource
	System_Facebook_TaskattributeService system_Facebook_TaskattributeService;
	
	@Resource
	FbTarget_ServiceI fbTarget_ServiceI;

	@RequestMapping(value = "getTaskgroupAll")
	@ResponseBody
	public List<System_GroupAnd_AttributeDto> getTaskgroupAll(
			@RequestParam(value = "groupType", required = true) String groupType) {
		// 获取数据库返回数据
		List<System_GroupAnd_AttributeDto> list = new ArrayList<>();
		list = system_Facebook_TaskgroupService.getTaskgroupAll(groupType);
		List<System_GroupAnd_AttributeDto> resultlist = new ArrayList<>();
		for (System_GroupAnd_AttributeDto system_GroupAnd_AttributeDto : list) {
			System_GroupAnd_AttributeDto group = new System_GroupAnd_AttributeDto();
			group.setId(system_GroupAnd_AttributeDto.getId());
			// 解析星期
			String str_weekchoice[] = system_GroupAnd_AttributeDto.getWeekchoice().split(" ");
			String str = "";
			for (int i = 0; i < str_weekchoice.length; i++) {
				String str_weekchoice1 = str_weekchoice[i];
				switch (Integer.parseInt(str_weekchoice1)) {
				case 1:
					str = str + "一  ";
					break;
				case 2:
					str = str + "二  ";
					break;
				case 3:
					str = str + "三  ";
					break;
				case 4:
					str = str + "四  ";
					break;
				case 5:
					str = str + "五  ";
					break;
				case 6:
					str = str + "六  ";
					break;
				case 7:
					str = str + "天";
					break;
				}
			}
			group.setWeekchoice(str);
			group.setTaskattributeid(system_GroupAnd_AttributeDto.getTaskattributeid());
			group.setStartregiontime(system_GroupAnd_AttributeDto.getStartregiontime());
			group.setEndregiontime(system_GroupAnd_AttributeDto.getEndregiontime());
			group.setGroupname(system_GroupAnd_AttributeDto.getGroupname());
			if(system_GroupAnd_AttributeDto.getGroupstatus().equals("0")){
				group.setGroupstatus("未开始");
			}else if(system_GroupAnd_AttributeDto.getGroupstatus().equals("1")){
				group.setGroupstatus("正在等待");
			}else if(system_GroupAnd_AttributeDto.getGroupstatus().equals("2")){
				group.setGroupstatus("正在运行");
			}else{
				group.setGroupstatus("完成");
			}
			
			
			if(system_GroupAnd_AttributeDto.getGrouptype()!=null){
				if(system_GroupAnd_AttributeDto.getGrouptype().equals("1")){
					group.setGrouptype("监控");
				}else{
					group.setGrouptype("搜索");
				}
			}
			group.setPriority(system_GroupAnd_AttributeDto.getPriority());
			group.setKeyword(system_GroupAnd_AttributeDto.getKeyword());
			// 解析是否
			String downLoadAttribute = system_GroupAnd_AttributeDto.getDownloadattribute();
			String download[] = downLoadAttribute.split(" ");
			String Overview = "";
			String Friends = "";
			String Timelin = "";
			String Comments = "";
			String Likes = "";
			String Photos = "";
			for (int i = 0; i < download.length; i++) {
				String str_download = download[i];
				switch (Integer.parseInt(str_download)) {
				case 1:
					Overview = "下载";
					break;
				case 2:
					Friends = "下载";
					break;
				case 3:
					Timelin = "下载";
					break;
				case 4:
					Comments = "下载";
					break;
				case 5:
					Likes = "下载";
					break;
				case 6:
					Photos = "下载";
					break;
				}
			}
			if (Overview.equals("")) {
				Overview = "不下载";
			}
			if (Friends.equals("")) {
				Friends = "不下载";
			}
			if (Timelin.equals("")) {
				Timelin = "不下载";
			}
			if (Comments.equals("")) {
				Comments = "不下载";
			}
			if (Likes.equals("")) {
				Likes = "不下载";
			}
			if (Photos.equals("")) {
				Photos = "不下载";
			}
			group.setOverview(Overview);
			group.setFriends(Friends);
			group.setTimelin(Timelin);
			group.setComments(Comments);
			group.setLikes(Likes);
			group.setPhotos(Photos);
			resultlist.add(group);
		}
		return resultlist;
	}

	@RequestMapping(value = "getTaskgroupAll1")
	@ResponseBody
	public List<System_GroupAnd_AttributeDto> getTaskgroupAll1() {
		// 获取数据库返回数据
		List<System_GroupAnd_AttributeDto> list = new ArrayList<>();
		list = system_Facebook_TaskgroupService.getTaskgroupAll1();
		List<System_GroupAnd_AttributeDto> resultlist = new ArrayList<>();
		for (System_GroupAnd_AttributeDto system_GroupAnd_AttributeDto : list) {
			System_GroupAnd_AttributeDto group = new System_GroupAnd_AttributeDto();
			group.setId(system_GroupAnd_AttributeDto.getId());
			// 解析星期
			String str_weekchoice[] = system_GroupAnd_AttributeDto.getWeekchoice().split(" ");
			String str = "";
			for (int i = 0; i < str_weekchoice.length; i++) {
				String str_weekchoice1 = str_weekchoice[i];
				switch (Integer.parseInt(str_weekchoice1)) {
				case 1:
					str = str + "一  ";
					break;
				case 2:
					str = str + "二  ";
					break;
				case 3:
					str = str + "三  ";
					break;
				case 4:
					str = str + "四  ";
					break;
				case 5:
					str = str + "五  ";
					break;
				case 6:
					str = str + "六  ";
					break;
				case 7:
					str = str + "天";
					break;
				}
			}
			group.setWeekchoice(str);
			group.setTaskattributeid(system_GroupAnd_AttributeDto.getTaskattributeid());
			group.setStartregiontime(system_GroupAnd_AttributeDto.getStartregiontime());
			group.setEndregiontime(system_GroupAnd_AttributeDto.getEndregiontime());
			group.setGroupname(system_GroupAnd_AttributeDto.getGroupname());
			if(system_GroupAnd_AttributeDto.getGroupstatus().equals("0")){
				group.setGroupstatus("未开始");
			}else if(system_GroupAnd_AttributeDto.getGroupstatus().equals("1")){
				group.setGroupstatus("正在等待");
			}else if(system_GroupAnd_AttributeDto.getGroupstatus().equals("2")){
				group.setGroupstatus("正在运行");
			}else{
				group.setGroupstatus("完成");
			}
			if(system_GroupAnd_AttributeDto.getGrouptype()!=null){
				if(system_GroupAnd_AttributeDto.getGrouptype().equals("1")){
					group.setGrouptype("监控");
				}else{
					group.setGrouptype("搜索");
				}
			}
			group.setPriority(system_GroupAnd_AttributeDto.getPriority());
			group.setKeyword(system_GroupAnd_AttributeDto.getKeyword());
			// 解析是否
			String downLoadAttribute = system_GroupAnd_AttributeDto.getDownloadattribute();
			String download[] = downLoadAttribute.split(" ");
			String Overview = "";
			String Friends = "";
			String Timelin = "";
			String Comments = "";
			String Likes = "";
			String Photos = "";
			for (int i = 0; i < download.length; i++) {
				String str_download = download[i];
				switch (Integer.parseInt(str_download)) {
				case 1:
					Overview = "下载";
					break;
				case 2:
					Friends = "下载";
					break;
				case 3:
					Timelin = "下载";
					break;
				case 4:
					Comments = "下载";
					break;
				case 5:
					Likes = "下载";
					break;
				case 6:
					Photos = "下载";
					break;
				}
			}
			if (Overview.equals("")) {
				Overview = "不下载";
			}
			if (Friends.equals("")) {
				Friends = "不下载";
			}
			if (Timelin.equals("")) {
				Timelin = "不下载";
			}
			if (Comments.equals("")) {
				Comments = "不下载";
			}
			if (Likes.equals("")) {
				Likes = "不下载";
			}
			if (Photos.equals("")) {
				Photos = "不下载";
			}
			group.setOverview(Overview);
			group.setFriends(Friends);
			group.setTimelin(Timelin);
			group.setComments(Comments);
			group.setLikes(Likes);
			group.setPhotos(Photos);
			resultlist.add(group);
		}
		return resultlist;
	}

	@RequestMapping(value = "getTaskgroupKeyWord")
	@ResponseBody
	public System_GroupAnd_AttributeDto getTaskgroupKeyWord() {
		// 获取数据库返回数据
		List<System_GroupAnd_AttributeDto> list = system_Facebook_TaskgroupService.getTaskgroupKeyWord();
		System_GroupAnd_AttributeDto group = new System_GroupAnd_AttributeDto();
		for (System_GroupAnd_AttributeDto system_GroupAnd_AttributeDto : list) {
			group.setId(system_GroupAnd_AttributeDto.getId());
			// 解析星期
			String str_weekchoice[] = system_GroupAnd_AttributeDto.getWeekchoice().split(" ");
			String str = "";
			for (int i = 0; i < str_weekchoice.length; i++) {
				String str_weekchoice1 = str_weekchoice[i];
				switch (Integer.parseInt(str_weekchoice1)) {
				case 1:
					str = str + "一  ";
					break;
				case 2:
					str = str + "二  ";
					break;
				case 3:
					str = str + "三  ";
					break;
				case 4:
					str = str + "四  ";
					break;
				case 5:
					str = str + "五  ";
					break;
				case 6:
					str = str + "六  ";
					break;
				case 7:
					str = str + "天";
					break;
				}
			}
			group.setWeekchoice(str);
			group.setTaskattributeid(system_GroupAnd_AttributeDto.getTaskattributeid());
			group.setStartregiontime(system_GroupAnd_AttributeDto.getStartregiontime());
			group.setEndregiontime(system_GroupAnd_AttributeDto.getEndregiontime());
			group.setGroupname(system_GroupAnd_AttributeDto.getGroupname());
			group.setGroupstatus(system_GroupAnd_AttributeDto.getGroupstatus());
			group.setGrouptype(system_GroupAnd_AttributeDto.getGrouptype());
			group.setPriority(system_GroupAnd_AttributeDto.getPriority());
			group.setKeyword(system_GroupAnd_AttributeDto.getKeyword());
			// 解析是否
			String downLoadAttribute = system_GroupAnd_AttributeDto.getDownloadattribute();
			String download[] = downLoadAttribute.split(" ");
			String Overview = "";
			String Friends = "";
			String Timelin = "";
			String Comments = "";
			String Likes = "";
			String Photos = "";
			for (int i = 0; i < download.length; i++) {
				String str_download = download[i];
				switch (Integer.parseInt(str_download)) {
				case 1:
					Overview = "下载";
					break;
				case 2:
					Friends = "下载";
					break;
				case 3:
					Timelin = "下载";
					break;
				case 4:
					Comments = "下载";
					break;
				case 5:
					Likes = "下载";
					break;
				case 6:
					Photos = "下载";
					break;
				}
			}
			if (Overview.equals("")) {
				Overview = "不下载";
			}
			if (Friends.equals("")) {
				Friends = "不下载";
			}
			if (Timelin.equals("")) {
				Timelin = "不下载";
			}
			if (Comments.equals("")) {
				Comments = "不下载";
			}
			if (Likes.equals("")) {
				Likes = "不下载";
			}
			if (Photos.equals("")) {
				Photos = "不下载";
			}
			group.setOverview(Overview);
			group.setFriends(Friends);
			group.setTimelin(Timelin);
			group.setComments(Comments);
			group.setLikes(Likes);
			group.setPhotos(Photos);
		}
		if (group.getId() != null) {
			TbFacebookSysTaskgroup taskGroup = new TbFacebookSysTaskgroup();
			taskGroup.setId(group.getId());
			taskGroup.setGroupname(group.getGroupname());
			taskGroup.setGroupstatus("2");
			taskGroup.setGrouptype(group.getGrouptype());
			taskGroup.setPriority(group.getPriority());
			taskGroup.setKeyword(group.getKeyword());
			taskGroup.setTaskattributeid(Long.parseLong(group.getTaskattributeid()));
			system_Facebook_TaskgroupService.updateGroup(taskGroup);
		}
		return group;
	}

	@RequestMapping(value = "addGroup")
	@ResponseBody
	public Map<String, String> addGroup(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "groupname", required = true) String groupname,
			@RequestParam(value = "keyword", required = true) String keyword,
			@RequestParam(value = "priority", required = true) String priority,
			@RequestParam(value = "attribute", required = true) String attribute) {
		Map<String, String> map = new HashMap<String, String>();
		TbFacebookSysTaskgroup group = new TbFacebookSysTaskgroup();
		if (null != id && !"".equals(id)) {
			group.setId(Long.parseLong(id));
			group.setGroupname(groupname);
			group.setGroupstatus("0");
			if (keyword != null && !"".equals(keyword)) {
				group.setKeyword(keyword);
				group.setGrouptype("2");
			} else {
				group.setKeyword("");
				group.setGrouptype("1");
			}
			group.setPriority(Integer.parseInt(priority));
			group.setTaskattributeid(Long.parseLong(attribute));
			int i = system_Facebook_TaskgroupService.updateGroup(group);
			if (!"".equals(i) && i != 0) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		} else {
			group.setGroupname(groupname);
			group.setGroupstatus("0");
			if (keyword != null && !"".equals(keyword)) {
				group.setKeyword(keyword);
				group.setGrouptype("2");
			} else {
				group.setKeyword("");
				group.setGrouptype("1");
			}
			group.setPriority(Integer.parseInt(priority));
			group.setTaskattributeid(Long.parseLong(attribute));
			int i = system_Facebook_TaskgroupService.addGroup(group);
			if (!"".equals(i) && i != 0) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		}
		return map;
	}

	/**
	 * 任务组的删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteGroup")
	@ResponseBody
	public Map<String, String> deleteGroup(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_Facebook_TaskgroupService.deleteGroup(Long.parseLong(id[i]));
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
	 * 任务组的开始搜索
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateGroupStatus")
	@ResponseBody
	public Map<String, String> updateGroupStatus(@RequestParam(value = "id", required = true) String id[]){
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				Task_StatusDto task_statusdto = new Task_StatusDto();
				task_statusdto.setId(Long.parseLong(id[i]));
				task_statusdto.setTaskStatus("1");
				status = system_Facebook_TaskgroupService.updateGroupStatus(task_statusdto);
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
	 * 任务表查询，根据ID查询任务表与任务组表 ID想同的数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getByidGroup")
	@ResponseBody
	public List<Syste_Facebook_Task_Attribute_Group> getByidGroup(
			@RequestParam(value = "id", required = true) Long id) {
		List<Syste_Facebook_Task_Attribute_Group> facebook_task_attribute_group = system_Facebook_TaskgroupService
				.getByidGroup(id);
		List<Syste_Facebook_Task_Attribute_Group> result_facebook_task_attribute_group = new ArrayList<>();
		for (Syste_Facebook_Task_Attribute_Group syste_Facebook_Task_Attribute_Group : facebook_task_attribute_group) {
			Syste_Facebook_Task_Attribute_Group result = new Syste_Facebook_Task_Attribute_Group();
			result.setId(syste_Facebook_Task_Attribute_Group.getId());
			result.setFBTarget_id(syste_Facebook_Task_Attribute_Group.getFBTarget_id());
			result.setGroupname(syste_Facebook_Task_Attribute_Group.getGroupname());
			result.setGrouptype(syste_Facebook_Task_Attribute_Group.getGrouptype());
			result.setUrl(syste_Facebook_Task_Attribute_Group.getUrl());
			if(syste_Facebook_Task_Attribute_Group.getTaskstatus().equals("0")){
				result.setTaskstatus("未开始");
			}else if(syste_Facebook_Task_Attribute_Group.getTaskstatus().equals("1")){
				result.setTaskstatus("正在等待");
			}else if(syste_Facebook_Task_Attribute_Group.getTaskstatus().equals("2")){
				result.setTaskstatus("正在运行");
			}else{
				result.setTaskstatus("完成");
			}
			result.setPriority(syste_Facebook_Task_Attribute_Group.getPriority());
			result.setKeyword(syste_Facebook_Task_Attribute_Group.getKeyword());
			result.setStartregiontime(syste_Facebook_Task_Attribute_Group.getStartregiontime());
			result.setEndregiontime(syste_Facebook_Task_Attribute_Group.getEndregiontime());
			result.setTaskName(syste_Facebook_Task_Attribute_Group.getTaskName());
			// 解析星期
			String str_weekchoice[] = syste_Facebook_Task_Attribute_Group.getWeekchoice().split(" ");
			String str = "";
			for (int i = 0; i < str_weekchoice.length; i++) {
				String str_weekchoice1 = str_weekchoice[i];
				switch (Integer.parseInt(str_weekchoice1)) {
				case 1:
					str = str + "一  ";
					break;
				case 2:
					str = str + "二  ";
					break;
				case 3:
					str = str + "三  ";
					break;
				case 4:
					str = str + "四  ";
					break;
				case 5:
					str = str + "五  ";
					break;
				case 6:
					str = str + "六  ";
					break;
				case 7:
					str = str + "天";
					break;
				}
			}
			result.setWeekchoice(str);
			String downLoadAttribute = syste_Facebook_Task_Attribute_Group.getDownloadattribute();
			String download[] = downLoadAttribute.split(" ");
			String Overview = "";
			String Friends = "";
			String Timelin = "";
			String Comments = "";
			String Likes = "";
			String Photos = "";
			for (int i = 0; i < download.length; i++) {
				String str_download = download[i];
				switch (Integer.parseInt(str_download)) {
				case 1:
					Overview = "下载";
					break;
				case 2:
					Friends = "下载";
					break;
				case 3:
					Timelin = "下载";
					break;
				case 4:
					Comments = "下载";
					break;
				case 5:
					Likes = "下载";
					break;
				case 6:
					Photos = "下载";
					break;
				}
			}
			if (Overview.equals("")) {
				Overview = "不下载";
			}
			if (Friends.equals("")) {
				Friends = "不下载";
			}
			if (Timelin.equals("")) {
				Timelin = "不下载";
			}
			if (Comments.equals("")) {
				Comments = "不下载";
			}
			if (Likes.equals("")) {
				Likes = "不下载";
			}
			if (Photos.equals("")) {
				Photos = "不下载";
			}
			result.setOverview(Overview);
			result.setFriends(Friends);
			result.setTimelin(Timelin);
			result.setComments(Comments);
			result.setLikes(Likes);
			result.setPhotos(Photos);
			result.setTaskattributeid(syste_Facebook_Task_Attribute_Group.getTaskattributeid());
			result.setTaskgroupid(syste_Facebook_Task_Attribute_Group.getTaskgroupid());
			result_facebook_task_attribute_group.add(result);
		}

		return result_facebook_task_attribute_group;
	}
	
	
	/**
	 * 任务表查询，根据ID查询任务表与任务组表 ID想同的数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getGroupByid")
	@ResponseBody
	public List<Syste_Facebook_Task_Attribute_Group> getGroupByid(
			@RequestParam(value = "id", required = true) Long id) {
		List<Syste_Facebook_Task_Attribute_Group> facebook_task_attribute_group = system_Facebook_TaskgroupService
				.getGroupByid(id);
		List<Syste_Facebook_Task_Attribute_Group> result_facebook_task_attribute_group = new ArrayList<>();
		for (Syste_Facebook_Task_Attribute_Group syste_Facebook_Task_Attribute_Group : facebook_task_attribute_group) {
			Syste_Facebook_Task_Attribute_Group result = new Syste_Facebook_Task_Attribute_Group();
			result.setId(syste_Facebook_Task_Attribute_Group.getId());
			result.setFBTarget_id(syste_Facebook_Task_Attribute_Group.getFBTarget_id());
			result.setGroupname(syste_Facebook_Task_Attribute_Group.getGroupname());
			result.setGrouptype(syste_Facebook_Task_Attribute_Group.getGrouptype());
			result.setUrl(syste_Facebook_Task_Attribute_Group.getUrl());
			if(syste_Facebook_Task_Attribute_Group.getTaskstatus().equals("0")){
				result.setTaskstatus("未开始");
			}else if(syste_Facebook_Task_Attribute_Group.getTaskstatus().equals("1")){
				result.setTaskstatus("正在等待");
			}else if(syste_Facebook_Task_Attribute_Group.getTaskstatus().equals("2")){
				result.setTaskstatus("正在运行");
			}else{
				result.setTaskstatus("完成");
			}
			result.setPriority(syste_Facebook_Task_Attribute_Group.getPriority());
			result.setKeyword(syste_Facebook_Task_Attribute_Group.getKeyword());
			result.setStartregiontime(syste_Facebook_Task_Attribute_Group.getStartregiontime());
			result.setEndregiontime(syste_Facebook_Task_Attribute_Group.getEndregiontime());
			result.setTaskName(syste_Facebook_Task_Attribute_Group.getTaskName());
			// 解析星期
			String str_weekchoice[] = syste_Facebook_Task_Attribute_Group.getWeekchoice().split(" ");
			String str = "";
			for (int i = 0; i < str_weekchoice.length; i++) {
				String str_weekchoice1 = str_weekchoice[i];
				switch (Integer.parseInt(str_weekchoice1)) {
				case 1:
					str = str + "一  ";
					break;
				case 2:
					str = str + "二  ";
					break;
				case 3:
					str = str + "三  ";
					break;
				case 4:
					str = str + "四  ";
					break;
				case 5:
					str = str + "五  ";
					break;
				case 6:
					str = str + "六  ";
					break;
				case 7:
					str = str + "天";
					break;
				}
			}
			result.setWeekchoice(str);
			String downLoadAttribute = syste_Facebook_Task_Attribute_Group.getDownloadattribute();
			String download[] = downLoadAttribute.split(" ");
			String Overview = "";
			String Friends = "";
			String Timelin = "";
			String Comments = "";
			String Likes = "";
			String Photos = "";
			for (int i = 0; i < download.length; i++) {
				String str_download = download[i];
				switch (Integer.parseInt(str_download)) {
				case 1:
					Overview = "下载";
					break;
				case 2:
					Friends = "下载";
					break;
				case 3:
					Timelin = "下载";
					break;
				case 4:
					Comments = "下载";
					break;
				case 5:
					Likes = "下载";
					break;
				case 6:
					Photos = "下载";
					break;
				}
			}
			if (Overview.equals("")) {
				Overview = "不下载";
			}
			if (Friends.equals("")) {
				Friends = "不下载";
			}
			if (Timelin.equals("")) {
				Timelin = "不下载";
			}
			if (Comments.equals("")) {
				Comments = "不下载";
			}
			if (Likes.equals("")) {
				Likes = "不下载";
			}
			if (Photos.equals("")) {
				Photos = "不下载";
			}
			result.setOverview(Overview);
			result.setFriends(Friends);
			result.setTimelin(Timelin);
			result.setComments(Comments);
			result.setLikes(Likes);
			result.setPhotos(Photos);
			result.setTaskattributeid(syste_Facebook_Task_Attribute_Group.getTaskattributeid());
			result.setTaskgroupid(syste_Facebook_Task_Attribute_Group.getTaskgroupid());
			result_facebook_task_attribute_group.add(result);
		}

		return result_facebook_task_attribute_group;
	}
	

	/**
	 * 获取任务的接口 查询任务表中taskStatus 为1的任务 默认新建任务为0，未加入任务采集则为0
	 * 
	 * @return
	 */
	@RequestMapping(value = "select_Task")
	@ResponseBody
	public Syste_Facebook_Task_Attribute_Group select_Task(String task_static) {
		List<Syste_Facebook_Task_Attribute_Group> tbFacebook = system_Facebook_TaskgroupService
				.select_Task(task_static);
		Syste_Facebook_Task_Attribute_Group result = new Syste_Facebook_Task_Attribute_Group();

		for (Syste_Facebook_Task_Attribute_Group syste_Facebook_Task_Attribute_Group : tbFacebook) {
			// 首先判断是否在日期时间内
			Integer Starttime = Integer.parseInt(getTime(syste_Facebook_Task_Attribute_Group.getStartregiontime()));
			Integer Endtime = Integer.parseInt(getTime(syste_Facebook_Task_Attribute_Group.getEndregiontime()));
			// 当前时间
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Integer Loadtime = Integer.parseInt(getTime(df.format(date)));
			// 当前时间是否大于开始时间 是否小于结束时间
			if (Loadtime >= Starttime && Loadtime <= Endtime) {
				// 解析星期
				String str_weekchoice[] = syste_Facebook_Task_Attribute_Group.getWeekchoice().split(" ");
				String str = "";
				for (int i = 0; i < str_weekchoice.length; i++) {
					String str_weekchoice1 = str_weekchoice[i];
					switch (Integer.parseInt(str_weekchoice1)) {
					case 1:
						str = str + "一  ";
						break;
					case 2:
						str = str + "二  ";
						break;
					case 3:
						str = str + "三  ";
						break;
					case 4:
						str = str + "四  ";
						break;
					case 5:
						str = str + "五  ";
						break;
					case 6:
						str = str + "六  ";
						break;
					case 7:
						str = str + "天";
						break;
					}
				}
				String split_wekchoice[] = str.split(" ");
				for (int j = 0; j < split_wekchoice.length; j++) {
					String wekchoice = getWeekOfDate(date);
					if (wekchoice.equals(split_wekchoice[j])) {
						result.setWeekchoice(str);
						result.setId(syste_Facebook_Task_Attribute_Group.getId());
						result.setGroupname(syste_Facebook_Task_Attribute_Group.getGroupname());
						result.setGrouptype(syste_Facebook_Task_Attribute_Group.getGrouptype());
						result.setUrl(syste_Facebook_Task_Attribute_Group.getUrl());
						result.setTaskstatus(syste_Facebook_Task_Attribute_Group.getTaskstatus());
						result.setPriority(syste_Facebook_Task_Attribute_Group.getPriority());
						result.setKeyword(syste_Facebook_Task_Attribute_Group.getKeyword());
						result.setStartregiontime(syste_Facebook_Task_Attribute_Group.getStartregiontime());
						result.setEndregiontime(syste_Facebook_Task_Attribute_Group.getEndregiontime());

						String downLoadAttribute = syste_Facebook_Task_Attribute_Group.getDownloadattribute();
						String download[] = downLoadAttribute.split(" ");
						String Overview = "";
						String Friends = "";
						String Timelin = "";
						String Comments = "";
						String Likes = "";
						String Photos = "";
						for (int i = 0; i < download.length; i++) {
							String str_download = download[i];
							switch (Integer.parseInt(str_download)) {
							case 1:
								Overview = "true";
								break;
							case 2:
								Friends = "true";
								break;
							case 3:
								Timelin = "true";
								break;
							case 4:
								Comments = "true";
								break;
							case 5:
								Likes = "true";
								break;
							case 6:
								Photos = "true";
								break;
							}
						}
						if (Overview.equals("")) {
							Overview = "false";
						}
						if (Friends.equals("")) {
							Friends = "false";
						}
						if (Timelin.equals("")) {
							Timelin = "false";
						}
						if (Comments.equals("")) {
							Comments = "false";
						}
						if (Likes.equals("")) {
							Likes = "false";
						}
						if (Photos.equals("")) {
							Photos = "false";
						}
						result.setOverview(Overview);
						result.setFriends(Friends);
						result.setTimelin(Timelin);
						result.setComments(Comments);
						result.setLikes(Likes);
						result.setPhotos(Photos);
						result.setTaskattributeid(syste_Facebook_Task_Attribute_Group.getTaskattributeid());
						result.setTaskgroupid(syste_Facebook_Task_Attribute_Group.getTaskgroupid());
						Task_StatusDto status = new Task_StatusDto();
						status.setId((long) syste_Facebook_Task_Attribute_Group.getId());
						status.setTaskStatus("2");
						system_Facebook_TaskgroupService.updateGroup_task_static(status);

					}
				}
			} else {
				continue;
			}
			break;
		}

		return result;
	}

	/**
	 * // 中文日期转时间戳
	 * 
	 * @param pubdate
	 * @return
	 */
	private static String getTime(String pubdate) {
		// TODO Auto-generated method stub
		String re_time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d;
		try {
			d = sdf.parse(pubdate);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re_time;
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @param dt
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
}
