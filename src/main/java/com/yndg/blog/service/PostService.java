package com.yndg.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yndg.blog.model.VM.ListVM;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public List<ListVM> 글목록(){
		
		List<ListVM> list = postRepository.findAllVM();
		
		return list;
	}
	
	public Post 상세보기(int id) {
		
		 Post post = postRepository.findById(id);
		 
		 return post;
		
	}
}
