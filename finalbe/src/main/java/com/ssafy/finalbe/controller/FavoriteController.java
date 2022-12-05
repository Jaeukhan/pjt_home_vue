package com.ssafy.finalbe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalbe.model.FavoriteAptDto;
import com.ssafy.finalbe.model.FavoriteHouseDto;
import com.ssafy.finalbe.model.service.FavoriteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/favorite")
@Api("관심매물 컨트롤러  API V1")
public class FavoriteController {
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    
    @Autowired
    private FavoriteService fservice;
    
    @ApiOperation(value="관심 아파트 매물 저장", response = String.class)
    @PostMapping("/apt")
    public ResponseEntity<String> addApt(@RequestBody @ApiParam(value = "아파트 매물 정보") FavoriteAptDto apt) throws Exception {
    	logger.info("addApt - 호출");
    	if(fservice.aptInsert(apt)==1)
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value="관심 주택 매물 저장", response = String.class)
    @PostMapping("/house")
    public ResponseEntity<String> addHouse(@RequestBody @ApiParam(value = "주택 매물 정보") FavoriteHouseDto house) throws Exception {
    	logger.info("addHouse - 호출");
    	if(fservice.houseInsert(house)==1)
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value="관심 아파트 매물 삭제", response = String.class)
    @DeleteMapping("/apt/{aptid}")
    public ResponseEntity<String> deleteApt(@PathVariable("aptid") @ApiParam(value = "아파트 매물 번호") int aptid) throws Exception {
    	if(fservice.aptDelete(aptid)==1)
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value="관심 주택 매물 삭제", response = String.class)
    @DeleteMapping("/house/{houseid}")
    public ResponseEntity<String> deleteHouse(@PathVariable("houseid") @ApiParam(value="주택 매물 번호") int houseid) throws Exception {
    	if(fservice.houseDelete(houseid)==1)
    		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    	return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value="관심 아파트 매물 선택", response = FavoriteAptDto.class)
    @GetMapping("/apt/{aptid}")
    public ResponseEntity<FavoriteAptDto> selectApt(@PathVariable("aptid") @ApiParam(value="아파트 매물 번호") int aptid) throws Exception {
        logger.info("selectApt - 호출 : " + aptid);
    	return new ResponseEntity<FavoriteAptDto>(fservice.getApt(aptid), HttpStatus.OK);
    }
    
    @ApiOperation(value="관심 주택 매물 선택", response = FavoriteHouseDto.class)
    @GetMapping("/house/{houseid}")
    public ResponseEntity<FavoriteHouseDto> selectHouse(@PathVariable("houseid") @ApiParam(value="주택 매물 번호") int houseid) throws Exception {
        logger.info("selectHouse - 호출 : " + houseid);
    	return new ResponseEntity<FavoriteHouseDto>(fservice.getHouse(houseid), HttpStatus.OK);
    }
    
    @ApiOperation(value="관심 아파트 매물 리스트", response = List.class)
    @GetMapping("/aptlist/{userid}")
    public ResponseEntity<List<FavoriteAptDto>> listApt(@PathVariable("userid") @ApiParam(value="유저아이디") String userid) throws Exception {
        logger.info("listApt - 호출 : ");
    	return new ResponseEntity<List<FavoriteAptDto>>(fservice.aptList(userid), HttpStatus.OK);
    }
    
    @ApiOperation(value="관심 주택 매물 리스트", response = FavoriteHouseDto.class)
    @GetMapping("/houselist/{userid}")
    public ResponseEntity<List<FavoriteHouseDto>> listHouse(@PathVariable("userid") @ApiParam(value="유저아이디") String userid) throws Exception {
        logger.info("listHouse - 호출 : ");
    	return new ResponseEntity<List<FavoriteHouseDto>>(fservice.houseList(userid), HttpStatus.OK);
    }
}

