package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Connections;

@Repository
public interface ConnectionsDAO {

	public int addConnections(List<Connections> connections);

	public List<Connections> getConnectionsByPeopleId(Long peopleId);

	// 删除
	public int deleteConnections(Long peopleId);

}
