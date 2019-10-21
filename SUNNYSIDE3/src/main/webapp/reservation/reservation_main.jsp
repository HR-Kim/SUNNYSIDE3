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
		.submit {
			width: 150px;
			border-radius: .5rem;
			margin-left: 25px;
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

			<form action="/sunnyside/test" method="post" id="mainForm">
				<input type="hidden" id="hd_selectedMovieId" value="">
				<input type="hidden" id="hd_selectedScreenId" value="">
				<input type="hidden" id="hd_selectedBranchId" value="">
				<input type="hidden" id="hd_selectedRoomId" value="">
				<input type="hidden" id="hd_resultCost" value="">
				<input type="hidden" id="hd_adultYN" value="">
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
            	$("#datePicker").datepicker("destroy");
				$("#selectTimeBtnCase>div").detach();
				$("#hd_selectedBranchId").val("");
				$(".branchInfo>label").text("");
				$("#numBtnCase").css("display", "none");
				
            	All_branch();
            	infoBox_imgNtitle(movieId);
            });			
			
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
			$(".numBtnAdult>button").on("click", function(){
				var btn = $(this)[0];
				
				$(".numBtnAdult>button").css("background-color", "window");
				$(".numBtnAdult>button").css("opacity", "0.5");
				$(this).css("opacity", "1");
				$(this).css("background-color", "lightblue");
				
				$("#hd_aNum").val(btn.value);
				
				infoBox_bottom();
			});
			
			//좌석선택시 (학생)
			$(".numBtnStudent>button").on("click", function(){
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
				function infoBox_bottom(){
					var adult = parseInt($("#hd_aNum").val());
					var student = parseInt($("#hd_sNum").val());
					var aCost = parseInt($("#hd_aCost").val());
					var sCost = parseInt($("#hd_sCost").val());
					var resultCost = eval( eval(adult*aCost) + eval(student*sCost) );
					
					$("#hd_resultCost").val(resultCost);
					if(adult > 0) $("#hd_adultYN").val("1");
					
					$("#infoBox_bottom>div").detach();
							
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
					
					$("#infoBox_bottom").append(
							"<div>"+
							"<button class='submit btn btn-danger btn-lg' onclick='javascript:submit();'>예매하기</button>"+
							"</div>"
					);
				}
				
				//예매하기 버튼
				function submit(){
					if(confirm("예매하시겠습니까?")==false) return;
					
					var form = $("#mainForm");
					form.submit();
					//넘어가는 정보
					//지점id, 상영관id, 영화id, 스크린id, 성인유무, 가격
					
				}
    	</script>
	</body>
</html>