package com.team404.reply.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.FreeReplyVO;
import com.team404.util.Criteria;

public interface ReplyService {
	
	public int regist(FreeReplyVO vo); //등록
	//public ArrayList<FreeReplyVO> getList(int bno);
	public ArrayList<FreeReplyVO> getList(int bno, Criteria cri);
	public int getTotal(int bno);
	
	public int pwCheck(FreeReplyVO vo);//비밀번호 확인
	public int update(FreeReplyVO vo);
	public int delete(FreeReplyVO vo);
}
