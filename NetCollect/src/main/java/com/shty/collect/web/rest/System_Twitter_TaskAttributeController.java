package com.shty.collect.web.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.shty.collect.domain.TbTwitterSysTaskattribute;
import com.shty.collect.service.System_Twitter_TaskAttributeServiceI;

@Controller
public class System_Twitter_TaskAttributeController {
	@Resource
	System_Twitter_TaskAttributeServiceI system_Twitter_TaskAttributeServiceI;

	@RequestMapping(value = "getTwitterAllAttribute")
	@ResponseBody
	public List<TbTwitterSysTaskattribute> getTwitterAllAttribute() {
		return system_Twitter_TaskAttributeServiceI.getTwitterAllAttribute();
	}

	@RequestMapping(value = "addTwitterAllAttribute", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addTwitterAllAttribute(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "weekchoice", required = true) String weekchoice,			
			@RequestParam(value = "startregiontime", required = true) String startregiontime,
			@RequestParam(value = "endregiontime", required = true) String endregiontime,
			@RequestParam(value = "starttime", required = true) String starttime,
			@RequestParam(value = "endtime", required = true) String endtime) {
		Map<String, String> map = new HashMap<String, String>();
		TbTwitterSysTaskattribute tbTwitterSysTaskattribute = new TbTwitterSysTaskattribute();
		if(id.equals("")){
		String weekchoiceBlankSpace = "";
		String weekchoiceArray[] = weekchoice.split(",");
		for(int i=1;i<weekchoiceArray.length;i++){
			String weekchoiceStr = weekchoiceArray[i];		
			switch (Integer.parseInt(weekchoiceStr)) {
			case 1:
				weekchoiceBlankSpace = weekchoiceBlankSpace +"1 ";
				break;
			case 2:
				weekchoiceBlankSpace = weekchoiceBlankSpace +"2 ";
				break;
			case 3:
				weekchoiceBlankSpace = weekchoiceBlankSpace +"3 ";
				break;
			case 4:
				weekchoiceBlankSpace = weekchoiceBlankSpace +"4 ";
				break;
			case 5:
				weekchoiceBlankSpace = weekchoiceBlankSpace +"5 ";
				break;
			case 6:
				weekchoiceBlankSpace = weekchoiceBlankSpace +"6 ";
				break;
			case 7:
				weekchoiceBlankSpace = weekchoiceBlankSpace +"7 ";
				break;

			}
		}
		tbTwitterSysTaskattribute.setWeekchoice(weekchoiceBlankSpace);
		tbTwitterSysTaskattribute.setStartregiontime(startregiontime);
		tbTwitterSysTaskattribute.setEndregiontime(endregiontime);
		tbTwitterSysTaskattribute.setStarttime(starttime);
		tbTwitterSysTaskattribute.setEndtime(endtime);
		system_Twitter_TaskAttributeServiceI.addTwitterAllAttribute(tbTwitterSysTaskattribute);
		map.put("result", "success");		
		}else{	
			System.out.println("修改数据");
			String weekchoiceBlankSpace = "";
			String weekchoiceArray[] = weekchoice.split(",");
			for(int i=1;i<weekchoiceArray.length;i++){
				String weekchoiceStr = weekchoiceArray[i];		
				switch (Integer.parseInt(weekchoiceStr)) {
				case 1:
					weekchoiceBlankSpace = weekchoiceBlankSpace +"1 ";
					break;
				case 2:
					weekchoiceBlankSpace = weekchoiceBlankSpace +"2 ";
					break;
				case 3:
					weekchoiceBlankSpace = weekchoiceBlankSpace +"3 ";
					break;
				case 4:
					weekchoiceBlankSpace = weekchoiceBlankSpace +"4 ";
					break;
				case 5:
					weekchoiceBlankSpace = weekchoiceBlankSpace +"5 ";
					break;
				case 6:
					weekchoiceBlankSpace = weekchoiceBlankSpace +"6 ";
					break;
				case 7:
					weekchoiceBlankSpace = weekchoiceBlankSpace +"7 ";
					break;
				}
			}
			tbTwitterSysTaskattribute.setId(Long.parseLong(id));
			tbTwitterSysTaskattribute.setWeekchoice(weekchoiceBlankSpace);
			tbTwitterSysTaskattribute.setStartregiontime(startregiontime);
			tbTwitterSysTaskattribute.setEndregiontime(endregiontime);
			tbTwitterSysTaskattribute.setStarttime(starttime);
			tbTwitterSysTaskattribute.setEndtime(endtime);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			tbTwitterSysTaskattribute.setUpdatetime(df.format(new Date()));
			system_Twitter_TaskAttributeServiceI.updateTwitterAllAttribute(tbTwitterSysTaskattribute);
			System.out.println("修改成功");
			map.put("result", "success");
		}
		return map;
	}
	@RequestMapping(value = "deleteTwitterAllAttribute", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> deleteAttribute(@RequestParam (value = "id",required = false)String id[]){
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if(id != null){
			for (int i = 0; i < id.length; i++) {
				status = system_Twitter_TaskAttributeServiceI.deleteTwitterAllAttribute(Long.parseLong(id[i]));
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
