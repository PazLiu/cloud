package com.shty.collect.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbSystemUser;
import com.shty.collect.service.System_userServiceI;
import com.shty.collect.web.rest.dto.System_UserDto;

/**
 * 用户Controller
 * 
 * @author ydd 2017年6月21日 上午11:19:02
 */
@Controller
public class System_userController {

	@Resource
	System_userServiceI system_user_service;

	/**
	 * 前台登录功能 后台代码实现登录
	 * 
	 * @param user
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loginAccount", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, String> Login(@RequestParam(value = "user", required = true) String user,
			@RequestParam(value = "password", required = true) String password, HttpServletRequest request,
			HttpServletResponse response) {
		TbSystemUser tb_user = new TbSystemUser();
		tb_user.setUser(user);
		tb_user.setPassword(password);
		TbSystemUser resultuser = new TbSystemUser();
		resultuser = system_user_service.Login(tb_user);
		// 返回map集合，因为map集合是键值对一一对应的，在前台可以方便找到并用到返回的数据
		Map<String, String> map = new HashMap<String, String>();
		if (resultuser == null) {
			map.put("result", "0");
		} else {
			// 将登录的用户添加到session中
			request.getSession().setAttribute("resultuser", resultuser.getUser());
			request.getSession().setMaxInactiveInterval(5);
			map.put("result", resultuser.getUser());
		}

		return map;
	}

	/**
	 * 查询user表中的所有数据 返回list数据到前台页面展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "getAlluser", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<System_UserDto> getAlluser() {
		List<TbSystemUser> list = new ArrayList<>();
		List<System_UserDto> listdto = new ArrayList<>();
		list = system_user_service.getAlluser();
		for (TbSystemUser tbSystemUser : list) {
			System_UserDto userdto = new System_UserDto();
			String crateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemUser.getCreattime());// 将时间格式转换成符合Timestamp要求的格式.
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemUser.getUpdatetime());// 将时间格式转换成符合Timestamp要求的格式.
			userdto.setId(tbSystemUser.getId());
			userdto.setUser(tbSystemUser.getUser());
			userdto.setPassword(tbSystemUser.getPassword());
			if (tbSystemUser.getAuthority() == 1) {
				userdto.setAuthority("管理员");
			} else if (tbSystemUser.getAuthority() == 2) {
				userdto.setAuthority("系统管理员");
			} else if (tbSystemUser.getAuthority() == 3) {
				userdto.setAuthority("用户");
			}
			userdto.setCreattime(crateTime);
			userdto.setUpdatetime(updateTime);
			listdto.add(userdto);
		}
		return listdto;
	}

	/**
	 * user表数据的添加功能 编辑的功能
	 */
	@RequestMapping(value = "adduserAccount", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> addUser(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "user", required = true) String user,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "authority", required = true) String authority) {
		Map<String, String> map = new HashMap<String, String>();
		if ("".equals(id)) {
			TbSystemUser tb_user = new TbSystemUser();
			tb_user.setUser(user);
			tb_user.setPassword(password);
			tb_user.setAuthority(Integer.parseInt(authority));
			system_user_service.adduser(tb_user);
			map.put("result", "success");
		} else {
			TbSystemUser tb_user = new TbSystemUser();
			tb_user.setId(Long.parseLong(id));
			tb_user.setUser(user);
			tb_user.setPassword(password);
			tb_user.setAuthority(Integer.parseInt(authority));
			system_user_service.updateuser(tb_user);
			map.put("result", "success");
		}
		return map;
	}

	/**
	 * user 管理员的删除功能
	 */
	@RequestMapping(value = "deleteAccount", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Map<String, String> deleteUser(@RequestParam(value = "id", required = true) String id[]) {
		Map<String, String> map = new HashMap<String, String>();
		if (id != null) {
			for (int i = 0; i < id.length; i++) {
				system_user_service.deleteUser(Long.parseLong(id[i]));
			}
			map.put("result", "success");
		} else {
			map.put("result", "0");
		}
		return map;
	}

}
