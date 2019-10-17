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
				<div class="branchInfo"><label></label></div>
				<div id="selectTimeBtnCase">
					<div class="selectTimeBtnBox"></div>
				</div>
			</div>
			
			<div id="selectNum" class="case">
				<div id="" class="bar">
					&nbsp;<label>인원선택</label>
				</div>
				<div>
					<button id="asd">asd</button>
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
			//var availableDates = ["10-17-2019", "10-18-2019", "10-19-2019"];
			var availableDates = [17,18,19];
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
	            	if(roomId != null && roomId != "") selectSchedule();
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
            	
            	//날짜가 선택되어 있으면 시간포를 띄운다
            	//var date = $("#hd_selectedDate").val();
            	//if(date != null && date != "") selectSchedule();
            });
			
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
								var time = convertTime(scheduleArr[q].startTime);

								if(todayFilter(time) == null){
									$(target).append(
											"&nbsp;<button class='btn btn-default btn-xs timeBtn' data-room='"+roomNm+"' disabled='disabled'>"+
											 time+
											"</button>"
									);	
								}else{		
									$(target).append(
											"&nbsp;<button class='btn btn-default btn-xs timeBtn' data-room='"+roomNm+"'>"+
											 time+
											"</button>"
									);
								}
							}				
						}
					}
				}
			}
			
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
				//data -> hh:mm
				var now = new Date();
				var h = now.getHours();
				var m = now.getMinutes();
				
				var dataArr = data.split(":");
				var ah = dataArr[0];
				var am = dataArr[1];

				//같은 Hour일 경우
				if(h == ah){
					if(m > am) return null;
				}
				
				//Hour가 다를경우
				if(h > ah) return null;
				
				return data;
			}
			
			
			$("#asd").on("click", function(){
				$("#datePicker").datepicker("destroy");
				$("#datePicker").datepicker({
					//beforeShowDay: asdasd
					beforeShowDay: testa
					
			});
			});
			
			
			 function asdasd(d) {
			        var dmy = 0;//(d.getMonth()+1); 
			        //if(d.getMonth()<9) 
			          //  dmy="0"+dmy; 
			        //dmy+= "-"; 

			        if(d.getDate()<10) dmy+="0"; 
			            dmy+=d.getDate();
			            console.log(typeof dmy);console.log(typeof availableDates);
			        console.log(dmy + ' .' +availableDates+' : '+($.inArray(dmy, availableDates)));

			        if ($.inArray(dmy, availableDates) != -1) {
			            return [true, "","Available"]; 
			        } else{
			             return [false,"","unAvailable"]; 
			        }
			    }
			 
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
	    					
	    					$("#datePicker").datepicker("destroy");
	    					$("#datePicker").datepicker({
	    						beforeShowDay: function(d) {	
	    							var dmy = 0
	    							if(d.getDate()<10) dmy+="0"; 
	    				            dmy+=d.getDate();
	    				            
	    				            console.log(typeof dmy); console.log(typeof unique_Arr);
	    				            console.log(typeof unique_Arr[0]);
	    							 console.log(dmy + ' .' +unique_Arr+' : '+($.inArray(dmy, unique_Arr)));
	    							if ($.inArray(dmy, unique_Arr) != -1) {
	    						           return [true, "","Available"]; 
	    						       } else{
	    						            return [false,"","unAvailable"]; 
	    						       }
	    						}
	    					});
	    				}//if End
	    			});//done End
				}//-function End
				
				 
				 function testa(da){
						var branchId = $("#hd_selectedBranchId").val();
						var movieId = $("#hd_selectedMovieId").val();
						
						
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
		    						if($.inArray(el, unique_Arr) == -1) unique_Arr.push(el);
		    					});
		    							var dmy = 0
		    							if(da.getDate()<10) dmy+="0"; 
		    				            dmy+=da.getDate();
		    				            
		    				            console.log(typeof dmy); console.log(typeof unique_Arr);
		    				            console.log(typeof unique_Arr[0]);
		    							 console.log(dmy + ' .' +unique_Arr+' : '+($.inArray(dmy, unique_Arr)));
		    							if ($.inArray(dmy, unique_Arr) != -1) {
		    						           return [true, "","Available"]; 
		    						       } else{
		    						            return [false,"","unAvailable"]; 
		    						       }
		    						
		    				//->ajax제거 피커함수, 아작스 독립필요
		    				}//if End
		    			});//done End
					}//-function End
    	</script>
	</body>
</html>