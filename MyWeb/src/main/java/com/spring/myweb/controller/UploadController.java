package com.spring.myweb.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class UploadController {
	
	@GetMapping("/upload")
	public void form() {
		
	}
	
	@PostMapping("/upload_ok")
	public String upload(@RequestParam("file") MultipartFile file) {
		
		String fileRealName = file.getOriginalFilename(); //파일 원본명
		long size = file.getSize();
		
		System.out.println("파일명: " + fileRealName);
		System.out.println("사이즈: " + size);
		
		//DB에는 파일 경로를 저장, 실제 파일은 서버 컴퓨터의 로컬 경로에 저장하는 방식.
		String uploadFolder = "/Users/dood"; //폴더 경로
		String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
		
		/*
        파일 업로드 시 파일명이 동일한 파일이 이미 존재할 수도 있고,
        사용자가 업로드하는 파일명이 영어 이외의 언어로 되어있을 수 있습니다.
        타 언어를 지원하지 않는 환경에서는 정상 동작이 되지 않습니다. (리눅스)
        고유한 랜덤 문자를 통해 DB와 서버에 저장할 파일명을 새롭게 만들어 줍니다.
        */
		
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		String[] uuids = uuid.toString().split("-");
		System.out.println("생성된 고유 문자열: " + uuids[0]);
		System.out.println("확장자명: " + fileExtension);
		
		return "fileupload/upload_ok";
	}

}
