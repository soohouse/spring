package com.spring.mvc.user.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AutoLoginInterceptor implements HandlerInterceptor {

	public boolean preHandel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("자동 로그인 인터셉터 발동!");
		
		//1. 자동 로그인 쿠키가 있는지의 여부를 확인.
		// -> loginCookie의 존재 유무를 확인.
		
		Cookie[] cookies = request.getCookies();
		
		//true이면 컨트롤러로 요청이 들어가고, false면 요청을 막습니다.
		//자동 로그인 신청 여부와 상관없이 홈 화면은 무조건 봐야 하니까 true를 작성합니다.
		return true;
	}
}
