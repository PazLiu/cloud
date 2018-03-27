package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Publications;

@Repository
public interface PublicationsDAO {

	public int addPublications(Publications publications);

	public List<Publications> getPublicationsByPeopleId(Long peopleId);

	// 删除
	public int deletePublications(Long peopleId);

}
