<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%

%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>극장 관리</title>
		<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

		<style type="text/css">
			.loadingLayer {
				position: absolute;
				width: 200px;
				height: 50px;
				padding: 15px;
				font-weight: bold;
				text-align: center;
				background-color: white;
				border: 1px solid black;
				z-index: 100;
				display: none;
			}
			.dim {
				position: absolute;
				top: 0;
				left: 0;
				width: 100%;
				height: 100%;
				background-color: black;
				opacity: 0.1;
				z-index: 95;
				display: none;
			}
			.seatSetting_layer {
				position: absolute;
				width: 500px;
				height: 500px;
				display: none;
				border: 1px solid black;
				background-color: white;
				text-align: center;
				padding: 10px;
				z-index: 20;
			}
			.room_Plus_layer {
				position: absolute;
				width: 200px;
				height: 180px;
				display: none;
				border: 1px solid black;
				background-color: white;
				text-align: center;
				padding: 10px;
				z-index: 10;
			}
			.branch_Plus_layer {
				position: absolute;
				width: 200px;
				height: 160px;
				display: none;
				border: 1px solid black;
				background-color: white;
				text-align: center;
				padding: 10px;
				z-index: 10;
			}
			.seatWrap {
				border: 1px solid black;
				width: 650px;
				display: none;
			}
			.seatWrap .seatY {
				font-weight: bold;
			}
			.seatWrap button {
				width: 20px;
				height: 20px;
				padding: 0;
			}
			.screen {
				width: 100%;
				height: 30px;
				text-align: center;
				background-color: #ececec;
				margin: 0 auto 30px auto;
				font-weight: bold;	
				padding: 5px;
			}
			.seatbox {
				text-align: center;
				padding-bottom: 20px;
			}
		</style>
		
	</head>
	<body>
		<div class="container">
		<h3>극장관리</h3>
		<hr/><br/>
			<div class="loadingLayer">
				<p>...작업중입니다...</p>
			</div>
			<div class="dim"></div>
		
			<div class="branch_Plus_layer">
				<p>새로운 지점 이름을 입력하세요.</p>
				<input type="text" id="new_branchNm" maxlength="20">
				<br/><hr/>
				<button id="branch_add" class="btn btn-default">추가</button>
				<button id="branch_cancel" class="btn btn-default">취소</button>
			</div>
		
			<div>
				<button id="branch_retrieve" class="btn btn-default">지점조회</button>
				<button id="branch_plus" class="btn btn-default">지점추가</button>
				<button id="branch_delete" class="btn btn-default">지점제거</button>
			</div>
			<input type="hidden" id="hd_branchId" value="">
			<input type="hidden" id="hd_branchNm" value="">
			<div class="table-responsive">
				<table id="branchTable" class="table table-hover table-bordered">
					<thead class="bg-primary">
						<tr>
							<td class="text-center col-md-6 col-xs-6">지점ID</td>
							<td class="text-center col-md-6 col-xs-6">지점명</td>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${branchList.size() > 0}">
								<c:forEach items="${branchList}" var="b">
									<tr>
										<td>${b.branchId}</td>
										<td>${b.branchNm}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="99">지점 데이터가 없습니다.</td></tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			
			<hr/>
			
			<div class="room_Plus_layer">
				<p>새로운 상영관 이름을 입력하세요.</p>
				<input type="text" id="new_roomNm" maxlength="20">
				<br/><hr/>
				<button id="room_add" class="btn btn-default">추가</button>
				<button id="room_cancel" class="btn btn-default">취소</button>
			</div>
			
			<div class="seatSetting_layer">
			
			</div>
			
			<div>
				<button id="room_plus" class="btn btn-default">상영관추가</button>
				<button id="room_delete" class="btn btn-default">상영관제거</button>
				<button id="seat_add" class="btn btn-default">좌석생성</button>
				<button id="seat_delete" class="btn btn-default">좌석제거</button>
				<button id="room_reset" class="btn btn-default">새로고침</button>
				<input type="hidden" id="hd_roomzero" value="">
				<input type="hidden" id="hd_roomId" value="">
				<input type="hidden" id="hd_roomNm" value="">
				<input type="hidden" id="hd_totalSeat" value="">
			</div>
			<div class="table-responsive">
				<table id="roomTable" class="table table-hover table-bordered">
					<thead class="bg-primary">
						<tr>
							<td class="text-center col-md-3 col-xs-3">지점ID</td>
							<td class="text-center col-md-3 col-xs-3">상영관ID</td>
							<td class="text-center col-md-4 col-xs-4">상영관명</td>
							<td class="text-center col-md-1 col-xs-1">총좌석</td>
							<td class="text-center col-md-1 col-xs-1">남은좌석</td>
						</tr>
					</thead>
					<tbody>
						<tr class='roomTr'>
							<td colspan="99">지점을 선택해주세요.</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<hr/>
			<p id="seatTableInfo"></p>
			<div class="seatWrap">
				<p class="screen">SCREEN</p>
				<div class="seatbox">
					<%= StringUtil.makeSeatButton(0) %>
				</div>
			</div>

			<br/>
			
			<div id="seatTableInfo2">
				
			
			</div>
			
			<br/>
			
		</div>
		
		<script src="${context}/resources/js/jquery-1.12.4.js"></script>
		<script src="${context}/resources/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">			
    		$(document).ready(function(){
    			//alert("ready");
    		});
    		
    		$("#branch_retrieve").on("click", function(){
    			location.href = "${context}/branchInfo/do_retrieve.do";
    		});
    		
    		//로딩
    		function loading(bool){
    			if(bool == true){
    				var width = $(".loadingLayer").width();
        			var height = $(".loadingLayer").height();
        			var outWidth = $("body").width();
        			var outHeight = $(".container").height();
        			
        			$(".loadingLayer").css("top", (outHeight-height)/2);
        			$(".loadingLayer").css("left", (outWidth-width)/2);
        			
        			$(".loadingLayer").css("display", "block");
        			$(".dim").css("display", "block");
    			}else{
    				$(".loadingLayer").css("display", "none");
    				$(".dim").css("display", "none");
    			}
    		}
    		
    		//상영관 새로고침버튼
    		$("#room_reset").on("click", function(){
    			var branchId = $("#hd_branchId").val();				//지점ID
    			var roomId = $("#hd_roomId").val();
    			if(roomId == null || roomId == "") roomId = ".";
    			update_room(roomId);
				create_Room_Table(branchId);
    		});
    		
    		//좌석생성버튼
    		$("#seat_add").on("click", function(){
    			var roomId = $("#hd_roomId").val();					//상영관ID
    			var roomNm = $("#hd_roomNm").val();					//상영관이름
    			var branchId = $("#hd_branchId").val();				//지점ID
    			var totalSeat = $("#hd_totalSeat").val();			//총좌석
    			
    			if(roomId.length == 0){
    				alert("선택한 상영관이 없습니다.");
    				return;
    			}
    			if(totalSeat > 0){
    				alert("좌석이 이미 존재합니다.");
    				return;
    			}
    			
    			if(confirm(roomNm + " 상영관에 좌석을 생성합니다.")==false) return;
    			
    			var xWidth = window.screen.width/2;
    			var xHeight = window.screen.height/2;    	    	
    			var pX = xWidth - 710/2;
    			var pY = xHeight - 555/2;

    			var opt = "width=710, height=555, left="+pX+", top="+pY+", status=0, toolbar=0, location=0, menubar=0";
    			var popup = window.open("<c:url value='/table/theater_table_seat.jsp'/>", "_blank", opt);
    			
    		});
			
    		//좌석생성
    		function setSeatInfo(arr){
    			var seatArr = new Array();
    			seatArr = arr;
    			var roomId = $("#hd_roomId").val();					//상영관ID
    			var branchId = $("#hd_branchId").val();				//지점ID
    			var errorCnt = 0;									//에러카운트
    			
    			for(var i=0 ; i<seatArr.length ; i++){
    				errorCnt += create_seat(roomId, branchId, seatArr[i].Y, seatArr[i].X, seatArr[i].use);	
    			}
    			
    			if(errorCnt > 0){
    				alert("좌석생성에 실패했습니다.\n잠시후 다시 시도해주세요.");
    				delete_seat(roomId);
    			}else{
    				alert("좌석이 생성되었습니다.");
    			}
    			
    			update_room(roomId);
    			$("#seatTableInfo").text("");
				create_Room_Table(branchId);
				
    		}
    		
    		//좌석생성
    		function create_seat(roomId, branchId, Y, X, use){
    			var errorCnt = 0;
    			$.ajax({
    				type : "POST",
    				url : "${context}/seat/do_save.do",
    				dataType : "json",
    				data : {
    					"roomId" : roomId,
    					"branchId" : branchId,
    					"seatY" : Y,
    					"seatX" : X,
    					"useYN" : use
    				}, 
    				success: function(data){
    					var msg = data;
    					if(msg.msgId != 1){
    						errorCnt++;
    					}
        			},
        			complete:function(data){
        				
        			},
        			error:function(xhr,status,error){
        				
        			}
        			}); 
    			return errorCnt;
    		}
    		
    		//좌석삭제
			$("#seat_delete").on("click", function(){
				var roomId = $("#hd_roomId").val();
				if(roomId.length < 1 || roomId == null) return;
				
				if(confirm("좌석을 정말로 삭제합니까?")==false) return; 
				delete_seat(roomId);				
    		});
    		
    		//상영관추가
    		$("#room_plus").on("click", function(){
    			if(confirm("상영관을 추가합니까?")==false) return;
    			var width = $(".room_Plus_layer").width();
    			var height = $(".room_Plus_layer").height();
    			var outWidth = $("body").width();
    			var outHeight = $(".container").height();
    			
    			$(".room_Plus_layer").css("top", (outHeight-height)/2);
    			$(".room_Plus_layer").css("left", (outWidth-width)/2);
    			
    			$(".room_Plus_layer").css("display", "block");
    		});
    		
    		//상영관추가버튼
    		$("#room_add").on("click", function(){
    			var roomNm = $("#new_roomNm").val();					//상영관이름
    			var branchId = $("#hd_branchId").val();					//지점ID
    			
    			if(roomNm.length == 0){
    				alert("새 상영관 이름을 입력해 주세요.");
    				return;
    			}
    			if(branchId.length == 0){
    				alert("선택한 지점이 없습니다.");
    				return;
    			}
    			
    			if(confirm("새 상영관 이름 : " + roomNm + "\n상영관을 추가하시겠습니까?")==false) return;
  			
    			create_room(roomNm, branchId);
    		});
    		
    		//상영관취소버튼
    		$("#room_cancel").on("click", function(){
    			room_cancel();
    		});
    		
    		//상영관제거버튼
    		$("#room_delete").on("click", function(){
    			var roomNm = $("#hd_roomNm").val();
    			var roomId = $("#hd_roomId").val();
    			
    			if(roomNm.length == 0 || roomId.length == 0) return;
    			
    			if(confirm("대상 : " + roomId + " (" + roomNm + ")"+ "\n정말로 삭제합니까?")==false) return;  
    			
    			delete_roomAll(roomId);
    		});
    		
    		//지점추가
    		$("#branch_plus").on("click", function(){
    			if(confirm("지점을 추가합니까?")==false) return;
    			var width = $(".branch_Plus_layer").width();
    			var height = $(".branch_Plus_layer").height();
    			var outWidth = $("body").width();
    			var outHeight = $(".container").height();
    			
    			$(".branch_Plus_layer").css("top", (outHeight-height)/2);
    			$(".branch_Plus_layer").css("left", (outWidth-width)/2);
    			
    			$(".branch_Plus_layer").css("display", "block");
    			
    		});
    		
    		//지점취소버튼
    		$("#branch_cancel").on("click", function(){
    			$(".branch_Plus_layer").css("display", "none");
    			$("#new_branchNm").val("");
    		});
    		
    		//지점추가버튼
    		$("#branch_add").on("click", function(){
    			create_branch();
    		});
    		
    		//지점삭제버튼
			$("#branch_delete").on("click", function(){
				delete_branch();		
    		});	
    		
    		//상영관 테이블 클릭
    		$("#roomTable>tbody").on("click", "tr", function(){
    			var tr = $(this);
    			var td = tr.children();
    			
    			$("#roomTable>tbody>tr").css("background-color", "");	//tr색 초기화
    			$(tr).css("background-color", "red");					//선택tr 색표시
    			$("#hd_roomId").val(td.eq(1).text());					//상영관id 표시(히든)
    			$("#hd_roomNm").val(td.eq(2).text());					//상영관이름 표시(히든)
    			$("#hd_totalSeat").val(td.eq(4).text());				//총좌석(히든)
    			
    			delete_Seat_Table();
    			create_Seat_Table(td);
    		});
    		
    		//지점 테이블 클릭
    		$("#branchTable>tbody").on("click", "tr", function(){
    			var tr = $(this);
    			var td = tr.children();
    			
    			$("#branchTable>tbody>tr").css("background-color", "");	//tr색 초기화
    			$(tr).css("background-color", "red");					//선택tr 색표시
    			$("#hd_branchId").val(td.eq(0).text());					//지점id표시(히든)
    			$("#hd_branchNm").val(td.eq(1).text());					//지점이름표시(히든)	
    			delete_Seat_Table();
    			var branchId = td.eq(0).text();
    			create_Room_Table(branchId);
    		});
    		
    		//지점테이블 선택시 상영관테이블 생성;
    		function create_Room_Table(branchId){
    			if(branchId == null || branchId.length == 1)return;
    			
    			var searchWord = branchId;
    			var searchDiv = "10";
    			
    			$.ajax({
    				type : "POST",
    				url : "${context}/room/do_retrieve.do",
    				dataType : "json",
    				data : {
    					"searchWord" : searchWord,
    					"searchDiv" : searchDiv 
    				}, 
    			success: function(data){
    				var roomArr = data;					console.log(data);			//roomList데이터를 json으로
    				$(".roomTr").detach();							//room테이블의 tr을 전부 삭제
    				if(roomArr.length > 0){
    					$("#hd_roomzero").val("1");					//상영관이 하나이상 존재함을 표시
    					for(var i=0 ; i< roomArr.length ; i++){		//바꾼 json데이터를 출력
        					$("#roomTable>tbody").append(
        							"<tr class='roomTr'>"+
        							"<td>"+roomArr[i].branchId+"</td>"+
        							"<td>"+roomArr[i].roomId+"</td>"+
        							"<td>"+roomArr[i].roomNm+"</td>"+
        							"<td>"+roomArr[i].totalSeat+"</td>"+
        							"<td>"+roomArr[i].restSeat+"</td>"+
        							"</tr>");
        				}
    				}else{
    					$("#hd_roomzero").val("0");
    					$("#roomTable>tbody").append(
    						"<tr class='roomTr'><td colspan='99'>상영관이 없습니다.</td></tr>");
    				}
    				
    			},
    			complete:function(data){

    			},
    			error:function(xhr,status,error){

    			}
    			}); 
    		}
    		
    		//지점추가
    		function create_branch(){
				if(confirm("정말로 지점을 추가합니까?")==false) return;
				loading(true);
    			$.ajax({
    				type : "POST",
    				url : "${context}/branchInfo/do_save.do",
    				dataType : "json",
    				data : {
    					"branchNm" :  $("#new_branchNm").val()
    				}, 
    			success: function(data){
    				var jsonData = data;
    				if(jsonData.msgId == 1){
    					alert("추가되었습니다.");
    					location.href = "${context}/branchInfo/do_retrieve.do";
    				}else{
    					alert("실패");
    				}
    				
    			},
    			complete:function(data){
    				loading(false);
    			},
    			error:function(xhr,status,error){
					alert("추가실패");
    			}
    			}); 
    		}
    		
    		//지점삭제
    		function delete_branch(){
    			var accept_key = $("#hd_roomzero").val();
				if(accept_key == 1){
					alert("지점에 상영관이 존재하면 삭제할 수 없습니다.");
					return;
				} 
				
				var id = $("#hd_branchId").val();
				var nm = $("#hd_branchNm").val();
				if(id.length == 0 || nm.length == 0) return;
				if(confirm("대상 : " + id + " (" + nm + ")"+ "\n정말로 삭제합니까?")==false) return; 
				
				loading(true);
				$.ajax({
    				type : "POST",
    				url : "${context}/branchInfo/do_delete.do",
    				dataType : "json",
    				data : {
    					"branchId" : id
    				}, 
    			success: function(data){
    				var jsonData = data;
    				if(jsonData.msgId == 1){
    					alert("삭제되었습니다.");
    					location.href = "${context}/branchInfo/do_retrieve.do";
    				}else{
    					alert("실패");
    				}
    				
    			},
    			complete:function(data){
    				loading(false);
    			},
    			error:function(xhr,status,error){
					alert("삭제실패");
    			}
    			});
    		}
    		
    		//좌석테이블 생성
    		function create_Seat_Table(td){
    			$("#seatTableInfo").text("");
    			if(td == null || td.length == 1)return;
    			loading(true);
    			
				var searchWord = td.eq(1).text();			//상영관ID
    			var searchDiv = "20";
    			
    			$.ajax({
    				type : "POST",
    				url : "${context}/seat/do_retrieve.do",
    				dataType : "json",
    				data : {
    					"searchWord" : searchWord,
    					"searchDiv" : searchDiv
    				}, 
    			success: function(data){
    				var seatArr = data;
    				if(seatArr.length != 0){
    					$("#seatTableInfo").text(td.eq(2).text() + "의 좌석테이블 입니다.");
    					$(".seatWrap").css("display", "block");
    					for(var i=0 ; i< seatArr.length ; i++){
        					var y= seatArr[i].seatY;		//Y축값
        					var x= seatArr[i].seatX;		//X축값
        					var yn = seatArr[i].useYN;		//사용여부
        					var seat = $("button[data-y="+y+"][data-x="+x+"]");	//좌석버튼찾기
        					seat.css("color", "white");
        					if(yn == '1'){										//사용가능좌석인 것(1)
        	   					seat.prop("disabled", false);					//해당 버튼을 사용가능하도록 만든다
        	   					seat.text(x);
        	   					seat.css("background-color", "blue");
        					}else if(yn == '0') {								//사용불가인것(0) X표시
        						seat.prop("disabled", false);
        	   					seat.text("X");
        	   					seat.css("background-color", "red");
        					}else{												//(3) 이면 복도
        						seat.css("visibility" ,"hidden");
        					}
        				}		
    				}else{
    					$("#seatTableInfo").text(td.eq(2).text() + "은 좌석이 없습니다.\n좌석을 생성해주세요.");
    				}
    			},
    			complete:function(data){
    				loading(false);
    			},
    			error:function(xhr,status,error){
    				
    			}
    			}); 
    		}
    		
    		//좌석클릭시 정보
    		$("button[data-y][data-x]").on("click", function(){
    			var seat = $(this);
    			if(seat.prop("disabled", false)){
    				alert("asd");
    			}
    		});

    		//좌석버튼에 마우스 올렸을떄 색 변화
    		$("button[data-y][data-x]").on("mouseenter", function(){
    			var seat = $(this);
    			seat.css("color", "red");
    		});
    		
    		//좌석버튼에 마우스 벗어났을시 색변화
    		$("button[data-y][data-x]").on("mouseleave", function(){
    			var seat = $(this);
    			seat.css("color", "white");
    		});
    		
    		//좌석테이블 제거
    		function delete_Seat_Table(){
    			var seat = $("button[data-y][data-x]");
    			$(".seatWrap").css("display", "none");
    			seat.css("background-color", false);		
    			seat.css("color", false);
    			seat.css("visibility" , "visible");		//보이지 않게 만들었던 버튼을 다시 보이게
    			seat.css("visibility" , false);			//css를 없앤다. 이 속성을 다시 만들면 이전의 값이 나오므로 false하기전에 visible로 반드시 만들어야한다
    			seat.prop("disabled", true);
    			seat.text("");
    			$("#seatTableInfo").text("");
    		}
    		
    		//상영관생성
    		function create_room(roomNm, branchId){
    			loading(true);
    			$.ajax({
    				type : "POST",
    				url : "${context}/room/do_save.do",
    				dataType : "json",
    				data : {
    					"roomNm" : roomNm,
    					"branchId" : branchId,
    					"totalSeat" : "0",
    					"restSeat" : "0"
    				}, 
    				success: function(data){
    					var msg = data;
    					if(msg.msgId == 1){
							alert("상영관 추가 성공.");
							room_cancel();
							create_Room_Table(branchId);
    					}else{
    						alert("상영관 추가 실패.");
    					}
        			},
        			complete:function(data){
        				loading(false);
        			},
        			error:function(xhr,status,error){
        				alert("상영관 추가 실패.") 
        			}
        			}); 
    		}
    		
    		//상영관과 그 좌석모두 삭제
    		function delete_roomAll(roomId){    	
    			delete_seat(roomId);  
        		var branchId =  $("#hd_branchId").val();
   				//상영관삭제
   				loading(true);
   				$.ajax({
       				type : "POST",
       				url : "${context}/room/do_delete.do",
       				dataType : "json",
       				data : {
       					"roomId" : roomId
       				}, 
       			success: function(data){
       				var msg = data;
       				if(msg.msgId == 1){
       					alert("상영관이 삭제되었습니다.");
       					create_Room_Table(branchId);
       				}else
       					alert("상영관 삭제실패.");
       			},
       			complete:function(data){
       				delete_Seat_Table();
       				loading(false);
       			},
       			error:function(xhr,status,error){
       				alert("상영관 삭제실패.");
       			}
       			});
    		}
    		
    		//좌석삭제
    		function delete_seat(roomId){
    			//상영관에 남이있는 좌석리스트
    			loading(true);
    			$.ajax({
    				type : "POST",
    				url : "${context}/seat/do_retrieve.do",
    				dataType : "json",
    				data : {
    					"pageSize" : "500",
    					"pageNum" : "1",
    					"searchDiv" : "20",
    					"searchWord" : roomId
    				}, 
    			success: function(data){
    				var list = data;
       				if(list.length > 0){
       					var seat = list[0];
           				$.ajax({
               				type : "POST",
               				url : "${context}/seat/do_delete.do",
               				dataType : "json",
               				data : {
               					"roomId" : seat.roomId
               				}, 
               			success: function(data){
               				
               			},
               			complete:function(data){
               				delete_Seat_Table();
               			},
               			error:function(xhr,status,error){
               				alert("좌석삭제실패.");
               				location.href = "${context}/branchInfo/do_retrieve.do";
               			}
               			});    
        				alert("해당 상영관의 좌석을 모두 삭제했습니다.");
        				$(".seatWrap").css("display", "none");
       				}else{
       					alert("해당 상영관의 좌석을 모두 삭제했습니다.");
       					$(".seatWrap").css("display", "none");
       				}
    			},
    			complete:function(data){
    				update_room(roomId);
    				$(".seatWrap").css("display", true);
    				var branchId = $("#hd_branchId").val();
    				create_Room_Table(branchId);
    				loading(false);
    			},
    			error:function(xhr,status,error){
					alert("리스트를 불러오는데 실패했습니다.");
    			}
    			});
    		}
    		
    		//상영관목록을 제거
    		function room_cancel(){
    			$(".room_Plus_layer").css("display", "none");
    			$("#new_roomNm").val("");
    			$("#new_roomY").val("");
    			$("#new_roomX").val("");
    		}
    		
    		//상영관의 좌석수를 업데이트한다
    		function update_room(roomId){
    			loading(true);
    			var branchId = $("#hd_branchId").val();
    			$.ajax({
    				type : "POST",
    				url : "${context}/seat/do_retrieve.do",
    				dataType : "json",
    				data : {
    					"searchDiv" : "20",
    					"searchWord" : roomId,
    					"pageNum" : "1",
    					"pageSize" : "1000"
    				}, 
    			success: function(data){
    				var list = data;
    				var enableSeatCnt = 0;
    				var unableSeatCnt = 0;
    				
    				for(var i=0 ; i<list.length ; i++){
    					if(list[i].useYN == '1') enableSeatCnt++; 
    					else if(list[i].useYN == '0') unableSeatCnt++; 
    					
    				}
    				$.ajax({
        				type : "POST",
        				url : "${context}/room/do_update.do",
        				dataType : "json",
        				data : {
        					"roomId" : roomId,
        					"totalSeat" : enableSeatCnt + unableSeatCnt,
        					"restSeat" : enableSeatCnt
        				}, 
        			success: function(data){
        				var msg = data;
        				if(msg.msgId == 1){
        					room_cancel();
        					create_Room_Table(branchId);
        				}
        				else{
        				}
        			},
        			complete:function(data){
        				
        			},
        			error:function(xhr,status,error){
        			}
        			});    
    			},
    			complete:function(data){
    				loading(false);
    			},
    			error:function(xhr,status,error){
    			}
    			});   
    		}
    	</script>
	</body>
</html>