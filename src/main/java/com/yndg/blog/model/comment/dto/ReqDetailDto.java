package com.yndg.blog.model.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqDetailDto {
	private int id; // 키값 반환을 위해
	private int userId;
	private int postId;
	private String Content;
	
}
