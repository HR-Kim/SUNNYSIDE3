<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 결과창</title>
<style type="text/css">
    .layer {
        position: absolute;
        text-align: center;
        width: 100%;
        height: 100%;
        top: 0;
        left: 0;
        font-size: 35px;
    }
    .layer .content {
        display: inline-block;
        vertical-align: middle
    }
    .layer .blank {
        display: inline-block;
        width: 0;
        height: 100%;
        vertical-align: middle
    }
</style>

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	
	<div class="layer">
        <span class="content">회원님의 아이디는   &nbsp <c:out value="${vo.userId }" /> &nbsp  입니다.</span>
        <span class="blank"></span>
    </div>
	
	
</body>
</html>