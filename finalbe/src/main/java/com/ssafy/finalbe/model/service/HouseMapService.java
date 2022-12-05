package com.ssafy.finalbe.model.service;

import java.util.List;

import com.ssafy.finalbe.model.SidoGugunCodeDto;



public interface HouseMapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;

	
}
