package com.shty.collect.service;

import java.util.List;

import com.shty.collect.domain.TbSystemHttp;

public interface TbSystemHttpServiceI {
	public List<TbSystemHttp> getAllHttp();
	public int insertHttp(TbSystemHttp TbSystemHttp);
	public int updateHttp(TbSystemHttp TbSystemHttp);
	public int deleteHttp(Long id);
	public TbSystemHttp getByidHttp(Long id);
}
