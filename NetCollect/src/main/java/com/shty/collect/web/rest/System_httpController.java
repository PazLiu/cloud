package com.shty.collect.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shty.collect.domain.TbSystemHttp;
import com.shty.collect.service.TbSystemHttpServiceI;
import com.shty.collect.web.rest.dto.System_HttpDto;

@Controller
public class System_httpController {
	
	@Resource
	TbSystemHttpServiceI tbSystemHttpServiceI;
	
	@RequestMapping(value="getAllHttp")
	@ResponseBody
	public List<System_HttpDto> getAllHttp(){
		List<TbSystemHttp> http= tbSystemHttpServiceI.getAllHttp();
		List<System_HttpDto> httpdto = new ArrayList<>();
		for (TbSystemHttp tbSystemHttp : http) {
			System_HttpDto dto = new System_HttpDto();
			String cretatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemHttp.getCreatTime());//将时间格式转换成符合Timestamp要求的格式.
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemHttp.getUpdateTime());//将时间格式转换成符合Timestamp要求的格式.
			dto.setId(tbSystemHttp.getId());
			dto.setHttpip(tbSystemHttp.getHttpip());
			dto.setPort(tbSystemHttp.getPort());
			dto.setHttpname(tbSystemHttp.getHttpname());
			dto.setHttppassword(tbSystemHttp.getHttppassword());
			dto.setIsused(tbSystemHttp.getIsused());
			dto.setCreattime(cretatTime);
			dto.setUpdatetime(updateTime);
			httpdto.add(dto);
		}
		return httpdto;
	}
	
	
	@RequestMapping(value="addUpdateHttp")
	@ResponseBody
	public Map<String, String> addUpdateHttp(@RequestParam(value="id")String id,
			@RequestParam(value="httpip")String httpip,
			@RequestParam(value="port")String port,
			@RequestParam(value="httpname")String httpname,
			@RequestParam(value="httppassword")String httppassword){
		Map<String, String> map = new HashMap<>();
		TbSystemHttp http = new TbSystemHttp();
		int status = 0;
		//修改
		if(!"".equals(id)){
			http.setId(Long.parseLong(id));
			http.setHttpip(httpip);
			http.setPort(port);
			http.setHttpname(httpname);
			http.setHttppassword(httppassword);
			status = tbSystemHttpServiceI.updateHttp(http);
		}else{
			http.setHttpip(httpip);
			http.setPort(port);
			http.setHttpname(httpname);
			http.setHttppassword(httppassword);
			http.setIsused(0);
			status = tbSystemHttpServiceI.insertHttp(http);
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}
		return map;
	}
	
	@RequestMapping(value="deleteHttp")
	@ResponseBody
	public Map<String, String> deleteHttp(@RequestParam(value = "id", required = false) String id[]){
		Map<String, String> map = new HashMap<>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = tbSystemHttpServiceI.deleteHttp(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}
		return map;
	}
	
	@RequestMapping(value="getByidHttp")
	@ResponseBody
	public TbSystemHttp getByidHttp(@RequestParam(value = "id", required = false) String id){
		TbSystemHttp http = tbSystemHttpServiceI.getByidHttp(Long.parseLong(id));
		return http;
	}
	
}
