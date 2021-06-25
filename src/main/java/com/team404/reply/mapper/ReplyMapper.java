package com.team404.reply.mapper;

import java.util.ArrayList;

import com.team404.command.FreeReplyVO;

public interface ReplyMapper {
	public int regist(FreeReplyVO vo);
	public ArrayList<FreeReplyVO> getList(int bno);
}
