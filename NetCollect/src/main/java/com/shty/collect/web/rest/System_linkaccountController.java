package com.shty.collect.web.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shty.collect.domain.TbSystemLinkaccount;
import com.shty.collect.service.System_linkaccountServiceI;
import com.shty.collect.web.rest.dto.GroupOrginizationDto;

/**
 * VPN账号Controller
 * @author ydd
 * 2017年6月21日
 * 上午11:19:02
 */
@Controller
public class System_linkaccountController {
	
	@Autowired
	System_linkaccountServiceI system_linkaccountService;
	
	@RequestMapping(value = "system_linkaccount",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody public TbSystemLinkaccount get(@RequestParam("id") Long id ,HttpServletRequest request){
		
		request.getSession().invalidate();
		return system_linkaccountService.selectByPrimaryKey(id);				
	}
	
	@RequestMapping(method = RequestMethod.GET,value="getAllLinkaccount")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<GroupOrginizationDto> getAllLinkaccount(){
		List<TbSystemLinkaccount> list = new ArrayList<>();
		List<GroupOrginizationDto> listaccount = new ArrayList<>(); 
		list = system_linkaccountService.getAllLinkaccount();
		for (TbSystemLinkaccount tbSystemLinkaccount : list) {
			GroupOrginizationDto Linkaccount = new GroupOrginizationDto();
			if(tbSystemLinkaccount.getIsused()==null){
				Linkaccount.setIsused("未分配");
			}else{
				Linkaccount.setIsused("已分配");
			}
			Linkaccount.setId(tbSystemLinkaccount.getId());
			Linkaccount.setUsername(tbSystemLinkaccount.getUsername());
			Linkaccount.setPassword(tbSystemLinkaccount.getPassword());
			Linkaccount.setIpaddress(tbSystemLinkaccount.getIpaddress());
			Linkaccount.setSharedkey(tbSystemLinkaccount.getSharedkey());
			Linkaccount.setVpntype(tbSystemLinkaccount.getVpntype());
			String cretatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemLinkaccount.getCreattime());//将时间格式转换成符合Timestamp要求的格式.
			String updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(tbSystemLinkaccount.getUpdatetime());//将时间格式转换成符合Timestamp要求的格式.
			Linkaccount.setCreattime(cretatTime);
			Linkaccount.setUpdatetime(updateTime);
			listaccount.add(Linkaccount);
		}
		return listaccount;
	}
}
