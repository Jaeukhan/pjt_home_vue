package com.ssafy.finalbe.controller;

import com.ssafy.finalbe.model.NewsDto;
import com.ssafy.finalbe.model.service.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsServiceImpl nservice;



	@GetMapping
	public void loadnews() throws IOException {
		nservice.load();
	}

	@GetMapping("/read")
	public ResponseEntity<?> readnews() {
		return new ResponseEntity<List<NewsDto>>(nservice.newsList(),HttpStatus.ACCEPTED);
	}

}
