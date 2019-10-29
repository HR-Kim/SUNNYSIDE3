<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
	<title>회원정보수정</title>
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
			
			
				<form class="login100-form flex-sb flex-w" id="updateForm" name="updateForm" method="post">
					<input type="hidden" id="flag" name="flag" value="${user.flag}" >
					
					<span class="text-center login100-form-title p-b-32">
						${user.userName}<spring:message code="message.usermypage.info_change"/>
					</span>
					
  					<c:choose>
						<c:when test="${user.flag == '1'}">
						
							<input type="hidden" id="userId" name="userId" value="${user.userId}" >
							<input type="hidden" id="passwd" name="passwd" value="${user.passwd}" >
							<input type="hidden" id="passwdCnfrm" name="passwdCnfrm" value="${user.passwd}" >
							<input type="hidden" id="email" name="email" value="${user.email}" >
							<input type="hidden" id="userName" name="userName" value="${user.userName}" >
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.birth"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100"  maxlength="8" type="text" id="birth" name="birth" value="${user.birth}">
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.cellphone"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100" maxlength="11" type="text" id="cellphone" name="cellphone" value="${user.cellphone}">
								<span class="focus-input100"></span>
							</div>		
											
  						</c:when>
						<c:otherwise>
						
							<span class="txt1 p-b-11">
								<spring:message code="message.login.user_id"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100" maxlength="20" type="text" id="userId" name="userId" value="${user.userId}" disabled="disabled" >
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.passwd"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100" maxlength="20" type="password" id="passwd" name="passwd" value="${user.passwd}" >
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.passwd_confirm"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100" maxlength="20" type="password" id="passwdCnfrm" name="passwdCnfrm" value="${user.passwd}" >
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.email"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100" maxlength: 30 type="text" id="email" name="email" value="${user.email}">
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.user_name"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100" maxlength="15" type="text" id="userName" name="userName" value="${user.userName}">
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.birth"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100"  maxlength="8" type="text" id="birth" name="birth" value="${user.birth}">
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								<spring:message code="message.login.cellphone"/>
							</span>
							<div class="wrap-input100  m-b-20">
								<input class="input100" maxlength="11" type="text" id="cellphone" name="cellphone" value="${user.cellphone}">
								<span class="focus-input100"></span>
							</div>
							
						</c:otherwise>
					</c:choose> 
				</form>
				
				<br/>
				
				<!-- 버튼 -->
				<div class="container-login100-form-btn">
					&nbsp &nbsp &nbsp &nbsp &nbsp
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

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script src="${context}/resources/js/login/jquery.validate.js"></script>
	
	<script type="text/javascript">
		
	
		//취소
		$("#undo").on("click",function(){
			if(false==confirm("수정을 취소하시겠습니까?"))return;
			location.href="${context}/main/main.do" //마이페이지로 변경
		});
	
	
		//수정
		$("#confirm").on("click",function(){
			
			//비밀번호 일치 validation
			if( $("#passwd").val() != $("#passwdCnfrm").val() ){
				alert("비밀번호가 일치하지 않습니다.");
				return;
			}
			//이름 validation
			if( $("#userName").val() == "" || ($("#userName").val()).length < 2 || ($("#userName").val()).length > 15){
				alert("이름을 입력하세요.(2~15자)");
				return;
			}
			//비밀번호 validation
			if( $("#passwd").val() == "" || ($("#passwd").val()).length < 8 || ($("#passwd").val()).length > 20){
				alert("비밀번호를 입력하세요.(8~20자)");
				return;
			}
			//생년월일 validation
			if( $("#birth").val() == "" || ($("#birth").val()).length < 8 || ($("#birth").val()).length > 8){
				alert("생년월일을 8자로 입력하세요. ex)19990101");
				return;
			}
			//휴대폰번호 validation
			if( $("#cellphone").val() == "" || ($("#cellphone").val()).length < 10 || ($("#cellphone").val()).length > 11){
				alert("휴대폰번호를 입력하세요.(10~11자)");
				return;
			}
			//이메일 validation
			if( $("#email").val() == "" || ($("#email").val()).length < 5 || ($("#email").val()).length > 30){
				alert("이메일을 5~30자로 입력하세요.");
				return;
			}
	
			
			if(false==confirm("수정 하시겠습니까?"))return;
			
			$.ajax({
		         type:"POST",
		         url:"${context}/usermypage/do_update.do",
		         dataType:"html",// JSON
		         data:{
		        	 "userId": $("#userId").val(),
		        	 "passwd": $("#passwd").val(),
		        	 "userName": $("#userName").val(),
		        	 "cellphone": $("#cellphone").val(),
		        	 "email": $("#email").val()
		         },
		         success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
		          	//console.log(data); //스트링으로 들어온 데이터
		          	var parseData = $.parseJSON(data); //JSON으로 바꿔줌
		          	if(parseData.msgId == "1"){
		          		alert(parseData.msgMsg);
		          		location.href="${context}/main/main.do" //메인페이지로 바꾸기
		          	}else{
		          		alert(parseData.msgMsg);
		          	}
		          	
		         },
		         complete: function(data){//무조건 수행
		          
		         },
		         error: function(xhr,status,error){
		          
		         }
			});
	     	 
		});
		
		
		
	</script>
</body>
</html>