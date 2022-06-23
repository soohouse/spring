package com.spring.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myweb.command.UserVO;

public class UserLoginSuccessHandler implements HandlerInterceptor {

	//컨트롤러 동작 이후에 실행되는 핸들러(postHandler
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
			System.out.println("로그인 인터셉터 동작!");
			//modelAndView.getModel().get("key");
			ModelMap mv = modelAndView.getModelMap();
			UserVO vo = (UserVO) mv.get("user");
			System.out.println("interceptor vo : " + vo);
			
			if(vo != null) { //컨트롤러에서 로그인을 성공했던 사람.
				System.out.println("로그인 성공 로직 동작!");
				//로그인 성공한 회원에게 ㅅ ㅔ션 데이터를 생성해서 로그인 유지를 하게 해 줌.
				HttpSession session = request.getSession();
				session.setAttribute("login", vo);
				response.sendRedirect(request.getContextPath());
				
			} else { //vo == null -> 로그인 실패.
				modelAndView.addObject("msg", "loginFail");
				modelAndView.setViewName("/user/userLogin");
			}
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
