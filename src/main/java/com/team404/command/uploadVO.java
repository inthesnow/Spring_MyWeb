package com.team404.command;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class uploadVO {

	//폼화면에 전달되는 변수를 지정
	private String name;
	private MultipartFile file;
}
