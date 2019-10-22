<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%--
  /**
  * @Class Name : board_attr_reg.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.04.26            최초 생성
  *
  * author SIST 개발팀
  * since 2018.04.26
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>

<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>첨부게시판 등록</title>

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

<!-- div container -->
	<div class="container">
		<!-- div title -->
		<div class="page-header">
			<h1>베너 이미지 관리</h1>
		</div>
		<!--// div title -->
		
		<!-- 등록버튼 -->
		<div class="row">
			<div class="col-md-12 text-right">
				<form class="form-inline" name="frm" id="frm" method="get">
					<div class="form-group">
						<button id="attrFile" type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#layerpop">이미지 등록</button>
					</div>
				</form>
			</div> 
		</div>
		<!--// 등록버튼 -->  
		<!-- Grid영역 -->
		<div class="table-responsive">
			<table class="table table-striped table-bordered table-hover" id="listTable">
				<thead class="bg-default" style="background-color: #333333; color: #f0f0f0">
					<th class="text-center col-md-3 col-xs-3">이미지ID</th>
					<th class="text-center col-md-8 col-xs-8">원본파일명</th>
					<th class="text-center col-md-1 col-xs-1">삭제</th>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${bannerList.size()>0}">
							<c:forEach var="vo" items="${bannerList}">
								<tr>
									<td class="text-center"><c:out value="${vo.imageId}"/></td>
									<td class="text-center"><c:out value="${vo.orgImgNm}"/></td>
									<td class="text-center"><input type="button" id="doDelete" name="doDelete" class="btn btn-default btn-sm doDelete" value="삭제" /></td>
								</tr>
							</c:forEach>        			
						</c:when>        	
						<c:otherwise>
							<tr><td class="text-center" colspan="99">등록된 데이터가 없습니다.</td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!--// Grid영역 -->
	</div>





	<!-- div container -->
	<div class="container">
		
		
		
		
		
	</div>	
	
	<!-- Modal -->
	<div class="modal fade" id="layerpop" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"><div class="modal-dialog" role="document"><div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<h4 class="modal-title" id="myModalLabel">이미지 등록</h4>
		</div>
		<div class="modal-body">
			<form class="form-horizontal" action="${context}/main/do_image_save.do" name="saveFileForm" id="saveFileForm" method="post" enctype="multipart/form-data">
				<!-- 첨부파일 -->
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="file01" name="file01">
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal" id="doFileUpload">저장</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
		</div>
	</div></div></div>
	<!--// Modal -->
	
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->	
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	    //파일업로드 
		$("#doFileUpload").on("click",function(e){
			//console.log("doFileUpload>>");
			if(confirm("등록 하시겠습니까?")== false)return;
			e.preventDefault();
			
			doUploadFile();
			
		});
		
		function doUploadFile(){
			var form = $('form')[1];
			var formData = new FormData(form);
			
			//ajax
			$.ajax({
				   type:"POST",
				   url:"${context }/main/do_image_save.do",
				   contentType:false,
				   async:false,
				   cache:false,
				   processData:false,
				   enctype:"multipart/form-data",
				   data:formData,
				success: function(data){	 
					if(null != data && data.msgId=="1"){' '
						alert(data.msgMsg);					
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

					
		});
	</script>
</body>
</html>