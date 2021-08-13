package com.team404.sns.mapper;

import java.util.ArrayList;

import com.team404.command.SnsVO;

public interface SnsMapper {
	public int insert(SnsVO vo);
	public ArrayList<SnsVO> getList();
}
