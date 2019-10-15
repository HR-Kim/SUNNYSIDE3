<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
		
		<div>
			<br/>
			<br/>
			<br/>
			<br/>
			<br/>
		</div>
		
		<!-- 입력 Form -->
		<div class="container">
			<h2>회원가입</h2>
			<hr/>
			<br/>
			<!-- 입력 Form -->
			<form action="do_update.do" name="joinForm" id="joinForm" method="post" class="form-horizontal">
				<input type="hidden" id="isCheck" value="N"/> 
				
				<div class="form-group">
					<label  class="col-sm-3 control-label">아이디</label>
					<div class="col-sm-5">
						<input type="text" maxlength="20" class="form-control input-sm" name="userId" id="userId" placeholder="아이디 (3~20자)">
					</div>
					<div class="col-sm-2">
						<button type="button" class="btn btn-default btn-sm" id="idCheck">중복체크</button>
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-3 control-label">비밀번호</label>
					<div class="col-sm-6">
						<input type="password" maxlength="300" class="form-control input-sm" name="passwd" id="passwd" placeholder="비밀번호 (8~20자)" >
					</div>
				</div>
				
				<div class="form-group">
					<label  class="col-sm-3 control-label">비밀번호 확인</label>
					<div class="col-sm-6">
						<input type="password" maxlength="300" class="form-control input-sm" name="passwdCnfrm" id="passwdCnfrm" placeholder="비밀번호 확인" >
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-3 control-label">이름</label>
					<div class="col-sm-6">
						<input type="text" maxlength="300" class="form-control input-sm" name="userName" id="userName" placeholder="이름" >
					</div>
				</div>	
	     		<div class="form-group">
					<label  class="col-sm-3 control-label">생년월일</label>
					<div class="col-sm-6">
						<input type="text" maxlength="8" class="form-control input-sm" name="birth" id="birth" placeholder="생년월일 8자리 (ex.19990101)" >
					</div>
				</div>
				
				<div class="form-group">
					<label  class="col-sm-3 control-label">휴대폰번호</label>
					<div class="col-sm-6">
						<input type="text" maxlength="11" class="form-control input-sm" name="cellphone" id="cellphone" placeholder="휴대폰번호 11자리(숫자만) (ex.01012345678)" >
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-3 control-label">이메일</label>
					<div class="col-sm-6">
						<input type="text" maxlength="320" class="form-control input-sm" name="email" id="email" placeholder="이메일" >
					</div>
				</div>
			</form>
			<!-- //입력 Form -->
				
			<br/>
		    <!-- Button Area -->
			<div class="row">
				  <div class="col-md-1"></div>
				  <div class="col-md-1"></div>
				  <div class="col-md-1"></div>
				  <div class="col-md-1"></div>
				  <div class="col-md-1"></div>
				  
				  <div class="col-md-4">
			        <div class="text-right">
						<button type="button" class="btn btn-default btn-sm" id="join">가입</button>
						&nbsp
						<button type="button" class="btn btn-default btn-sm" id="undo">취소</button>
					</div>
				  </div>
				  
				  <div class="col-md-1"></div>
				  <div class="col-md-1"></div>
				  
			</div>



	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<script src="${context}/resources/js/login/jquery.validate.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	
		//취소
		$("#undo").on("click",function(){
			if(false==confirm("회원가입을 취소하시겠습니까?"))return;
			location.href="https://www.naver.com/" //메인페이지로 바꾸기
		});
	
		
		//아이디 중복체크 idCheck
		$("#idCheck").on("click",function(){
			
			//validation
 			if( $("#userId").val() == "" ){
				alert("아이디를 입력해주세요.");
				return;
			} 
			
			$.ajax({
		         type:"POST",
		         url:"${context}/login/id_check.do",
		         dataType:"html",// JSON
		         data:{
		        	 "userId": $("#userId").val()
		         },
		         success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
		          	//console.log(data); //스트링으로 들어온 데이터
		          	var parseData = $.parseJSON(data); //JSON으로 바꿔줌
		          	if(parseData.msgId == "20"){
		          		alert(parseData.msgMsg);
		          		$("#isCheck").val("Y");
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
		
		
		
		//가입
		$("#join").on("click",function(){
			//console.log("join");
			
			//아이디 중복체크  validation
 			if( $("#isCheck").val() != "Y" ){
				alert("아이디 중복체크는 필수입니다.");
				return;
			} 
 			
			//비밀번호 validation
 			if( $("#passwd").val() != $("#passwdCnfrm").val() ){
				alert("비밀번호가 일치하지 않습니다.");
				return;
			}

			if($("#joinForm").valid()==false)return;
			if(false==confirm("회원가입 하시겠습니까?"))return;

			
			$.ajax({
		         type:"POST",
		         url:"${context}/login/do_save.do",
		         dataType:"html",// JSON
		         data:{
		        	 "userId": $("#userId").val(),
		        	 "passwd": $("#passwd").val(),
		        	 "userName": $("#userName").val(),
		        	 "birth": $("#birth").val(),
		        	 "cellphone": $("#cellphone").val(),
		        	 "email": $("#email").val()
		         },
		         success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
		          	//console.log(data); //스트링으로 들어온 데이터
		          	var parseData = $.parseJSON(data); //JSON으로 바꿔줌
		          	if(parseData.msgId == "1"){
		          		alert(parseData.msgMsg);
		          		location.href="https://www.naver.com/" //메인페이지로 바꾸기
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
		$("#joinForm").validate({
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
						minlength: $.validator.format("생년월일을 {0}자로 입력하세요."),
						maxlength: $.validator.format("생년월일을 {0}자로 입력하세요.")
					},
					cellphone: {
						required: "휴대폰번호를 입력하세요.",
						minlength: $.validator.format("휴대폰번호를 {0}자 이상 입력하세요."),
						maxlength: $.validator.format("휴대폰번호를 {0}자 내로 입력하세요.")
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
		
		
		$(document).ready(function() {
			//alert("ready");
		});
		
	</script>

</body>
</html>