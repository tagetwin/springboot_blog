package com.yndg.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yndg.blog.model.ReturnCode;
import com.yndg.blog.model.comment.dto.ReqDetailDto;
import com.yndg.blog.model.comment.dto.RespDetailDto;
import com.yndg.blog.model.user.User;
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
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		RespDetailDto comment = commentRepository.deleteId(id); 
		
		if(principal.getId() == comment.getUserId()) {
			return commentRepository.delete(id);
		}else {
			return ReturnCode.오류; 
		}
		
	}
	
	public List<RespDetailDto> 댓글목록보기(int postId) {
		
		return commentRepository.findByPostId(postId);
		
	}
}
