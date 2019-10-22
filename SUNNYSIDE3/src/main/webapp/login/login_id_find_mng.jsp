<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
	<title>아이디찾기</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/util.css">
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/main.css">
<!--===============================================================================================-->
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
			
			
				<form class="login100-form validate-form flex-sb flex-w" id="idFindForm" name="idFindForm" method="post">
					
					<span class="text-center login100-form-title p-b-32">
						<spring:message code="message.login.id_find"/>
					</span>
					
					<span class="txt1 p-b-11">
						<spring:message code="message.login.user_name"/>
					</span>
					<div class="wrap-input100 validate-input m-b-15">
						<input class="input100" type="text" id="userName" name="userName" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						<spring:message code="message.login.email"/>
					</span>
					<div class="wrap-input100 validate-input m-b-15">
						<input class="input100" type="text" id="email" name="email" >
						<span class="focus-input100"></span>
					</div>
				</form>
				
				<br/>
				
				<!-- 버튼 -->
				<div class="container-login100-form-btn">
					&nbsp &nbsp &nbsp &nbsp
					<button class="login100-form-btn" id="confirm">
						<spring:message code="message.common.confirm"/>
					</button>
					&nbsp &nbsp  &nbsp
					<button class="login100-form-btn" id="undo">
						<spring:message code="message.common.undo"/>
					</button>
				</div>
			
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/popper.js"></script>
	<script src="${context}/resources/js/login/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/moment.min.js"></script>
	<script src="${context}/resources/js/login/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/main.js"></script>
	<script src="${context}/resources/js/login/jquery.validate.js"></script>
	<script type="text/javascript">
	

		
		/** 아이디찾기 확인 버튼 */
		$("#confirm").on("click",function(){
			//if($("#idFindForm").valid()==false)return;
			
	    	var frm = document.idFindForm;
			frm.action = "${context}/login/id_find.do"
	    	frm.submit();
		});
		
		
		
		/** 취소 버튼 */
		$("#undo").on("click",function(){
			location.href = "${context}/login/login_view.do";
		});
		
	
		$(document).ready(function(){
			
		});
		
	</script>
</body>
</html>