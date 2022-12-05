package com.ssafy.finalbe.model.mapper;

import com.ssafy.finalbe.model.CommentDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
	public int insert(CommentDto comment);
	public List<CommentDto> selectList(int articleno);
}
