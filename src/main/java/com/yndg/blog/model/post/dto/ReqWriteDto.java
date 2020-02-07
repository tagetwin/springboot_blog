package com.yndg.blog.model.post.dto;

import lombok.Data;

@Data
public class ReqWriteDto {
	
	private String title;
	private String content;
	private int userId;
}
