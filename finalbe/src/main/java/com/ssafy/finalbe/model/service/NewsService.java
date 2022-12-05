package com.ssafy.finalbe.model.service;

import java.util.List;

import com.ssafy.finalbe.model.NewsDto;

public interface NewsService {
	public List<NewsDto> newsList() throws Exception;
	public void load( )throws Exception;
	public void newscrolling() throws Exception;
	public void init () throws Exception;
}
