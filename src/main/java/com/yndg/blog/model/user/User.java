package com.yndg.blog.model.user;


import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String profile;
	private Timestamp createDate;
	
	
}
