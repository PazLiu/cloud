package com.shty.collect.web.rest;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbLinkedinSysKeyword;
import com.shty.collect.domain.TbLinkedinSysTask;
import com.shty.collect.domain.TbLinkedinSysTaskgroup;
import com.shty.collect.service.Linkedin_taskServiceI;
import com.shty.collect.service.System_linkedin_keywordGroupI;
import com.shty.collect.service.System_linkedin_keywordI;
import com.shty.collect.service.lnk.LnkAccountService;
import com.shty.collect.service.lnk.PeopleService;
import com.shty.collect.web.rest.dto.CollectAccountDto;
import com.shty.collect.web.rest.dto.LinkedinKeywordDto;
import com.shty.collect.web.rest.dto.LnkKeywordGroupDto;
import com.shty.collect.web.rest.dto.LnkPeopleDto;
import com.shty.linkedin.util.MyUtil;

@Controller
public class System_GetLinkedinTaskController {
	@Autowired
	Linkedin_taskServiceI linkedin_taskServiceI;

	@Autowired
	PeopleService peopleServiceI;

	@Autowired
	LnkAccountService lnkAccount;

	@Autowired
	System_linkedin_keywordI linkedinKeyword;

	@Autowired
	System_linkedin_keywordGroupI lnkKeywordGroup;

	@RequestMapping(value = "getLinkedinTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbLinkedinSysTask getLinkedinTask() {
		try {
			TbLinkedinSysTask tbLinkedinSysTask = linkedin_taskServiceI.getTask();
			if (tbLinkedinSysTask != null) {
				tbLinkedinSysTask.setTaskstatus("5");
				tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));
				linkedin_taskServiceI.updateTask(tbLinkedinSysTask);
				return tbLinkedinSysTask;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "getAllLinkedinTask", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<TbLinkedinSysTask> getAllLinkedinTask() {

		List<TbLinkedinSysTask> tbLinkedinSysTaskList = linkedin_taskServiceI.getAllTask();

		return tbLinkedinSysTaskList;

	}

	@RequestMapping(value = "getOneLnkKeyword", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbLinkedinSysKeyword getOneLnkKeyword() {

		TbLinkedinSysKeyword tbLinkedinSysKeyword = linkedinKeyword.getOneTbLinkedinSysKeyword();
		if (tbLinkedinSysKeyword != null) {
			tbLinkedinSysKeyword.setFinished(5);
			tbLinkedinSysKeyword.setUpdatatime(new Timestamp(System.currentTimeMillis()));
			linkedinKeyword.updateKeywordbyid(tbLinkedinSysKeyword);
		}
		return tbLinkedinSysKeyword;

	}

	@RequestMapping(value = "addLinkedinTask", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addLinkedinTask(@RequestParam(value = "urlList", required = true) List<String> urlList,
			@RequestParam(value = "group", required = true) String group) {

		Map<String, String> map = new HashMap<>();

		try {

			TbLinkedinSysTaskgroup tbLinkedinSysTaskgroup = new TbLinkedinSysTaskgroup();

			tbLinkedinSysTaskgroup.setGroupname(group);
			tbLinkedinSysTaskgroup.setGroupstatus("1");
			tbLinkedinSysTaskgroup.setCreattime(new Timestamp(System.currentTimeMillis()));
			tbLinkedinSysTaskgroup.setUpdatetime(new Timestamp(System.currentTimeMillis()));
			lnkKeywordGroup.addLinkedinSysTaskgroup(tbLinkedinSysTaskgroup);
			int id = tbLinkedinSysTaskgroup.getId();

			TbLinkedinSysKeyword tbGroup = new TbLinkedinSysKeyword();
			tbGroup.setCreatetime(new Timestamp(System.currentTimeMillis()));
			tbGroup.setUpdatatime(new Timestamp(System.currentTimeMillis()));
			tbGroup.setFinished(1);
			tbGroup.setGroupid(id);
			tbGroup.setKeyword(group);
			linkedinKeyword.addKeyword(tbGroup);

			id = tbGroup.getId();
			for (String url : urlList) {
				TbLinkedinSysTask tbLinkedinSysTask = new TbLinkedinSysTask();
				tbLinkedinSysTask.setFinished(0);
				tbLinkedinSysTask.setUrl(url);
				tbLinkedinSysTask.setUrlpattern(MyUtil.getsubdomainUrl(url));
				tbLinkedinSysTask.setTaskstatus("0");
				tbLinkedinSysTask.setCreattime(new Timestamp(System.currentTimeMillis()));
				tbLinkedinSysTask.setUpdatetime(new Timestamp(System.currentTimeMillis()));
				tbLinkedinSysTask.setKeywordid(id);
				linkedin_taskServiceI.addTask(tbLinkedinSysTask);
			}

			map.put("result", "success");

		} catch (Exception e) {
			map.put("result", "0");
		}

		return map;

	}

	@RequestMapping(value = "getAllLinkedinPeople", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LnkPeopleDto> getAllLinkedinPeople() {

		return peopleServiceI.getAllPeople();

	}

	@RequestMapping(value = "getLinkedinAccount", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public CollectAccountDto getAccount() {
		return lnkAccount.getLinkaccount();
	}

	@RequestMapping(value = "getLinkedinkeywordgroup", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LnkKeywordGroupDto> getLinkedinkeywordgroup() {
		return lnkKeywordGroup.getAllKeywordGroup();
	}

	@RequestMapping(value = "getLinkedinkeyword", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LinkedinKeywordDto> getLinkedinkeyword() {
		return linkedinKeyword.getAllKeyword();
	}

	@RequestMapping(value = "deleteLinkedinkeyword", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> deleteLinkedinkeyword(@RequestParam(value = "id", required = true) int id) {
		Map<String, String> map = new HashMap<>();
		try {
			linkedinKeyword.deletekeywordbyid(id);
			map.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "0");
		}
		return map;
	}

	@RequestMapping(value = "updateLinkedinkeyword", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> updateLinkedinkeyword(@RequestParam(value = "id", required = true) int id,
			@RequestParam(value = "keyword", required = true) String keyword) {
		Map<String, String> map = new HashMap<>();
		try {
			TbLinkedinSysKeyword tb = linkedinKeyword.getKeywordbyid(id);
			tb.setUpdatatime(new Timestamp(System.currentTimeMillis()));
			tb.setKeyword(keyword);
			linkedinKeyword.updateKeywordbyid(tb);
			map.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "0");
		}
		return map;
	}

	@RequestMapping(value = "getLinkedinkeywordbyid", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<LinkedinKeywordDto> getLinkedinkeywordbyid(@RequestParam(value = "id", required = true) int id) {
		return linkedinKeyword.getKeywordByGroupId(id);
	}

	@RequestMapping(value = "getIdByLnkid", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public int getIdByLnkid(@RequestParam(value = "lnkid", required = true) String lnkid) {

		return peopleServiceI.getIdByLnkid(lnkid);
	}

	@RequestMapping(value = "updatelnkKeywordGroup", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> updatelnkKeywordGroup(@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "keywordgroup", required = true) String keywordgroup) {

		Map<String, String> map = new HashMap<>();
		try {
			TbLinkedinSysTaskgroup tbgroup = lnkKeywordGroup.getKeywordById(id);
			tbgroup.setGroupname(keywordgroup);
			tbgroup.setUpdatetime(new Timestamp(System.currentTimeMillis()));
			lnkKeywordGroup.updateLinkedinSysTaskgroup(tbgroup);
			map.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", "0");
		}

		return map;
	}

	@RequestMapping(value = "addLinkedinkeywordgroup", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addLinkedinkeywordgroup(
			@RequestParam(value = "keywordgroup", required = false) String keywordgroup,
			@RequestParam(value = "keyword", required = true) String keyword,
			@RequestParam(value = "id", required = true) int id) {
		Map<String, String> map = new HashMap<>();

		TbLinkedinSysTaskgroup tbg = new TbLinkedinSysTaskgroup();
		if (id == 0) {
			try {
				tbg.setGroupname(keywordgroup);
				tbg.setGroupstatus("0");
				tbg.setCreattime(new Timestamp(System.currentTimeMillis()));
				tbg.setUpdatetime(new Timestamp(System.currentTimeMillis()));
				lnkKeywordGroup.addLinkedinSysTaskgroup(tbg);
				id = tbg.getId();
			} catch (Exception e) {
				e.printStackTrace();
				map.put("result", "0");
				return map;
			}
		}

		try {
			String[] kStrings = keyword.split(",");
			for (int i = 0; i < kStrings.length; i++) {
				if (kStrings[i] != null && kStrings[i] != "") {
					TbLinkedinSysKeyword tb = new TbLinkedinSysKeyword();
					tb.setCreatetime(new Timestamp(System.currentTimeMillis()));
					tb.setUpdatatime(new Timestamp(System.currentTimeMillis()));
					tb.setFinished(0);
					tb.setGroupid(id);
					tb.setKeyword(kStrings[i]);
					linkedinKeyword.addKeyword(tb);
				}
			}
			map.put("result", "success");
		} catch (Exception e) {
			map.put("result", "0");
		}
		return map;
	}
}