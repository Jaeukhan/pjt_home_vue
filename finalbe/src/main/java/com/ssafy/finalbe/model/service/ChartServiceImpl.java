package com.ssafy.finalbe.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalbe.model.ChartDto;
import com.ssafy.finalbe.model.mapper.ChartMapper;

@Service
public class ChartServiceImpl implements ChartService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ChartDto listChart(String name) throws Exception {
		return sqlSession.getMapper(ChartMapper.class).listChart(name); 
	}

}
