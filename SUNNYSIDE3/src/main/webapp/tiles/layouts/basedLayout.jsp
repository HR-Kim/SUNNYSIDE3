<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
<head>
<tiles:insertAttribute name="menu"/>

<title><tiles:getAsString name="title"/></title>
</head>
<body>
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;">        
        <tiles:insertAttribute name="header"/>  
    </nav>
	<div id="page-wrapper">
		<div class="row">                 
			<tiles:insertAttribute name="body" />                                                  
		</div>
     </div>    
 
    <div class="main_footer">
        <div class="main_footer-inner">
            <tiles:insertAttribute name="footer" />
        </div>
    </div>	
</body>
</html>
