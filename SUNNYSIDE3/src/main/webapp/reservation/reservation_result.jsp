<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>결제완료</title>
		<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
		.MAIN_MSG {
			margin: 20px;
			border-bottom: 2px double black;
			border-top: 2px double black;
			text-align: center;
		}
		.HIGHLIGHT {
			color: red;
		}
	</style>
	</head>
	<body>
		<div class="container">
			<div class="MAIN_MSG">
				<h3>결제가 <h class="HIGHLIGHT">완료</h>되었습니다.</h3>
			</div>
		
		</div>
	
	
		<script src="${context}/resources/js/jquery-1.12.4.js"></script>
		<script src="${context}/resources/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
    		$(document).ready(function(){

    		});
    			

    	</script>
	</body>
</html>