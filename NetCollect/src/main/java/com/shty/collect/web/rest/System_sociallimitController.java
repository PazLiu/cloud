package com.shty.collect.web.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbSystemSociallimit;
import com.shty.collect.service.System_LimitServiceI;

/**
 * 账号限制Controller
 * @author ydd
 * 2017年6月21日
 * 上午11:19:02
 */
@Controller
public class System_sociallimitController {
	@Resource
	System_LimitServiceI system_LimitServiceI;
	
	
	@RequestMapping(value="getAllLimit",method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TbSystemSociallimit> getAllLimit(){
		List<TbSystemSociallimit> list = system_LimitServiceI.getAllLimit();
		return list;
	}
}
