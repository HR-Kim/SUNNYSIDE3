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
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
			
			
				<form class="login100-form validate-form flex-sb flex-w" id="withdrawForm" name="withdrawForm" method="post">
					
					<span class="text-center login100-form-title p-b-32">
						회원정보수정
					</span>
					
					<span class="txt1 p-b-11">
						아이디
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="20" type="text" id="userId" name="userId" value="${vo.boardId }" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						비밀번호
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="20" type="text" id="passwd" name="passwd" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						비밀번호 확인
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="20" type="text" id="passwdCnfrm" name="passwdCnfrm" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						이름
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="15" type="text" id="userName" name="userName" >
						<span class="focus-input100"></span>
					</div>

					<span class="txt1 p-b-11">
						생년월일
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100"  maxlength="8" type="text" id="birth" name="birth" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						휴대폰번호
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength="11" type="text" id="cellphone" name="cellphone" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						이메일주소
					</span>
					<div class="wrap-input100 validate-input m-b-20">
						<input class="input100" maxlength: 30 type="text" id="email" name="email" >
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
	<script src="${context}/resources/js/login/main.js"></script>
	<script src="${context}/resources/js/login/jquery.validate.js"></script>
	<script type="text/javascript">
		
	
		//가입
		$("#confirm").on("click",function(){
			//console.log("join");
			
			//비밀번호 validation
				if( $("#passwd").val() != $("#passwdCnfrm").val() ){
				alert("비밀번호가 일치하지 않습니다.");
				return;
			}
	
			if($("#withdrawForm").valid()==false)return;
			if(false==confirm("탈퇴 하시겠습니까?"))return;
	
			
			$.ajax({
		         type:"POST",
		         url:"${context}/usermypage/do_delete.do",
		         dataType:"html",// JSON
		         data:{
		        	 "userId": $("#userId").val(),
		        	 "passwd": $("#passwd").val()
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