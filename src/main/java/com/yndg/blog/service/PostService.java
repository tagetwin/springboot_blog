package com.yndg.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		List<RespListDto> list = postRepository.findAllVM();
		return list;
	}
	
	public Post 상세보기(int id) {
		
		 Post post = postRepository.findById(id);
		 return post;
	}
	
	@Transactional
	public int 삭제(int id, User principal) {

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
	public Post 수정페이지(int id, User principal){
		
		Post post = postRepository.findById(id);
		
		if(principal.getId() == post.getUserId()) {
			return post;
		}else {
			return null;
		}
		
	}
	
	public int 수정(ReqUpdateDto reqUpdateDto, User principal){
		
		Post post = postRepository.findById(reqUpdateDto.getId());
	
		if(principal.getId() == post.getUserId()) {
			
			int result = postRepository.updateProc(reqUpdateDto);
			return result;
			
		}	return -1;	
	}
	
	public int 게시글수(){
		System.out.println(postRepository.getBoardListCnt());
		return postRepository.getBoardListCnt();
 
	}
	
	public List<Post> 한페이지(int startList, int listSize){

		return postRepository.getBoardList(startList, listSize);
 
	}
}
