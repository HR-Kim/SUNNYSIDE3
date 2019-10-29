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
   <link rel="stylesheet" href="${context}/resources/css/userpage.css">
   <link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css">
</head>	
<body>

	<div class="container">
      <div class="row">
        <div class="col"> 
          <div>
           	<input type="hidden" id="userId" value="${user.userId}" >
          <c:choose>
			
					<c:when test="${membershiplist.size()>0}">
							<c:forEach var="vo" items="${membershiplist}">
								<h4>${vo.user_name}회원님 환영합니다</h4>
								<h4>회원 등급은 ${vo.user_level} 입니다</h4>
							</c:forEach>		
					</c:when>
						<c:otherwise>
							
						</c:otherwise>
			</c:choose>
          </div>
            <ul class="nav nav-tabs nav-pills" id="nav-tab" role="tablist">
              <li class="nav-item" name="do_tiketHistory.do">
                <a class="nav-link" data-toggle="tab" href="#tiketHistory">예매/구매 내역</a>
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
              <div class="tab-pane fade active show in" id="tiketHistory">
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
	
	
	$("#nav-tab").on("click","li",function(event){
		var location = $(this).attr('name');
		console.log("${context}/userpage/"+location);
		var User_id=$("#userId").val();
		console.log("userId "+userId);
		if(location!=null){
			
	
		
			$.ajax({
				   type:"POST",
				   url:"${context}/userpage/"+location,
				   dataType:"html",
				   data:{
					   "User_id" :User_id
					   
				   }, 
				success: function(data){
					var event_data = '';
					var jData = JSON.parse(data);
					console.log(jData);
					if(location=="do_tiketHistory.do"){
						
					
					$("#tiketHistoryTable>tbody").empty();
					 $.each(jData,function(index, value){
						 
						 event_data += '<tr>                                                                                       ' ;
						 event_data += '<td class="text-center">'+value.kortitle+'</td>                                                              ' ;
						 event_data += '<td style="display:none;">'+value.ticket_code+'</td>' ;
						 event_data += '<td class="text-center">'+value.room_nm+'</td>                                                              ' ;
						 event_data += '<td>'+value.ticket_dt+'</td>  ';
						 event_data += '<td class="text-center"><button type="button" class="btn btn-default btn-sm" id="doDelete">예매 취소</button></td>' ;
						 event_data += '</tr>																						';
					 });
					 console.log(event_data);
					
					 $("#tiketHistoryTable>tbody").append(event_data);
					}
					
					else if(location=="do_reservationList.do"){
						$("#reservationTable>tbody").empty();
						 $.each(jData,function(index, value){
							 
							 event_data += '<tr>' ;
							 event_data += '<td class="text-center">'+value.product_nm+'</td>' ;
							 event_data += '<td class="text-center">'+value.pay_code+'</td>' ;
							 event_data += '<td class="text-right">'+value.total_cost+'</td>' ;
							 event_data += '<td class="text-right">'+value.pay_dt+'</td>' ;
							 event_data += '<td class="text-center"><button type="button" class="btn btn-default btn-sm" id="doDelete">결제 취소</button></td>' ;
							 event_data += '</tr>';
							 
						 });
						 console.log(event_data);
						
						 $("#reservationTable>tbody").append(event_data);
					}
					else if(location=="do_movieHistory.do"){
						$("#movielistTable>tbody").empty();
						 $.each(jData,function(index, value){
							 
							 event_data += '<tr>' ;
							 event_data += '<td class="text-center">'+value.kortitle+'</td>' ;
							 event_data += '<td class="text-center"><img src="../resources/img/phototicket/'+value.ThisFileNm+'" /></td>' ;
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
							else{
								var useable="사용함";
							}
							 event_data += '<tr> ' ;
							 event_data += '<td class="text-center">'+value.num+'</td>' ;
							 event_data += '<td class="text-center">'+value.coupon_nm+'</td>'  ;
							 event_data += '<td class="text-center">'+value.coupon_code+'</td> ' ;
							 event_data += '<td class="text-right">'+value.use_dt+'</td>'  ;
							 event_data += '<td class="text-center">'+useable+'</td> ' ;
							 event_data += '</tr>';
						 });
						 console.log(event_data);
						
						 $("#couponlistTable>tbody").append(event_data);
						}
						
					
						else if(location=="do_qnaList.do"){
						
						
						$("#qnalistTable>tbody").empty();
						 $.each(jData,function(index, value){
							
							 event_data += '<tr> ' ;
							 event_data += '<td class="text-center">'+value.num+'</td>' ;
							 event_data += '<td class="text-center">'+value.title+'</td>'  ;
							 event_data += '<td class="text-left">'+value.reg_dt+'</td> ' ;
							 event_data += '<td class="text-center">'+value.status+'</td>'  ;
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
	
	
	
	
	$("#tiketHistoryTable>tbody").on("click","button",function(event){
		
		var tr = $(this).parent().parent();
		console.log(tr);
		var td = tr.children();
		
		console.log(td);
		var ticket_code = td.eq(1).text();
		console.log(ticket_code);
		
	
		$.ajax({
			   type:"POST",
			   url:"${context}/userpage/do_delete.do",
			   dataType:"html",
			   data:{
				   "ticket_code" :ticket_code
				   
			   }, 
			   success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/userpage/do_userpage.do";

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
		
	});
	$("#reservationTable>tbody").on("click","button",function(event){
		var tr = $(this).parent().parent();
		console.log(tr);
		var td = tr.children();
		console.log(td);
		var pay_code = td.eq(1).text();
		console.log(pay_code);
		
		$.ajax({
			   type:"POST",
			   url:"${context}/userpage/do_delete_item.do",
			   dataType:"html",
			   data:{
				   "pay_code" :pay_code
				   
			   }, 
			   success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/userpage/do_userpage.do";

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
		
	});
	
	$(document).ready(function(){
		console.log("ready");
		
		
	});
	
	
	$("#photo").on("click","button",function(event){
	
		window.location.href=  "${context}/phototicket/do_retrieve.do?user_id=${user.userId}";
	});
	
	$("#update").on("click",function(event){
	
		window.location.href=  "${context}/usermypage/update_view.do?user_id=${user.userId}";
	});
	$("#withdraw").on("click",function(event){

		window.location.href=  "${context}/usermypage/withdraw_view.do?user_id=${user.userId}";
	});
	
	
	
</script>


</body>
</html>