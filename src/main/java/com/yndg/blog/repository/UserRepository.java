package com.yndg.blog.repository;

import com.yndg.blog.model.user.User;
import com.yndg.blog.model.user.dto.ReqJoinDto;
import com.yndg.blog.model.user.dto.ReqLoginDto;

public interface UserRepository {

	int save(ReqJoinDto dto); 	// 회원가입
	int findByUsername(String username); 	// 아이디 중복확인
	User findByUsernameAndPassword(ReqLoginDto dto); 	// 로그인
}
