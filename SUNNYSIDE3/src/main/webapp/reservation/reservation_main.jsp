<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>영화예매</title>
		<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
		<link href="${context}/resources/css/jquery-ui.css" rel="stylesheet">
	<style type="text/css">
		.ui-datepicker{ font-size: 15px; width: 198px; }
		.ui-datepicker select.ui-datepicker-month{ width:30%; font-size: 11px; }
		.ui-datepicker select.ui-datepicker-year{ width:40%; font-size: 11px; }
		.ui-widget-content .ui-icon {background-image: url("../resources/image/jquery_ui/ui-icons_444444_256x240.png");}
		.timeBtn {
			margin-left: 10px;
			margin-right: 10px;
			margin-top: 5px;
			margin-bottom: 5px;
			
		}
		.selectTimeBtnBox {
			padding: 10px;
			overflow: auto;
			height: 70px;
		}
		.branchInfo {
			width: 448px;
			height: 50px;
			background-color: lightgray;
		}
		.noPlan {
			color: lightgray;
		}
		#resetBtn {
			width: 150px;
			border-radius: .5rem;
		}
		#selectAllInfo {
			position: absolute;
			top: 0px;
			left: 650px;
			width: 200px;
			height: 510px;
			background-color: #525572;
			border-radius: .5rem;
		}
		#selectNum {
			position: absolute;
			top: 360px;
			left: 450px;
			width: 200px;
			height: 150px;
		}
		#selectTime {
			position: absolute;
			top: 360px;
			left: 0px;
			width: 450px;			
			height: 150px;
		}
		#selectDate {
			position: absolute;
			top: 0px;
			left: 450px;
			width: 200px;
			height: 350px;
			background-color: #7B7E9F;
		}
		#selectMovie {
			position: absolute;
			top: 0px;
			left: 0px;
			width: 250px;
			height: 350px;
		}
		#selectBranchRoom {
			position: absolute;
			top: 0px;
			left: 250px;
			width: 200px;
			height: 350px;
		}
		.case {
			border: 1px solid black;
			border-radius: .5rem;
		}
		.bar {
			background-color: #7B7E9F;
			height: 25px;
			line-height: 25px;
		}
		.bar label {
			color: #EFF0F7;
		}
		.infoBar {
			height: 30px;
			line-height: 25px;
			padding-bottom: 10px;
		}
		.formal {
			background-color: #EEEEF0;
			height: 25px;
			line-height: 25px;
			border-radius:.25rem;
		}
		.formal button {
			background-color: #EEEEF0;
			color: #525572;
			font-weight: 700;
		}
		.date {
			line-height: 25px;
		}
		.tableCase {
			overflow: auto;
			height: 300px;
		}
		::-webkit-scrollbar {
		  width: 10px;		/* 세로축 스크롤바 길이 */
		  height: 20px;		/* 가로축 스크롤바 길이 */
		}
		::-webkit-scrollbar-track {
		
		}
		::-webkit-scrollbar-track-piece {
		  background-color: lightgray;
		}
		::-webkit-scrollbar-thumb {
		  border-radius: 8px;
		  background-color: #4D5068;
		}
		::-webkit-scrollbar-button {
		  background-color: #373949;
		  width: 20px;
		  height: 10px;
		  border-radius:.3rem;
		}
		::-webkit-scrollbar-button:start {
		
		}
		::-webkit-scrollbar-button:end {
		
		}
		::-webkit-scrollbar-button:vertical:increment {
		}
		::-webkit-scrollbar-button:vertical:decrement {
		}
		::-webkit-scrollbar-corner {
			/* 우측 하단의 코너 부분 */
		}
		::-webkit-resizer {
		
		}
	</style>
	</head>
	<body>
		<div class="container">

			<input type="hidden" id="hd_selectedMovieId" value="">
			<div id="selectMovie" class="case">
				<div class="bar">
					&nbsp;<label>영화선택</label>
				</div>
				<div class="formal">
					&nbsp;<button id="movieFBtn" class="btn btn-xs">가나다순</button>
				</div>
				<div class="tableCase">
					<table id="movieTable" class="table table-hover table-bordered">
						<tbody></tbody>
					</table>
				</div>
			</div>
					
			<input type="hidden" id="hd_selectedBranchId" value="">
			<input type="hidden" id="hd_selectedBranchNm" value="">
			<div id="selectBranchRoom" class="case">
				<div class="bar">
					&nbsp;<label>극장선택</label>
				</div>
				<div class="formal">
					&nbsp;<button id="testBtn" class="btn btn-xs">전체극장</button>
					&nbsp;<button id="" class="btn btn-xs">상영극장</button>
				</div>
				<div class="tableCase">
				<table id="branchTable" class="table table-hover table-bordered">
					<tbody></tbody>
				</table>
				</div>
				<br/>
			</div>
				
			<input type="hidden" id="hd_selectedDate" value="">
			<div id="selectDate" class="case">
				<div class="bar">
					&nbsp;<label>날짜선택</label>
				</div>
				<div class="date">
					<div id="datePicker"></div>
				</div>
			</div>
			
			
			<div id="selectTime" class="case">
				<div id="" class="bar">
					&nbsp;<label>상영시간표</label>
				</div>
				<div class="branchInfo case">
					
				</div>
				<div class="selectTimeBtnBox">
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<br>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<br>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
					<button class="btn btn-default btn-xs timeBtn">11:11</button>
				</div>
			</div>
			
			<div id="selectNum" class="case">
				<div id="" class="bar">
					&nbsp;<label>인원선택</label>
				</div>
				<div>
				<button id="test">테스트</button>
				</div>
			</div>

			<div id="selectAllInfo">
				<div id="" class="infoBar">
					&nbsp;<button id="resetBtn" class="btn btn-warning btn-xs">예매 다시하기</button>
				</div>
			</div>

			
		</div>
		<script src="${context}/resources/js/jquery-1.12.4.js"></script>
		<script src="${context}/resources/js/bootstrap.min.js"></script>
		<script src="${context}/resources/js/jquery-ui.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				planedMovieList();
			});
		
			//datepicker 설정
			$.datepicker.setDefaults({
	            dateFormat: 'yy-mm-dd' //Input Display Format 변경
	            ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	            ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
	            ,changeYear: false //콤보박스에서 년 선택 가능
	            ,changeMonth: false //콤보박스에서 월 선택 가능                
	            ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
	            ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
	            ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
	            ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
	            ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
	            ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
	            ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
	            ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
	            ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
	            ,minDate: "+0D" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년년전)
	            ,maxDate: "+13D" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
	        });
			
			//datePicker 부여
			$("#datePicker").datepicker({
				onSelect: function(value){
					$("#hd_selectedDate").val(value);
					var roomId = $("#hd_selectedBranchId").val();
	            	if(roomId != null && roomId != "") selectTime();
				}		
			});
			
			//영화선택테이블 조회
			$("#movieFBtn").on("click", function(){
				planedMovieList();
			});
			
			function planedMovieList(){
				$.ajax({
    				type : "POST",
    				url : "${context}/screenInfo/do_retrieve_forUser.do",
    				dataType : "json",
    				data : {
    					"pageSize" : 10000,
    					"searchDiv" : "10"
    				}, 
    			success: function(data){
    				var movieArr = data;
    				$("#movieTable>tbody>tr").detach();
    				if(movieArr.length > 0){
    					for(var i=0 ; i< movieArr.length ; i++){
        					$("#movieTable").append(
        							"<tr>"+
        							"<td hidden='hidden'>"+movieArr[i].movieId+"</td>"+
        							"<td>"+movieArr[i].korTitle+"</td>"+
        							"</tr>"
        					);
        				}
    					
    				}else{
    					$("#movieTable").append(
    						"<tr><td colspan='99'>No Data</td></tr>");
    				}
    			},
    			complete:function(data){
    			},
    			error:function(xhr,status,error){

    			}
    			});
			}
			
			//영화클릭시
            $("#movieTable>tbody").on("click","tr", function(){
            	var tr = $(this);
            	var td = tr.children();
            	$("#movieTable>tbody>tr").css("background-color", "");		//tr색 초기화
    			$(tr).css("background-color", "lightgray");					//선택tr 색표시
    			
            	if(td.eq(1).length == 0)return;
    			
    			var movieId = td.eq(0).text();
            	$("#hd_selectedMovieId").val(movieId);
            	
            	All_branch();
            });			
			
			//선택한 영화의 영화ID로 지점-상영관 불러오기
			function All_branch(){
				$.ajax({
    				type : "POST",
    				url : "${context}/branchInfo/do_retrieveList.do",
    				dataType : "json"
    			}).done(function(data){
    				var movieId = $("#hd_selectedMovieId").val();
    				planedBranch(movieId, data);
    			});

			}
			
			//극장목록표시
			function planedBranch(movieId, AllList){
				$.ajax({
    				type : "POST",
    				url : "${context}/screenInfo/do_retrieve_branch.do",
    				dataType : "json",
    				data : {
    					"pageSize" : 10000,
    					"searchWord" : movieId
    				}, 
    			success: function(data){
    				var arr = data;
    				$("#branchTable>tbody>tr").detach();
    				if(AllList == null){								//상영중인 극장목록만
    					if(arr.length > 0){
    						for(var i=0 ; i< arr.length ; i++){
    							$("#branchTable").append(
    									"<tr>"+
    									"<td hidden='hidden'>"+arr[i].branchId+"</td>"+
    									"<td>"+arr[i].branchNm+" - "+arr[i].branchNm+"</td>"+
    									"</tr>"
    							);
    						}
    						
    					}else{
    						$("#branchTable").append(
    							"<tr><td colspan='99'>No Data</td></tr>");
    					}
    				}else{//전체목록
    					var branchId = "";
    					var branchNm = "";
    					var classNm = "";
    					for(var i=0 ; i< AllList.length ; i++){
    						for(var q=0 ; q< arr.length ; q++){
    							var existName = arr[q].branchNm;
    							var AllName = AllList[i].branchNm;
    							if(AllName == existName){				//선택영화가 편성된 극장일때
   									branchId = arr[q].branchId;
   									branchNm = AllName;
       								classNm = "";
       								break;
    							}else{									//편성안된극장
    								branchId = "";
    								branchNm = AllName;
    								classNm = "noPlan";
    							}
    						}
    						$("#branchTable").append(
								"<tr>"+
								"<td hidden='hidden'>"+branchId+"</td>"+
								"<td class='"+classNm+"'>"+branchNm+"</td>"+
								"</tr>"
							);						
    					}
    				}
    			},
    			complete:function(data){
    			},
    			error:function(xhr,status,error){

    			}
    			});				
			}
			
			//지점-상영관 테이블 클릭시
            $("#branchTable>tbody").on("click","tr", function(){
            	var tr = $(this);
            	var td = tr.children();
            	$("#branchTable>tbody>tr").css("background-color", "");		//tr색 초기화
    			$(tr).css("background-color", "lightgray");							//선택tr 색표시
    			
            	if(td.eq(1).length == 0)return;
    			
    			var branchId = td.eq(0).text();
    			var branchNm = td.eq(1).text();
            	$("#hd_selectedBranchId").val(branchId);
            	$("#hd_selectedBranchNm").val(branchNm);
            	
            	//날짜가 선택되어 있으면 시간포를 띄운다
            	var date = $("#hd_selectedDate").val();
            	if(date != null && date != "") selectSchedule();
            });
			
			//상영시간표
			function selectSchedule(){
				var branchId = $("#hd_selectedBranchId").val();
				var movieId = $("#hd_selectedMovieId").val();
				var date = $("#hd_selectedDate").val();
				
				if(date == null || date == "") return;
				if(movieId == null || movieId == "") return;
				if(branchId == null || branchId == "") return;
				
				$.ajax({
    				type : "POST",
    				url : "${context}/screenInfo/do_retrieve.do",
    				dataType : "json",
    				data : {
    					"pageSize" : 10000,
    					"searchDiv" : "40",
    					"searchWord_second" : date,
    					"searchWord" : roomId
    				}
				}).done(function(data){
    				var scheduleArr = data;
    				
    			});
    			
			}
			
			function schedule(){
				
				
				
			}
			
			
			$("#test").on("click", function(){
				
			});
    	</script>
	</body>
</html>