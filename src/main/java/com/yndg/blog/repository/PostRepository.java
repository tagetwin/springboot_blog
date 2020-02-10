package com.yndg.blog.repository;

import java.util.List;

import com.yndg.blog.model.comment.Comment;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.model.post.dto.ReqUpdateDto;
import com.yndg.blog.model.post.dto.ReqWriteDto;
import com.yndg.blog.model.post.dto.RespListDto;

public interface PostRepository {

	// 전체보기
	List<RespListDto> findAllVM();
	
	// 상세보기
	Post findById(int id);
	
	// 글 삭제
	int delete(int id);
	
	// 글 수정
	Post update(int id);
	
	// 글 수정proc
	int updateProc(ReqUpdateDto reqUpdateDto);
	
	// 글 등록
	int save(ReqWriteDto reqWriteDto);

	// 댓글 불러오기
	public List<Comment> findByPostId(int id);

}
