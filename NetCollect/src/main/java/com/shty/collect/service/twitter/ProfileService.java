package com.shty.collect.service.twitter;

import com.shty.collect.domain.twitter.TwitterProfile;

public interface ProfileService {
	public void insertNew(TwitterProfile profile);

	public int findProfileByNickName(String userNickName);

	public String findProfileID(String userNickName);

}
