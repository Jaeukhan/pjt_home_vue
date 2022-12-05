package com.ssafy.finalbe.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.finalbe.model.TransportDto;

@Mapper
public interface TransportMapper {
	public List<TransportDto> getList(int sidocode) throws SQLException;
	
}
