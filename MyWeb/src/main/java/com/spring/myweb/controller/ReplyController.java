package com.spring.myweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.ReplyVO;
import com.spring.myweb.reply.service.IReplyService;
import com.spring.myweb.util.PageVO;

import oracle.security.crypto.fips.RNGTest;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	//컨트롤러는 서비스가 필요
	@Autowired
	private IReplyService service;
	
	//댓글 등록
	@PostMapping("/replyRegist")
	public String replyRegist(@RequestBody ReplyVO vo){//, RedirectAttributes ra) {
		//System.out.println("/reply/regist: POST");
		System.out.println("댓글 등록 요청이 들어옴!");
		service.replyRegist(vo);
		//ra.addFlashAttribute("msg", "regSuccess");
		
		return "regSuccess";
	}
	
	//페이징이 추가된 댓글 목록
	@GetMapping("/getList/{bno}/{pageNum}")
	public Map<String, Object> getList(@PathVariable int bno, @PathVariable int pageNum) {
		
		//1. getList 메서드가 (글 번호, 페이지번호)를 매개값으로 받습니다.
		//2. Mapper 인터페이스에 각각 다른 값을 전달하기 위해 Map을 쓰던지, @Param 아노테이션을 사용.
		//3. ReplyMapper.xml에 sql문을 페이징 쿼리로 작성합니다.
		//4. 레스트 방식은 화면에 필요한 값을 여러개 보낼 때, 리턴에 Map이나 VO형식으로
		//필요한 데이터를 한번에 담아서 처리.
		//댓글 목록 리스트와, 전체 댓글 개수를 함께 전달할 예정.
		
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum); //화면에서 전달된 페이지 번호
		vo.setCpp(5); //댓글은 한 화면에 5개씩.
		
		List<ReplyVO> list = service.getList(vo, bno); //댓글 목록 데이터
		int total = service.getTotal(bno); //전체 댓글 개수
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("total", total);
		
		return map;
		
	}
	
	//댓글 수정
	@PostMapping("/update")
	public String update(@RequestBody ReplyVO vo) {
		//System.out.println("댓글 수정 요청이 들어옴!");
		//비밀번호 확인
		int result = service.pwCheck(vo);
		
		if(result == 1) { //비밀번호가 맞는 경우
			service.update(vo);
			return "modSuccess";
		} else {
			return "pwFail";
		}

	}
	
	//댓글 삭제
	@PostMapping("/delete")
	public String delete(@RequestBody ReplyVO vo) {
		int result = service.pwCheck(vo);
		
		if(result == 1 ) {
			service.delete(vo.getRno());
			return "delSuccess";
		} else {
			return "pwFail";
		}
	}
}
