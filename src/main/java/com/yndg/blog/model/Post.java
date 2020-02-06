package com.yndg.blog.model;

import java.security.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	private int id;
	private String title;
	private String content;
	private int userId;
	private Timestamp createDate;
	
	@Builder
	public Post(String title, String content, int userId) {
		this.title = title;
		this.content = content;
		this.userId = userId;
	}
	
	
}
