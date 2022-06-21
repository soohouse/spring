package com.spring.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.myweb.command.FreeBoardVO;
import com.spring.myweb.freeboard.service.IFreeBoardService;
import com.spring.myweb.util.PageCreator;
import com.spring.myweb.util.PageVO;

@Controller
@RequestMapping("/freeboard")
public class FreeBoardController {
	
	@Autowired
	private IFreeBoardService service;
	
	@GetMapping("/freeList")
	public void freeList(PageVO vo, Model model) {
		
		System.out.println(vo);
		PageCreator pc = new PageCreator();
		pc.setPaging(vo);
		pc.setArticleTotalCount(service.getTotal(vo));
		
		System.out.println(pc);
		
		model.addAttribute("boardList", service.getList(vo));
		model.addAttribute("pc", pc);
		
	}
	
	//글쓰기 화면 처리
	@GetMapping("/freeRegist")
	public void freeRegist() {
		
	}
	
	//글 등록 처리
	@PostMapping("/registForm")
	public String registForm(FreeBoardVO vo, RedirectAttributes ra) {
		service.regist(vo);
		ra.addFlashAttribute("msg", "정상 등록 처리되었습니다.");
		return "redirect:/freeboard/freeList";
	}
	
	//글 상세보기 처리
	@GetMapping("/freeDetail/{bno}")
	public String getContent(@PathVariable int bno, @ModelAttribute("p") PageVO vo,
			Model model) {
		model.addAttribute("article", service.getContent(bno));
		
		return "freeboard/freeDetail";
	}
	
	//글 수정페이지 이동 처리
	@GetMapping("/freeModify")
	public void modify(int bno, Model model) {
		model.addAttribute("article", service.getContent(bno));
	}
	
	//글 수정 처리
	@PostMapping("/freeUpdate")
	public String freeUpdate(FreeBoardVO vo, RedirectAttributes ra) {
		service.update(vo);
		ra.addFlashAttribute("msg", "updateSuccess");
		return "redirect:/freeboard/freeDetail/" + vo.getBno();
	}
	
	//글 삭제 처리
	@PostMapping("/freeDelete")
	public String freeDelete(int bno, RedirectAttributes ra) {
		service.delete(bno);
		
		ra.addFlashAttribute("msg", "게시글이 정상적으로 삭제되었습니다.");
		return "redirect:/freeboard/freeList";
	}
}
