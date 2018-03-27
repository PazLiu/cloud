package com.shty.collect.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.shty.collect.service.SystemImportExeclI;

@Controller
public class SystemImportExcelController {
	@Autowired
	private SystemImportExeclI systemImportExeclI;

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		String result = systemImportExeclI.readExcelFile(file);
		return result;
	}

	@RequestMapping(value = "uploadFacebook", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String uploadFacebook(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		String result = systemImportExeclI.readFacebookFile(file);
		return result;
	}

	@RequestMapping(value = "uploadLinkedin", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String uploadLinkedin(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "groupId", required = true) Integer groupId,
			@RequestParam(value = "fileMode", required = true) String fileMode, HttpServletRequest request,
			HttpServletResponse response) {
		if (file != null && !file.isEmpty()) {
			// System.out.println("组ID ： " + groupId);
			// System.out.println("数据类型: " + fileMode);
			// System.out.println("文件原名: " + file.getOriginalFilename());
			// System.out.println("文件名称: " + file.getName());
			// System.out.println("文件长度: " + file.getSize());
			// System.out.println("文件类型: " + file.getContentType());
			String result = systemImportExeclI.readLinkedinFile(file, groupId, fileMode);
			return result;
		} else {
			return null;
		}
	}
	
	@RequestMapping(value = "uploadAccount", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String uploadAccount(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {
		String result = systemImportExeclI.readAccountFile(file);
		return result;
	}
}
