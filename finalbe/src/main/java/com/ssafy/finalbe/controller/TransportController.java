	package com.ssafy.finalbe.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalbe.model.TransportDto;
import com.ssafy.finalbe.model.service.TransportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/transport")
@Api("버스정류소 컨트롤러  API V1")
public class TransportController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    
    @Autowired
    private TransportService tservice;
    
    @ApiOperation(value="선택한 시에 있는 버스정류장 리스트", response = List.class)
    @GetMapping("/{sidocode}")
    public ResponseEntity<List<TransportDto>> listBus(@PathVariable("sidocode") @ApiParam(value="시도코드") int sidocode) throws Exception {
    	logger.info("listBus- 호출");
    	return new ResponseEntity<List<TransportDto>>(tservice.getList(sidocode), HttpStatus.OK);
    }


}
