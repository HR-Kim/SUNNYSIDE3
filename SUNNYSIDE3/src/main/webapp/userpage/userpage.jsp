<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <link rel="stylesheet" href="${context}/resources/css/bootstrap.min.css">
</head>
<body>


	<div class="container">
      <div class="row">
        <div class="col">
          <div>
          <c:choose>
			
					<c:when test="${membershiplist.size()>0}">
							<c:forEach var="vo" items="${membershiplist}">
								<h3>${vo.user_name}회원님 환영합니다</h3>
								<h3>회원 등급은 ${vo.user_level} 입니다</h3>
							</c:forEach>		
					</c:when>
						<c:otherwise>
							
						</c:otherwise>
			</c:choose>
          </div>
            <ul class="nav nav-pills" id="tab_id">
              <li class="nav-item" name="do_tiketHistory.do">
                <a name="tiketHistory" class="nav-link" data-toggle="tab" href="#tiketHistory">예매/구매 내역</a>
              </li>
              <li class="nav-item" name="do_reservationList.do">
                <a class="nav-link" data-toggle="tab" href="#reservationList">상품결제 내역</a>
              </li>
               <li class="nav-item" name="do_coupon_retrieve.do">
                <a class="nav-link" data-toggle="tab" href="#couponList">나의 쿠폰함</a>
              </li>
              <li class="nav-item" name="do_movieHistory.do">
                <a class="nav-link" data-toggle="tab" href="#movieHistory">무비히스토리</a>
              </li>
              <li class="nav-item" name="do_qnaList.do">
                <a class="nav-link" data-toggle="tab" href="#qnaList">나의 문의 내역</a>
              </li>
              <li class="nav-item" >
                <a class="nav-link" data-toggle="tab" href="#infoManage">나의 정보 관리</a>
              </li>
            </ul>
            <div class="tab-content">
              <div class="tab-pane fade" id="tiketHistory">
                <%@include file="../userpage/tiketHistory.jsp"%>
			  </div>
			  <div class="tab-pane fade" id="reservationList">
               <%@include file="../userpage/reservationList.jsp"%>
               </div>
			   <div class="tab-pane fade" id="couponList">
               <%@include file="../userpage/couponList.jsp"%>
               </div>
              <div class="tab-pane fade" id="movieHistory">
                <%@include file="../userpage/movieHistory.jsp"%> 
              </div>
              <div class="tab-pane fade" id="qnaList">
               <%@include file="../userpage/qnaList.jsp"%> 
              </div>
              <div class="tab-pane fade" id="infoManage">
              <%@include file="../userpage/infoManage.jsp"%>
              </div>
            </div>
        </div>
      </div>
    </div>
    
    	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	
	$("#tab_id").on("click","li",function(event){
		var location = $(this).attr('name');
		console.log("${context}/userpage/"+location);
		
		if(location!=null){
			
	
		
			$.ajax({
				   type:"POST",
				   url:"${context}/userpage/"+location,
				   dataType:"html",
				   data:{
				   }, 
				success: function(data){
					var event_data = '';
					var jData = JSON.parse(data);
					console.log(jData);
					if(location=="do_tiketHistory.do"){
						
					
					$("#tiketHistoryTable>tbody").empty();
					 $.each(jData,function(index, value){
						 
						 event_data += '<tr>                                                                                       ' ;
						 event_data += '<td>'+value.kortitle+'</td>                                                              ' ;
						 event_data += '<td style="display:none;"><input type="hidden"  name="selected"  id="selected" value="'+value.ticket_code+'"></td> ' ;
						 event_data += '<td>'+value.room_id+'</td>                                                              ' ;
						 event_data += '<td>'+value.ticket_dt+'</td>  ';
						 event_data += '<td><button type="button" class="btn btn-default btn-sm" id="doDelete">예매 취소</button></td>' ;
						 event_data += '</tr>																						';
					 });
					 console.log(event_data);
					
					 $("#tiketHistoryTable>tbody").append(event_data);
					}
					
					else if(location=="do_reservationList.do"){
						$("#reservationTable>tbody").empty();
						 $.each(jData,function(index, value){
							 
							 event_data += '<tr>' ;
							 event_data += '<td>'+value.product_nm+'</td>' ;
							 event_data += '<td>'+value.pay_code+'</td>' ;
							 event_data += '<td>'+value.pay_cost+'</td>' ;
							 event_data += '<td>'+value.pay_dt+'</td>' ;
							 event_data += '</tr>';
							 
						 });
						 console.log(event_data);
						
						 $("#reservationTable>tbody").append(event_data);
					}
					else if(location=="do_movieHistory.do"){
						$("#movielistTable>tbody").empty();
						 $.each(jData,function(index, value){
							 
							 event_data += '<tr>                                                                                       ' ;
							 event_data += '<td>'+value.kortitle+'</td>                                                              ' ;
							 event_data += '<td style="display:none;"><input type="hidden"  name="selected"  id="selected" value="'+value.ticket_code+'"></td> ' ;
							 event_data += '</tr>																						';
						 });
						 console.log(event_data);
						
						 $("#movielistTable>tbody").append(event_data);
					}
					else if(location=="do_coupon_retrieve.do"){
						
						
						$("#couponlistTable>tbody").empty();
						 $.each(jData,function(index, value){
							if(value.usable==0){
								var useable="사용가능";
							}
							 event_data += '<tr> ' ;
							 event_data += '<td>'+value.num+'</td>' ;
							 event_data += '<td>'+value.coupon_nm+'</td>'  ;
							 event_data += '<td>'+value.coupon_code+'</td> ' ;
							 event_data += '<td>'+value.use_dt+'</td>'  ;
							 event_data += '<td>'+useable+'</td> ' ;
							 event_data += '</tr>';
						 });
						 console.log(event_data);
						
						 $("#couponlistTable>tbody").append(event_data);
						}
						
					
						else if(location=="do_qnaList.do"){
						
						
						$("#qnalistTable>tbody").empty();
						 $.each(jData,function(index, value){
							
							 event_data += '<tr> ' ;
							 event_data += '<td>'+value.num+'</td>' ;
							 event_data += '<td>'+value.title+'</td>'  ;
							 event_data += '<td>'+value.reg_dt+'</td> ' ;
							 event_data += '<td>'+value.status+'</td>'  ;
							 event_data += '</tr>';
							
						 });
						 console.log(event_data);
						
						 $("#qnalistTable>tbody").append(event_data);
						}
				},
				
				
				
				error:function(xhr,status,error){
					alert("error:"+error);
				}
			
			})
		}
	});
	
	function doDelete(){
		var frm = document.deleteFrm;
		frm.action = "${context}/userpage/do_delete.do";
		frm.selected.value=$("#movieList option:selected").val();
		frm.submit();
	}
	
	$("#doDelete").on("click",function(){
		doDelete();
		
		
		
	});

	
	
	$(document).ready(function(){
		console.log("ready");
		
		

		
	});
	
</script>


</body>
</html>