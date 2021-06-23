package com.team404.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.FreeBoardVO;
import com.team404.freeboard.service.FreeBoardService;
import com.team404.util.Criteria;
import com.team404.util.PageVO;

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {

	@Autowired
	@Qualifier("freeService")
	private FreeBoardService freeService;
	
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		return "freeBoard/freeRegist";
	}
	
	@RequestMapping("/freeList")
	public String freeList(Model model,Criteria cri) {
//		ArrayList<FreeBoardVO> list = freeService.getList(cri);
//		model.addAttribute("list",list);
		
		//페이지
//		ArrayList<FreeBoardVO> list = freeService.getList(cri);
//		int total =freeService.getTotal();
//		PageVO pageVO = new PageVO(cri, total);
		
		//검색 페이징 - 검색 키워드에따라 게시글의 수와, 데이터가 변경되기 때문에
		ArrayList<FreeBoardVO> list = freeService.getList(cri);
		int total = freeService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		
		System.out.println(cri.toString());
		
		model.addAttribute("pageVO",pageVO); //페이지네이션 전달
		model.addAttribute("list",list);
		
		return "freeBoard/freeList";
	}
	
	@RequestMapping({"/freeDetail","/freeModify"})
	public void getDetail( @RequestParam("bno") int bno, Model model) {
		//getdetail();
		//sql문을 이용해서 FreeBoardVo에 결과값을 반환
		//화면에서 사영할 수 있도록 boardVo 이름으로 modelㅂ전환
		FreeBoardVO boardVo = freeService.getDetail(bno);
		model.addAttribute("vo", boardVo);
	}
	
	@RequestMapping("/registForm")
	public String registForm(FreeBoardVO vo , RedirectAttributes Ra) {
		int result = freeService.regist(vo);
		
		if(result ==1) {
			Ra.addFlashAttribute("msg", "등록처리되었습니다");
			
		}else {
			Ra.addFlashAttribute("msg", "등록실패했습니다.");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	
	@RequestMapping("/freeUpdate")
	public String freeUpdate(FreeBoardVO vo, RedirectAttributes Ra) {
		int  ttt= freeService.update(vo);
		
		if(ttt ==1) {
			Ra.addFlashAttribute("msg", "등록처리되었습니다");
			
		}else {
			Ra.addFlashAttribute("msg", "등록실패했습니다.");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	@RequestMapping({"/freeDelete"})
	public String getDelete( @RequestParam("bno") int bno, RedirectAttributes Ra) {
		//getdetail();
		//sql문을 이용해서 FreeBoardVo에 결과값을 반환
		//화면에서 사영할 수 있도록 boardVo 이름으로 modelㅂ전환
		int result = freeService.delete(bno);
		if(result ==1) {
			Ra.addFlashAttribute("msg", "삭제성공");
			
		}else {
			Ra.addFlashAttribute("msg", "삭제실패했습니다.");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	
	
	
}
