package com.shty.collect.dao.lnk;

import org.springframework.stereotype.Repository;

import com.shty.collect.domain.lnk.ImgCode;

@Repository
public interface ImgCodeDAO {

	public int addImgCode(ImgCode i);

	public ImgCode getImgCodeById(Long id);

	// 删除
	public int deleteImgCode(Long id);

}
