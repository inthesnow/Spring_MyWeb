<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<link href="http://api.nongsaro.go.kr/css/api.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="http://api.nongsaro.go.kr/js/framework.js"></script>	
	<script type="text/javascript" src="http://api.nongsaro.go.kr/js/openapi_nongsaro.js"></script>
		
	<script type="text/javascript">
		nongsaroOpenApiRequest.apiKey = "09DTPDKUPHLJTUORKIJSFQ?";
		nongsaroOpenApiRequest.serviceName = "garden";
		nongsaroOpenApiRequest.operationName = "lightList";
		nongsaroOpenApiRequest.htmlArea="nongsaroApiLoadingArea";
		nongsaroOpenApiRequest.callback = "http://www.mygreenery.co.kr/ajax_local_callback.jsp";
	</script>

	<div id="nongsaroApiLoadingArea"></div><!-- 검색 HTML 로딩 영역 -->
	<div id="nongsaroApiLoadingAreaResult"></div><!-- 검색결과 HTML 로딩 영역 -->