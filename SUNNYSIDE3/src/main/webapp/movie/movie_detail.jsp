<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

<!------ Include the above in your HEAD tag ---------->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
<!-- div container -->
	<div class="container">
		<!-- div title -->
		<div class="page-header">
			<h1>영화 상세보기</h1>
		</div>
		<!--// div title -->

		<!-- div title -->
		<form class="form-horizontal">
			<input type="hidden" name="movieId" id="movieId" value=""/>
			<!-- 영화포스터 -->
			<div class="form-group">
				<label for="synopsis" class="col-sm-2 control-label">영화포스터</label>
				<div class="col-sm-8">
					<img src="<c:out value='${vo.poster}'/>" alt="영화포스터" style="height: 350px; width:250px;">
				</div>
			</div>
			<!-- 한글영화제목 -->
			<div class="form-group">
				<label for="kortitle" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">한글영화제목</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="kortitle" name="kortitle" value="<c:out value='${vo.kortitle}'/>">
				</div>
			</div>
			<!-- 영문영화제목 -->
			<div class="form-group">
				<label for="engtitle" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">영문영화제목</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="engtitle" name="engtitle" value="<c:out value='${vo.engtitle}'/>">
				</div>
			</div>
			<!-- 평점 -->
			<div class="form-group">
				<label for="visitorRate" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">평점</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="visitorRate" name="visitorRate" value="<c:out value='${vo.visitorRate}'/>">
				</div>
			</div>
			<!-- 개봉일 -->
			<div class="form-group">
				<label for="relDate" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">개봉일</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="relDate" name="relDate" value="<c:out value='${vo.relDate}'/>">
				</div>
			</div>
			<!-- 관람가능연령 -->
			<div class="form-group">
				<label for="limitage" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">관람가능연령</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="limitage" name="limitage" value="<c:out value='${vo.limitage}'/>">
				</div>
			</div>
			<!-- 감독 -->
			<div class="form-group">
				<label for="director" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">감독</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="director" name="director" value="<c:out value='${vo.director}'/>">
				</div>
			</div>
			<!-- 출연진 -->
			<div class="form-group">
				<label for="cast" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">출연진</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="cast" name="cast" value="<c:out value='${vo.cast}'/>">
				</div>
			</div>
			<!-- 장르 -->
			<div class="form-group">
				<label for="genre" class="hidden-xs hidden-sm col-md-2 col-lg-2 control-label">장르</label>
				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
					<input type="text" class="form-control" id="genre" name="genre" value="<c:out value='${vo.genre}'/>">
				</div>
			</div>
			<!-- 줄거리 -->
			<div class="form-group">
				<label for="synopsis" class="col-sm-2 control-label">줄거리</label>
				<div class="col-sm-8">
					<textarea class="form-control" id="synopsis" name="synopsis" rows="5"><c:out value='${vo.synopsis}'/></textarea>
				</div>
			</div>			
		</form>
	</div>
	<!--// div container -->

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>  	
	<script type="text/javascript">	
		$(document).ready(function(){
		//alert("ready");
		});
	</script>  
</body>
</html>