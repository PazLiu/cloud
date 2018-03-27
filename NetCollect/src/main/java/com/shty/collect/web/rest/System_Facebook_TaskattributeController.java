package com.shty.collect.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbFacebookSysTaskattribute;
import com.shty.collect.service.impl.System_Facebook_TaskattributeService;
import com.shty.collect.web.rest.dto.System_Facebook_Taskattribute_Dto;

/**
 * 任务属性Controller
 * 分为 增 删 改 查四个方法
 * @author Administrator
 *
 */
@Controller
public class System_Facebook_TaskattributeController {
	@Resource
	System_Facebook_TaskattributeService system_Facebook_TaskattributeService;
	
	/**
	 * 查询Facebook采集属性表中的全部数据
	 * @return
	 */
	@RequestMapping(value = "getAllattribute")
	@ResponseBody
	public List<System_Facebook_Taskattribute_Dto> getAllattribute(){
		List<System_Facebook_Taskattribute_Dto> System_Facebook_Taskattribute_Dto = new ArrayList<>();
		List<TbFacebookSysTaskattribute> TbFacebookSysTaskattribute = system_Facebook_TaskattributeService.getAllattribute();
		for (TbFacebookSysTaskattribute tbFacebookSysTaskattribute2 : TbFacebookSysTaskattribute) {
			System_Facebook_Taskattribute_Dto Facebook_Taskattribute_Dto = new System_Facebook_Taskattribute_Dto();
			Facebook_Taskattribute_Dto.setId(tbFacebookSysTaskattribute2.getId());
			String weekchoice = tbFacebookSysTaskattribute2.getWeekchoice();
			String str_weekchoice[] = weekchoice.split(" ");
			String str = "";
			for (int i = 0; i < str_weekchoice.length; i++) {
				String str_weekchoice1 =str_weekchoice[i];
				switch(Integer.parseInt(str_weekchoice1)){
					case 1 : str = str+"一  ";
					break;
					case 2 : str = str+"二  ";
					break;
					case 3 : str = str+"三  ";
					break;
					case 4 : str = str+"四  ";
					break;
					case 5 : str = str+"五  ";
					break;
					case 6 : str = str+"六  ";
					break;
					case 7 : str = str+"天";
					break;
				}
			}
			Facebook_Taskattribute_Dto.setWeekchoice(str);
			Facebook_Taskattribute_Dto.setStartregiontime(tbFacebookSysTaskattribute2.getStartregiontime());
			Facebook_Taskattribute_Dto.setEndregiontime(tbFacebookSysTaskattribute2.getEndregiontime());
			Facebook_Taskattribute_Dto.setStarttime(tbFacebookSysTaskattribute2.getStartTime());
			Facebook_Taskattribute_Dto.setEndtime(tbFacebookSysTaskattribute2.getEndtime());
			
			String downLoadAttribute = tbFacebookSysTaskattribute2.getDownloadattribute();
			String download[] =  downLoadAttribute.split(" ");
			String Overview = "";
			String Friends = "";
			String Timelin = "";
			String Comments = "";
			String Likes = "";
			String Photos = "";
			for (int i = 0; i < download.length; i++) {
				String str_download =download[i];
				switch(Integer.parseInt(str_download)){
					case 1 : Overview = "下载";
					break;
					case 2 : Friends = "下载";
					break;
					case 3 : Timelin = "下载";
					break;
					case 4 : Comments = "下载";
					break;
					case 5 : Likes = "下载";
					break;
					case 6 : Photos = "下载";
					break;
				}
			}
			if(Overview.equals("")){
				Overview = "不下载";
			}
			if(Friends.equals("")){
				Friends = "不下载";
			}
			if(Timelin.equals("")){
				Timelin ="不下载";
			}
			if(Comments.equals("")){
				Comments = "不下载";
			}
			if(Likes.equals("")){
				Likes = "不下载";
			}
			if(Photos.equals("")){
				Photos = "不下载";
			}
			Facebook_Taskattribute_Dto.setOverview(Overview);
			Facebook_Taskattribute_Dto.setFriends(Friends);
			Facebook_Taskattribute_Dto.setTimelin(Timelin);
			Facebook_Taskattribute_Dto.setComments(Comments);
			Facebook_Taskattribute_Dto.setLikes(Likes);
			Facebook_Taskattribute_Dto.setPhotos(Photos);
			
			Facebook_Taskattribute_Dto.setCreattime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbFacebookSysTaskattribute2.getCreattime()));
			Facebook_Taskattribute_Dto.setUpdatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbFacebookSysTaskattribute2.getUpdatetime()));
			System_Facebook_Taskattribute_Dto.add(Facebook_Taskattribute_Dto);
		}
		return System_Facebook_Taskattribute_Dto;
	}
	
	
	/**
	 * user表数据的添加功能
	 * 编辑的功能
	 */
	@RequestMapping(value = "addAttribute" , method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addAttribute(@RequestParam(value = "id" , required =false )String id,
			@RequestParam(value = "weekchoice" , required =true )String weekchoice,
			@RequestParam(value = "strdownload" , required =true )String strdownload,
			@RequestParam(value = "startregiontime" , required =true )String startregiontime,
			@RequestParam(value = "endregiontime" , required =true )String endregiontime,
			@RequestParam(value = "starttime" , required =true )String starttime,
			@RequestParam(value = "endtime" , required =true )String endtime){
			Map<String, String> map = new HashMap<String, String>();
			TbFacebookSysTaskattribute TbFacebookSysTaskattribute = new TbFacebookSysTaskattribute(); 
			if(id.equals("")){
				String str = "";
				String str_weekchoice[] = weekchoice.split(",");
				for (int i = 0; i < str_weekchoice.length; i++) {
					String str_weekchoice1 =str_weekchoice[i];
						switch(str_weekchoice1){
							case "星期一" : str = str+"1 ";
							break;
							case "星期二" : str = str+"2 ";
							break;
							case "星期三" : str = str+"3 ";
							break;
							case "星期四" : str = str+"4 ";
							break;
							case "星期五" : str = str+"5 ";
							break;
							case "星期六" : str = str+"6 ";
							break;
							case "星期天" : str = str+"7";
							break;
						}
					}
				String strdown = "";
				String str_strdownload[] = strdownload.split(",");
				for (int i = 1; i < str_strdownload.length; i++) {
					String str_strdownload1 =str_strdownload[i];
					if(str_strdownload1!=""){
						switch(Integer.parseInt(str_strdownload1)){
							case 1 : strdown = strdown+"1 ";
							break;
							case 2 : strdown = strdown+"2 ";
							break;
							case 3 : strdown = strdown+"3 ";
							break;
							case 4 : strdown = strdown+"4 ";
							break;
							case 5 : strdown = strdown+"5 ";
							break;
							case 6 : strdown = strdown+"6";
							break;
						}
					}
				}
				TbFacebookSysTaskattribute.setWeekchoice(str);
				TbFacebookSysTaskattribute.setStartregiontime(startregiontime);
				TbFacebookSysTaskattribute.setEndregiontime(endregiontime);
				TbFacebookSysTaskattribute.setStartTime(starttime);
				TbFacebookSysTaskattribute.setEndtime(endtime);
				TbFacebookSysTaskattribute.setDownloadattribute(strdown);
				system_Facebook_TaskattributeService.addAttribute(TbFacebookSysTaskattribute);
				map.put("result", "success");
			}else{
				String str = "";
				String str_weekchoice[] = weekchoice.split(",");
				for (int i = 0; i < str_weekchoice.length; i++) {
					String str_weekchoice1 =str_weekchoice[i];
						switch(str_weekchoice1){
							case "星期一" : str = str+"1 ";
							break;
							case "星期二" : str = str+"2 ";
							break;
							case "星期三" : str = str+"3 ";
							break;
							case "星期四" : str = str+"4 ";
							break;
							case "星期五" : str = str+"5 ";
							break;
							case "星期六" : str = str+"6 ";
							break;
							case "星期天" : str = str+"7";
							break;
						}
					}
				String strdown = "";
				String str_strdownload[] = strdownload.split(",");
				for (int i = 1; i < str_strdownload.length; i++) {
					String str_strdownload1 =str_strdownload[i];
					if(str_strdownload1!=""){
						switch(Integer.parseInt(str_strdownload1)){
							case 1 : strdown = strdown+"1 ";
							break;
							case 2 : strdown = strdown+"2 ";
							break;
							case 3 : strdown = strdown+"3 ";
							break;
							case 4 : strdown = strdown+"4 ";
							break;
							case 5 : strdown = strdown+"5 ";
							break;
							case 6 : strdown = strdown+"6";
							break;
						}
					}
				}
				TbFacebookSysTaskattribute.setId(Long.parseLong(id));
				TbFacebookSysTaskattribute.setWeekchoice(str);
				TbFacebookSysTaskattribute.setStartregiontime(startregiontime);
				TbFacebookSysTaskattribute.setEndregiontime(endregiontime);
				TbFacebookSysTaskattribute.setStartTime(starttime);
				TbFacebookSysTaskattribute.setEndtime(endtime);
				TbFacebookSysTaskattribute.setDownloadattribute(strdown);
				system_Facebook_TaskattributeService.updateAttribute(TbFacebookSysTaskattribute);
				map.put("result", "success");
			}
			return map;
	}
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteAttribute", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> deleteAttribute(@RequestParam (value = "id",required = false)String id[]){
			Map<String, String> map = new HashMap<String, String>();
			int status = 0;
			if(id != null){
				for (int i = 0; i < id.length; i++) {
					status = system_Facebook_TaskattributeService.deleteAttribute(Long.parseLong(id[i]));
				}
			} 
			if(status == 0 ){
				map.put("result", "0");
			}else{
				map.put("result", "success");
			}
			return map;
	}
	
}
