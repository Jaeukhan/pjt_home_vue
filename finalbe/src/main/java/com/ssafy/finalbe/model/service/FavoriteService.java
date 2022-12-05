package com.ssafy.finalbe.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.finalbe.model.FavoriteAptDto;
import com.ssafy.finalbe.model.FavoriteHouseDto;

public interface FavoriteService {
	//insert
	public int aptInsert(FavoriteAptDto apt) throws Exception;
	public int houseInsert(FavoriteHouseDto house) throws Exception;
		
	//delete
	public int aptDelete(int aptid) throws Exception;
	public int houseDelete(int houseid) throws Exception;
		
	//selectOne
	public FavoriteAptDto getApt(int aptid) throws Exception;
	public FavoriteHouseDto getHouse(int houseid) throws Exception;
		
	//selectAll
	public List<FavoriteAptDto> aptList(String userid) throws Exception;
	public List<FavoriteHouseDto> houseList(String userid) throws Exception;
	
	//check duplicate
//	public List<FavoriteAptDto> checkAptDuplicate(String userid, int aptid) throws Exception;
//	public List<FavoriteHouseDto> checkHouseDuplicate(String userid, int houseid) throws Exception;
}
