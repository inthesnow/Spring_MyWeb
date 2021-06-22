<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 첫번쨰 타일즈 템플릿 조립. -->
	<!-- name속성에는 실제파일명과 함께 tiles.xml에서 상ㅇ할 이름 -->
	<tiles:insertAttribute name="header"/>
	<tiles:insertAttribute name="body"/>
</body>
</html>