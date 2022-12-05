package com.ssafy.finalbe.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalbe.model.FavoriteAptDto;
import com.ssafy.finalbe.model.FavoriteHouseDto;
import com.ssafy.finalbe.model.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int aptInsert(FavoriteAptDto apt) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).aptInsert(apt);
	}

	@Override
	public int houseInsert(FavoriteHouseDto house) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).houseInsert(house);
	}

	@Override
	public int aptDelete(int aptid) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).aptDelete(aptid);
	}

	@Override
	public int houseDelete(int houseid) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).houseDelete(houseid);
	}

	@Override
	public FavoriteAptDto getApt(int aptid) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).getApt(aptid);
	}

	@Override
	public FavoriteHouseDto getHouse(int houseid) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).getHouse(houseid);
	}

	@Override
	public List<FavoriteAptDto> aptList(String userid) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).aptList(userid);
	}

	@Override
	public List<FavoriteHouseDto> houseList(String userid) throws Exception {
		return sqlSession.getMapper(FavoriteMapper.class).houseList(userid);
	}

}