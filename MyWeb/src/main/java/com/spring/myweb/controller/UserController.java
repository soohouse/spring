package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
