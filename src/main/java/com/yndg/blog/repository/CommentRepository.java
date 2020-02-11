package com.yndg.blog.repository;

import java.util.List;

import com.yndg.blog.model.comment.dto.ReqDetailDto;
import com.yndg.blog.model.comment.dto.RespDetailDto;

public interface CommentRepository {

	public int save(ReqDetailDto dto);
	public RespDetailDto findById(int id);
	public int delete(int id);
	public RespDetailDto deleteId(int id);
	public List<RespDetailDto> findByPostId(int postId);
}
