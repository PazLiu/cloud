package com.shty.collect.web.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbTwitterCount;
import com.shty.collect.service.SystemCountValueServiceI;

@Controller
public class SystemCountValueController {
	@Resource
	SystemCountValueServiceI systemCountValueServiceI;

	@RequestMapping(value = "getTwitterCountValue",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TbTwitterCount> getTwitterCountValue(){
		
		List<TbTwitterCount> list =new ArrayList<>();
		for(int i=6;i>=0;i--){			
						
		TbTwitterCount tbTwitterCounts = systemCountValueServiceI.getTwitterCountByDate(i);
	       TbTwitterCount count = new TbTwitterCount();
	    
	       String date = tbTwitterCounts.getDateTime();
		   Integer number = tbTwitterCounts.getCount();
		   count.setCount(number);
		   count.setDateTime(date);
	       list.add(count);
		}
		return list;
	}

}
