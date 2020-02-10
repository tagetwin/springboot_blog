package com.yndg.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yndg.blog.model.comment.dto.ReqDetailDto;
import com.yndg.blog.model.comment.dto.RespDetailDto;
import com.yndg.blog.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	
	public RespDetailDto 댓글쓰기(ReqDetailDto dto) {
		int result = commentRepository.save(dto);
		
		if(result == 1) {  // 댓글쓰기 성공
			// select
			return commentRepository.findById(dto.getId());
			
		}else {
			return null;
		}
	}
	
	
	public int 댓글삭제(int	id) {
		return commentRepository.delete(id);

	}
}
