package com.ssafy.finalbe.model.service;

import java.util.List;

import com.ssafy.finalbe.model.CommentDto;

public interface CommentService {
	public int writeComment(CommentDto comment) throws Exception;
	public List<CommentDto> getComments(int articleno) throws Exception;
}
