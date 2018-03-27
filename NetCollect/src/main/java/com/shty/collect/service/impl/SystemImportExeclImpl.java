package com.shty.collect.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shty.collect.dao.TbLinkedinSysKeywordMapper;
import com.shty.collect.dao.TbLinkedinSysTaskMapper;
import com.shty.collect.dao.TbSystemImportExeclMapper;
import com.shty.collect.dao.TbSystemSocialaccountMapper;
import com.shty.collect.domain.TbFacebookSysTask;
import com.shty.collect.domain.TbLinkedinSysKeyword;
import com.shty.collect.domain.TbLinkedinSysTask;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.domain.TbTwitterSysTask;
import com.shty.collect.service.SystemImportExeclI;
import com.shty.collect.util.SystemReadExecl;

@Service
public class SystemImportExeclImpl implements SystemImportExeclI {

	@Autowired
	TbSystemImportExeclMapper tbSystemImportExeclMapper;

	@Autowired
	TbLinkedinSysKeywordMapper tbLinkedinSysKeywordMapper;

	@Autowired
	TbLinkedinSysTaskMapper tbLinkedinSysTaskMapper;
	@Autowired
	TbSystemSocialaccountMapper tbSystemSocialaccountMapper;

	@Override
	public String readExcelFile(MultipartFile file) {
		String result = "";
		int status = 0;
		// 创建处理EXCEL的类
		SystemReadExecl readExcel = new SystemReadExecl();
		// 解析excel，获取上传的事件单
		List<TbTwitterSysTask> taskList = readExcel.getExcelInfo(file);
		TbTwitterSysTask tbTwitterSysTask = new TbTwitterSysTask();
		// 至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
		// 和你具体业务有关,这里不做具体的示范
		if (taskList != null && !taskList.isEmpty()) {
			for (TbTwitterSysTask pList : taskList) {
				tbTwitterSysTask.setUrl(pList.getUrl());
				tbTwitterSysTask.setTaskName(pList.getTaskName());
				tbTwitterSysTask.setTaskattributeid(pList.getTaskattributeid());
				tbTwitterSysTask.setTaskgroupid(pList.getTaskgroupid());
				status = tbSystemImportExeclMapper.insertImportTask(tbTwitterSysTask);
			}

			if (status == 0) {
				result = "fail";
			} else {
				result = "success";
			}
		} else {
			result = "fail";
		}
		return result;
	}

	@Override
	public String readFacebookFile(MultipartFile file) {
		String result = "";
		int status = 0;
		// 创建处理EXCEL的类
		SystemReadExecl readExcel = new SystemReadExecl();
		// 解析excel，获取上传的事件单
		List<TbTwitterSysTask> taskList = readExcel.getExcelInfo(file);
		TbFacebookSysTask tbFacebookSysTask = new TbFacebookSysTask();
		// 至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
		// 和你具体业务有关,这里不做具体的示范
		if (taskList != null && !taskList.isEmpty()) {
			for (TbTwitterSysTask pList : taskList) {
				tbFacebookSysTask.setUrl(pList.getUrl());
				tbFacebookSysTask.setTaskName(pList.getTaskName());
				tbFacebookSysTask.setTaskattributeid(pList.getTaskattributeid());
				tbFacebookSysTask.setTaskgroupid(pList.getTaskgroupid());
				tbFacebookSysTask.setTaskstatus("1");
				status = tbSystemImportExeclMapper.insertImportFacebookTask(tbFacebookSysTask);
			}

			if (status == 0) {
				result = "fail";
			} else {
				result = "success";
			}
		} else {
			result = "fail";
		}
		return result;
	}

	@Override
	public String readLinkedinFile(MultipartFile file, Integer groupId, String fileMode) {
		String result = "";
		int status = 0;
		// 创建处理EXCEL的类
		SystemReadExecl readExcel = new SystemReadExecl();
		// 解析excel，获取上传的事件单
		List<TbLinkedinSysKeyword> taskList = readExcel.getExcelLinkedin(file);

		// 至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
		// 和你具体业务有关,这里不做具体的示范
		if (taskList != null && !taskList.isEmpty()) {
			if (fileMode.equals("keyword")) {
				for (TbLinkedinSysKeyword pList : taskList) {
					try {
						TbLinkedinSysKeyword tbLinkedinSysKeyword = new TbLinkedinSysKeyword();
						tbLinkedinSysKeyword.setKeyword(pList.getKeyword());
						tbLinkedinSysKeyword.setFinished(0);
						tbLinkedinSysKeyword.setGroupid(groupId);
						tbLinkedinSysKeyword.setCreatetime(new Timestamp(System.currentTimeMillis()));
						tbLinkedinSysKeyword.setUpdatatime(new Timestamp(System.currentTimeMillis()));
						status = tbLinkedinSysKeywordMapper.insert(tbLinkedinSysKeyword);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				for (TbLinkedinSysKeyword pList : taskList) {
					try {
						TbLinkedinSysTask tbLinkedinSysTask = new TbLinkedinSysTask();
						tbLinkedinSysTask.setFinished(0);
						tbLinkedinSysTask.setUrl(pList.getKeyword());
						tbLinkedinSysTask
								.setUrlpattern(com.shty.linkedin.util.MyUtil.getsubdomainUrl(pList.getKeyword()));
						tbLinkedinSysTask.setTaskstatus("0");
						tbLinkedinSysTask.setKeywordid(groupId);
						tbLinkedinSysTask.setCreattime(new Timestamp(System.currentTimeMillis()));
						tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));
						status = tbLinkedinSysTaskMapper.insert(tbLinkedinSysTask);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if (status == 0) {
				result = "fail";
			} else {
				result = "success";
			}
		} else {
			result = "fail";
		}
		return result;
	}

	@Override
	public String readAccountFile(MultipartFile file) {
		String result = "";
		int status = 0;
		// 创建处理EXCEL的类
		SystemReadExecl readExcel = new SystemReadExecl();
		// 解析excel，获取上传的事件单
		List<TbSystemSocialaccount> accountList = readExcel.getAccountExcelInfo(file);
		TbSystemSocialaccount tbSystemSocialaccount = new TbSystemSocialaccount();
		// 至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
		// 和你具体业务有关,这里不做具体的示范
		if (accountList != null && !accountList.isEmpty()) {

			flag: for (TbSystemSocialaccount pList : accountList) {
				// System.out.println("导入账号："+pList.getAccountname());
				List<TbSystemSocialaccount> accountNameList = tbSystemSocialaccountMapper.getAllAccountName();
				for (TbSystemSocialaccount aList : accountNameList) {
					// System.out.println("数据库账号:" +
					// aList.getAccountname()+"导入账号：" + pList.getAccountname());
					if (pList.getAccountname().trim().toString().equals(aList.getAccountname().trim().toString())) {
						continue flag;
					}
				}
				tbSystemSocialaccount.setAccountname(pList.getAccountname());
				tbSystemSocialaccount.setPassword(pList.getPassword());
				tbSystemSocialaccount.setAccounttype(pList.getAccounttype());
				tbSystemSocialaccount.setIsused("0");
				tbSystemSocialaccount.setHttpid(Long.parseLong("0"));
				if (null != tbSystemSocialaccount.getAccountname()
						|| !"".equals(tbSystemSocialaccount.getAccountname())) {
					status = tbSystemImportExeclMapper.insertImportAccount(tbSystemSocialaccount);
				}
			}
			if (status == 0) {
				result = "fail";
			} else {
				result = "success";
			}
		} else {
			result = "fail";
		}
		return result;
	}
}