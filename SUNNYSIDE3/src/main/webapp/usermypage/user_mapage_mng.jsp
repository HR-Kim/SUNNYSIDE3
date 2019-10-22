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
			
			
				<form class="login100-form validate-form flex-sb flex-w" id="updateForm" name="updateForm" method="post">
					
					<span class="text-center login100-form-title p-b-32">
						회원정보수정
					</span>
					
<%--  					<c:choose>
						<c:when test="${user.flag == '0'}"> --%>
							<span class="txt1 p-b-11">
								아이디
							</span>
							<div class="wrap-input100 validate-input m-b-20">
								<input class="input100" maxlength="20" type="text" id="userId" name="userId" value="${user.userId}" disabled="disabled" >
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								비밀번호
							</span>
							<div class="wrap-input100 validate-input m-b-20">
								<input class="input100" maxlength="20" type="password" id="passwd" name="passwd" value="${user.passwd}" >
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								비밀번호 확인
							</span>
							<div class="wrap-input100 validate-input m-b-20">
								<input class="input100" maxlength="20" type="password" id="passwdCnfrm" name="passwdCnfrm" value="${user.passwd}" >
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								이메일주소
							</span>
							<div class="wrap-input100 validate-input m-b-20">
								<input class="input100" maxlength: 30 type="text" id="email" name="email" value="${user.email}">
								<span class="focus-input100"></span>
							</div>
							
							<span class="txt1 p-b-11">
								이름
							</span>
							<div class="wrap-input100 validate-input m-b-20">
								<input class="input100" maxlength="15" type="text" id="userName" name="userName" value="${user.userName}">
								<span class="focus-input100"></span>
							</div>
<%--  						</c:when>
						<c:otherwise>
							<div></div>
						</c:otherwise>
					</c:choose> --%>

					<span class="txt1 p-b-11">
						생년월일
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100"  maxlength="8" type="text" id="birth" name="birth" value="${user.birth}">
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						휴대폰번호
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="11" type="text" id="cellphone" name="cellphone" value="${user.cellphone}">
						<span class="focus-input100"></span>
					</div>
					
				</form>
				
				<br/>
				
				<!-- 버튼 -->
				<div class="container-login100-form-btn">
					&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
					<button class="login100-form-btn" id="confirm">
						확인
					</button>
					&nbsp &nbsp  &nbsp
					<button class="login100-form-btn" id="undo">
						취소
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
	<script src="${context}/resources/js/login/jquery.validate.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	
		//수정
		$("#confirm").on("click",function(){
			//console.log("join");
			
			//비밀번호 validation
			if( $("#passwd").val() != $("#passwdCnfrm").val() ){
			alert("비밀번호가 일치하지 않습니다.");
			return;
			}
			
	
			//if($("#updateForm").valid()==false)return;
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
		
		
		//form validation
		$("#updateForm").validate({
				rules: {
					userId: {
						required: true,
						minlength: 3,
						maxlength: 20
					},
					passwd: {
						required: true,
						minlength: 8,
						maxlength: 20
					},
					passwdCnfrm: {
						required: true,
						minlength: 8,
						maxlength: 20
					},
					userName: {
						required: true,
						minlength: 2,
						maxlength: 15
					},
					birth: {
						required: true,
						minlength: 8,
						maxlength: 8
					},
					cellphone: {
						required: true,
						minlength: 10,
						maxlength: 11
					},
					email: {
						required: true,
						minlength: 5,
						maxlength: 30
					}
				},
				messages: {
					userId: {
						required: "ID를 입력 하세요.",
						minlength: $.validator.format("ID를 {0}자 이상 입력하세요."),
						maxlength: $.validator.format("ID를 {0}자 내로 입력하세요.")
					},
					passwd: {
						required: "비밀번호를 입력하세요.",
						minlength: $.validator.format("비밀번호를 {0}자 이상 입력하세요."),
						maxlength: $.validator.format("비밀번호를 {0}자 내로 입력하세요.")
					},
					passwdCnfrm: {
						required: "비밀번호 확인을 입력하세요.",
						minlength: $.validator.format("비밀번호 확인을 {0}자 이상 입력하세요."),
						maxlength: $.validator.format("비밀번호 확인을 {0}자 내로 입력하세요.")
					},
					userName: {
						required: "이름을 입력하세요.",
						minlength: $.validator.format("이름을 {0}자 이상 입력하세요."),
						maxlength: $.validator.format("이름을 {0}자 내로 입력하세요.")
					},
					birth: {
						required: "생년월일을 입력하세요.",
						minlength: $.validator.format("생년월일을 {0}자로 입력하세요. ex)19990101"),
						maxlength: $.validator.format("생년월일을 {0}자로 입력하세요. ex)19990101")
					},
					cellphone: {
						required: "휴대폰번호를 입력하세요.",
						minlength: $.validator.format("휴대폰번호를 {0}자 이상 입력하세요. ex)01012345678"),
						maxlength: $.validator.format("휴대폰번호를 {0}자 내로 입력하세요. ex)01012345678")
					},
					email: {
						required: "이메일을 입력하세요.",
						minlength: $.validator.format("이메일을 {0}자 이상 입력하세요."),
						maxlength: $.validator.format("이메일을 {0}자 내로 입력하세요.")
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
		
		
	

		
	</script>
</body>
</html>