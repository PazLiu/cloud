package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Teamer;

@Repository
public interface TeamerDAO {

	public int addTeamer(List<Teamer> teamer);

	public List<Teamer> getTeamerByProjectId(Long projectId);

	// 删除
	public int deleteTeamer(Long peopleId);

}
