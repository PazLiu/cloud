package com.shty.collect.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.service.IPAccountServiceI;
import com.shty.collect.web.rest.dto.IPAccountDto;

@Controller
public class System_GetAccount {

	@Autowired
	IPAccountServiceI ipAccountService;

	@RequestMapping(value = "getIpAccount", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public IPAccountDto getIpAccount() {
		IPAccountDto ipAccountDto = new IPAccountDto();
		return ipAccountDto;
	}
}
