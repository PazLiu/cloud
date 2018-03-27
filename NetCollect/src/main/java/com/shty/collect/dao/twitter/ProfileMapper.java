package com.shty.collect.dao.twitter;
import com.shty.collect.domain.twitter.TwitterProfile;

public interface ProfileMapper {
	
	public void insertNew(TwitterProfile profile);

	public int findProfileByNickName(String userNickName);

	public String findProfileID(String userNickName);
}
