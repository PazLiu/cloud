package com.shty.collect.web.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.gson.JsonObject;
import com.shty.collect.domain.TbSystemRelation;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.fb.test.FacebookTest;
import com.shty.collect.fb.test.Facebook_Status;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.service.System_RelationServiceI;
import com.shty.collect.twitter.task.TwitterNodeStatus;
import com.shty.collect.web.rest.dto.System_Node_Socialacount_LinkaccountDto;
import com.smit.fb.util.Utils;

@Controller
public class System_ReationController {

	@Resource
	System_RelationServiceI system_RelationServiceI;

	@Resource
	System_AccountServiceI System_accountServiceI;

	@RequestMapping(value = "select_Reation_id")
	@ResponseBody
	public List<TbSystemRelation> select_Reation_id(@RequestParam(value = "id", required = true) Long id) {
		List<TbSystemRelation> tbSystemRelation = system_RelationServiceI.select_Reation_nodeid(id);
		return tbSystemRelation;
	}

	@RequestMapping(value = "select_ReationAll")
	@ResponseBody
	public List<System_Node_Socialacount_LinkaccountDto> select_ReationAll() {
		List<System_Node_Socialacount_LinkaccountDto> node_socialaccount_linkaccountDto = system_RelationServiceI
				.select_ReationAll();
		String status = "待检测";
		for (System_Node_Socialacount_LinkaccountDto system_Node_Socialacount_LinkaccountDto : node_socialaccount_linkaccountDto) {
			if (system_Node_Socialacount_LinkaccountDto.getSocialid()==1) {
				system_Node_Socialacount_LinkaccountDto.setAccountName(Facebook_Status.getFacebook_Socialaccount());
				system_Node_Socialacount_LinkaccountDto.setHttpip(Facebook_Status.getFacebook_httpaccount()+" : "+Facebook_Status.getFacebook_httpport());
				system_Node_Socialacount_LinkaccountDto.setStatus(status);
				system_Node_Socialacount_LinkaccountDto.setNode_status(status);
				system_Node_Socialacount_LinkaccountDto.setAccountType("Facebook");
				system_Node_Socialacount_LinkaccountDto.setAccount_status(status);
			} else if (system_Node_Socialacount_LinkaccountDto.getSocialid()==2) {
				system_Node_Socialacount_LinkaccountDto.setAccountName(LnkController.localIpAccount.getAccountName());
				system_Node_Socialacount_LinkaccountDto.setHttpip(LnkController.localIpAccount.getHttpip()+":"+LnkController.localIpAccount.getPort());
				system_Node_Socialacount_LinkaccountDto.setStatus(status);
				system_Node_Socialacount_LinkaccountDto.setNode_status(status);
				system_Node_Socialacount_LinkaccountDto.setAccountType("Linkedin");
				system_Node_Socialacount_LinkaccountDto.setAccount_status(status);
			} else if (system_Node_Socialacount_LinkaccountDto.getSocialid()==3) {
				system_Node_Socialacount_LinkaccountDto.setAccountName(TwitterNodeStatus.getTwitter_Socialaccount());
				system_Node_Socialacount_LinkaccountDto.setHttpip(TwitterNodeStatus.getTwitter_httpaccount()+" : "+TwitterNodeStatus.getTwitter_httpport());
				system_Node_Socialacount_LinkaccountDto.setStatus(status);
				system_Node_Socialacount_LinkaccountDto.setNode_status(status);
				system_Node_Socialacount_LinkaccountDto.setAccountType("Twitter");
				system_Node_Socialacount_LinkaccountDto.setAccount_status(status);
			}
		}
		return node_socialaccount_linkaccountDto;
	}

	@RequestMapping(value = "insert_Reation")
	@ResponseBody
	public Map<String, String> insert_Reation(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "nodeid", required = false) String nodeid,
			@RequestParam(value = "socialid", required = false) String socialid) {
		Map<String, String> map = new HashMap<>();
		TbSystemRelation relation = new TbSystemRelation();
		// id为空则为添加
		int i = 0;
		if (null == id || "".equals(id)) {
			relation.setNodeid(Long.parseLong(nodeid));
			relation.setSocialid(Long.parseLong(socialid));
			i = system_RelationServiceI.insert_Reation(relation);
			if (i != 0) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		} else {
			relation.setId(Long.parseLong(id));
			relation.setNodeid(Long.parseLong(nodeid));
			relation.setSocialid(Long.parseLong(socialid));
			i = system_RelationServiceI.update_Reation(relation);
			if (i != 0) {
				map.put("result", "success");
				
			} else {
				map.put("result", "0");
			}
		}

		return map;
	}

	/**
	 * 删除的功能
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteReation", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> deleteReation(@RequestParam(value = "id", required = false) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_RelationServiceI.deleteReation(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}
		return map;
	}
}
