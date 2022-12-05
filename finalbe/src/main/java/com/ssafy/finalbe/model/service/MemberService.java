package com.ssafy.finalbe.model.service;

import java.sql.SQLException;

import com.ssafy.finalbe.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public int regist(MemberDto memberDto) throws SQLException;
	public int updateInfo(MemberDto memberDto) throws SQLException;
	public int delete(String userid) throws SQLException;
	
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
}
