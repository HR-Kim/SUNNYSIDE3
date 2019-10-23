<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가정보 입력</title>
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

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

</head>>
<body>
		
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form flex-sb flex-w" id="joinForm" name="joinForm">
					<input type="hidden" id="isCheck" value="N"/> 
					
					<span class="text-center login100-form-title p-b-32">
						<spring:message code="message.login.add_info"/>
					</span>
					
					<input class="input100" maxlength="20" type="hidden" id="userId" name="userId" value="${user.userId}" >
					<input class="input100" maxlength="15" type="hidden" id="userName" name="userName" value="${user.userName}" >
					<input class="input100" maxlength: 30 type="hidden" id="email" name="email" value="${user.email}" >
					<input class="input100" maxlength: 30 type="hidden" id="flag" name="flag" value="${user.flag}" >

					<span class="txt1 p-b-11">
						<spring:message code="message.login.birth"/>
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100"  maxlength="8" type="text" id="birth" name="birth" placeholder="생년월일 8자리 (ex.19990101)" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						<spring:message code="message.login.cellphone"/>
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="11" type="text" id="cellphone" name="cellphone" placeholder="휴대폰번호 11자리(숫자만) (ex.01012345678)" >
						<span class="focus-input100"></span>
					</div>
					
				</form>
				
				<br/>
				
				<div class="container-login100-form-btn">
					&nbsp &nbsp &nbsp &nbsp &nbsp
					<button class="login100-form-btn" id="join">
						<spring:message code="message.common.confirm"/>
					</button>
					&nbsp &nbsp 
					<button class="login100-form-btn" id="undo">
						<spring:message code="message.common.undo"/>
					</button>
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
	<script src="${context}/resources/js/login/jquery.validate.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
		//취소
		$("#undo").on("click",function(){
			if(false==confirm("추가정보는 반드시 입력해야 합니다.\n회원가입을 취소하시겠습니까?"))return;
			location.href="${context}/login/logout.do"
		});
		
		
		//네이버 추가정보 입력 가입
		$("#join").on("click",function(){
			
			//생년월일 validation
			if( $("#birth").val() == ""){
				alert("생년월일을 입력하세요.");
				return;
			}
			//휴대폰번호 validation
			if( $("#cellphone").val() == ""){
				alert("휴대폰번호을 입력하세요.");
				return;
			}
			
			if(false==confirm("회원가입 하시겠습니까?"))return;
			
			$.ajax({
		         type:"POST",
		         url:"${context}/login/do_save.do",
		         dataType:"html",// JSON
		         data:{
		        	 "userId": $("#userId").val(),
		        	 "userName": $("#userName").val(),
		        	 "email": $("#email").val(),
		        	 "flag": $("#flag").val(),
		        	 "birth": $("#birth").val(),
		        	 "cellphone": $("#cellphone").val()
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