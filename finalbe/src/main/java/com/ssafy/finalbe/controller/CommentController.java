package com.ssafy.finalbe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.finalbe.model.CommentDto;
import com.ssafy.finalbe.model.service.CommentService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = {"*"}, maxAge = 6000	)
public class CommentController {
	@Autowired
	private CommentService service;
	
	@GetMapping("/list/{articleno}")
	@ResponseBody //  ViewResolver한테 가지말고 이게 그냥 다이렉트 응답이다. jsp찾으러 갈필요X.
	public List<CommentDto> commentList(@PathVariable("articleno") @ApiParam(value = "얻어올 글의 글번호.", required = true) int articleno) throws Exception{
		return service.getComments(articleno);//gson 라이브러리로 js 객체 형태로 만들어줬음.스프링이 이제 자동으로해줌.
	}
	
	@PostMapping("/write")
	@ResponseBody //view가아니라 응답데이터를 바로보낼것이다
	public String write(@RequestBody CommentDto comment) throws Exception {//프런트에서 백으로 오는 데이터(POST) RequestBody
		System.out.println(comment.getCwriter());
		if(service.writeComment(comment)==1) {
			return "success";
		}else
			return "fail";
	}
	
}
