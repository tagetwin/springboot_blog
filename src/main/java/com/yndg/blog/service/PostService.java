package com.yndg.blog.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yndg.blog.model.ReturnCode;
import com.yndg.blog.model.VM.ListVM;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.model.post.dto.ReqUpdateDto;
import com.yndg.blog.model.post.dto.ReqWriteDto;
import com.yndg.blog.repository.PostRepository;
import com.yndg.blog.util.Script;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private HttpServletResponse resp;
	
	public List<ListVM> 글목록(){
		List<ListVM> list = postRepository.findAllVM();
		return list;
	}
	
	public Post 상세보기(int id) {
		
		 Post post = postRepository.findById(id);
		 return post;
	}
	
	@Transactional
	public int 삭제(int id){
		
		try {
			int result  = postRepository.delete(id);
			System.out.println("PostSevice: "+result);
			
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
		
		try {
			Post post = postRepository.update(id);
			
			if(post != null) {
				return post;
			}else {
				Script.href(resp, "글을 찾을 수 없습니다.", "/post");
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return null; 
	}
	
	@Transactional
	public int 수정(ReqUpdateDto reqUpdateDto){
		
		try {
			int result = postRepository.updateProc(reqUpdateDto);
			
			if(result == 1) {
				return result;
			}else {
				Script.href(resp, "글을 찾을 수 없습니다.", "/post");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
