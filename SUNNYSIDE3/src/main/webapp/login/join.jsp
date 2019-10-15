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
			<form action="do_update.do" name="frmEdit" id="frmEdit" method="post" class="form-horizontal">
				<div class="form-group">
					<label  class="col-sm-3 control-label">아이디</label>
					<div class="col-sm-6">
						<input type="text" maxlength="20" class="form-control input-sm" name="userId" id="userId" placeholder="아이디" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label  class="col-sm-3 control-label">비밀번호</label>
					<div class="col-sm-6">
						<input type="password" maxlength="300" class="form-control input-sm" name="passwd" id="passwd" placeholder="비밀번호" >
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
						<input type="text" maxlength="5" class="form-control input-sm" name="birth" id="birth" placeholder="생년월일" >
					</div>
				</div>
				
				<div class="form-group">
					<label  class="col-sm-3 control-label">휴대폰번호</label>
					<div class="col-sm-6">
						<input type="text" maxlength="5" class="form-control input-sm" name="cellphone" id="cellphone" placeholder="휴대폰번호" >
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
			    <div class="col-lg-10 col-sm-10 col-xs-10">
			        <div class="text-right">
						<button type="button" class="btn btn-default btn-sm" id="join">가입</button>
						<button type="button" class="btn btn-default btn-sm" id="undo">취소</button>
					</div>
				</div>
			</div>



	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
		
	</script>

</body>
</html>