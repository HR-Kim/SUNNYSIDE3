<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
	<title>SUNNYSIDE LOGIN</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/image/login/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/font-awesome.min.css">
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
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
				<form class="login100-form validate-form" id="loginForm" name="loginForm" action="${context}/login/do_login.do" method="post">
					<div class="text-right" >
						<select class="input-sm" name="lang" id="lang">
					        <option value="ko">한글</option>
							<option value="en">영어</option>
					     </select>
					</div>
					</br>
					</br>
					<span class="login100-form-title p-b-33">
						SUNNYSIDE THEATER
					</span>

					<div class="wrap-input100 validate-input" data-validate = "ID를 입력해주세요.">
						<input class="input100" type="text" id="userId" name="userId" placeholder="ID">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>

					<div class="wrap-input100 rs1 validate-input" data-validate="비밀번호를 입력해주세요.">
						<input class="input100" type="password" id="passwd" name="passwd" placeholder="Password">
						<span class="focus-input100-1"></span>
						<span class="focus-input100-2"></span>
					</div>
				</form>
				
				<div class="container-login100-form-btn m-t-20">
					<button class="login100-form-btn" id="signIn">
						로그인
					</button>
				</div>

				<div class="container-login100-form-btn m-t-20">
					<button class="login100-form-btn" id="naverLogin">
						네이버로 로그인
					</button>
				</div>
				
				<div class="text-center p-t-45 p-b-4">
					<a href="#" class="txt2 hov1" id="id_find">
						ID
					</a>
					/
					<a href="#" class="txt2 hov1" id="pw_find">
						Password
					</a>
					<span class="txt1">
						찾기
					</span>
				</div>

				<div class="text-center">
					<a href="#" class="txt2 hov1" id="join">
						회원가입
					</a>
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
	<script src="${context}/resources/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/moment.min.js"></script>
	<script src="${context}/resources/js/login/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/jquery.validate.js"></script>
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    
	
	
		$("#signIn").on("click",function(){
			//alert("signIn");
			if($("#loginForm").valid()==false)return;
			
			$.ajax({
				type : "POST",
				url : "${context}/login/do_login.do",
				dataType : "html",
				data : {
					"lang" :  $("#lang").val(),
					"userId" : $("#userId").val(),
					"passwd" : $("#passwd").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if(null != jData){
						if (jData.msgId == "30") {
							location.href = "http://localhost:8080/sunnyside/logintest/index.jsp";
	
						}else if (jData.msgId == "10") {
							$("#userId").focus();
							alert(jData.msgMsg);
	
						}else if ( jData.msgId == "20") {
							$("#passwd").focus();
							alert(jData.msgMsg);
						}
					}
				},
				complete : function(data) {
	
				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  			
		});
	
		
		
		//form validation
		$("#loginForm").validate({
				rules: {
					userId: {
						required: true,
					},
					passwd: {
						required: true,
					}
				},
				messages: {
					userId: {
						required: "ID를 입력하세요.",
					},
					passwd: {
						required: "비밀번호를 입력하세요.",
					}
				},					
				errorPlacement : function(error, element) {
				     //do nothing
			    },
			    invalidHandler : function(form, validator) {
				    var errors = validator.numberOfInvalids();
				    if (errors) {
					    alert(validator.errorList[0].message);
					    validator.errorList[0].element.focus();
				    }
				}
		 });
		

		$(document).ready(function(){
			//alert("ready");
		});
	</script>
</body>
</html>