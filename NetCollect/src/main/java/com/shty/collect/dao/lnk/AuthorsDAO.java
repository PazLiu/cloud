package com.shty.collect.dao.lnk;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.Authors;

@Repository
public interface AuthorsDAO {

	public int addAuthors(List<Authors> authors);

	public List<Authors> getAuthorsByPublicationId(Long publicationId);

	// 删除
	public int deleteAuthors(Long peopleId);
}
