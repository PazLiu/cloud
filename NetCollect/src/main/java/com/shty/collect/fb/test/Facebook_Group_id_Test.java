package com.shty.collect.fb.test;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.shty.collect.service.System_Facebook_TaskgroupService;
import com.shty.collect.web.rest.dto.Syste_Facebook_Task_Attribute_Group;
import com.shty.collect.web.rest.dto.Task_StatusDto;

@Controller
@EnableScheduling
public class Facebook_Group_id_Test {

	@Resource
	System_Facebook_TaskgroupService system_Facebook_TaskgroupService;

	@Scheduled(cron = "0 0 0 * * ?")//*/15 * * * * ?
	public void Group_id() {
		System.out.println("每天晚上12点执行：任务状态为1！");
		//还要修改查询third——id不为null的
		List<Syste_Facebook_Task_Attribute_Group> tbFacebook = system_Facebook_TaskgroupService.select_Task("3");
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
				if(!syste_Facebook_Task_Attribute_Group.getTaskstatus().equals("1")){
				Task_StatusDto statusDto = new Task_StatusDto();
				statusDto.setId((long) syste_Facebook_Task_Attribute_Group.getId());
				statusDto.setTaskStatus("1");
				system_Facebook_TaskgroupService.updateGroup_task_static(statusDto);
				}
			}
		}
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
}