package com.yndg.blog.model.post.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReqWriteDto {
	
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	private int userId;
}
