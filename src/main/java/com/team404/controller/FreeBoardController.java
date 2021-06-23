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
		System.out.println(pageVO.toString());
		
		model.addAttribute("pageVO",pageVO); //페이지네이션 전달
		model.addAttribute("list",list);
		
		return "freeBoard/freeList";
	}
	
	//상세화면과 변경화면은 동일함으로 묶어서 사용
	@RequestMapping({"/freeDetail","/freeModify"})
	public void getDetail( @RequestParam("bno") int bno, Model model) {
		//getdetail();
		//sql문을 이용해서 FreeBoardVo에 결과값을 반환
		//화면에서 사영할 수 있도록 boardVo 이름으로 model전달하고, 화면에 처리
		FreeBoardVO boardVo = freeService.getDetail(bno);
		model.addAttribute("boardVO", boardVo);
	}
	
	@RequestMapping("/registForm")
	public String registForm(FreeBoardVO vo , RedirectAttributes Ra) {
		int result = freeService.regist(vo); //성공시 1, 실패시 0 반환
		
		if(result ==1) {
			Ra.addFlashAttribute("msg", "등록처리되었습니다");
			
		}else {
			Ra.addFlashAttribute("msg", "등록실패했습니다.");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	
	@RequestMapping("/freeUpdate")
	public String freeUpdate(FreeBoardVO vo, RedirectAttributes Ra) {
		
		/*
		 * 1. form에서 넘어오는 값을 받습니다.
		 * 2. update()를 이용해서 게시글을 수정처리 합니다.
		 * 3. update()메서드는 성공 or 실패의 결과를 받아옵니다.
		 * 4. list화면으로 msg담아서 이동
		 * 
		 */
		
		int  result= freeService.update(vo);
		
		if(result ==1) {
			Ra.addFlashAttribute("msg", "등록처리되었습니다");
			
		}else {
			Ra.addFlashAttribute("msg", "등록실패했습니다.");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	@RequestMapping({"/freeDelete"})
	public String getDelete( @RequestParam("bno") int bno, RedirectAttributes Ra) {
		
		int result = freeService.delete(bno);
		if(result ==1) {
			Ra.addFlashAttribute("msg", "삭제성공");
			
		}else {
			Ra.addFlashAttribute("msg", "삭제실패했습니다.");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	
	
	
}
