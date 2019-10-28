<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.movie.service.LHJ_MovieVO"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

<!------ Include the above in your HEAD tag ---------->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/headerStyle.css" rel="stylesheet" type="text/css"> 

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>	
	<!-- div container -->
	<div class="container" style="margin-bottom: 70px">
		<!-- div title --> 
		<div class="page-header">
			<h1><spring:message code="message.header.faq"/></h1>
			<c:choose>
				<c:when test="${not empty user.userId && user.userId == 'admin'}">
					<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#savePop"><spring:message code="message.button.save"/></button>					
				</c:when>
			</c:choose>
		</div>
		
		<form class="form-horizontal" id="faqForm" name="faqForm">			
		<div class="table-responsive">
			<c:choose>
				<c:when test="${list.size()>0}">
					<c:forEach var="vo" items="${list}">						
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="panel panel-default">								
								<!-- 질문내용 -->
								<div class="panel-heading">									
									<strong class="voTitle">Q. <c:out value="${vo.title}"/></strong>
									<c:choose>
										<c:when test="${'admin' == user.userLevel}">
											<!-- 수정 -->
											<input type="button" id="doUpdateSelect" name="doUpdateSelect" class="btn btn-default btn-sm doUpdateSelect" data-toggle="modal" data-target="#updatePop" value='<spring:message code="message.button.edit"/>'/>
											<!-- 삭제 --> 
											<input type="button" id="doDelete" name="doDelete" class="btn btn-default btn-sm doDelete" value='<spring:message code="message.button.delete"/>'/>
										</c:when>
									</c:choose>
									<span class="questionId" style="display: none"><c:out value="${vo.questionId}"/></span>
								</div>	
								<!--// 질문내용 -->
								<!-- 답변내용 -->
								<div class="panel-body">
									<strong class="contents">A. <c:out value="${vo.contents}"/></strong>
								</div>
								<!--// 답변내용 -->
							</div>
						</div>						
					</c:forEach>        			
				</c:when>        	
				<c:otherwise>
					<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
						<div class="panel panel-default">
							<!-- 질문내용 -->
							<div class="panel-heading">
								<!-- 등록된 내용이 없습니다. -->
								<strong><spring:message code="message.faq.nodata"/></strong>
							</div>	
							<!--// 질문내용 -->
							<!-- 답변내용 -->
							<div class="panel-body list_contents">
							</div>
							<!--// 답변내용 -->
						</div>
					</div>		
				</c:otherwise>
			</c:choose>
		</div>
		</form>
	</div>
	
	<!-- 저장 모달 -->
	<div class="modal fade" id="savePop" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"><div class="modal-dialog" role="document"><div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<!-- 등록 -->
			<h4 class="modal-title" id="myModalLabel"><spring:message code="message.button.edit"/></h4>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" action="${context}/faq/do_save.do" name="saveForm" id="saveForm" method="post">
				<!-- 질문 -->
				<div class="form-group">
					<label for="saveTitle" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label"><spring:message code="message.faq.question"/></label>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<input type="text" class="form-control" id="saveTitle" name="saveTitle">
					</div>
				</div>
				<!-- 답변 -->
				<div class="form-group">
					<label for="saveContents" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label"><spring:message code="message.faq.answer"/></label>
					<div class="col-sm-8">
						<textarea class="form-control" id="saveContents" name="saveContents" rows="5"></textarea>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal" id="doSave" name="doSave"><spring:message code="message.button.save"/></button>
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
		</div>
	</div></div></div>

	<!-- 수정 모달 -->
	<div class="modal fade" id="updatePop" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"><div class="modal-dialog" role="document"><div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<!-- 수정 -->
			<h4 class="modal-title" id="myModalLabel"><spring:message code="message.button.edit"/></h4>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" action="${context}/faq/do_update.do" name="updateForm" id="updateForm" method="post">
				<input type="hidden" id="updateQuestionId" name="updateQuestionId">
				<!-- 질문 -->
				<div class="form-group">
					<label for="updateTitle" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label"><spring:message code="message.faq.question"/></label>
					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<input type="text" class="form-control" id="updateTitle" name="updateTitle">
					</div>
				</div>
				<!-- 답변 -->
				<div class="form-group">
					<label for="updateContents" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label"><spring:message code="message.faq.answer"/></label>
					<div class="col-sm-8">
						<textarea class="form-control" id="updateContents" name="updateContents" rows="5"></textarea>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<!-- 수정 -->
			<button type="button" class="btn btn-default" data-dismiss="modal" id="doUpdate" name="doUpdate"><spring:message code="message.button.edit"/></button>
			<!-- 취소 -->
			<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="message.button.cancel"/></button>
		</div>
	</div></div></div>
	
	
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<!-- reservation 페이지 팝업 -->
	<script src="${context}/resources/js/reservationPopup.js"></script>
	<script type="text/javascript">
		$(".doUpdateSelect").click(function(){
			var questionId = $(this).parent().children('.questionId').text();
			var title = $(this).parent().children('.voTitle').text();
			var contents = $(this).parent().parent().children('.panel-body').children('.contents').text();
			
			$("#updateQuestionId").val(questionId);
			$("#updateTitle").val(title);
			$("#updateContents").val(contents);			
			
			console.log("questionId : "+questionId);
			console.log("title : "+title);
			console.log("contents : "+contents);
			
		});
		
		$("#doSave").click(function(){
			if(confirm("저장 하시겠습니까?")==false)return;
			
			//ajax
			$.ajax({
				type:"POST",
				url:"${context}/faq/do_save.do",
				dataType:"html",
				data:{
					"title":$("#saveTitle").val()
					,"contents":$("#saveContents").val()
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgMsg);
						location.href="${context}/faq/do_retrieve.do";
					}else{
						alert(jData.msgId+"|"+jData.msgMsg);
					}
	        	},
				complete:function(data){
	         
	       		},
				error:function(xhr,status,error){
					alert("error:"+error);
	        	}
			}); 
	       //--ajax
	    });
		
		$("#doUpdate").click(function(){
			var questionId = $(this).parent().children('.questionId').text();
			
			if(confirm("수정 하시겠습니까?")==false)return;
			
			//ajax
			$.ajax({
				type:"POST",
				url:"${context}/faq/do_update.do",
				dataType:"html",
				data:{
					"questionId":$("#updateQuestionId").val()
					,"title":$("#updateTitle").val()
					,"contents":$("#updateContents").val()
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgMsg);
						location.href="${context}/faq/do_retrieve.do";
					}else{
						alert(jData.msgId+"|"+jData.msgMsg);
					}
	        	},
				complete:function(data){
	         
	       		},
				error:function(xhr,status,error){
					alert("error:"+error);
	        	}
			}); 
	       //--ajax
	    });
		
		$(".doDelete").click(function(){
			var questionId = $(this).parent().children('.questionId').text();
			
// 			console.log("questionId : "+questionId);
			//valication
			if(confirm("삭제 하시겠습니까?")==false)return;
			
			//ajax
			$.ajax({
				type:"POST",
				url:"${context}/faq/do_delete.do",
				dataType:"html",
				data:{
					"questionId":questionId
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgMsg);
						location.href="${context}/faq/do_retrieve.do";
					}else{
						alert(jData.msgId+"|"+jData.msgMsg);
					}
            	},
				complete:function(data){
             
           		},
				error:function(xhr,status,error){
					alert("error:"+error);
            	}
			}); 
           //--ajax
	    });
		
		  	
		$(document).ready(function() {

        });
	</script>  
</body>
</html>