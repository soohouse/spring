package com.spring.mvc.user.controller;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
							HttpSession session) {
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
	public ModelAndView logout(HttpSession session, RedirectAttributes ra) {
		System.out.println("/user/logout: GET");
		
//		session.invalidate();
		session.removeAttribute("login");//로그인이라는 데이터를 지워라
		
		ra.addFlashAttribute("msg", "logout");
		
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName(("/");
		
		return new ModelAndView("redirect:/");
	}

}
