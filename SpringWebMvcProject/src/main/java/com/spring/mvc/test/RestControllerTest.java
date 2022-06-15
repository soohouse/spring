package com.spring.mvc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestControllerTest {

	/*
	 @ResponseBody
	 - 메서드가 리턴하는 데이터를 viewResResoler"
	 클라이언트에게 해당 데이터를 바로 응답하게 합니ㅏ.
	 비동기 통신에서 주로 많이 사용합니다.
	 - @RestController를 사용하면 모든 메서드에
	 @ResponseBody를 붙인 결과와 같습니다.
	 */
	@GetMapping("/hello")
	//@ResponseBody
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping("/hobby")
	//@ResponseBody
	public List<String> hobby() {
		List<String> hobby = Arrays.asList("축구", "영화감상", "수영");
		return hobby;
	}
	
	@GetMapping("/study")
	public Map<String, Object> study() {
		Map<String, Object> subject = new HashMap<>();
		subject.put("자바", "java");
		subject.put("jsp", "java server pages");
		subject.put("스프링", "spring framework5");
		
		return subject;
	}
	
	@GetMapping("/person")
	public Person person() {
		Person p = new Person();
		p.setName("김철수");
		p.setAge(30);
		p.setHobby(Arrays.asList("수영", "축구", "독서"));
		
		return p;
	}
}
