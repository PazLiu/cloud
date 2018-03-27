package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Recommenders;

@Repository
public interface RecommendersDAO {

	public int addRecommenders(List<Recommenders> recommenders);

	public List<Recommenders> getRecommendersByReceivedId(Long receivedId);

	// 删除
	public int deleteRecommenders(Long peopleId);

}
