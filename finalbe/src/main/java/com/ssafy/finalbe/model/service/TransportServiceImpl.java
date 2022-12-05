package com.ssafy.finalbe.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalbe.model.TransportDto;
import com.ssafy.finalbe.model.mapper.TransportMapper;

@Service
public class TransportServiceImpl implements TransportService {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<TransportDto> getList(int sidocode) throws SQLException {
		return sqlSession.getMapper(TransportMapper.class).getList(sidocode);
	}

}
