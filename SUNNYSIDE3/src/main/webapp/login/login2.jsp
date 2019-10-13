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
	<link rel="icon" type="image/png" href="${context}/resources/image/login2/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href=""${context}/resources/css/login2/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/util.css">
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login2/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w">
					<div class="text-right" >
						<select class="input-sm" name="lang" id="lang">
					        <option value="ko">한글</option>
							<option value="en">영어</option>
					     </select>
					</div>
					<span class="login100-form-title p-b-32">
						SUNNYSIDE LOGIN
					</span>

					<span class="txt1 p-b-11">
						ID
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "Username is required">
						<input class="input100" type="text" id="userId" name="userId" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
						<span class="btn-show-pass" id="passwd" name="passwd">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="pass" >
						<span class="focus-input100"></span>
					</div>
				</form>
					
				<div class="flex-sb-m w-full p-b-48">
					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1">
							ID저장
						</label>
					</div>

					<div>
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
				</div>

				<div class="container-login100-form-btn">
					<button class="login100-form-btn" id="signIn">
						로그인
					</button>
				</div>
				<div class="container-login100-form-btn">
					<button class="login100-form-btn" id="naverLogin">
						네이버로 로그인
					</button>
				</div>
				<div class="container-login100-form-btn">
					<button class="login100-form-btn" id="join">
						회원가입
					</button>
				</div>

			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${context}/resources/js/login2/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login2/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login2/popper.js"></script>
	<script src="${context}/resources/js/login2/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login2/moment.min.js"></script>
	<script src="${context}/resources/js/login2/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login2/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login2/main.js"></script>
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