<%@page import="kr.co.sunnyside.qna.service.KYMQnaVO"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <c:set var="context" value="${pageContext.request.contextPath }" />
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title><spring:message code="message.header.questions"/></title>
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- div container -->
	<div class="container" style="margin-bottom: 70px">
		<!-- div title -->
		<div class="page-header">
			<h1><spring:message code="message.header.questions"/></h1>
		</div>
		<!--// div title -->
		<!-- Button Area -->
		<div class="row">
			<div class="col-lg-10 col-sm-10 col-xs-10">
				<div class="text-right">
					<c:choose>
						<c:when test="${'admin' == user.userLevel}">
								<!-- 답변달기 -->
								<button type="button" class="btn btn-default btn-sm" id="doSelectOneAdmin"><spring:message code="message.qna.reply"/></button>		
						</c:when>
					</c:choose>
					<!-- <button type="button" class="btn btn-default btn-sm" id="doSelectOneAdmin">답변달기</button> -->
					<!-- 목록 -->
					<button type="button" class="btn btn-default btn-sm" id="doRetrieve"><spring:message code="message.button.list"/></button>
					<!-- 초기화 -->
					<button type="button" class="btn btn-default btn-sm" id="doInit"><spring:message code="message.button.init"/></button>
					<!-- 등록 -->
					<button type="button" class="btn btn-default btn-sm" id="doSave"><spring:message code="message.button.save"/></button>
					<!-- 수정 -->
					<button type="button" class="btn btn-default btn-sm" id="doUpdate"><spring:message code="message.button.edit"/></button>
					<!-- 삭제 -->
					<button type="button" class="btn btn-default btn-sm" id="doDelete"><spring:message code="message.button.delete"/></button>
				</div>
			</div>
		</div>
		<br>
		<div class="col-lg-11"></div>
		<!-- div title -->
		<form class="form-horizontal" name="qnaEditFrm" id="qnaEditFrm" method="POST" action="${context}/qna/do_save.do">
			<input type="hidden" class="form-control" name="userId" id="userId" value="${user.userId }">
			<input type="hidden" id="qnaNum" value="<c:out value='${vo.qnaNum }' />">
			<div class="form-group">
				<!-- 제목 -->
				<label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.qna.title"/></label>
			<div class="col-sm-8">
					<input type="text" class="form-control" name="title" id="title" placeholder='<spring:message code="message.qna.title"/>' value="<c:out value='${vo.title }' />">
				</div>
			</div>

			<div class="form-group">
				<!-- 내용 -->
				<label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.qna.contents"/></label>
				<div class="col-sm-8">
					<textarea class="form-control" name="contents" id="contents" rows="7" placeholder='<spring:message code="message.qna.contents"/>'><c:out value="${vo.contents }" /></textarea>
				</div>
			</div>

			<div class="form-group">
				<!-- 등록일 -->
				<label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.qna.reg_dt"/></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="regDt" id="regDt" placeholder='<spring:message code="message.qna.reg_dt"/>' value="<c:out value='${vo.regDt }' />" disabled="disabled">
				</div>
			</div> 
			
			<hr/>
			
			<div id="a" class="form-group">
				<!-- 답변내용 -->
				<label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.qna.replycontents"/></label>
				<div class="col-sm-8">
					<!-- 답변대기 -->
					<textarea class="form-control" name="reContents" id="reContents" rows="7" placeholder='<spring:message code="message.qna.waiting"/>' disabled="disabled"><c:out value="${vo.reContents }" /></textarea>
				</div>
			</div>
					
		</form>
	</div>
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<%-- <script src="${context}/resources/js/jquery.validate.js"></script> --%>
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
		//답변달기
		$("#doSelectOneAdmin").on("click",function(){
		
			//alert("listTable");
			var trs = $(this);
			var td  = trs.children();
			if(null==td || td.length==1)return;
			//alert("td.length:"+td.length);
			var test = $("#userId").val();

			//console.log("userId:"+userId);
			var frm = document.qnaEditFrm;
			frm.action = "${context}/qna/do_selectOne_admin.do";
			frm.submit();
		
	}); 
	
		//초기화
		$("#doInit").on("click",function(){
			//alert("doInit");
			$("#title").val("");
			$("#contents").val(""); 
		});
		
		//목록이동
		$("#doRetrieve").on("click",function(){
			//alert("doRetrieve");
			//board_attr/get_retrieve.do
			if(confirm("목록으로 이동 하시겠습니까?")== false)return;
			location.href ="${context}/qna/do_retrieve.do";
		});    
	
		//등록	    
		$("#doSave").on("click", function() {
			//validation
			/* if($("#qnaEditFrm").valid()==false)return; */
			if (confirm("등록 하시겠습니까?") == false)return;
	
			$.ajax({
				type : "POST",
				url : "${context}/qna/do_save.do",
				dataType : "html",
				data : {
					"userId" : $("#userId").val(),
					"title" : $("#title").val(),
					"contents" : $("#contents").val(),
					"status" : "답변대기"
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/qna/do_retrieve.do";

					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
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

		//수정:submit->control->qna_mng.jsp: (성공)?qna_list.jsp:qna_mng.jsp
		//삭제:submit->
		$("#doUpdate").on("click", function() {
			//validation
			/* if($("#qnaEditFrm").valid()==false)return; */
			
			console.log("userId:" + $("#userId").val());
			if (confirm("수정 하시겠습니까?") == false)
				return;

			$.ajax({
				type : "POST",
				url : "${context}/qna/do_update.do",
				dataType : "html",
				data : {
					"userId" : $("#userId").val(),
					"qnaNum" : $("#qnaNum").val(),
					"title" : $("#title").val(),
					"contents" : $("#contents").val(),
					"status" : "답변대기",
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/qna/do_retrieve.do";

					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
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

		//삭제:submit->
		$("#doDelete").on("click", function() {
			//validation
			console.log("userId:" + $("#userId").val());
			if (confirm("삭제 하시겠습니까?") == false)
				return;

			$.ajax({
				type : "POST",
				url : "${context}/qna/do_delete.do",
				dataType : "html",
				data : {
					"userId" : $("#userId").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/qna/do_retrieve.do";

					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
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

		$(document).ready(function() {
			//form validate
			/* $("#qnaEditFrm").validate({
				rules: {					
					title: {
						required: true,
						minlength: 2,
						maxlength: 100
					},
					contents: {
						required: true,
						minlength: 2,
						maxlength: 1000000
					}
				},
				messages: {
					title: {
						required: "제목을 입력 하세요.",
						minlength: $.validator.format("{0}자 이상 입력 하세요."),
						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
					},
					contents: {
						required: "내용을 입력 하세요.",
						minlength: $.validator.format("{0}자 이상 입력 하세요."),
						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
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

			});			 */
			
		});
	</script>
</body>
</html>