package com.team404.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team404.command.MultiUploadVO;
import com.team404.command.uploadVO;

@Controller
@RequestMapping("sns")
public class SnsBoardController {
	//예제 화면처리
	@RequestMapping("/upload")
	public void snsBoard() {
		
	}
	
	//단일 파일업로드 형식
	@RequestMapping("/upload_ok")
	public String upload_ok(@RequestParam("file") MultipartFile file) {
		
		System.out.println(file);
		
		try {
			String fileRealName=file.getOriginalFilename(); //실제 파일명
			Long size = file.getSize();//파일사이즈
			String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());//확장자
			
			System.out.println("파일명 : "+fileRealName);
			System.out.println("파일크기 : "+size);
			System.out.println("파일확장자 : "+fileExtention);
			
			File saveFile = new File(APP_CONSTANT.UPLOAD_PATH+"\\"+fileRealName);//업로드 경로

			file.transferTo(saveFile);//실제로 컬 환경으로 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sns/upload_ok";
	}
	
	//다중 파일업로드형식1
	@RequestMapping("/upload_ok2")
	public String upload_ok2(MultipartHttpServletRequest files) {
		
		List<MultipartFile> file = files.getFiles("file");
		try {
			for(int i=0;i<file.size();i++) {
				
				String fileRealName=file.get(i).getOriginalFilename(); //실제 파일명
				Long size = file.get(i).getSize();//파일사이즈
				String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());//확장자
				
				System.out.println("파일명 : "+fileRealName);
				System.out.println("파일크기 : "+size);
				System.out.println("파일확장자 : "+fileExtention);
				
				File saveFile = new File(APP_CONSTANT.UPLOAD_PATH+"\\"+fileRealName);//업로드 경로
				
				file.get(i).transferTo(saveFile);//실제로 컬 환경으로 저장
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sns/upload_ok";
	}

	//다중파일 업로드 형식2
	@RequestMapping("/upload_ok3")
	public String upload_ok3(@RequestParam("file") List<MultipartFile> file) {
		
		try {
			for(int i=0;i<file.size();i++) {
				
				String fileRealName=file.get(i).getOriginalFilename(); //실제 파일명
				Long size = file.get(i).getSize();//파일사이즈
				String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());//확장자
				
				System.out.println("파일명 : "+fileRealName);
				System.out.println("파일크기 : "+size);
				System.out.println("파일확장자 : "+fileExtention);
				
				File saveFile = new File(APP_CONSTANT.UPLOAD_PATH+"\\"+fileRealName);//업로드 경로
				
				file.get(i).transferTo(saveFile);//실제로 컬 환경으로 저장
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "sns/upload_ok";
	}
	
	//가변적인 폼혁식의 업로드
	@RequestMapping("/upload_ok4")
	public String upload_ok4(MultiUploadVO vo) {
		ArrayList<uploadVO> list = vo.getList();
		try {
			for(int i=0;i<list.size();i++) {
				
				String fileRealName=list.get(i).getFile().getOriginalFilename(); //실제 파일명
				Long size = list.get(i).getFile().getSize();//파일사이즈
				String fileExtention = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());//확장자
				
				String name = list.get(i).getName();//폼에서 작성한이름
				
				System.out.println("파일명 : "+fileRealName);
				System.out.println("파일크기 : "+size);
				System.out.println("파일확장자 : "+fileExtention);
				System.out.println("폼에서 작성한 이름 : "+name);
				
				File saveFile = new File(APP_CONSTANT.UPLOAD_PATH+"\\"+fileRealName);//업로드 경로
				
				list.get(i).getFile().transferTo(saveFile);//실제로 컬 환경으로 저장
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sns/upload_ok";
	}
}
