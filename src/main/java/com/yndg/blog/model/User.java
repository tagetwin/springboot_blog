package com.yndg.blog.model;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private Timestamp createDate;
	
	@Builder
	public User(String username, String password, String email, String profile) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.profile = profile;
	}
	
	
}
