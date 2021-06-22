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

@Controller
@RequestMapping("/freeBoard")
public class FreeBoardController {

	@Autowired
	@Qualifier("freeService")
	private FreeBoardService freeService;
	
	//리스트화면
	@RequestMapping("/freeList")
	public String freeList(Model model) {
		
		ArrayList<FreeBoardVO> list = freeService.getList();
		
		model.addAttribute("list",list);
		
		return "freeBoard/freeList";
	}
	
	//등록화면
	@RequestMapping("/freeRegist")
	public String freeRegist() {
		return "freeBoard/freeRegist";
	}
	
//	//상세화면
//	@RequestMapping("/freeDetail")
//	public String freeDetail(@RequestParam("bno") int bno,
//							 Model model) {
//		
//		//메서드이름 -getDetail()
//		//sql문을 이용해서 FreeBoardVO에 결과값을 반환 받습니다.
//		//화면에서 사용할수 있도록 boardBO이름으로 model을 전달하고, 화면에 처리
//		
//		FreeBoardVO boardVO = freeService.getDetail(bno);
//		model.addAttribute("boardVO", boardVO);
//		
//		return "freeBoard/freeDetail";
//	}
//	
//	//변경화면
//	@RequestMapping("/freeModify")
//	public String freeModify(@RequestParam("bno") int bno,
//							 Model model) {
//
//		FreeBoardVO boardVO = freeService.getDetail(bno);
//		model.addAttribute("boardVO", boardVO);
//		return "freeBoard/freeModify";
//	}

	//상세화면과 변경화면의 내용이 동일하기 때문에 하나로 묶음
	@RequestMapping({"/freeDetail", "/freeModify"})
	public void getDetail(@RequestParam("bno") int bno,
						  Model model) {
	
		FreeBoardVO boardVO = freeService.getDetail(bno);
		model.addAttribute("boardVO", boardVO);
	}
	
	@RequestMapping("/registForm")
	public String registForm(FreeBoardVO vo, RedirectAttributes RA) {
		int result = freeService.regist(vo); //성공시 1, 실패시0
		if(result==1) {
			RA.addFlashAttribute("msg", "등록처리되었습니다.");
		} else {
			RA.addFlashAttribute("msg", "등록에 실패하였습니다");
		}
		return "redirect:/freeBoard/freeList";
	}
	
	//글업데이트 처리
	@RequestMapping("/freeUpdate")
	public String freeUpdate() {
		
		/*
		 * 1. form에서 넘어오는 값을 받습니다.
		 * 2. update()를 이용해서 게시글을 수정처리합니다.
		 * 3. update()메서드는 성공 or실패의 결과를 받아옵니다.
		 * 4. list화면으로 msg담아서 이동 
		 */
		
		return null;
	}
}
