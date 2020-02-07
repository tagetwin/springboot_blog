package com.yndg.blog.model.post.dto;

import lombok.Data;

@Data
public class ReqUpdateDto {
	private int id;
	private String title;
	private String content;
	private int userId;
	
}
