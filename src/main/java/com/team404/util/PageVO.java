package com.team404.util;

import lombok.Data;

@Data
public class PageVO {
	private int startPage; //첫페이지번호
	private int endPage;// 마지막페이지번호
	private boolean next; //다음페이지번호
	private boolean prev;//이전페이지 번호
	
	private int total;//총게시글수
	private int pageNum;//조회하는 페이지번호(cri에도 존재)
	private int amount;//보여질데이터개수
	
	private Criteria cri;
	//생성자
	public PageVO(Criteria cri, int total) {
		//번호, 개수, 	총게시글 수 초기화
		this.pageNum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		//끝페이지
		//pageNum이 5번 > 끝번호 페이지는 10
		//pageNum이 10번 > 끝번호페이지는 20
		//(int)math.ceil(pageNum/보여질 페이지 수 ) * 보여질 페이지수
		this.endPage = (int)Math.ceil(this.pageNum/10.0)*10;
		
		//시작페이지번호
		//끝페이지-보여질페이지수 +1
		this.startPage = this.endPage-10 +1;
		
		//실제 마지막 번호 페이지
		//ex) 게시클이 53일떄 > 끝번호는 6
		//게시불이112일때 > 끝번호는 12
		//(int)Math.ceil(전세게시글수 /화면에 뿌려질데이터 amount값)
		int realEnd = (int)Math.ceil(this.total/(double)this.amount);
		
		//end page를 다시계산
		//예를 들어 404개 게시글 >
		//페이지넘버는31번 > endPage공식은40, realEnd는 41
		//페이지넘버는 41번 > endPage공식은 50, realEnd=41
		if(realEnd<this.endPage) {
			this.endPage = realEnd;//마지막에 도달했을때 치환
		}
		
		//이전버튼 활성화 여부 - 1보다 크면 활성화
		//startPage는 1, 11, 21, 31 ...
		this.prev = this.startPage >1;
		//다음버튼 활성화여부 -
		this.next = realEnd>this.endPage;
	}
}
