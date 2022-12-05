package com.ssafy.finalbe.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalbe.model.ChartDto;
import com.ssafy.finalbe.model.service.ChartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/chart")
@Api("차트 컨트롤러  API V1")
public class ChartController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ChartService service;

	@ApiOperation(value = "차트 목록", notes = "모든 차트보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<ChartDto>> listChart(@ApiParam(value = "차트를 얻기 위한 부가정보.", required = true) String name)
			throws Exception {
		logger.info("listChart - 호출");
		System.out.println(name);
		return new ResponseEntity<List<ChartDto>>(service.listChart(name), HttpStatus.OK);
	}
}