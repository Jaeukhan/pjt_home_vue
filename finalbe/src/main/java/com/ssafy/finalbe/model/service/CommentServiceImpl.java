package com.ssafy.finalbe.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalbe.model.CommentDto;
import com.ssafy.finalbe.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentMapper cdao;
	
	public int writeComment(CommentDto comment) {
		return cdao.insert(comment);
	}

	public List<CommentDto> getComments(int articleno) {
		return cdao.selectList(articleno);
	}
}
