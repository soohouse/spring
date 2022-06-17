package com.spring.mvc.user.controller;

import java.sql.Date;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;

import oracle.jdbc.proxy.annotation.Post;

/*
 만약에 컨트롤러에 비동기 통신 요청을 받는 메서드가 있다고 해서
 무조건 그 컨트롤러가 restController일 필요는 없습니다.
 일반 컨트롤러에도 @ResponseBody가 붙은 메서드가 있으면
 클라이언트로 값을 바로 리턴할 수 있습니다.
 @RestController는 스프링 4부터 가능한 문법입니다.
 */

@RestController
public class UserController {
	
	@Autowired
	private IUserService service;
	
	//아이디 중복 여부 체크
	@PostMapping("/checkId")
	public String checkId(@RequestBody String account) {
		System.out.println("/user/checkId: POST");
		System.out.println("param: " + account);
		
		int checkNum = service.checkId(account);
		
		if(checkNum == 1) {
			System.out.println("아이디가 중복됨!");
			return "duplicated";
		} else {
			System.out.println("아이디 사용 가능!");
			return "available";
		}
	}
	
	//회원 가입 요청 처리
	@PostMapping("/")
	public String register(@RequestBody UserVO vo) {
		System.out.println("/user/: POST");
		service.register(vo);
		return "joinSuccess";
	}
	
	//로그인 요청 처리
	@PostMapping("/loginCheck")
	public String loginCheck(@RequestBody UserVO vo, /*HttpServletRequest request*/
							HttpSession session,
							HttpServletResponse response) {
		System.out.println("/user/loginCheck: POST");
		System.out.println("param: " + vo);
		
		//서버에서 세션 객체를 얻는 방법
		//HttpServletRequest 객체 사용
		//HttpSession session = request.getSession();
		
		//2. 매개값으로 HttpSession 객체 받아서 사용.
		
		UserVO dbData = service.selectOne(vo.getAccount());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(dbData !=null) {
			if(encoder.matches(vo.getPassword(), dbData.getPassword())) {
				//로그인 성공 회원을 대상으로 세션 정보를 생성
				session.setAttribute("login", dbData);
				
				long limitTime = 60 * 60 * 24 * 90;
				
				//자동 로그인 체크 시 처리해야 할 내용.
				if(vo.isAutoLogin()) {
					//자동 로그인을 희망하는 경우
					//쿠키를 이용하여 자동 로그인 정보를 저장.
					System.out.println("자동 로그인 쿠키 생성 중...");
					//세션 아이디를 가지고 와서 쿠키에 저장(고유한 값이 필요해서)
					Cookie loginCookie = new Cookie("loginCookie", session.getId());
					loginCookie.setPath("/"); //쿠키가 동작할 수 있는 유효한 url.
					loginCookie.setMaxAge((int) limitTime);
					response.addCookie(loginCookie);
					
					//자동 로그인 유지 시간을 날짜 객체로 변환. (DB에 삽입하기 위해, 밀리초)
					long expiredDate = System.currentTimeMillis() + (limitTime*1000);
					//Date객체의 생성자에 매개값으로 밀리초의 정수를 전달하면 날짜 형태로 변경해 줍니다.
					Date limitDate = new Date(expiredDate);
					
					System.out.println("자동 로그인 만료 시간: " + limitDate);
					
					service.keepLogin(session.getId(), limitTime, vo.getAccount());
				}
				
				return "loginSuccess";
			} else {
				return "pwFail";
			}
		} else {
			return "idFail";
		}
	}
	
	//로그아웃 처리
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session, RedirectAttributes ra,
								HttpServletRequest request,
								HttpServletResponse response) {
		System.out.println("/user/logout: GET");
		
//		session.invalidate();
		UserVO user = (UserVO) session.getAttribute("login");
		
		session.removeAttribute("login");//로그인이라는 데이터를 지워라
		
		/*
		 자동 로그인 쿠키가 있는지를 확인(없는 사람도 있으니까요) 
		 쿠키가 존재한다면 쿠키의 수명을 0
		 쿠키를 지우실 때도 setPath를 동일하게 지정해 주어야 합니다.
		 DB의 내용도 바꿔주셔야 합니다.
		 세션ID: 'none', 만료시간: 지금 이 시간 ->
		 */
		
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			loginCookie.setPath("/");
			response.addCookie(loginCookie);
			service.keepLogin("none", new Date, user.getAccount());
		}
		
		ra.addFlashAttribute("msg", "logout");
		
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName(("/");
		
		return new ModelAndView("redirect:/");
	}

}
