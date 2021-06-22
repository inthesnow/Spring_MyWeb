package com.team404.freeboard.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.team404.command.FreeBoardVO;

@Mapper
public interface FreeBoardMapper {
	public int regist(FreeBoardVO vo); //등록
	public ArrayList<FreeBoardVO> getList();
	public FreeBoardVO getDetail(int bno); //상세
}
