package com.shty.collect.web.rest;

import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.shty.collect.domain.TbSystemHttp;
import com.shty.collect.domain.TbSystemNode;
import com.shty.collect.domain.TbSystemRelation;
import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.fb.test.FacebookTest;
import com.shty.collect.fb.test.Facebook_Status;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.service.System_Facebook_TaskServiceI;
import com.shty.collect.service.System_Facebook_TaskgroupService;
import com.shty.collect.service.System_NodeServiceI;
import com.shty.collect.service.System_RelationServiceI;
import com.shty.collect.service.TbSystemHttpServiceI;
import com.shty.collect.service.ThridTaskServiceI;
import com.shty.collect.service.fb.FBPhoto_ServiceI;
import com.shty.collect.service.fb.FbOverviewWork_ServiceI;
import com.shty.collect.service.fb.FbTarget_ServiceI;
import com.shty.collect.service.fb.Friends_ServiceI;
import com.shty.collect.service.fb.OverviewBackgroung_ServiceI;
import com.shty.collect.service.fb.OverviewContacts_ServiceI;
import com.shty.collect.service.fb.OverviewEducation_ServiceI;
import com.shty.collect.service.fb.OverviewFamily_ServiceI;
import com.shty.collect.service.fb.OverviewLivings_ServiceI;
import com.shty.collect.service.fb.OverviewSkills_ServiceI;
import com.shty.collect.service.fb.OverviewWorkdescriptions_ServiceI;
import com.shty.collect.service.fb.TimelineComment_ServiceI;
import com.shty.collect.service.fb.TimelineStory_ServiceI;
import com.shty.collect.service.fb.TimelineStoryimgCodeList_ServiceI;
import com.shty.collect.service.fb.TimelineYear_ServiceI;
import com.shty.collect.twitter.task.TwitterNodeStatus;
import com.shty.collect.web.rest.dto.System_NodeDto;
import com.smit.fb.controller.FbDetailController;
import com.smit.fbAppclient.MyRequestEntity;
import com.smit.fbAppclient.MyResponseEntity;

@Controller
public class System_nodeController {

	@Resource
	System_NodeServiceI system_NodeServiceI;

	@Resource
	FbTarget_ServiceI fbTarget_ServiceI;

	@Resource
	FbOverviewWork_ServiceI fbOverview_ServiceI;

	@Resource
	OverviewWorkdescriptions_ServiceI overviewWorkdescriptions_ServiceI;

	@Resource
	OverviewEducation_ServiceI overviewEducation_ServiceI;

	@Resource
	OverviewLivings_ServiceI overviewLivings_ServiceI;

	@Resource
	OverviewSkills_ServiceI overviewSkills_ServiceI;

	@Resource
	OverviewContacts_ServiceI overviewContacts_ServiceI;

	@Resource
	OverviewFamily_ServiceI overviewFamily_ServiceI;

	@Resource
	OverviewBackgroung_ServiceI overviewBackgroung_ServiceI;

	@Resource
	Friends_ServiceI friends_ServiceI;

	@Resource
	TimelineYear_ServiceI timelineYear_ServiceI;

	@Resource
	TimelineStory_ServiceI timelineStory_ServiceI;

	@Resource
	TimelineStoryimgCodeList_ServiceI timelineStoryimgCodeList_ServiceI;

	@Resource
	TimelineComment_ServiceI timelineComment_ServiceI;

	@Resource
	FBPhoto_ServiceI fBPhoto_ServiceI;

	@Resource
	System_RelationServiceI system_RelationServiceI;

	@Resource
	System_AccountServiceI system_AccountServiceI;

	@Resource
	System_Facebook_TaskServiceI system_Facebook_TaskServiceI;

	@Resource
	System_Facebook_TaskgroupService system_Facebook_TaskgroupService;

	@Resource
	TbSystemHttpServiceI tbSystemHttpServiceI;

	@Resource
	ThridTaskServiceI thirdService;

	static MyRequestEntity request = new MyRequestEntity();
	static MyResponseEntity response = new MyResponseEntity();
	private WebClient webClient;

	/**
	 * 查询节点表 node中的全部数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllnode", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<System_NodeDto> getAllnode() {
		List<TbSystemNode> list = system_NodeServiceI.getAllnode();
		List<System_NodeDto> listdto = new ArrayList<>();
		for (TbSystemNode tbSystemNode : list) {
			System_NodeDto system_NodeDto = new System_NodeDto();
			system_NodeDto.setId(tbSystemNode.getId());
			system_NodeDto.setNodeip(tbSystemNode.getNodeip());
			system_NodeDto.setPhysicalip(tbSystemNode.getPhysicalip());
			if (system_NodeDto.getIsused() == null) {
				system_NodeDto.setIsused("未绑定");
			} else {
				system_NodeDto.setIsused("已绑定");
			}
			String crateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemNode.getCreattime());// 将时间格式转换成符合Timestamp要求的格式.
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemNode.getUpdatetime());// 将时间格式转换成符合Timestamp要求的格式.
			system_NodeDto.setCreattime(crateTime);
			system_NodeDto.setUpdatetime(updateTime);
			listdto.add(system_NodeDto);
		}
		return listdto;
	}

	/**
	 * 添加一个节点
	 * 
	 * @return
	 */
	@RequestMapping(value = "addNode", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addNode(@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "nodeip", required = true) String nodeip,
			@RequestParam(value = "physicalip", required = true) String physicalip) {
		Map<String, String> map = new HashMap<>();
		TbSystemNode node = new TbSystemNode();
		int status = 0;
		if ("".equals(id)) {
			node.setNodeip(nodeip);
			node.setPhysicalip(physicalip);
			status = system_NodeServiceI.addNode(node);
		} else {
			node.setId(Long.parseLong(id));
			node.setNodeip(nodeip);
			node.setPhysicalip(physicalip);
			status = system_NodeServiceI.updateNode(node);
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}
		return map;
	}

	@RequestMapping(value = "deleteNode", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> deleteNode(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<>();
		int status = 0;
		for (int i = 0; i < id.length; i++) {
			status = system_NodeServiceI.deleteNode(Long.parseLong(id[i]));
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}
		return map;
	}

	/**
	 * 根据ip查找节点的数据
	 */
	@RequestMapping(value = "selectNode", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbSystemNode selectNode(@RequestParam(value = "nodeIp", required = true) String nodeIp) {
		TbSystemNode tbSystemNode = system_NodeServiceI.selectNodeip(nodeIp);
		return tbSystemNode;
	}

	/**
	 * 节点启动
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "start_node", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> start_node(@RequestParam(value = "nodeip", required = true) String nodeip)
			throws Exception {
		Map<String, String> map = new HashMap<>();
		FacebookTest s = new FacebookTest(fbTarget_ServiceI, fbOverview_ServiceI, overviewWorkdescriptions_ServiceI,
				overviewEducation_ServiceI, overviewLivings_ServiceI, overviewSkills_ServiceI,
				overviewContacts_ServiceI, overviewFamily_ServiceI, overviewBackgroung_ServiceI, friends_ServiceI,
				timelineYear_ServiceI, timelineStory_ServiceI, timelineStoryimgCodeList_ServiceI,
				timelineComment_ServiceI, fBPhoto_ServiceI, system_NodeServiceI, system_RelationServiceI,
				system_AccountServiceI, system_Facebook_TaskServiceI, system_Facebook_TaskgroupService,
				tbSystemHttpServiceI, thirdService);
		if (Facebook_Status.getFacebook_nodeStatus().equals("100")) {
			System.out.println("节点未启动，启动节点！");
			s.facebook_status = false;
			s.start();
			if (s != null) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		} else {
			map.put("result", "success");
		}
		return map;
	}

	/**
	 * 节点关闭
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "stop_node", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> stop_node(@RequestParam(value = "nodeip", required = true) String nodeip)
			throws Exception {
		Map<String, String> map = new HashMap<>();
		FacebookTest s = new FacebookTest(fbTarget_ServiceI, fbOverview_ServiceI, overviewWorkdescriptions_ServiceI,
				overviewEducation_ServiceI, overviewLivings_ServiceI, overviewSkills_ServiceI,
				overviewContacts_ServiceI, overviewFamily_ServiceI, overviewBackgroung_ServiceI, friends_ServiceI,
				timelineYear_ServiceI, timelineStory_ServiceI, timelineStoryimgCodeList_ServiceI,
				timelineComment_ServiceI, fBPhoto_ServiceI, system_NodeServiceI, system_RelationServiceI,
				system_AccountServiceI, system_Facebook_TaskServiceI, system_Facebook_TaskgroupService,
				tbSystemHttpServiceI, thirdService);
		if (Facebook_Status.getFacebook_nodeStatus() != "100") {
			System.out.println("节点启动，关闭节点！");
			s.facebook_status = true;
			s.interrupt();// 退出阻塞状态
			s.join();
			if (s != null) {
				map.put("result", "success");
			} else {
				map.put("result", "0");
			}
		} else {
			map.put("result", "success");
		}
		return map;
	}

	/**
	 * 节点状态查询
	 */
	@RequestMapping(value = "node_status", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> node_status() {
		Map<String, String> map = new HashMap<>();
		String nodeStatus = Facebook_Status.getFacebook_nodeStatus();
		if (nodeStatus.equals("100")) {
			nodeStatus = "未启动";
		} else if (nodeStatus.equals("200")) {
			nodeStatus = "空闲";
		} else if (nodeStatus.equals("300")) {
			nodeStatus = "忙碌";
		} else if (nodeStatus.equals("400")) {
			nodeStatus = "采集friends";
		} else if (nodeStatus.equals("500")) {
			nodeStatus = "采集timeline";
		} else if (nodeStatus.equals("600")) {
			nodeStatus = "采集photo";
		} else if (nodeStatus.equals("700")) {
			nodeStatus = "出错";
		}
		map.put("result", nodeStatus);
		return map;
	}

	@RequestMapping(value = "get_Account", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> getFacebook_Account(@RequestParam(value = "type", required = true) String type) {
		Map<String, String> map = new HashMap<>();
		if (type.equals("Facebook")) {
			map.put("result", Facebook_Status.getFacebook_Socialaccount());
		} else if (type.equals("Linkedin")) {
			map.put("result", LnkController.localIpAccount.getAccountName());
		}
		return map;
	}

	@RequestMapping(value = "get_HTTP", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> getFacebook_HTTP(@RequestParam(value = "type", required = true) String type) {
		Map<String, String> map = new HashMap<>();
		if (type.equals("Facebook")) {
			map.put("result",
					Facebook_Status.getFacebook_httpaccount() + " : " + Facebook_Status.getFacebook_httpport());
		} else if (type.equals("Linkedin")) {
			map.put("result",
					LnkController.localIpAccount.getHttpip() + " : " + LnkController.localIpAccount.getPort());
		} else if (type.equals("Twitter")) {
			map.put("result",
					TwitterNodeStatus.getTwitter_httpaccount() + " : " + TwitterNodeStatus.getTwitter_httpport());
		}
		return map;
	}

	/**
	 * 账号状态查询
	 */
	@RequestMapping(value = "account_status", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> account_status(@RequestParam(value = "nodeip", required = true) String nodeip) {
		Map<String, String> map = new HashMap<>();
		// 获取本机IP地址得到所绑定的账号
		String ResultAccount = Facebook_Status.getFacebook_Socialaccount();
		if (!ResultAccount.equals("")) {
			String password = Facebook_Status.getFacebook_Socialpassword();
			TbSystemHttp tbSystemHttp = new TbSystemHttp();
			tbSystemHttp.setHttpip(Facebook_Status.getFacebook_httpaccount());
			tbSystemHttp.setPort(Facebook_Status.getFacebook_httpport());
			// 创建Webclient
			WebclientCreat(tbSystemHttp);
			FbDetailController fb = new FbDetailController(webClient);
			String account = FacebookTest.Account(ResultAccount);
			String loginResult = "";
			try {
				if (account != null || !"".equals(account) && password != null || !"".equals(password)) {
					loginResult = fb.login(account, password, request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(loginResult);
			if (loginResult.equals("0")) {
				Facebook_Status.setFacebook_SocialStatus("登录正常");
				map.put("result", Facebook_Status.getFacebook_SocialStatus());
			} else {
				Facebook_Status.setFacebook_SocialStatus("登录异常");
				map.put("result", Facebook_Status.getFacebook_SocialStatus());
			}
		}else {
			map.put("result", "节点未启动");
		}
		return map;
	}

	public void WebclientCreat(TbSystemHttp tbSystemHttp) {
		// 用http代理
		webClient = new WebClient();
		// 设置代理
		if (tbSystemHttp != null) {
			ProxyConfig proxyConfig = webClient.getOptions().getProxyConfig();
			if (tbSystemHttp.getHttpip().equals("")) {
				proxyConfig.setProxyHost(null);
			} else {
				proxyConfig.setProxyHost(tbSystemHttp.getHttpip());
			}
			proxyConfig.setProxyPort(Integer.parseInt(tbSystemHttp.getPort()));
			DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
					.getCredentialsProvider();
			// HTTP代理用户验证
			if (tbSystemHttp.getHttpname() != null && !tbSystemHttp.getHttpname().equals("")
					&& tbSystemHttp.getHttppassword() != null && !tbSystemHttp.equals("")) {
				credentialsProvider.addCredentials(tbSystemHttp.getHttpname(), tbSystemHttp.getHttppassword());
			}
		}
		try {
			webClient.getBrowserVersion().setUserAgent(FacebookTest.userAgent);
			webClient.setRefreshHandler(new ThreadedRefreshHandler());
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			webClient.getOptions().setRedirectEnabled(true);
			webClient.getOptions().setTimeout(120000);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.setJavaScriptTimeout(300000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
