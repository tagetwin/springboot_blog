package com.yndg.blog.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


// 요리사와 킬러는 각자에 맞는 adapter 패턴을!
// @Contorller, @Configulation, @Service, @Repository

//@Component  // 내가 만든 걸 메모리에 띄운다
public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		if(session.getAttribute("principal") == null) {
			System.out.println("세션 인터셉터 인증 불가");
			response.sendRedirect("/user/login");
			return false;
		}
		System.out.println("세션 인터셉터 인증 통과");
		return true;
	}

}
