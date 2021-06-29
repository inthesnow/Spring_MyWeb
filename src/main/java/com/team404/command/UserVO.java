package com.team404.command;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail1;
	private String userEmail2;
	private String addrZipNum;
	private String addrBasic;
	private String addrDetail;
	private Timestamp regdate;
	
}
