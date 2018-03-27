package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Inventors;

@Repository
public interface InventorsDAO {

	public int addInventors(List<Inventors> inventors);

	public List<Inventors> getInventorsByPatentId(Long patentId);

	// 删除
	public int deleteInventors(Long peopleId);

}
