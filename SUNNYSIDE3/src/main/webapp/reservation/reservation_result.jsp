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
		<title>결제완료</title>
		<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
	<style type="text/css">
		.MAIN_MSG {
			margin: 20px;
			border-bottom: 2px double black;
			border-top: 2px double black;
			text-align: center;
		}
		.HIGHLIGHT {
			color: red;
		}
		.INFOMATION {
			padding-left: 50px;
		}
		.OK {
			text-align: center;
		}
	</style>
	</head>
	<body>
		<div class="container">
			<div class="MAIN_MSG">
				<h3>결제가 <h class="HIGHLIGHT">완료</h>되었습니다.</h3>
			</div>
			
			<div>
				<div class="INFOMATION">
					<div id="title"></div>
					<div id="cost"></div>
				</div>
				
				<hr/>
				
				<div class="INFOMATION arrData"></div>
			</div>
			
			<hr/>
			
			
			<div class="OK">
				<p>예매정보는 마이페이지에서 다시 보실 수 있습니다.</p>
				<button id="okBtn" class="btn btn-primary btn-lg">확인</button>
			</div>
		</div>
	
	
		<script src="${context}/resources/js/jquery-1.12.4.js"></script>
		<script src="${context}/resources/js/bootstrap.min.js"></script>
		
		<script type="text/javascript">
    		$(document).ready(function(){
    			getInfo();
    		});
    			
			function getInfo(){
				var userId = "${user.userId}";
				var screenId = "${vo.screenId}";
				var cost = "${cost}";
				var kortitle = "${vo.korTitle}";
				var engtitle = "${vo.engTitle}";
				var seatData = "${seatData}";
				
				setTitle(kortitle, engtitle);
				setCost(cost);
				setSeatNCode(seatData, screenId, userId);
			}
			
			function setTitle(kor, eng){
				var title = "[영화] " + kor + "(" + eng + ")";
				$("#title").append(
					"<label>"+title+"</label>"
				);
			}
			
			function setCost(cost){
				$("#cost").append(
					"<label>가격 : "+cost+" 원</label>"
				);
			}
			
			//각각의 좌석,코드 출력
			function setSeatNCode(arrData, screenId, userId){
				//좌석문자열을 배열로
				var stringInfo = arrData;
    			var arr = stringInfo.split("%");
    			
    			$(".arrData>.individual").detach();
    			for(var i=1 ; i<arr.length ; i++){
    				$.ajax({
	    				type : "POST",
	    				url : "${context}/reservation/do_selectOne_result.do",
	    				dataType : "json",
	    				data : {
	    					"seatNm" : arr[i],
	    					"screenId" : screenId,
	    					"userId" : userId
	    				}
					}).done(function(data){
						var vo = data;
						var code = vo.ticketCode;
						var seat = vo.seatNm;

						$(".arrData").prepend(
							"<labal class='individual'>좌석 : "+seat+
							"(티켓코드 : <h class='HIGHLIGHT'>"+code+"</h>)</label>"+
							"<br/>"
						);
					});
    			}
			}
			
			$("#okBtn").on("click", function(){
				if(confirm("페이지에서 벗어납니다.")==false) return;
				alert("예매정보는 마이페이지에서 다시 보실 수 있습니다.");
				self.close();
			});
    	</script>
	</body>
</html>