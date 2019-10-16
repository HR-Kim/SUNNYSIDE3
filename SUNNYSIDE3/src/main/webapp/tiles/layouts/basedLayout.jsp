<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>

<!-- 디폴트 리소스 -->
<tiles:insertAttribute name="menu"/>
<!--// 디폴트 리소스 -->

<title><tiles:getAsString name="title"/></title>
</head>
<body>
	<!-- 헤더  -->
	<!--<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;">-->
    <tiles:insertAttribute name="header"/>  
	<!--</nav> -->
	<!--// 헤더  -->
	
	<!-- 바디 -->
	<div id="page-wrapper">
		<div class="row">                 
			<tiles:insertAttribute name="body" />                                                  
		</div>
	</div>    
	<!--// 바디 -->
	
	<!-- 풋터 -->
    <div class="main_footer">
        <div class="main_footer-inner">
            <tiles:insertAttribute name="footer" />
        </div>
    </div>	
    <!--// 풋터 -->
</body>
</html>
