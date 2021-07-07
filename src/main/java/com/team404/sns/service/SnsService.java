package com.team404.sns.service;

import java.util.ArrayList;

import com.team404.command.SnsVO;

public interface SnsService {
	public int insert(SnsVO vo);
	public ArrayList<SnsVO> getList();
}
