package com.yndg.blog.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yndg.blog.model.RespCM;

@Configuration // 메모리에 띄우기
@EnableWebSecurity // 필터로 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 진입직전에 낚아챈다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder encode() { // 비밀번호 암호
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encode());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { // http 를 관리
		http.csrf().disable();

		http.authorizeRequests() // request 요청을
				.antMatchers("/user/profile", "/post/write/**", "/post/delete/**", "/post/update/**") // 이
																															// 주소는
																															// .
																															// 인증해야한다.
				.authenticated().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
				.anyRequest().permitAll()

				.and()
				.formLogin()
				.loginPage("/user/login")
				.loginProcessingUrl("/user/login") // post 만 낚아챔
				.defaultSuccessUrl("/") // successHandler를 사용할 수 있음
				.successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						PrintWriter out = response.getWriter();
						ObjectMapper mapper = new ObjectMapper();

						String jsonString = mapper.writeValueAsString(new RespCM(200, "ok"));
						out.print(jsonString);
						out.flush();

						System.out.println("successhandler");
					}
				}).failureHandler(new AuthenticationFailureHandler() {

					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
							AuthenticationException exception) throws IOException, ServletException {
						PrintWriter out = response.getWriter();
						ObjectMapper mapper = new ObjectMapper();

						String jsonString = mapper.writeValueAsString(new RespCM(400, "fail"));
						out.print(jsonString);
						out.flush();
						System.out.println(exception.getMessage());
						System.out.println("failhandler");
					}
				});
	}

}
