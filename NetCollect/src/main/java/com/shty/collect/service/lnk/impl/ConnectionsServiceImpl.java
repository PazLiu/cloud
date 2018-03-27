package com.shty.collect.service.lnk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.shty.collect.dao.lnk.ConnectionsDAO;
import com.shty.collect.domain.lnk.Connections;
import com.shty.collect.service.lnk.ConnectionsService;

@Service
public class ConnectionsServiceImpl implements ConnectionsService {

	@Autowired
	private ConnectionsDAO connectionsDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public int addConnections(List<Connections> connections) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 1);
		return connectionsDao.addConnections(connections);
	}

}
