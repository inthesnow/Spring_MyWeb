package com.team404.sns.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.SnsVO;
import com.team404.sns.mapper.SnsMapper;

@Service("snsService")
public class SnsServiceImpl implements SnsService{

	@Autowired
	private SnsMapper snsMapper;
	
	@Override
	public int insert(SnsVO vo) {
	
		return snsMapper.insert(vo);
	}

	@Override
	public ArrayList<SnsVO> getList() {
		// TODO Auto-generated method stub
		return snsMapper.getList();
	}
	
}
