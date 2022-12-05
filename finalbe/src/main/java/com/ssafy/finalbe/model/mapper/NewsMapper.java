package com.ssafy.finalbe.model.mapper;

import java.util.List;

import com.ssafy.finalbe.model.NewsDto;
import org.springframework.stereotype.Repository;


@Repository
public interface NewsMapper {

	List<NewsDto> selectAll();

	void insert(NewsDto newsInfo);

	void init();
	
}
