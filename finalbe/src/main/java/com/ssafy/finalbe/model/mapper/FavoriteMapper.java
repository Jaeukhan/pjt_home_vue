package com.ssafy.finalbe.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.finalbe.model.FavoriteAptDto;
import com.ssafy.finalbe.model.FavoriteHouseDto;

@Mapper
public interface FavoriteMapper {
	//insert
	public int aptInsert(FavoriteAptDto apt) throws SQLException;
	public int houseInsert(FavoriteHouseDto house) throws SQLException;
	
	//delete
	public int aptDelete(int aptid) throws SQLException;
	public int houseDelete(int houseid) throws SQLException;
	
	//selectOne
	public FavoriteAptDto getApt(int aptid) throws SQLException;
	public FavoriteHouseDto getHouse(int houseid) throws SQLException;
	
	//selectAll
	public List<FavoriteAptDto> aptList(String userid) throws SQLException;
	public List<FavoriteHouseDto> houseList(String userid) throws SQLException;
	
}
