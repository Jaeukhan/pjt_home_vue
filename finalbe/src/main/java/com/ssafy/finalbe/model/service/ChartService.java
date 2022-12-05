package com.ssafy.finalbe.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.finalbe.model.ChartDto;

public interface ChartService {
	public ChartDto listChart(String name) throws Exception;
}
