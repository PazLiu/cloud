package com.shty.collect.web.rest;

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

import com.shty.collect.domain.TbSystemSocialaccount;
import com.shty.collect.service.System_AccountServiceI;
import com.shty.collect.web.rest.dto.System_socialAccountDto;

/**
 * 社交账号Controller
 * 
 * @author ydd 2017年6月21日 上午11:19:02
 */
@Controller
public class System_socialaccountController {
	@Resource
	System_AccountServiceI system_accountServiceI;

	/**
	 * 查询账号表中的所有数据 返回list数据到前台页面展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllaccount", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<System_socialAccountDto> getAllaccount() {
		// 数据库查询出来的数据
		List<System_socialAccountDto> list = new ArrayList<>();
		// 经过处理后的数据，传到前台显示
		List<System_socialAccountDto> listdto = new ArrayList<>();
		// 执行查询操作
		list = system_accountServiceI.getAllaccount();
		// 遍历查询出来的数据，并进行处理
		for (System_socialAccountDto system_socialAccountDto : list) {
			System_socialAccountDto socialAccountDto = new System_socialAccountDto();
			String Accounttype = null;
			String isused = null;
			// 处理时间格式
			String crateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(system_socialAccountDto.getCreattime());// 将时间格式转换成符合Timestamp要求的格式.
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(system_socialAccountDto.getUpdatetime());// 将时间格式转换成符合Timestamp要求的格式.
			// 处理账号类型
			if (system_socialAccountDto.getAccounttype().equals("1")) {
				Accounttype = "Facebook";
			} else if (system_socialAccountDto.getAccounttype().equals("2")) {
				Accounttype = "Linkedin";
			} else if (system_socialAccountDto.getAccounttype().equals("3")) {
				Accounttype = "Twitter";
			} else {
				Accounttype = "未标识";
			}
			switch (system_socialAccountDto.getIsused()) {
			case "0":
				isused = "未分配";
				break;
			case "1":
				isused = "已分配";
				break;
			case "2":
				isused = "网络错误";
				break;
			case "3":
				isused = "账号错误";
				break;
			}
			socialAccountDto.setIsused(isused);
			socialAccountDto.setId(system_socialAccountDto.getId());
			socialAccountDto.setAccountname(system_socialAccountDto.getAccountname());
			socialAccountDto.setPassword(system_socialAccountDto.getPassword());
			socialAccountDto.setAccounttype(Accounttype);
			socialAccountDto.setHttpip(system_socialAccountDto.getHttpip());
			socialAccountDto.setHttpid(system_socialAccountDto.getHttpid());
			socialAccountDto.setDatecreattime(crateTime);
			socialAccountDto.setDateupdatetime(updateTime);
			listdto.add(socialAccountDto);
		}
		return listdto;
	}

	/**
	 * 查询账号表中的没有绑定数据 返回list数据到前台页面展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllaccountIsuesdTo", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<System_socialAccountDto> getAllaccountIsuesdTo() {
		// 数据库查询出来的数据
		List<System_socialAccountDto> list = new ArrayList<>();
		// 经过处理后的数据，传到前台显示
		List<System_socialAccountDto> listdto = new ArrayList<>();
		// 执行查询操作
		list = system_accountServiceI.getAllaccountIsuesdTo();
		// 遍历查询出来的数据，并进行处理
		for (System_socialAccountDto system_socialAccountDto : list) {
			System_socialAccountDto socialAccountDto = new System_socialAccountDto();
			String Accounttype = null;
			String isused = null;
			// 处理时间格式
			String crateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(system_socialAccountDto.getCreattime());// 将时间格式转换成符合Timestamp要求的格式.
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(system_socialAccountDto.getUpdatetime());// 将时间格式转换成符合Timestamp要求的格式.
			// 处理账号类型
			if (system_socialAccountDto.getAccounttype().equals("1")) {
				Accounttype = "Facebook";
			} else if (system_socialAccountDto.getAccounttype().equals("2")) {
				Accounttype = "Linkedin";
			} else if (system_socialAccountDto.getAccounttype().equals("3")) {
				Accounttype = "Twitter";
			} else {
				Accounttype = "未标识";
			}
			// 处理分配情况
			if (system_socialAccountDto.getIsused() == null || "".equals(system_socialAccountDto.getIsused())) {
				isused = "未分配";
			} else if (system_socialAccountDto.getIsused().equals("1")) {
				isused = "已分配";
			}
			socialAccountDto.setId(system_socialAccountDto.getId());
			socialAccountDto.setAccountname(system_socialAccountDto.getAccountname());
			socialAccountDto.setPassword(system_socialAccountDto.getPassword());
			socialAccountDto.setAccounttype(Accounttype);
			socialAccountDto.setDatecreattime(crateTime);
			socialAccountDto.setDateupdatetime(updateTime);
			listdto.add(socialAccountDto);
		}
		return listdto;
	}

	/**
	 * 查询账号表中的没有绑定数据 返回list数据到前台页面展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAllaccountIsuesd", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<System_socialAccountDto> getAllaccountIsuesd(@RequestParam(value = "id", required = false) String id) {
		// 数据库查询出来的数据
		List<System_socialAccountDto> list = new ArrayList<>();
		// 经过处理后的数据，传到前台显示
		List<System_socialAccountDto> listdto = new ArrayList<>();
		// 执行查询操作
		list = system_accountServiceI.getAllaccountIsuesd(Long.parseLong(id));
		// 遍历查询出来的数据，并进行处理
		for (System_socialAccountDto system_socialAccountDto : list) {
			System_socialAccountDto socialAccountDto = new System_socialAccountDto();
			String Accounttype = null;
			String isused = null;
			// 处理时间格式
			String crateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(system_socialAccountDto.getCreattime());// 将时间格式转换成符合Timestamp要求的格式.
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(system_socialAccountDto.getUpdatetime());// 将时间格式转换成符合Timestamp要求的格式.
			// 处理账号类型
			if (system_socialAccountDto.getAccounttype().equals("1")) {
				Accounttype = "Facebook";
			} else if (system_socialAccountDto.getAccounttype().equals("2")) {
				Accounttype = "Linkedin";
			} else if (system_socialAccountDto.getAccounttype().equals("3")) {
				Accounttype = "Twitter";
			} else {
				Accounttype = "未标识";
			}
			// 处理分配情况
			if (system_socialAccountDto.getIsused() == null || "".equals(system_socialAccountDto.getIsused())) {
				isused = "未分配";
			} else if (system_socialAccountDto.getIsused().equals("1")) {
				isused = "已分配";
			}
			socialAccountDto.setId(system_socialAccountDto.getId());
			socialAccountDto.setAccountname(system_socialAccountDto.getAccountname());
			socialAccountDto.setPassword(system_socialAccountDto.getPassword());
			socialAccountDto.setAccounttype(Accounttype);
			socialAccountDto.setDatecreattime(crateTime);
			socialAccountDto.setDateupdatetime(updateTime);
			listdto.add(socialAccountDto);
		}
		return listdto;
	}

	/**
	 * 添加一条账号
	 * 
	 * @param id
	 * @param accountname
	 * @param password
	 * @param accounttype
	 *            账号类型
	 * @param limittableid
	 *            是否分配，默认不分配
	 * @return
	 */
	@RequestMapping(value = "addsocialAccount", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addAccount(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "accountname", required = true) String accountname,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "accounttype", required = true) String accounttype,
			@RequestParam(value = "isused", required = false) String isused,
			@RequestParam(value = "httpid", required = false) String httpid) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		TbSystemSocialaccount tbSystemSocialaccount = new TbSystemSocialaccount();
		// 添加
		if ("".equals(id)) {
			tbSystemSocialaccount.setAccountname(accountname);
			tbSystemSocialaccount.setPassword(password);
			tbSystemSocialaccount.setAccounttype(accounttype);
			tbSystemSocialaccount.setIsused(isused);
			if (httpid != null || !httpid.equals("")) {
				tbSystemSocialaccount.setHttpid(Long.parseLong(httpid));
			} else {
				tbSystemSocialaccount.setHttpid(Long.parseLong(""));
			}
			status = system_accountServiceI.addAccount(tbSystemSocialaccount);
		} else {
			tbSystemSocialaccount.setId(Long.parseLong(id));
			tbSystemSocialaccount.setAccountname(accountname);
			tbSystemSocialaccount.setPassword(password);
			tbSystemSocialaccount.setAccounttype(accounttype);
			tbSystemSocialaccount.setIsused(isused);
			if (httpid != null || !httpid.equals("")) {
				tbSystemSocialaccount.setHttpid(Long.parseLong(httpid));
			} else {
				tbSystemSocialaccount.setHttpid(null);
			}
			status = system_accountServiceI.updateAccount(tbSystemSocialaccount);
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}
		return map;
	}

	/**
	 * 用户删除的功能
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deletesocialAccount", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> deletesocialAccount(@RequestParam(value = "id", required = false) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		int status = 0;
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				status = system_accountServiceI.deletesocialAccount(Long.parseLong(id[i]));
			}
		}
		if (status == 0) {
			map.put("result", "0");
		} else {
			map.put("result", "success");
		}
		return map;
	}

	@RequestMapping(value = "select_socialAcount_id", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbSystemSocialaccount select_socialAcount_id(@RequestParam(value = "id", required = false) Long id) {
		return system_accountServiceI.select_socialAcount_id(id);
	}

	/**
	 * 查询账号表中的facebook账号
	 * 
	 * @param accountType
	 * @return
	 */
	@RequestMapping(value = "select_type_facebook", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbSystemSocialaccount select_type_facebook(@RequestParam(value = "accountType") String accountType) {
		// 修改选中的账号状态
		TbSystemSocialaccount socialaccount = system_accountServiceI.select_type_facebook(accountType);
		if (socialaccount != null) {
			socialaccount.setIsused("1");
			system_accountServiceI.updateAccount(socialaccount);
		}
		return socialaccount;
	}
	
	/**
	 * 查询账号表中的facebook账号
	 * 
	 * @param accountType
	 * @return
	 */
	@RequestMapping(value = "select_type_twitter", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public TbSystemSocialaccount select_type_twitter(@RequestParam(value = "accountType") String accountType) {
		// 修改选中的账号状态
		TbSystemSocialaccount socialaccount = system_accountServiceI.select_type_facebook(accountType);
		return socialaccount;
	}
	
}
