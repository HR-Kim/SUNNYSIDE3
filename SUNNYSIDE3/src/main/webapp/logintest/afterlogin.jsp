<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NAVER LOGIN TEST</title>
</head>
<body>
	<h1>After Login Main</h1>
	<hr>
	<br>
	<center>
		<h2> 네이버 아이디 로그인 성공하셨습니다!! </h2>
		<h3>'${sessionId}' 님 환영합니다! </h3>
		<h3><a href="logout.do">로그아웃</a></h3>
	</center>
</body>
</html>