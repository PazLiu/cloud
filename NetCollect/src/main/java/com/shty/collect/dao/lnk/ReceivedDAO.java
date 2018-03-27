package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Received;

@Repository
public interface ReceivedDAO {

	public int addReceived(Received received);

	public List<Received> getReceivedByPeopleId(Long peopleId);

	// 删除
	public int deleteReceived(Long peopleId);

}
