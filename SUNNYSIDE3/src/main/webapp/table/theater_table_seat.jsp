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
		<title>Seat Setting</title>
		<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
		<style type="text/css">
		.seatWrap {
				margin: 20px;
				padding: 20px;
				border: 1px solid black;
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
			.seatBax {
				text-align: center;
			}
		</style>
	</head>
	<body>
		<div class="container">
			좌석 : 
			<select id="select">
				<option value="2">통로</option>
				<option value="0">사용가능좌석</option>
				<option value="1">사용불가좌석</option>
			</select>
			<br/>
			<button id="btn_resetA">초기화 : 통로</button>
			<button id="btn_resetB">초기화 : 사용가능좌석</button>
			
			<div class="seatWrap">
				<p class="screen">SCREEN</p>
				<div class="seatBox">
					<%= StringUtil.makeSeatButton(1) %>
				</div>
			</div>
			
			<button id="submit">완료</button>
			<button id="cancel">취소</button>
		</div>
		
		<script src="${context}/resources/js/jquery-1.12.4.js"></script>
		<script src="${context}/resources/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
    		$(document).ready(function(){
    			
    		});
		
    		$("#submit").on("click", function(){
    			var buttonLength = 420;	//setting 전 총 좌석수
    			var setComplete = $(".seatBox>:button[data-setseat=true]"); //setseat이 true면 setting 끝난 좌석
    			if(setComplete.length != 420){
    				alert("모든 좌석에 역할이 부여되어야 합니다.\n지정되지 않은 좌석 수 : " + (buttonLength-setComplete.length));
    				return;
    			}
    			
    			if(confirm("좌석설정을 완료하시겠습니까?")==false) return;
    			
    			var jsonArr = new Array();
    			
    			for(var i=0 ; i<buttonLength ; i++){
    				var thisSeat = setComplete[i];
    				var obj = new Object();
    				obj.Y = $(thisSeat).attr('data-y');		//Y축
    				obj.X = $(thisSeat).attr('data-x');		//X축
    				obj.use = $(thisSeat).attr('data-use');	//사용여부
    				console.log(thisSeat);
    				console.log(obj);
    				jsonArr.push(obj);
    				
    			}
				//console.log(jsonArr);
    			//parent창의 메소드로 배열전달
    			window.opener.setSeatInfo(jsonArr);
    			window.self.close();
    		});
    		
    		//연속좌석설정on
    		$(".seatBox").on("mousedown", function(){
    			$(".seatBox>:button").on("mouseover", function(){
    				var data = $(this);
        			var select = $("#select").val();
        			var y = data.data().y;
        			var x = data.data().x;
        			var seat = $('button[data-y='+y+'][data-x='+x+']');
        			
        			if(select == 2){
        				seat.attr("data-use" ,"2");
        				seat.css("background-color" ,"white");
        			}else if(select == 0){
        				seat.attr("data-use" ,"1");
        				seat.css("background-color" ,"blue");
        			}else{
        				seat.attr("data-use" ,"0");
        				seat.css("background-color" ,"red");
        			}
        			seat.attr("data-setseat" ,"true");
        		});
    		});
    		
    		//연속좌석설정off
    		$(".seatBox").on("mouseup", function(){
    			$(".seatBox>:button").off("mouseover");
    		});
    		
    		$(".seatBox>:button").on("click", function(){
				var data = $(this);
    			var select = $("#select").val();
    			var y = data.data().y;
    			var x = data.data().x;
    			var seat = $('button[data-y='+y+'][data-x='+x+']');
    			
    			if(select == -1){
    				seat.attr("data-use" ,"2");
    				seat.css("background-color" ,"white");
    			}else if(select == 0){
    				seat.attr("data-use" ,"1");
    				seat.css("background-color" ,"blue");
    			}else{
    				seat.attr("data-use" ,"0");
    				seat.css("background-color" ,"red");
    			}
    			seat.attr("data-setseat" ,"true");
    		});
    		
    		//통로로 초기화
    		$("#btn_resetA").on("click", function(){
    			$(".seatBox>:button[data-use]").css("background-color" ,"white");
    			$(".seatBox>:button[data-use]").attr("data-use" ,"2");
    			$(".seatBox>:button[data-use]").attr("data-setseat" ,"true");
    		});
    		
    		//사용가능좌석으로 초기화
    		$("#btn_resetB").on("click", function(){
    			$(".seatBox>:button[data-use]").css("background-color" ,"blue");
    			$(".seatBox>:button[data-use]").attr("data-use" ,"1");
    			$(".seatBox>:button[data-use]").attr("data-setseat" ,"true");
    		});
    		
    	</script>
	</body>
</html>