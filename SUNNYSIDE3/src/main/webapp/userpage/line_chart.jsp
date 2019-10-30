<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%--
  /**
  * @Class Name : pie_chart.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.04.26            최초 생성
  *
  * author SIST 개발팀
  * since 2018.04.26
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 부트스트랩 -->
	<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<script src="${context}/resources/js/jquery.validate.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
    	  
  	  	var jsonString = $.ajax({
				  			url : "${context}/chart/line_chart.do",
				  			async : false,
				  			dataType : "html",
				  			data : {
				  		
				  			}
  		       		   }).responseText;
  		console.log("jsonString:"+jsonString);
  		
  		var newArr = JSON.parse(jsonString);
  		
  		//Label
  		var data = new google.visualization.DataTable();
  		data.addColumn('string','month');
  		data.addColumn('number','월별 예매횟수');
  		
  		//Data
  		for(var i=0; i<newArr.length; i++){
  			console.log("newArr[i]:"+newArr[i]);
  			data.addRow(newArr[i]);
  		}
  		

        var options = {
          title: '예매통계',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
  	<div class="container">
  		<div class="page-header">
  			<h1>예매통계</h1>
  		</div>
  		<div class="row">
    		<div id="curve_chart" style="width:100%; height:100%;"></div>
    	</div>
    </div>
  </body>
</html>