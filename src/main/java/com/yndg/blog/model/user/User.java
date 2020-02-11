package com.yndg.blog.model.user;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User implements UserDetails{
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private String role; //USER, MANAGER, ADMIN
	private Timestamp createDate;
	
	@Builder
	public User(String username, String password, String email, String profile, String role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.role = role;
	}
	
	// username과 password의 getter도 만들어져야 하는데
	// 우리는 필드명을 username과 password로 만들었고 Lombok도 있어서
	// 안만들어지는 것이다.
	
	// 여러개의 권한줄수 있다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<SimpleGrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new SimpleGrantedAuthority("ROLE_"+role));
		return collectors;
	}
	
	// 계정의 만료 여부 (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	// 계정이 잠겨있는지 (true:안 잠김) ex 비밀번호 재시도가 몇번이상일때
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 해당 계정 활성화 여부
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	
	
}
