package com.yndg.blog.repository;

import java.util.List;

import com.yndg.blog.model.Criteria;
import com.yndg.blog.model.post.Post;
import com.yndg.blog.model.post.dto.ReqUpdateDto;
import com.yndg.blog.model.post.dto.ReqWriteDto;
import com.yndg.blog.model.post.dto.RespListDto;

public interface PostRepository {

	// 전체보기
	List<RespListDto> findAllVM(Criteria cri);
	
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

	// 글 전체개수 불러오기
	int getBoardListCnt(Criteria cri);
	
	// 글 한 페이지 불러오기
	List<Post> getBoardList(int startList, int listSize);
	
}
