<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>상세보기</p>
                        </div>
                        
                        <form action="freeModify" method="post">
                            <div>
                                <label>DATE</label>
                                <p><fmt:formatDate value="${boardVO.regdate }" pattern="yyyy년MM월dd일"/></p>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name='bno' value="${boardVO.bno }" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='writer' value="${boardVO.writer }" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='title' value="${boardVO.title }" readonly>
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content' readonly>${boardVO.content }</textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">변경</button>
                            <button type="button" class="btn btn-dark" onclick="location.href='freeList'">목록</button>
                    </form>
                </div>
            </div>
        </div>
        </section>
        
        <section style="margin-top: 80px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-9 write-wrap">
                        <form class="reply-wrap">
                            <div class="reply-image">
                                <img src="../resources/img/profile.png">
                            </div>
                            <!--form-control은 부트스트랩의 클래스입니다-->
	                    <div class="reply-content">
	                        <textarea class="form-control" rows="3" name="reply" id="reply"></textarea>
	                        <div class="reply-group">
	                              <div class="reply-input">
	                              <input type="text" class="form-control" placeholder="이름" name="replyId" id="replyId">
	                              <input type="password" class="form-control" placeholder="비밀번호" name="replyPw" id="replyPw">
	                              </div>
	                              
	                              <button type="button" class="right btn btn-info" id="replyRegist">등록하기</button>
	                        </div>
	
	                    </div>
                        </form>

                        <!--여기에접근 반복-->
                        <div id="replyList">
                        <!-- 
                        <div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='../resources/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class='left'>honggildong</strong> 
                                    <small class='left'>2019/12/10</small>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                </div>
                                <p class='clearfix'>여기는 댓글영역</p>
                            </div>
                        </div>
						-->
                        </div>
                        <button tpye="button" class="btn btn-default" id="moreList">더보기</button>
                    </div>
                </div>
            </div>
        </section>
        
	<!-- 모달 -->
	<div class="modal fade" id="replyModal" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
					<h4 class="modal-title">댓글수정</h4>
				</div>
				<div class="modal-body">
					<!-- 수정폼 id값을 확인하세요-->
					<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
						    <input type="hidden" id="modalRno">
							<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
					</div>
					</div>
					<!-- 수정폼끝 -->
				</div>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(function() {
			//등록이벤트
			$("#replyRegist").click(function() {
				var bno = "${boardVO.bno}";
				var reply = $("#reply").val();
				var replyId = $("#replyId").val();
				var replyPw = $("#replyPw").val();
				
				
				if(reply ==''|| replyId==''||replyPw==''){
					alert("이름, 비밀번호, 내용은 필수입니다.");
					return;//함수종료
				}
				
				$.ajax({
					type : "post",
					url : "../reply/replyRegist",
					dataType : "json",
					contentType : "application/json; charset=UTF-8",
					data : JSON.stringify({"bno":bno,"reply":reply,"replyId":replyId,"replyPw":replyPw}),
					success : function(data) {
						if(data==1) {
							$("#reply").val("");
							$("#replyId").val("");
							$("#replyPw").val("");
						} else{
							alert("등록에 실패했습니다. 다시 시도 해주세요");
						}
					},
					error : function(status, error) {
						alert("등록 실패입니다. 잠시 후에 다시 시도하세요");
					}
					
				});
				getList();//댓글등록시 바로 보여줘
			})
			//페이지 기능
			var pageNum=1;//페이지번호
			var str = "";// 댓글목록 누적변수
			
			$("#moreList").click(function() {
				getList(++pageNum, false); //목록호출 (페이지 넘버가 리셋 되어야 하는 경우 true)
			})
			
			getList(1, true);//데이터 조회 메서드 호출
			//데이터 조회
			function getList(pageNum, reset) {
				var bno = "${boardVO.bno}";//게시글번호
				$.getJSON("../reply/getList/"+bno+"/"+pageNum, function(data) {
					//콜백영역
					var list = data.list;
					var total = data.total;
					console.log(data);

					//페이지조건처리
					if(pageNum *20 >= total) {
						$("#moreList").css("display", "none");//더보기 버튼 처리
					} else {
						$("#moreList").css("display","block");
					}
					
					//reset이 true라면 strAdd를 공백으로 비우고 page=1로
					if(reset=true) {
						strAdd="";
						page=1;
					}
					
					for(var i=0; i<list.length;i++){	
					//누적할 문자열을 만들고 innerHTML형식으로 replyList아래에 삽입
					str += "<div class='reply-wrap'>";
                    str += "	<div class='reply-image'>";
                    str += "	    <img src='../resources/img/profile.png'>";
                    str += "	</div>";
                    str += "	<div class='reply-content'>";
                    str += "		<div class='reply-group'>";
                    str += "			<strong class='left'>"+list[i].replyId+"</strong> ";
                    str += "			<small class='left'>"+list[i].timegap+"</small>";
                    str += "		  	<a href='"+list[i].rno+"' class='right modalModify'><span class='glyphicon glyphicon-pencil'></span>수정</a>";
                    str += "		   	<a href='"+list[i].rno+"' class='right modalDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a>";
                    str += "		</div>";
                    str += "		<p class='clearfix'>"+list[i].reply+"</p>";
                    str += "	</div>";
                    str += "</div>";
                    
                    $("#replyList").html(str);
					}
				})
			}//end getList
			
			/* 
				수정삭제
				에이젝스 싱행이 더 늑게 환됴라 괴면, 실제 이벤트 틍록이 먼저일어나게됩낟. (원문수정x)
				부모에 on함수를 이용해서 이벤트를 걸고 이벤트를 a태그에 전파시켜서 사용하는방법.
			*/
			$("#replyList").on("click", "a" ,function(){
				event.preventDefault();
				
				var rno = $(this).attr("href");
				$("#modalRno").val(rno);
				
				
				//replayModify라면 수정창, replyDelete라면 상제창의 형태로 사용
				if($(this).hasClass("modalModify")){//t수정창
					$(".modal-title").html("댓글수정");
					$("#modalModBtn").css("display","inline");//수정버튼이 보여지도록 처리
					$("#modalDelBtn").css("display","none");//삭제버튼이 안보이게 처리
					$("#modalReply").css("display","inline");//수정창이 보여지도록
					
				} else { //삭제창
					$(".modal-title").html("댓글삭제");
					$("#modalModBtn").css("display","none");//수정버튼이 안보여지도록 처리
					$("#modalDelBtn").css("display","inline");//삭제버튼이 보이게 처리
					$("#modalReply").css("display","none");//수정창 가리기
				}
				$("#replyModal").modal("show");
			});
			
			//수정함수
			$("#modalModBtn").click(function() {
				var rno=$("#modalRno").val();
				var reply=$("#modalReply").val();
				var replyPw=$("#modalPw").val();
				
				if(rno==''||reply==''||replyPw=='') {
					alert("내용, 비밀번호는 필수 입니다.");
					return;
				}
				$.ajax({
					type:"post",
					url :"../reply/update",
					contentType:"application/json; charset=UTF-8",
					data : JSON.stringify({"rno": rno, "reply":reply,"replyPw":replyPw}),
					success:function(data){
						if(data==1) { //성공
							$("#modalRno").val("");
							$("#modalReply").val("");
							$("#modalPw").val(""); //값 비우기
							
							$("#replyModal").modal("hide");//모달 내리기
							getList(1, true);
						}else { //실패
							alert("비밀번호를 확인하세요;");
							$("#modalPw").val(""); //값 비우기
						}
					},
					error:function(data) {
						alert("수정에 실패했습니다. 관리자에게 문의하세요");
					}
				})
			})
			
			$("#modalDelBtn").click(function() {
				/* 
					1. 모달창에서 rno값, replyPw값을 얻습니다.
					2. ajax함수를 이용해서 Post방식으로 "reply/delete" 요청을 내보냅니다.
					   필요한 값은 rest api에서 json형식으로 받아서 처리
					3. 서버에서는 요청을 받아서 비밀번호 확인하고, 비밀번호가 일치하면 삭제를 진행합니다.
					4. 비밀번호가 틀린경우는 0을 반환해서 경고창을 띄워주시면됩니다.
				*/
				var rno=$("#modalRno").val();
				var replyPw=$("#modalPw").val();
				
				console.log(rno, replyPw);
				if(rno==''||replyPw=='') {
					alert("비밀번호는 필수 입니다.");
					return;
				}
				$.ajax({
					type:"post",
					url :"../reply/delete",
					contentType:"application/json; charset=UTF-8",
					data : JSON.stringify({"rno": rno, "replyPw":replyPw}),
					success:function(data){
						if(data==1) { //성공						
							$("#modalPw").val("");
							$("#replyModal").modal("hide");//모달 내리기
							getList(1, true);
						}else { //실패
							alert("비밀번호를 확인하세요;");
							$("#modalPw").val(""); //값 비우기
						}
					},
					error:function(data) {
						alert("삭제에 실패했습니다. 관리자에게 문의하세요");
					}
				})
			})
			
		});//end ready
		
	</script>