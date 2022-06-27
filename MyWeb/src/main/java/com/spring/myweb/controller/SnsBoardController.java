package com.spring.myweb.controller;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.myweb.command.SnsBoardVO;
import com.spring.myweb.command.UserVO;
import com.spring.myweb.snsboard.service.ISnsBoardService;
import com.spring.myweb.util.PageVO;

@Controller
@RequestMapping("/snsBoard")
public class SnsBoardController {
	
	@Autowired
	private ISnsBoardService service;
	
	@GetMapping("/snsList")
	public void snsList() {
		
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String upload(MultipartFile file, String content, HttpSession session) {
		
		String writer = ((UserVO) session.getAttribute("login")).getUserId();
		
		//날짜별로 폴더를 생성해서 파일을 관리
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String fileloca = sdf.format(date);
		
		//저장할 폴더 경로
		String uploadPath = "/Users/dood/Desktop/test" + fileloca;
		
		File folder = new File(uploadPath);
		if(!folder.exists()) {
			folder.mkdirs(); //폴더가 존재하지 않으면 생성하라.
		}
		
		String fileRealName = file.getOriginalFilename();
		
		//파일명을 고유한 랜덤 문자로 생성.
		UUID uuid = UUID.randomUUID();
		String uuids = uuid.toString().replaceAll("-", "");
		
		//확장자를 추출합니다.
		String fileExtension = fileRealName.substring(fileRealName.indexOf("."), fileRealName.length());
		
		System.out.println("저장할 폴더 경로: " + uploadPath);
		System.out.println("실제 파일명: " + fileRealName);
		System.out.println("폴더명: " + fileloca);
		System.out.println("확장자: " + fileExtension);
		System.out.println("고유랜덤문자: " + uuids);
		
		String fileName = uuids + fileExtension;
		System.out.println("변경해서 저장할 파일명: " + fileName);
		
		//업로드한 파일을 서버 컴퓨터 내의 지정한 경로에 실제로 저장.
		File saveFile = new File(uploadPath + "\\" + fileName);
		try {
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//DB에 insert 작업을 실행.
		SnsBoardVO snsVo = new SnsBoardVO(0, writer, uploadPath, fileloca, fileName, fileRealName, content, null);
		service.insert(snsVo);
		return "success";
				
	}
	
	//비동기 통신 후 가져올 글 목록
	@GetMapping("/getList")
	@ResponseBody
	public List<SnsBoardVO> getList(PageVO paging) {
		paging.setCpp(3);
		return service.getList(paging);
	}
	
	
	
}
