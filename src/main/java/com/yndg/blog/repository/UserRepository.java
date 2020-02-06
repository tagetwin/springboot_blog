package com.yndg.blog.repository;

import com.yndg.blog.model.user.dto.ReqJoinDto;

public interface UserRepository {

	//회원가입
	int save(ReqJoinDto dto);
	
	//로그인
	int findByUsername(String username);

}
