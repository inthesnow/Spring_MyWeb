package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.FreeReplyVO;
import com.team404.reply.service.ReplyService;
import com.team404.util.Criteria;

@RestController//비동기 전용컨트롤러
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	@Qualifier("replyService")
	private ReplyService replyService;
	
	@PostMapping(value = "/replyRegist", produces = "application/json")//conpath/reply/replyRegist
	public int replyRegist(@RequestBody FreeReplyVO vo) {
		
		int result = replyService.regist(vo);
		
		System.out.println("성공여부 : " +result);
		return result;
	}
	
	//조회메서드
	@GetMapping("/getList/{bno}/{pageNum}")
	public HashMap<String, Object> getList(@PathVariable("bno") int bno,
										  @PathVariable("pageNum") int pageNum) {
		
		//1st-일반댓글
		/*
		 * ArrayList<FreeReplyVO> list = replyService.getList(bno);
		 *  HashMap<String, Object> map = new HashMap<String, Object>(); map.put("list", list);
		 */
		//2nd - 더보기처리
		//1.화면에 더보기버튼생성
		//2. getList(글번호, criteria)를 매개변수로 받습니다.
		//3. mybatis
		//4. sql변경
		//5. 전체댓긄 수를 화면에 전달.
		Criteria cri = new Criteria(pageNum, 20);//20개씩 데이터 조회
		ArrayList<FreeReplyVO> list = replyService.getList(bno, cri);
		
		int total = replyService.getTotal(bno);
		
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("list", list);//댓글목록
		map.put("total", total);//전체게시글수 
		
		return map;
	}
	
	//수정요청
	@PostMapping(value = "update", produces = "application/json")
	public int update(@RequestBody FreeReplyVO vo) {
		
		int count = replyService.pwCheck(vo);
		if(count==1) {
			return replyService.update(vo);
		} else {
			return 0;
		}
	}
	
	//삭제요청
	@PostMapping(value = "delete", produces = "application/json")
	public int delete(@RequestBody FreeReplyVO vo) {
		
		int count = replyService.pwCheck(vo);
		if(count==1) {
			return replyService.delete(vo);
		} else {
			return 0;
		}
		
	}
}
