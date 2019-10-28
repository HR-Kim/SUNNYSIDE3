<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>SUNNYSIDE LOGIN</title>
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
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet"> 

</head>
<body> 
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="loginForm" name="loginForm">
					<div class="form-group">
						<!-- 로그인 다국어 -->
						<select name="loginLang" id="loginLang">
					        <option value="ko" <c:if test="${loginLang == 'ko'}">selected</c:if> >한글</option>
							<option value="en" <c:if test="${loginLang == 'en'}">selected</c:if> >English</option>
					     </select>
				    </div>
				    
					<span class="text-center login100-form-title p-b-32">
						LOGIN
					</span>

					<span class="txt1 p-b-11">
						ID
					</span>
					<div class="wrap-input100 validate-input m-b-36" >
						<input class="input100" type="text" id="userId" name="userId" >
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" >
						<span class="btn-show-pass" >
						</span>
						<input class="input100" type="password" id="passwd" name="passwd" >
						<span class="focus-input100"></span>
					</div>
				</form>
					

					
				<div class="flex-sb-m w-full p-b-48">
					<div>
						<label class="txt2" for="ckb1">
				           <input  class="txt2 hov1" type="checkbox" id="ckb1" name="ckb1"> &nbsp <spring:message code="message.login.id_save"/>
				        </label>
					</div>	
					<div>
						<a href="#" class="txt2 hov1" id="id_find">
							<spring:message code="message.login.id_find"/>
						</a>
						<a>/</a>
						<a href="#" class="txt2 hov1" id="pw_find">
							<spring:message code="message.login.pw_find"/>
						</a>
						<br/>
						<a href="#" class=" txt2 hov1 text-left" id="join">
							<spring:message code="message.login.join"/>
						</a>
					</div>
				</div>
				
				<!-- 버튼 -->
				<div class="container-login100-form-btn">
					&nbsp &nbsp &nbsp &nbsp &nbsp
					<button class="login100-form-btn" id="signIn">
						LOGIN
					</button>
					&nbsp &nbsp
					<input type="hidden" id="naverUrl" name="naverUrl" value="${url}" >
					<a id="naverLogin"><img src="${context}/resources/image/login/naverlogin.png"/></a>	
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
		

	
		/** 네이버로 로그인 */
		$("#naverLogin").on("click",function(){
			if(confirm("네이버로 로그인 하시겠습니까?")== false)return;
			location.href = $("#naverUrl").val();
		});
		
	
		/** 회원가입 */
		$("#join").on("click",function(){
			location.href = "${context}/login/join_view.do";
		});
	
	
		/** 아이디 찾기 */
		$("#id_find").on("click",function(){
			location.href = "${context}/login/id_find_mng_view.do";
		});
		
		/** 비밀번호 찾기 */
		$("#pw_find").on("click",function(){
			location.href = "${context}/login/pw_find_mng_view.do";
		});
		
	
	
		/** 로그인 */
		$("#signIn").on("click",function(){
			
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
							location.href = "${context}/main/main.do";
	
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
		
		
		/** 로그인창 다국어처리 */
		$("#loginLang").on('change', function() {
			var frm = document.loginForm;
	    	frm.lang.value = $("#loginLang").val();
	    	frm.action = "${context}/login/login_view.do";
	    	frm.submit();
		});

		
		$(document).ready(function(){
			
			/** 쿠키 저장 */
		    var key = getCookie("key");
		    $("#userId").val(key); 
		     
		    if($("#userId").val() != ""){
		        $("#ckb1").attr("checked", true);
		    }
		     
		    $("#ckb1").change(function(){
		        if($("#ckb1").is(":checked")){
		            setCookie("key", $("#userId").val(), 7);
		        }else{
		            deleteCookie("key");
		        }
		    });
		     
		    $("#userId").keyup(function(){
		        if($("#ckb1").is(":checked")){
		            setCookie("key", $("#userId").val(), 7);
		        }
		    });
		    
		 });
		 
		
		 /** 아래 3개 = 쿠키 관련 함수 */
		 function setCookie(cookieName, value, exdays){
		    var exdate = new Date();
		    exdate.setDate(exdate.getDate() + exdays);
		    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
		    document.cookie = cookieName + "=" + cookieValue;
		 }
		 
		 function deleteCookie(cookieName){
		    var expireDate = new Date();
		    expireDate.setDate(expireDate.getDate() - 1);
		    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
		 }
		 
		 function getCookie(cookieName) {
		    cookieName = cookieName + '=';
		    var cookieData = document.cookie;
		    var start = cookieData.indexOf(cookieName);
		    var cookieValue = '';
		    if(start != -1){
		        start += cookieName.length;
		        var end = cookieData.indexOf(';', start);
		        if(end == -1)end = cookieData.length;
		        cookieValue = cookieData.substring(start, end);
		    }
		    return unescape(cookieValue);
		 }

		
	</script>
</body>
</html>