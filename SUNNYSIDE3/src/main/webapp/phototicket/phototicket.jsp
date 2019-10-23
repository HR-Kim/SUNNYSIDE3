<%@page import="java.util.List"%>
<%@page import="kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketVO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File</title>


   <link rel="stylesheet" href="${context}/resources/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
      <div class="row">
        <div class="col">
		<h3>포토티켓 생성</h3>
		<hr/>
		<form class="form-inline" enctype="multipart/form-data" method="post" name="saveFrm" id="saveFrm" >
			<div>포토 티켓을만들 영화 리스트를 선택해 주세요:<select class="form-control input-sm" id="movieList">
				
				<c:choose>
				
						<c:when test="${moiveList.size()>0}">
								<c:forEach var="vo" items="${moiveList}">
									<option id="movieList" value='${vo.ticket_code}'>${vo.kortitle}</option>
								</c:forEach>		
						</c:when>
							<c:otherwise>
								
							</c:otherwise>
				</c:choose>
			  	<input type="hidden"  name="selected"  id="selected" value="" >
			 
				</select></div><br/>
				<div>포토 티켓을만들 이미지를 첨부해 주세요:<input type="file" name="file" /></div>	<br/>
				
			<input type="button" class="btn btn-default btn-sm" id="do_Save"
								value="사진추가" />
		
		</form>

	
	<br/>
	<br/>
	<h3>포토티켓 결과</h3>

	<hr/>

	
	<table border="1" id="listTable">
	
		<tbody>
					<tr>
							<td>사진 </td>
							<td>영화이름 </td>
							<td>좌석 </td>
							<td>티켓코드 </td>
							
						</tr>
			
				
						<tr>
							<td><img src='<c:url value="/resources/img/phototicket/${selectMovie.thisFileNm}" />'> </td>
							<td><c:out value="${selectMovie.kortitle}" /> </td>
							<td><c:out value="${selectMovie.seat_num}" /> </td>
							<td><c:out value="${selectMovie.ticket_code}" /> </td>
							
						</tr>
						
						
					
		
		</tbody>
		
		
	</table>
	</div>
	</div>
	</div>
	
	
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">

	function doSave(){
		var frm = document.saveFrm;
		frm.action = "${context}/phototicket/do_save.do";
		frm.selected.value=$("#movieList option:selected").val();
		frm.submit();
	}
	
	$("#do_Save").on("click",function(){
		doSave();
		
		
		
	});
	
	



	$(document).ready(function(){
		console.log("ready");
		
		

		
	});
	
</script>	
	
	
</body>
</html>