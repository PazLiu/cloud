package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Follow_channels;

@Repository
public interface FollowChannelsDAO {

	public int addFollowChannels(List<Follow_channels> follow_channels);

	public List<Follow_channels> getFollowChannelsByPeopleId(Long peopleId);

	// 删除
	public int deleteFollowChannels(Long peopleId);

}
