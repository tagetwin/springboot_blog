package com.yndg.blog.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yndg.blog.model.ReturnCode;
import com.yndg.blog.model.comment.Comment;
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
	
	@Autowired
	private HttpSession session;
	
	public List<RespListDto> 글목록(){
		List<RespListDto> list = postRepository.findAllVM();
		return list;
	}
	
	public Post 상세보기(int id) {
		
		 Post post = postRepository.findById(id);
		 return post;
	}
	
	@Transactional
	public int 삭제(int id) {

		User principal = (User) session.getAttribute("principal");
		Post post = postRepository.findById(id);

		if (principal.getId() == post.getUserId()) {
			return postRepository.delete(id);

		} else {
			return ReturnCode.오류;
		}
	}
	
	@Transactional
	public int 작성(ReqWriteDto reqWriteDto){
		
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
		
		User principal = (User) session.getAttribute("principal");
		Post post = postRepository.findById(id);
		
		if(principal.getId() == post.getUserId()) {
			return post;
		}else {
			return null;
		}
		
	}
	
	public int 수정(ReqUpdateDto reqUpdateDto){
		
		User principal= (User) session.getAttribute("principal");
		Post post = postRepository.findById(reqUpdateDto.getId());
	
		if(principal.getId() == post.getUserId()) {
			
			int result = postRepository.updateProc(reqUpdateDto);
			return result;
			
		}	return -1;	
	}
	
	public List<Comment> 댓글불러오기(int id) {
		
		List<Comment> comments = postRepository.findByPostId(id);
		return comments;
			
	}
}
