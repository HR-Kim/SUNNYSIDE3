<%@page import="kr.co.sunnyside.notice.service.KYMBranchVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
	String selectedNm  = ((String)request.getAttribute("branchSNm")==null)?
				"" : (String)request.getAttribute("branchSNm");

	//게시판 검색 구분
	List<KYMBranchVO> Branchlist=(request.getAttribute("Branchlist")==null)?
			(List<KYMBranchVO>)new ArrayList<KYMBranchVO>():(List<KYMBranchVO>)(request.getAttribute("Branchlist"));
%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>게시관리</title>

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css">

</head>
<body>
	<!-- div container -->
	<div class="container">
		<!-- div title -->
		<div class="page-header">
			<h1><spring:message code="message.main.notice"/></h1>
		</div>
		<!--// div title -->
		<!-- Button Area -->
		<div class="row">
			<div class="col-lg-10 col-sm-10 col-xs-10">
				<div class="text-right">
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
		<!--// Button Area -->
		<br>
		<div class="col-lg-11"></div>
		<!-- div title -->
		<form class="form-horizontal" name="noticeEditFrm" id="noticeEditFrm" method="POST" action="${context}/notice/do_save.do">
			<input type="hidden" class="form-control" name="noticeId" id="noticeId" value="${vo.noticeId }">
		    <input type="hidden" class="form-control" name="fileId" id="fileId" value="${vo.fileId }" >
			<div class="form-group">
				<!-- 제목 -->
				<label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.qna.title"/></label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="title" id="title" placeholder='<spring:message code="message.qna.title"/>' value="<c:out value='${vo.title }' />">
				</div>
			</div>
			
			<div class="form-group">
				<!-- 지점 -->
				<label for="inputEmail3" class="col-sm-2 control-label"><spring:message code="message.notice.store"/></label>
					<div class="col-sm-8">
						<%=StringUtil.makeBranchBox(Branchlist, "branchSNm", selectedNm, true) %>
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
			
			<!-- 첨부 -->
			<div class="form-group">
				<!-- 파일 첨부 -->
				<label for="attrFile" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label"><spring:message code="message.notice.file_add"/></label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<!-- 파일 -->
					<button id="attrFile" type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#layerpop"><spring:message code="message.notice.file"/></button>
				</div>
			</div>
			
			<!-- 첨부그리드 -->
			<div class="form-group">
				<label for="listFileTable" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label"></label>
				<div class="table-responsive col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<table class="table  table-striped table-bordered table-hover" id="listFileTable">
						<tbody></tbody>
					</table>
				</div>
			</div>									
		</form>
		
		<!-- Modal -->
		<div class="modal fade" id="layerpop" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <!-- 파일 업로드 -->
		        <h4 class="modal-title" id="myModalLabel"><spring:message code="message.notice.file_upload"/></h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal" action="${context }/file/do_save.do" 
		           name="saveFileForm" id="saveFileForm" method="post" enctype="multipart/form-data">
		            <input type="hidden"  name="work_dir"   id="work_dir" value="com" >
		            <input type="hidden"  name="attrFileId" id="attrFileId" value="<c:out value='${vo.fileId}'/>">
		            <input type="hidden"  name="orgFileNm"  id="orgFileNm" >
		            <input type="hidden"  name="saveFileNm" id="saveFileNm" >
		           
		        	<!-- 첨부파일 -->
		        	<div class="custom-file">
		        		<input type="file" class="custom-file-input" id="file01" name="file01">
		        	</div>
		        </form>
		      </div>
		      <div class="modal-footer">
		      	<!-- 저장 -->
		        <button type="button" class="btn btn-default" data-dismiss="modal" id="doFileUpload"><spring:message code="message.button.save"/></button>
		        <!-- 취소 -->
		        <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="message.button.cancel"/></button>
		      </div>
		    </div>
		  </div>
		 </div>
	</div>
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<%-- 	<script src="${context}/resources/js/jquery.validate.js"></script> --%>
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
		//초기화
		$("#doInit").on("click",function(){
			//alert("doInit");
			$("#title").val("");
			$("#contents").val(""); 
		});
		
		//목록이동
		$("#doRetrieve").on("click",function(){
			//alert("doRetrieve");
			if(confirm("목록으로 이동 하시겠습니까?")== false)return;
			location.href ="${context}/notice/do_retrieve.do";
		});    
	
		//등록	    
		$("#doSave").on("click", function() {
			var branchSNm = $("#branchSNm").val();
			if(branchSNm == "" || branchSNm == null){
				branchSNm = "전체";
			}
			//validation
// 			if($("#noticeEditFrm").valid()==false)return;
			
			if (confirm("등록 하시겠습니까?") == false)return;

			$.ajax({
				type : "POST",
				url : "${context}/notice/do_save.do",
				dataType : "html",
				data : {
					"fileId":$("#fileId").val(),
					"title":$("#title").val(),
					"contents" : $("#contents").val(),
					"branchSNm" : branchSNm,
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/notice/do_retrieve.do";

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

		//수정:submit->control->notice_mng.jsp: (성공)?notice_list.jsp:notice_mng.jsp
		//삭제:submit->
		$("#doUpdate").on("click", function() {
			//validation
// 			if($("#noticeEditFrm").valid()==false)return;
			
			console.log("noticeId:" + $("#noticeId").val());
			if (confirm("수정 하시겠습니까?") == false)
				return;

			$.ajax({
				type : "POST",
				url : "${context}/notice/do_update.do",
				dataType : "html",
				data : {
					"noticeId" : $("#noticeId").val(),
					"title" : $("#title").val(),
					"branchSNm" : $("#branchSNm").val(),
					"contents" : $("#contents").val(),
					"regId" : $("#regId").val(),
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/notice/do_retrieve.do";

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
			console.log("noticeId:" + $("#noticeId").val());
			if (confirm("삭제 하시겠습니까?") == false)
				return;

			$.ajax({
				type : "POST",
				url : "${context}/notice/do_delete.do",
				dataType : "html",
				data : {
				   /*  "noticeId":"do_delete", */
				    "noticeId" : $("#noticeId").val(),
				    "fileId":$("#fileId").val(),
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/notice/do_retrieve.do";

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
// 			$("#noticeEditFrm").validate({
// 				rules: {					
// 					title: {
// 						required: true,
// 						minlength: 2,
// 						maxlength: 100
// 					},
// 					contents: {
// 						required: true,
// 						minlength: 2,
// 						maxlength: 1000000
// 					}
// 				},
// 				messages: {
// 					title: {
// 						required: "제목을 입력 하세요.",
// 						minlength: $.validator.format("{0}자 이상 입력 하세요."),
// 						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
// 					},
// 					contents: {
// 						required: "내용을 입력 하세요.",
// 						minlength: $.validator.format("{0}자 이상 입력 하세요."),
// 						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
// 					}
// 				},
// 				errorPlacement : function(error, element) {
// 				     //do nothing
// 				    },
// 				    invalidHandler : function(form, validator) {
// 				     var errors = validator.numberOfInvalids();
// 				     if (errors) {
// 				      alert(validator.errorList[0].message);
// 				      validator.errorList[0].element.focus();
// 				     }
// 				}

// 			});			
			
		});
		
		//파일삭제:db delete,file삭제
		$("#listFileTable>tbody").on("click",".btn-danger",function(e){
			//alert(".btn-danger");
			//button > td parent -> tr
			var tr    = $(this).parent().parent();
			var tds   = tr.children();	

			var fileId = tds.eq(0).text();
			var num    = tds.eq(1).text();
			var orgFileNm = tds.eq(2).text();
			var saveFileNm = tds.eq(3).text();
			console.log("fileId:"+fileId);
			console.log("num:"+num);
			console.log("orgFileNm:"+orgFileNm);
			console.log("saveFileNm:"+saveFileNm);
			//ajax
			$.ajax({
				   type:"POST",
				   url:"${context}/file/do_delete.do",
				   dataType:"html",
				   data:{
				   "fileId":fileId,
				   "num":num,
				   "orgFileNm":orgFileNm,
				   "saveFileNm":saveFileNm
				  }, 
				success: function(data){
				  var jData = JSON.parse(data);
				  if(null != jData && jData.msgId=="1"){
					alert(jData.msgMsg);
					//화면삭제:
					tr.remove();
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


			
			//alert("orgFileNm:"+orgFileNm+"|"+saveFileNm);
			console.log("orgFileNm:"+orgFileNm);
			console.log("saveFileNm:"+saveFileNm);
		});

	
		//파일다운로드 
		$("#listFileTable>tbody").on("click",".org-file-name",function(e){
			//alert(".org-file-name");
			//@RequestMapping(value="file/download.do",method = RequestMethod.POST)
			//String orgFileNm  = req.getParameter("orgFileNm");// 원본파일명
		    //String saveFileNm = req.getParameter("saveFileNm");// 저장파일명 

			//td parent -> tr
			var tr    = $(this).parent();
			var tds   = tr.children();	

			var fileId = tds.eq(0).text();
			var num    = tds.eq(1).text();
			var orgFileNm = tds.eq(2).text();
			var saveFileNm = tds.eq(3).text();
			
			//alert("orgFileNm:"+orgFileNm+"|"+saveFileNm);
			console.log("orgFileNm:"+orgFileNm);
			console.log("saveFileNm:"+saveFileNm);
			
			var frm = document.saveFileForm;
			frm.action = "${context}/file/download.do";
			frm.orgFileNm.value = orgFileNm;
			frm.saveFileNm.value= saveFileNm;
			frm.submit();
			
		});
	
		//파일목록 조회:
		function doFileList(fileId){
			//ajax
			alert("fileId:"+fileId);
			$.ajax({
				   type:"POST",
				   url:"${context}/file/do_retrieve.do",
				   dataType:"html",
				   data:{
				   "fileId":fileId
				  }, 
				success: function(data){
				    //alert(data);	
				    var jData = JSON.parse(data);
				  
				    if(null != jData){
						//기존 : listFileTable 삭제.
						$("#listFileTable tbody tr").remove();
						  
						//전체 Data를 동적으로 생성.
						$.each(jData,function(index,item){
							$("#listFileTable tbody:last").append("<tr>"+
									"<td class='text-center hidden-xs hidden-sm hidden-md hidden-lg'>"+<c:out value='item.fileId'/>+"</td>"+  
									"<td class='text-center hidden-xs hidden-sm hidden-md hidden-lg'>"+<c:out value='item.num'/>+"</td>"+  
									"<td class='text-left org-file-name'>"+<c:out value='item.orgFileNm'/>+"</td>"+ 
									"<td class='text-center hidden-xs hidden-sm hidden-md hidden-lg'>"+<c:out value='item.saveFileNm'/>+"</td>"+ 
									"<td class='text-right'>"+<c:out value='item.fSize'/>+" &nbsp; byte</td>"+
									"<td class='text-right'><button type='button' class='btn btn-default btn-sm btn-danger' >X</button></td>"+
									"</tr>");
							
						});//$.each
				  }else{
					alert(jData);
				  }
				},
				complete:function(data){
				 
				},
				error:function(xhr,status,error){
					alert("error:"+error);
				}
			}); 
			//--ajax 
		}
	
	
	    //파일업로드 
		$("#doFileUpload").on("click",function(e){
			//console.log("doFileUpload>>");
			if(confirm("등록 하시겠습니까?")== false)return;
			e.preventDefault();
			
			doUploadFile();
			
		});
		
		function doUploadFile(){
			var form = $('form')[2];
			var formData = new FormData(form);
			
			//ajax
			$.ajax({
				   type:"POST",
				   url:"${context }/file/do_save.do",
				   contentType:false,
				   async:false,
				   cache:false,
				   processData:false,
				   enctype:"multipart/form-data",
				   data:formData,
				success: function(data){
				  console.log("data.msgId:"+data.msgId)
				  console.log("data:"+data.msgMsg)
				  $("#attrFileId").val(data.msgMsg);
				  $("#fileId").val(data.msgMsg);
				  
				  //
				  document.getElementById('file01').value="";
				  
				  //var jData = JSON.parse(data);
				  if(null != data && data.msgId=="1"){
					//alert(data.msgMsg);
					//fileId file_mng조회
					doFileList($("#fileId").val());
					
				  }else{
					alert(data.msgId+"|"+data.msgMsg);
				  }
				},
				complete:function(data){
				 
				},
				error:function(xhr,status,error){
					alert("error:"+error);
				}
			}); 
			//--ajax 			
		}

		$(document).ready(function() {
			//alert("ready");
			
			if("${vo.fileId}" !=""){
				doFileList('${vo.fileId}');
			}

			//form validate
// 			$("#noticeEditFrm").validate({
// 				rules: {					
// 					title: {
// 						required: true,
// 						minlength: 2,
// 						maxlength: 100
// 					},
// 					contents: {
// 						required: true,
// 						minlength: 2,
// 						maxlength: 1000000
// 					}
// 				},
// 				messages: {
// 					title: {
// 						required: "제목을 입력 하세요.",
// 						minlength: $.validator.format("{0}자 이상 입력 하세요."),
// 						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
// 					},
// 					contents: {
// 						required: "내용을 입력 하세요.",
// 						minlength: $.validator.format("{0}자 이상 입력 하세요."),
// 						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
// 					}
// 				},
// 				errorPlacement : function(error, element) {
// 				     //do nothing
// 				    },
// 				    invalidHandler : function(form, validator) {
// 				     var errors = validator.numberOfInvalids();
// 				     if (errors) {
// 				      alert(validator.errorList[0].message);
// 				      validator.errorList[0].element.focus();
// 				     }
// 				}

// 			});						
		});
		
	</script>
</body>
</html>