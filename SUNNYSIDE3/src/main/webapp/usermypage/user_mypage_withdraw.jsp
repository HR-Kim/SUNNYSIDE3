<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
	<title>회원탈퇴</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${context}/resources/image/login/favicon.ico"/>
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
			
			
				<form class="login100-form validate-form flex-sb flex-w" id="withdrawForm" name="withdrawForm" method="post">
					
					<span class="text-center login100-form-title p-b-32">
						<spring:message code="message.usermypage.withdrawal"/>
					</span>
					
					<span class="txt1 p-b-11">
						<spring:message code="message.login.user_id"/>
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="20" type="text" id="userId" name="userId" placeholder="아이디 ">
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						<spring:message code="message.login.passwd"/>
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="20" type="password" id="passwd" name="passwd" placeholder="비밀번호" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						<spring:message code="message.login.passwd_confirm"/>
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="20" type="password" id="passwdCnfrm" name="passwdCnfrm" placeholder="비밀번호 확인"  >
						<span class="focus-input100"></span>
					</div>
				</form>
				
				<br/>
				
				<!-- 버튼 -->
				<div class="container-login100-form-btn">
					&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
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
		
	
		//탈퇴
		$("#confirm").on("click",function(){
			//console.log("join");
			
			//비밀번호 validation
			if( $("#passwd").val() != $("#passwdCnfrm").val() ){
				alert("비밀번호가 일치하지 않습니다.");
				return;
			}
			//아이디 validation
			if( $("#userId").val() == ""){
				alert("아이디를 입력하세요.");
				return;
			}
			//비밀번호 validation
			if( $("#passwd").val() == ""){
				alert("비밀번호를 입력하세요.");
				return;
			}
			
			
			
			if(false==confirm("탈퇴 하시겠습니까?"))return;
			
			$.ajax({
		         type:"POST",
		         url:"${context}/usermypage/do_delete.do",
		         dataType:"html",
		         data:{
		        	 "userId": $("#userId").val(),
		        	 "passwd": $("#passwd").val()
		         },
		         success: function(data){
		          	var parseData = $.parseJSON(data);
		          	if(parseData.msgId == "1"){
		          		alert(parseData.msgMsg);
		          		location.href="${context}/login/logout.do"
		          	}else{
		          		alert(parseData.msgMsg);
		          	}
		          	
		         },
		         complete: function(data){
		          
		         },
		         error: function(xhr,status,error){
		          
		         }
			});
	     	 
		});
	
	
	

		
	</script>
</body>
</html>