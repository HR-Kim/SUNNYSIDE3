<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%System.out.println("rererer-> : " + request.getParameter("movieId")); %>
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
		.seatPage p {
			padding-left: 10px;
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
			position: inherit;
			text-align: center;
			padding-bottom: 20px;
		}
		.seatWrap {
			border-bottom: 1px solid black;
			width: 648px;
		}
		.seatWrap .seatY {
			font-weight: bold;
		}
		.seatWrap button {
			width: 20px;
			height: 20px;
			padding: 0;
		}
		.seatPage {
			position: absolute;
			top: 0px;
			left: 0px;
			height: 560px;
			width: 650px;
			z-index: 50;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid black;
			display: none;
		}
		.submit {
			width: 150px;
			border-radius: .5rem;
			margin-left: 25px;
			margin-top: 10px;	
		}
		.submit_half {
			width: 72px;
			border-radius: .5rem;
			margin-left: 20px;
			margin-top: 10px;	
		}
		.costlight {
			color: yellow;
			font-weight: bold;
			font-size: 30px;
		}
		.lowlight {
			color: #BFBFBF;
			font-weight: bold;
		}
		.highlight {
			color: #EFF0F7;
			font-weight: bold;
		}
		#imgBox img{
			width: 180px;
			height: 250px;
		}
		#numBtnCase {
			display: none;
		}
		.center {
			text-align: center;
		}
		#numBtnCase button{
			width: 20px;
			height: 20px;
			font-size: 12px;
			font-weight: bold;
		}
		.seatFont {
			font-size: 5px;
		}
		.costFont {
			font-size: 5px;
			color: gray;
			padding-left: 90px;
		}
		.enableDate {
			background-color: yellow;
		}
		.timeBtn {
			margin-left: 10px;
			margin-right: 10px;
			margin-top: 5px;
			margin-bottom: 5px;
			
		}
		.selectTimeBtnCase {
			overflow: scroll;
			width: 5px;
			height: 5px;
		}
		.selectTimeBtnBox {
			padding: 0, 5, 0, 5;
			overflow: auto;
		}
		.branchInfo {
			font-size: 15px;
			padding-left: 20px;
			border-bottom: 1px solid #EEEEF0;
			background-color: #EEEEF0;
		}
		.branchInfo label {
			color: #525572;
			margin-bottom: 0px;
			margin-top: 0px;
		}
		.noPlan {
			color: lightgray;
		}
		#resetBtn {
			width: 150px;
			border-radius: .5rem;
		}
		#summary {
			position: absolute;
			top: 0px;
			left: 650px;
			width: 200px;
			height: 560px;
			background-color: #525572;
			border-radius: .5rem;
		}
		#selectNum {
			position: absolute;
			top: 360px;
			left: 450px;
			width: 200px;
			height: 200px;
		}
		#selectTime {
			position: absolute;
			top: 360px;
			left: 0px;
			width: 450px;			
			height: 200px;
			overflow: auto;
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
		  height: 5px;		/* 가로축 스크롤바 길이 */
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
		  width: 5px;
		  height: 5px;
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

			<div class="seatPage">
				<div class="bar">
					&nbsp;<label>좌석선택</label>
				</div>
				<div class="seatWrap">
					<p class="screen">SCREEN</p>
					<div class="seatbox">
						<%= StringUtil.makeSeatButton(0) %>
					</div>
				</div>
				<p>선택한 인원 수 만큼 좌석을 선택해주세요.</p>
			</div>
			<input type="hidden" id="isStart" value="0">
			<input type="hidden" id="hd_personTotal" value="0">
			<input type="hidden" id="hd_selectedSeatTotal" value="0">
			
			<form action="${context}/reservation/do_pay.do" method="post" id="mainForm">
				<input type="hidden" id="hd_selectedMovieId" name="movieId" value="">
				<input type="hidden" id="hd_selectedScreenId" name="screenId" value="">
				<input type="hidden" id="hd_selectedBranchId" name="branchId" value="">
				<input type="hidden" id="hd_selectedRoomId" name="roomId" value="">
				<input type="hidden" id="hd_resultCost" name="cost" value="">
				<input type="hidden" id="hd_adultYN" name="adultCnt" value="">
				<input type="hidden" id="hd_selectedSeat" name="seatInfo" value="">
			</form>
			
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
					
			
			<input type="hidden" id="hd_selectedBranchNm" value="">
			<div id="selectBranchRoom" class="case">
				<div class="bar">
					&nbsp;<label>극장선택</label>
				</div>
				<div class="formal">
					&nbsp;<button id="allBranch" class="btn btn-xs">전체극장</button>
					&nbsp;<button id="liveBranch" class="btn btn-xs">상영극장</button>
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
				<div class="branchInfo"><label></label></div>
				<div id="selectTimeBtnCase">
					<div class="selectTimeBtnBox"></div>
				</div>
			</div>
			
			<input type="hidden" id="hd_aNum" value="0">
			<input type="hidden" id="hd_sNum" value="0">
			<input type="hidden" id="hd_aCost" value="0">
			<input type="hidden" id="hd_sCost" value="0">
			<div id="selectNum" class="case">
				<div id="" class="bar">
					&nbsp;<label>인원선택</label>
				</div>
				<div id="numBtnCase">
					&nbsp;<label class="seatFont"></label>
					<div class="numBtnAdult">
						&nbsp;<b>성인</b><label class="costFont"></label>
						<br/>&nbsp;
						<button class="aBtn" value="0">0</button>
						<button class="aBtn" value="1">1</button>
						<button class="aBtn" value="2">2</button>
						<button class="aBtn" value="3">3</button>
						<button class="aBtn" value="4">4</button>
						<button class="aBtn" value="5">5</button>
						<button class="aBtn" value="6">6</button>
						<button class="aBtn" value="7">7</button>
					</div>
					<br/>
					<div class="numBtnStudent">
						&nbsp;<b>학생</b><label class="costFont"></label>
						<br/>&nbsp;
						<button class="sBtn" value="0">0</button>
						<button class="sBtn" value="1">1</button>
						<button class="sBtn" value="2">2</button>
						<button class="sBtn" value="3">3</button>
						<button class="sBtn" value="4">4</button>
						<button class="sBtn" value="5">5</button>
						<button class="sBtn" value="6">6</button>
						<button class="sBtn" value="7">7</button>
					</div>
				</div>
			</div>

			<div id="summary">
				<div id="" class="infoBar center">
					<button id="resetBtn" class="btn btn-warning btn-xs">예매 다시하기</button>
				</div>
				<div id="imgBox" class="center"></div>
				<div id="infoBox"></div>
				<div id="infoBox_middle"></div>
				<div id="infoBox_bottom"></div>
				<div id="submit_btn"></div>
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
    				selectedMovieStart();
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
            	create_branchTable(movieId);
            });			
			
			//영화를 선택하고 페이지에 들어왔을때 영화자동선택
			function selectedMovieStart(){
				var isStart = $("#isStart").val();
				if(isStart == "0"){
					var inputMovieId = opener.document.getElementById("selectedMovieId").value;
					var trList = $("#movieTable>tbody>tr");
					
					for(var i=0 ; i<trList.length ; i++){
						var td = trList.eq(i).children();
						if(td.eq(0).text() == inputMovieId){
							$("#movieTable>tbody>tr").css("background-color", "");
		    				$(trList.eq(i)).css("background-color", "lightgray");
		    				create_branchTable(inputMovieId);
		    				return;
						}
					}
					alert("선택하신 영화는 아직 예매할 수 없습니다.")
				}
				$("#isStart").val("1");
			}
			
			function create_branchTable(movieId){
				$("#hd_selectedMovieId").val(movieId);
            	$("#datePicker").datepicker("destroy");
				$("#selectTimeBtnCase>div").detach();
				$("#hd_selectedBranchId").val("");
				$(".branchInfo>label").text("");
				$("#numBtnCase").css("display", "none");
				
            	All_branch();
            	infoBox_imgNtitle(movieId);
			}

			//선택한 영화의 영화ID로 지점 불러오기
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
    									"<td>"+arr[i].branchNm+"</td>"+
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
			
			//모든상영관만 표시
			$("#allBranch").on("click", function(){
				var movieId = $("#hd_selectedMovieId").val();
				if(movieId == "") return;
				
				All_branch();
			});

			//상영중인 상영관만 표시
			$("#liveBranch").on("click", function(){	
				var movieId = $("#hd_selectedMovieId").val();
				if(movieId == "") return;
				
				planedBranch(movieId, null);
				
			});
			
			//지점 테이블 클릭시
            $("#branchTable>tbody").on("click","tr", function(){
            	var tr = $(this);
            	var td = tr.children();
            	$("#branchTable>tbody>tr").css("background-color", "");		//tr색 초기화
    			$(tr).css("background-color", "lightgray");					//선택tr 색표시
    			
            	if(td.eq(1).length == 0)return;
    			
    			var branchId = td.eq(0).text();
    			var branchNm = td.eq(1).text();
            	$("#hd_selectedBranchId").val(branchId);
            	$("#hd_selectedBranchNm").val(branchNm);
            	
            	branchView();
            	
            	//편성없는날은 선택불가처리
            	datepickerFilter();
            });
			
			//상영시간표에 지점이름 표시
			function branchView(){
				var branchNm = $("#hd_selectedBranchNm").val();
				if(branchNm == null || branchNm == "") return;
				
				$(".branchInfo>label").text(branchNm);
			} 
			
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
    					"searchWord" : movieId
    				}
				}).done(function(data){
    				var scheduleArr = data;    				
    				createTimeBtnBox(scheduleArr);
    			});
			}
			
			//상영시간표 생성
			function createTimeBtnBox(scheduleArr){
				$("#selectTimeBtnCase>div").detach();
				
				if(scheduleArr.length > 0){
					//상영관 개수 파악
					var roomArr = new Array();
					var unique_roomArr = new Array();
					for(var i=0 ; i<scheduleArr.length ; i++){
						roomArr.push(scheduleArr[i].roomNm);
					}
					$.each(roomArr, function(i, el){
						if($.inArray(el, unique_roomArr) === -1) unique_roomArr.push(el);
					});
					
					//상영관 개수만큼 div생성
					var roomData = unique_roomArr;
					if(roomData.length > 0){
						for(var i=0 ; i<roomData.length ; i++){
							var roomNm = roomData[i];
							$("#selectTimeBtnCase").append(
									"<div class='selectTimeBtnBox' data-room='"+roomNm+"'></div>"
							);
							$("div[data-room="+roomNm+"]").prepend(
									"&nbsp;<label>"+roomNm+"</label><br/>"
							);
						}
					}

					//상영관div에 버튼생성
					var boxData = $("div[data-room]");
					for(var i=0 ; i<boxData.length ; i++){			//상영관
						var boxNm = $(boxData[i]).attr("data-room");
						for(var q=0 ; q<scheduleArr.length ; q++){	//일정
							var schedule = scheduleArr[q].roomNm;
							if(boxNm == schedule){
								var target = $("div[data-room="+boxNm+"]");
								var sId = scheduleArr[q].screenId;
								var script = 'javascript:scheduleBtn("'+sId+'")';
								var startTime = scheduleArr[q].startTime;
								var time = convertTime(scheduleArr[q].startTime);
								$("#hd_selectedScreenId").val(sId);
								
								if(todayFilter(startTime) == null){//끝난영화일때
									$(target).append(
											"&nbsp;<button class='btn btn-default btn-xs timeBtn' data-room='"+roomNm+"' disabled='disabled'>"+
											 time+
											"</button>"
									);	
								}else{		
									$(target).append(
											"&nbsp;<button class='btn btn-primary btn-xs timeBtn' data-room='"+roomNm+"' onclick='"+script+"'>"+
											 time+
											"</button>"
									);
								}
							}//if END				
						}//for END
					}//for END
				}//if END
			}//function END
			
			//상영관 선택시
			function scheduleBtn(screenId){
				$.ajax({
    				type : "POST",
    				url : "${context}/screenInfo/do_retrieve.do",
    				dataType : "json",
    				data : {
    					"searchDiv" : "60",
    					"searchWord" : screenId
    				}
				}).done(function(data){
					var screenInfo = data[0];
					var roomId = screenInfo.roomId;
					var roomNm = screenInfo.roomNm
					
					var adultCost = screenInfo.adultCost;
					var studentCost = screenInfo.studentCost;
					$("#hd_aCost").val(adultCost);
					$("#hd_sCost").val(studentCost);
					$("#hd_selectedRoomId").val(roomId);
					
					var date = screenInfo.startTime;
					$.ajax({
	    				type : "POST",
	    				url : "${context}/room/do_retrieve.do",
	    				dataType : "json",
	    				data : {
	    					"searchDiv" : "20",
	    					"searchWord" : roomId
	    				}
					}).done(function(data){
						var roomInfo = data[0];
						var totolSeat = roomInfo.totalSeat;
						var restSeat = roomInfo.restSeat;
						var selectedAdult = 0;
						var selectedStudent = 0;
						
						infoBox_center(roomNm, date);
						
						$("#numBtnCase").css("display", "block");
						
						$(".seatFont").text("좌석현황: "+restSeat+"/"+totolSeat);
						$(".costFont").text("1인/"+adultCost+"원");
						$(".costFont").text("1인/"+studentCost+"원");
					});
				});
			}
			
			//좌석선택시 (성인)
			$(".numBtnAdult>button").on("click", function(){asd();
				var btn = $(this)[0];
				
				$(".numBtnAdult>button").css("background-color", "window");
				$(".numBtnAdult>button").css("opacity", "0.5");
				$(this).css("opacity", "1");
				$(this).css("background-color", "lightblue");
				
				$("#hd_aNum").val(btn.value);
				
				infoBox_bottom();
			});
			
			//좌석선택시 (학생)
			$(".numBtnStudent>button").on("click", function(){asd();
				var btn = $(this)[0];
				
				$(".numBtnStudent>button").css("background-color", "window");
				$(".numBtnStudent>button").css("opacity", "0.5");
				$(this).css("opacity", "1");
				$(this).css("background-color", "lightblue");
				
				$("#hd_sNum").val(btn.value);

				infoBox_bottom();
			});
			
			//상영시간표 시간정보 변형
			function convertTime(scheduleData){
				//scheduleData -> yyyy-mm-dd hh:mm;ss
				var scheduleDataArr = scheduleData.split(" ");
				//var dateArr = scheduleDataArr[0].split("-");
				var timeArr = scheduleDataArr[1].split(":");
				var format = timeArr[0] + ":" + timeArr[1];
				return format;
			}
			
			//현재시간보다 늦은 데이터는 null반환
			function todayFilter(data){
				//data -> yyyy-mm-dd hh:mm:ss
				var now = new Date();
				var y = now.getYear() + 1900;
				var mt = now.getMonth() + 1;
				var d = now.getDate();
				var h = now.getHours();
				var m = now.getMinutes();
				
				var dataArr = data.split(" ");
				var indateArr = dataArr[0];
				var inTimeArr = dataArr[1];
				
				var inDate = indateArr.split("-");
				var inTime = inTimeArr.split(":");
				
				var inY = inDate[0];
				var inMt = inDate[1];
				var inD = inDate[2];
				var inH = inTime[0];
				var inM = inTime[1];
				
				if(d == inD){
					//같은 Hour일 경우
					if(h == inH){
						if(m > inM) return null;
					}
					
					//Hour가 다를경우
					if(h > inH) return null;
				}
				return data;
			}

			 //datepicker 설정
			 function datepickerFilter(){
					var branchId = $("#hd_selectedBranchId").val();
					var movieId = $("#hd_selectedMovieId").val();
					
					if(movieId == null || movieId == "") return;
					if(branchId == null || branchId == "") return;
					
					$.ajax({
	    				type : "POST",
	    				url : "${context}/screenInfo/do_retrieve.do",
	    				dataType : "json",
	    				data : {
	    					"pageSize" : 10000,
	    					"searchDiv" : "50",
	    					"searchWord" : movieId
	    				}
					}).done(function(data){
	    				var scheduleArr = data;    				
	    				//startTime: "2019-10-17 15:00:00"

	    				if(scheduleArr.length > 0){
	    					var unique_Arr = new Array();
	    					var dataArr = new Array();
	    					for(var i=0 ; i<scheduleArr.length ; i++){
	    						var time = scheduleArr[i].startTime;
	    						var dateArr = time.split(" ");
	    						var timeArr = dateArr[0].split("-");
	    						var d = parseInt(timeArr[2]);
	    						
	    						dataArr.push(d);
	    					}
	    					$.each(dataArr, function(i, el){
	    						if($.inArray(el, unique_Arr) === -1) unique_Arr.push(el);
	    					});
	    					
	    					$("#datePicker").datepicker("destroy");			//제거
	    					$("#datePicker").datepicker({					//생성
	    						beforeShowDay: function(d) {				//피커 생성 전 처리
	    							var pd = d.getDate();
	    							if ($.inArray(pd, unique_Arr) != -1) {
	    						           return [true, "",""]; 			//Arr에 있는 날짜만 표시
	    						       } else{
	    						            return [false,"",""];			//아니면 선택불가
	    						       }
	    						},
	    						onSelect: function(value){					//날짜클릭시
	    							$("#hd_selectedDate").val(value);
	    							var roomId = $("#hd_selectedBranchId").val();
	    			            	if(roomId != null && roomId != "") selectSchedule();
	    						}
	    					});//datepicker End
	    				}//if End
	    			});//done End
				}//-function End
				
				//요약 - 포스터, 제목
				function infoBox_imgNtitle(movieId){
					$.ajax({
	    				type : "POST",
	    				url : "${context}/screening/do_selectOne_movie.do",
	    				dataType : "json",
	    				data : {
	    					"movieId" : movieId
	    				}
					}).done(function(data){
						var vo = data;
						var title = vo.kortitle;
						
						$("#infoBox>div").detach();
						
						//제목
						if(title.length > 14){
							var upper = title.substring(0,14);
							var lower = title.substr(14,title.length);
							$("#infoBox").append(
									"<div>"+
									"<br/>&nbsp;&nbsp;&nbsp;"+
									"<label class='highlight'>"+upper+"</label>"+
									"<br/>&nbsp;&nbsp;&nbsp;&nbsp;<label class='highlight'>"+lower+"</label>"+
									"<div>"				
							);
						}else{
							$("#infoBox").append(
									"<div>"+
									"<br/>&nbsp;&nbsp;&nbsp;"+
									"<label class='highlight'>"+title+"</label>"+
									"<div>"				
							);
						}

						//포스터
						$("#imgBox>img").detach();
						$("#imgBox").append(
							"<img class='case' alt='moviePoster' src='"+vo.poster+"'>"		
						);
					});
				}
				
				//요약 - 극장, 날짜
				function infoBox_center(roomNm, date){
					var branchNm = $("#hd_selectedBranchNm").val();
					
					$("#infoBox_middle>div").detach();
					$("#infoBox_bottom>div").detach();
					
					$("#infoBox_middle").append(
						"<div>"+
						"<hr/>"+
						"&nbsp;&nbsp;<label class='lowlight'>극장</label>"+
						"&nbsp;<label class='highlight'>"+branchNm+" - "+roomNm+"</label>"+
						"<br/>"+
						"&nbsp;&nbsp;<label class='lowlight'>날짜</label>"+
						"&nbsp;<label class='highlight'>"+date+"</label>"+
						"<br/>"
					);	
				}
	
				//요약 - 인원, 금액, 예매버튼
				function infoBox_bottom(){asd();
					var adult = parseInt($("#hd_aNum").val());
					var student = parseInt($("#hd_sNum").val());
					var aCost = parseInt($("#hd_aCost").val());
					var sCost = parseInt($("#hd_sCost").val());
					var resultCost = eval( eval(adult*aCost) + eval(student*sCost) );
					
					$("#hd_resultCost").val(resultCost);
					if(adult > 0){
						$("#hd_adultYN").val("1");
					}else{
						$("#hd_adultYN").val("0");
					}
						
					
					$("#infoBox_bottom>div").detach();
					$("#submit_btn>div").detach();
					
					$("#infoBox_bottom").append(
							"<div>"+
							"&nbsp;&nbsp;<label class='lowlight'>인원</label>"+
							"&nbsp;<label class='highlight'>성인 "+adult+", 학생 "+student+"</label>"+
							"<br/>"+
							"&nbsp;&nbsp;<label class='lowlight'>금액</label>"+
							"&nbsp;<label class='costlight'>"+resultCost+"</label>"+
							"<label class='highlight'>원</label>"+
							"<br/>"
					);	
					submit_btn(1);
				}
				
				//단계버튼		1:좌석선택으로, 2:결제단계로, 3:뒤로가기
				function submit_btn(select){asd();
					if(select == 1){
						$("#submit_btn").append(
								"<div>"+
								"<button class='submit btn btn-danger btn-lg' onclick='javascript:go_seat(true);'>다음단계</button>"+
								"</div>"
						);
						return;
					}else if(select == 2){
						$("#submit_btn").append(
								"<div>"+
								"<button class='submit_half btn btn-warning btn-sm' onclick='javascript:go_back();'>뒤로가기</button>"+
								"<button class='submit_half btn btn-danger btn-sm' onclick='javascript:go_pay();'>결제하기</button>"+
								"</div>"
						);
						return;
					}else if(select == 3){
						$("#submit_btn").append(
								"<div>"+
								"<button class='submit btn btn-warning btn-lg' onclick='javascript:go_back();'>뒤로가기</button>"+
								"</div>"
						);
						return;
					}
				}
				
				//오른쪽 요약박스를 지운다
				function reset_infoBox(){
					$("#infoBox_middle>div").detach();
					$("#infoBox_bottom>div").detach();
					$("#infoBox_bottom>div").detach();
					$("#infoBox>div").detach();
					$("#submit_btn>div").detach();
				}
				
				//좌석선택으로 가는 버튼
				function go_seat(bool){asd();
					if(bool == true){
						var adult = parseInt($("#hd_aNum").val());
						var student = parseInt($("#hd_sNum").val());
						var personTotal = eval(adult + student);
						if(personTotal == 0){
							alert("인원 수가 0명 입니다.");
							return;
						}
						
						$(".seatPage").css("display", "block");
						create_Seat_Table();
					}else{
						delete_Seat_Table();
						$(".seatPage").css("display", "none");
					}
					$("#submit_btn>div").detach();
					submit_btn(3);
				}
				
				//예매다시하기버튼
				$("#resetBtn").on("click", function(){
					if(confirm("기록을 지우고 처음으로 돌아갑니다.")==false) return;
					
					$(".seatPage").css("display", "none");
					
					$("#selectTimeBtnCase>div").detach();
					$("#numBtnCase").css("display", "none");
					$("#branchTable>tbody>tr").detach();
					$("#datePicker").datepicker("destroy");
					$("#submit_btn>div").detach();
					
					delete_Seat_Table();
					
					reset_infoBox();
					
					$(".branchInfo>label").text("");
					$("#hd_selectedMovieId").val("");
					$("#hd_selectedScreenId").val("");
					$("#hd_selectedBranchId").val("");
					$("#hd_selectedRoomId").val("");
					$("#hd_resultCost").val("");
					$("#hd_adultYN").val("");
					$("#hd_selectedBranchNm").val("");
					$("#hd_selectedDate").val("");
					$("#hd_aNum").val("0");
					$("#hd_sNum").val("0");
					$("#hd_aCost").val("0");
					$("#hd_sCost").val("0");
					$("#hd_personTotal").val("0");
    				$("#hd_selectedSeatTotal").val("0");
    				$("#hd_selectedSeat").val("");
    				$(".numBtnAdult>button").css("background-color", "window");
    				$(".numBtnAdult>button").css("opacity", "1");
    				$(".numBtnStudent>button").css("background-color", "window");
    				$(".numBtnStudent>button").css("opacity", "1");
    				
    				$("button[data-y][data-x]").attr("data-nowSelect", "0");
				});
				
				//좌석테이블 생성
	    		function create_Seat_Table(){
					var searchWord = $("#hd_selectedScreenId").val();
	    			var searchDiv = "70";
	    			
	    			$.ajax({
	    				type : "POST",
	    				url : "${context}/seat/do_retrieve_reservation.do",
	    				dataType : "json",
	    				data : {
	    					"searchWord" : searchWord,
	    					"searchDiv" : searchDiv
	    				}, 
	    			success: function(data){
	    				var seatArr = data;
	    				if(seatArr.length != 0){
	    					console.log(seatArr);
	    					$(".seatPage").css("display", "block");
	    					
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
	    					var adult = parseInt($("#hd_aNum").val());
	    					var student = parseInt($("#hd_sNum").val());
	    					var total = eval(adult + student);
	    					$("#hd_personTotal").val(total);
	    				}
	    			},
	    			complete:function(data){
	    				
	    			},
	    			error:function(xhr,status,error){
	    				
	    			}
	    			}); 
	    		}
				
	    		//좌석테이블 제거
	    		function delete_Seat_Table(){
	    			var seat = $("button[data-y][data-x]");
	    			seat.css("background-color", false);		
	    			seat.css("color", false);
	    			seat.css("visibility", "visible");		//보이지 않게 만들었던 버튼을 다시 보이게
	    			seat.css("visibility", false);			//css를 없앤다. 이 속성을 다시 만들면 이전의 값이 나오므로 false하기전에 visible로 반드시 만들어야한다
	    			seat.prop("disabled", true);
	    			seat.text("");
	    		}
	    		
	    		//좌석클릭시
	    		$("button[data-y][data-x]").on("click", function(){
	    			var seat = $(this);
	    			var Y = seat.attr("data-y");
					var X = seat.attr("data-x");
					var seatNm = Y+""+X;
					
					var enable = seat.text();
					if(enable == 'X') return;
					
					$("#submit_btn>div").detach();
					submit_btn(3);
					
					var seatInfo = $("#hd_selectedSeat").val();	
	    			var seatArr = convert_arrayNstring(true, seatInfo);
	    			
	    			if(seat.prop("disabled", false)){						//좌석이 사용가능할때
	    				var nowSelect = seat.attr("data-nowSelect");
	    				
	    				var total = parseInt($("#hd_personTotal").val());
	    				var now = parseInt($("#hd_selectedSeatTotal").val());
	    				var d = total - now; 

		    			if(nowSelect != 1 && d > 0){						//선택
	    					seat.css("background-color", "yellow");
	    					seat.attr("data-nowSelect", 1);
	    					$("#hd_selectedSeatTotal").val( eval(now+1) );
	    					
	    					seatArr.push(seatNm);
	    					
	    					//배열->문자열
	    					var seatString = convert_arrayNstring(false, seatArr);
	    					
	    					$("#hd_selectedSeat").val(seatString);

		    				now = parseInt($("#hd_selectedSeatTotal").val());
							d = total - now; 
	    					if(d <= 0){
	    						$("#submit_btn>div").detach();
	    						submit_btn(2);
	    					}
		    			}else if(nowSelect == 1){							//취소
		    				seat.attr("data-nowSelect", 0);
		    				seat.css("background-color", "blue");
		    				$("#hd_selectedSeatTotal").val( eval(now-1) );
		    				
		    				//배열요소삭제
		    				var index = seatArr.indexOf(seatNm);
							seatArr.splice(index, 1);
							
							//배열->문자열
							var seatString = convert_arrayNstring(false, seatArr);
							$("#hd_selectedSeat").val(seatString);
		    			}else if(d <= 0){									//선택불가
		    				alert("좌석을 모두 선택했습니다.\n다음단계로 진행해주세요.");
		    				$("#submit_btn>div").detach();
		    				submit_btn(2);
		    			}
		    			
		    			
	    			}
	    		});

	    		//배열을 문자열로 혹은 문자열을 배열로, 문자열= %A1%A2... , 배열= [],[A1],[A2],.. 
	    		function convert_arrayNstring(bool, value){			//[0]번째 존재X
	    			if(bool == true){//문자열을 배열로
	    				var stringInfo = value;
		    			var arr = stringInfo.split("%");		
	    				return arr;
	    			}else{			//배열을 문자열로
	    				//중복제거작업
    					var uniqueArr = new Array();
    					$.each(value, function(i, el){
    						if($.inArray(el, uniqueArr) == -1) uniqueArr.push(el);
    					});
    					
    					var stringValue = "";
    					for(var i=1 ; i<uniqueArr.length ; i++){
    						stringValue += "%" + uniqueArr[i];
    					}	    				
    					return stringValue;
	    			}
	    		}
	    		
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
	    		
	    		//결제페이지로
	    		function go_pay(){
	    			if(confirm("결제페이지로 이동하시겠습니까?")==false) return;
	    			var frm = $("#mainForm");
	    			frm.submit();
	    		}
	    		
	    		//뒤로
	    		function go_back(){asd();
	    			$("button[data-y][data-x]").attr("data-nowSelect", "0");
	    			$("#hd_selectedSeatTotal").val("0");
	    			$("#hd_selectedSeat").val("");	
					
					go_seat(false);
	    			$("#submit_btn>div").detach();
	    			submit_btn(1);
	    		}
	    		
	    		function asd(){
	    			console.log("=================");
	    			console.log("hd_adultYN : " + $("#hd_adultYN").val());
	    			console.log("hd_resultCost : " + $("#hd_resultCost").val());
	    			console.log("hd_aNum : " + $("#hd_aNum").val());
	    			console.log("hd_sNum : " + $("#hd_sNum").val());
	    			console.log("hd_aCost : " + $("#hd_aCost").val());
	    			console.log("hd_sCost : " + $("#hd_sCost").val());
	    			console.log("hd_selectedMovieId : " + $("#hd_selectedMovieId").val());
	    			console.log("hd_selectedScreenId : " + $("#hd_selectedScreenId").val());
	    			console.log("hd_selectedBranchId : " + $("#hd_selectedBranchId").val());
	    			console.log("hd_selectedRoomId : " + $("#hd_selectedRoomId").val());
	    			console.log("hd_resultCost : " + $("#hd_resultCost").val());
	    			console.log("hd_selectedSeat : " + $("#hd_selectedSeat").val());
	    			console.log("hd_personTotal : " + $("#hd_personTotal").val());
	    			console.log("hd_selectedSeatTotal : " + $("#hd_selectedSeatTotal").val());
	    			console.log("=================");
	    		}
    	</script>
	</body>
</html>