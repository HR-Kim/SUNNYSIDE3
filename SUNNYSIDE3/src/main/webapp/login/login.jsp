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
	<link rel="icon" type="image/png" href="${context}/resources/image/login/favicon.ico"/>
<!--===============================================================================================-->
<%-- 	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/bootstrap.min.css"> --%>
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
<%-- 	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/main.css"> --%>
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="loginForm" name="loginForm" action="${context}/login/do_login.do" method="post">
					<div class="form-group">
						<select name="lang" id="lang">
					        <option value="ko">한글</option>
							<option value="en">영어</option>
					     </select>
				    </div>
					<span class="text-center login100-form-title p-b-32">
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
						<span class="btn-show-pass" >
						</span>
						<input class="input100" type="password" id="passwd" name="passwd" >
						<span class="focus-input100"></span>
					</div>
				</form>
					

					
				<div class="flex-sb-m w-full p-b-48">
					<div>
						<label class="txt2" for="ckb1">
				           <input  class="txt2 hov1" type="checkbox" id="ckb1" name="ckb1"> 아이디 저장
				        </label>
					</div>	
					<div>
						<a href="#" class="txt2 hov1" id="id_find">
							ID
						</a>
						<a>/</a>
						<a href="#" class="txt2 hov1" id="pw_find">
							Password
						</a>
						<span class="txt2">
							찾기
						</span>
						<br/>
						<a href="#" class=" txt2 hov1 text-left" id="join">
							회원가입
						</a>
					</div>
				</div>
				
				<!-- 버튼 -->
				<div class="container-login100-form-btn">
					&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
					<button class="login100-form-btn" id="signIn">
						로그인
					</button>
					&nbsp &nbsp
					<input type="hidden" id="naverUrl" value="${url}">
					<a id="naverLogin"><img src="${context}/resources/image/login/naverlogin.png"/></a>	
				</div>
							
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${context}/resources/js/login/popper.js"></script>
<%-- 	<script src="${context}/resources/js/login/bootstrap.min.js"></script> --%>
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
		
	
		/** 네이버로 로그인 */
		$("#naverLogin").on("click",function(){
			if(confirm("네이버로 로그인 시 등급에 따른 할인혜택을 받을 수 없습니다. 네이버로 로그인 하시겠습니까?")== false)return;
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
			//alert("signIn");
			console.log($("#userId").val());
			console.log($("#passwd").val());
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
		

		
		
		$(document).ready(function(){
			// 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
		    var key = getCookie("key");
		    $("#userId").val(key); 
		     
		    if($("#userId").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
		        $("#ckb1").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
		    }
		     
		    $("#ckb1").change(function(){ // 체크박스에 변화가 있다면,
		        if($("#ckb1").is(":checked")){ // ID 저장하기 체크했을 때,
		            setCookie("key", $("#userId").val(), 7); // 7일 동안 쿠키 보관
		        }else{ // ID 저장하기 체크 해제 시,
		            deleteCookie("key");
		        }
		    });
		     
		    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
		    $("#userId").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
		        if($("#ckb1").is(":checked")){ // ID 저장하기를 체크한 상태라면,
		            setCookie("key", $("#userId").val(), 7); // 7일 동안 쿠키 보관
		        }
		    });
		    
		 });
			 
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