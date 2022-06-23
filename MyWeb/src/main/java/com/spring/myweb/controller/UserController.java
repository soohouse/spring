package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.UserVO;
import com.spring.myweb.user.service.IUserService;
import com.spring.myweb.util.MailSendService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//도로명 주소 승인키 -> devU01TX0FVVEgyMDIyMDYyMjE2MTc1NTExMjcxNjk=
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private MailSendService mailservice;
	
	//회원가입 페이지로 이동
	@GetMapping("/userJoin")
	public void userJoin() {
		
	}
	
	//아이디 중복 확인(비동기)
	@ResponseBody //Rest Controller가 아닌 경우에는 아노테이션을 붙여야 비동기 통신이 가능하다.
	@PostMapping("/idCheck")
	public String idCheck(@RequestBody String userId) {
		int result = service.idCheck(userId); //보니까 int로 리턴하니까 우리도 int로 받아주자
		if(result == 1) {
			return "duplicated";
		} else {
			return "ok";
		}
	} 
	
	//이메일 인증
	@GetMapping("mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("이메일 인증 요청 들어옴!");
		System.out.println("인증 이메일: " + email);
		return mailservice.joinEmail(email);
	}
	
	//회원 가입 처리
	@PostMapping("/join")
	public String join(UserVO vo, RedirectAttributes ra) {
		System.out.println("param: " + vo);
		service.join(vo);
		ra.addFlashAttribute("msg", "joinSuccess");
		return "redirect:/user/userLogin";
	}
	
	//로그인 페이지로 이동 요청
	@GetMapping("/userLogin")
	public void userLogin() {
		
	}
	
	//로그인 요청
	@PostMapping("/userLogin")
	public String login(String userId, String userPw, Model model) {
		
	      //mapper의 login 메서드 리턴 타입이 UserVO죠?
	      //그거 모델에 담으세요.
	      //리턴은 /user/userLogin으로 세팅을 하세요.
	      //util 패키지 안에 interceptor 패키지를 생성해서
	      //UserLoginSuccessHandler 클래스를 하나 생성해 주세요.
	      //UserLoginSuccessHandler는 로그인 처리 이후에 실행되는 핸들러를 
	      //오버라이딩 해서 -> 모델을 꺼내오세요.
	      //모덜 내의 데이터가 null인지 아닌지를 확인하셔서 
	      //null이라면 로그인 실패입니다. msg라는 이름으로 loginFail을 담아서
	      //userLogin.jsp 파일로 응답하도록 viewName을 세팅하시고,
	      //null이 아니라면 세션 만드셔서 홈 화면으로 이동시켜 주세요.
		
		model.addAttribute("user", service.login(userId, userPw));
		return "/user/userLogin";
	}
	
	@GetMapping("/userMypage")
	public void userMypage() {
		
	}

}
