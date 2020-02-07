package com.yndg.blog.repository;

import java.util.List;

import com.yndg.blog.model.VM.ListVM;
import com.yndg.blog.model.post.Post;

public interface PostRepository {

	// 전체보기
	List<ListVM> findAllVM();
	
	// 상세보기
	Post findById(int id);
}
