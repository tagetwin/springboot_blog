package com.yndg.blog.repository;

import com.yndg.blog.model.comment.Comment;
import com.yndg.blog.model.comment.dto.ReqDetailDto;
import com.yndg.blog.model.comment.dto.RespDetailDto;

public interface CommentRepository {

	public int save(ReqDetailDto dto);
	public RespDetailDto findById(int id);
	public int delete(int id);
	public Comment deleteId(int id);
}
