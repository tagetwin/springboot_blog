package com.yndg.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yndg.blog.model.Pagination;
import com.yndg.blog.model.ReturnCode;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.model.post.dto.ReqUpdateDto;
import com.yndg.blog.model.post.dto.ReqWriteDto;
import com.yndg.blog.model.post.dto.RespListDto;
import com.yndg.blog.model.user.User;
import com.yndg.blog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<RespListDto> 글목록(Pagination pagination){
		List<RespListDto> list = postRepository.findAllVM(pagination);
		return list;
	}
	
	public Post 상세보기(int id) {
		
		 Post post = postRepository.findById(id);
		 return post;
	}
	
	@Transactional
	public int 삭제(int id) {
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Post post = postRepository.findById(id);

		if (principal.getId() == post.getUserId()) {
			return postRepository.delete(id);

		} else {
			return ReturnCode.오류;
		}
	}
	
	@Transactional
	public int 작성(ReqWriteDto reqWriteDto){
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		reqWriteDto.setUserId(principal.getId());
		try {
			int result = postRepository.save(reqWriteDto);
			
			if(result == 1) {
				return result;
			}else {
				return ReturnCode.오류;
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} 
	}
	
	@Transactional
	public Post 수정페이지(int id){
		
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Post post = postRepository.findById(id);
		
		if(principal.getId() == post.getUserId()) {
			return post;
		}else {
			return null;
		}
		
	}
	
	public int 수정(ReqUpdateDto reqUpdateDto){
		
		// 2. 직접 세션 객체에 접근하여 가져오기
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		Post post = postRepository.findById(reqUpdateDto.getId());
	
		if(principal.getId() == post.getUserId()) {
			
			int result = postRepository.updateProc(reqUpdateDto);
			return result;
			
		}	return -1;	
	}
	
	public int 게시글수(){
		return postRepository.getBoardListCnt();
	}
}
