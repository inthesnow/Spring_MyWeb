package com.team404.freeboard.service;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;

public interface FreeBoardService {
	public int regist(FreeBoardVO vo); //등록
	public ArrayList<FreeBoardVO> getList();
	public FreeBoardVO getDetail(int bno); //상세
	public int update(FreeBoardVO vo);//수정
	public int delete(int bno);
}
