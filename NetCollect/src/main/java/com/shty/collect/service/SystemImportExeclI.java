package com.shty.collect.service;

import org.springframework.web.multipart.MultipartFile;

public interface SystemImportExeclI {

	String readExcelFile(MultipartFile file);

	String readFacebookFile(MultipartFile file);

	String readLinkedinFile(MultipartFile file, Integer groupId, String fileMode);
	
	String readAccountFile(MultipartFile file);
}
