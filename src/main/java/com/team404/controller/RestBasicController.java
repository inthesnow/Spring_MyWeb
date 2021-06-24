package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.velocity.texen.ant.TexenTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.TestVO;

@RestController //비동기 전용컨트롤러(@RequestBody+ResponseBody)
public class RestBasicController {
	
	//1. restController는 기본적으로 return에 담기는 값이 리졸버 뷰로 가는게 아니고, 요청된 주소로 반환됩니다.
	//2. rest API에서는 produces키워드는 보내는 데이터에ㅐ 대한 내용
	//				  consumers전달받는 데이터에대한내용
	
	//@RequestMapping(value="/getText", method=RequestMethod.GET)
	@GetMapping(value="/getText", produces = "test/plain")
	public String getText() {
		System.out.println("실행됨");
		return "hello world";
	}
	
	//자바에서는 json형식을 받고, json형식으로 보낼때 jackson라이브러리를 반드시 필요
	//json형식의 데이터라면 produces에 내용을 생략할 수 있습니다.
	
	@GetMapping(value="/getObject", produces = "applicatuin/json")
	public TestVO getObject() {
		return new TestVO("홍길동","20","2020",2000);
	}
	
	//단일 값을 받고, 객체를 반환
	@GetMapping(value = "/getCollection")
	public ArrayList<TestVO> getCollection(@RequestParam("num") String num) {
		System.out.println("받은 값 : "+ num);
		
		ArrayList<TestVO> list = new ArrayList<TestVO>();
		for(int i = 1; i <=10; i++) {
			TestVO vo = new TestVO("홍길동",i+"","2020",2000);
			list.add(vo);
		}
		
		return list;
	}
	
	//값/값/값 형태로 받고 Map으로 반환
	@GetMapping("/getPath/{sort}/{rank}/{page}")
	public HashMap<String, TestVO> getPath(@PathVariable("sort") String sort,
										   @PathVariable("rank") String rank,
	   									   @PathVariable("page") int page) {
		
		HashMap<String, TestVO> map = new HashMap<String, TestVO>();
		TestVO vo = new TestVO("홍길동", "20", "2020", 2000);
		map.put("info", vo);		
		
		return map;
	}
	
	//포스트형식의 JSON형식의 데이터를 받는다.
	//요청값의 키 :값을 뽑아서 vo에 자동으로 매핑하는 태그 @RequestBody
	//consumer를 작성하게 되면, 해당 데이터 타입이 아니라면 요청을 거절 합니다.
	@PostMapping(value = "/getJson", consumes = "application/json")
	public ArrayList<TestVO> getJson(@RequestBody TestVO vo) {
		
		System.out.println(vo.toString());
		
		ArrayList<TestVO> list = new ArrayList<TestVO>();
		TestVO t = new TestVO("홍길동", "20", "2020", 2000);
		list.add(t);
		
		return list;
	}
}
