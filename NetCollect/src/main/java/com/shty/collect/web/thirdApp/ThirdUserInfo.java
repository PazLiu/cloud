package com.shty.collect.web.thirdApp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/threaduserinfo")
public class ThirdUserInfo {

	@RequestMapping(method = RequestMethod.GET, value = "register")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response) {

		return "===============";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getUserInfo")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String getUserInfo() {
		return "resource1";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getUserInfo2")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String getUserInfo2() {
		return "resource2";
	}

}
