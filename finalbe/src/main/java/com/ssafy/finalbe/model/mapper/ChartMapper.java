package com.ssafy.finalbe.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.finalbe.model.ChartDto;

@Mapper
public interface ChartMapper {
	public ChartDto listChart(String name) throws SQLException;
}
