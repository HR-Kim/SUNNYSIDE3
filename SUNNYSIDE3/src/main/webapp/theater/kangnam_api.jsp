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
			<td id="hongdaeInfo" onMouseOver="this.className='mouseOver';" onMouseOut="this.className='mouseOut'" style="width: 200px;" class="text-center">
				영화관 소개
			</td>
			<td style="width: 200px; background: #000000; color:#ffffff;" class="text-center">
				약도
			</td>
		</table>
	</div>
	
	
	<br/>
	<br/>
	
	<!-- 지도 -->
	<div class="center-block" id="map" style="width:50%;height:600px;"></div>
	
	
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="${context}/resources/js/jquery-1.12.4.js"></script>
    <script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=79dc5d9ee17692f633e4726d83f25b50&libraries=services"></script>
	<script>
	
	
		/** 강남정보로 이동 */
		$("#hongdaeInfo").on("click",function(){
			location.href = "${context}/theater/kangnam_info.do";
		});
	
		
	
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.4992874, 127.0310189), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 주소로 좌표를 검색합니다
		geocoder.addressSearch('서울특별시 강남구 역삼동 735-1', function(result, status) {
		
		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:3px 0;">강남점</div>'
		        });
		        infowindow.open(map, marker);
		
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
	</script>
</body>
</html>