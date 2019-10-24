<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<style>
	.center-block {
	  display: block;
	  margin-left: auto;
	  margin-right: auto;
	}
	.element {
	  .center-block();
	}
	td.mouseOver {
    	background: #ff9728;
    	color:#ffffff;
    	text-align: center;
	}
	td.mouseOut {
	    background: #fff;
	    text-align: center;
	}
</style>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 부트스트랩 -->
<link href="${context }/resources/css/bootstrap.min.css" rel="stylesheet">
	<title>영화관 강남</title>
</head>
<body>
	
	<br/>
	<br/>
	
	<div class="container">
		<h1>SUNNYSIDE 강남점</h1>		
		<table style="width: 700px;">
			<td style="width: 40%;"><strong>서울특별시 강남구 역삼동 735-1</strong></td>
			<td style="width: 10%;"><strong>총 상영관수</strong></td>
			<td style="width: 10%;">22개관</td>
			<td style="width: 10%;"><strong>총 좌석수</strong></td>
			<td style="width: 10%;">4,633석</td>
		</table>
	</div>
	
	<br/>
	
	<div class="container">
		<br/>
		<table class="table table-bordered" style="width: 400px;">
			<td style="width: 200px; background: #000000; color:#ffffff;" class="text-center">
				영화관 소개
			</td>
			<td id="kangnamApi" onMouseOver="this.className='mouseOver';" onMouseOut="this.className='mouseOut'" style="width: 200px;" class="text-center">
				약도
			</td>
		</table>
	</div>
	
	
	<br/>
	<br/>
	<br/>
	<br/>
	
	<div class="container">
		<img class="center-block" src="${context }/resources/image/theater.png">
	</div>
	
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	
	
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${context}/resources/js/jquery-1.12.4.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>
    
    
	<script type="text/javascript">
		
		/** 약도로 이동 */
		$("#kangnamApi").on("click",function(){
			location.href = "${context}/theater/kangnam_api.do";
		});
	
	
	
	</script>
	
</body>
</html>