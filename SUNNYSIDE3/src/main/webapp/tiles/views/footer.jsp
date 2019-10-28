<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
 	<!-- Footer section -->
	<section class="footer-section">
		<div class="container">
			<div class="footer-logo text-center">
				<a href="${context}/main/main.do"><img src="${context}/resources/img/divisima/logo-light.png" alt="로고이미지"></a>
			</div>
			<div class="row">
				<div class="col-lg-4 col-sm-7">
					<div class="footer-widget about-widget">
						<div class="fw-latest-post-widget">
							<h2>SUNNYSIDE THEATER</h2>
							<!-- 홍대점 -->
							<p><h6 style="font-weight: bold; color: #fff">=<spring:message code="message.footer.hongdae_store"/>=</h6><br>
							<!-- 서울특별시 마포구 월드컵북로 21 2층 풍성빌딩 -->
							<h6 style="color: #fff"><spring:message code="message.footer.hongdae_address"/></h6><br>
							<h6 style="color: #fff">--------------------------------------------</h6><br>
							<!-- 강남점 -->
							<h6 style="color: #fff">=<spring:message code="message.footer.gangnam_store"/>=</h6><br>
							<!-- 서울특별시 강남구 테헤란로 132 한독약품빌딩 -->
							<h6 style="color: #fff"><spring:message code="message.footer.gangnam_address"/></h6>
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="footer-widget about-widget">
						<h2>REPRESENTATIVE</h2>
						<div class="fw-latest-post-widget">
							<!-- 대표자 김도연 -->
							<p><h6 style="font-weight: bold; color: #fff"><spring:message code="message.footer.master"/></h6><br>
							<!-- 사업자 등록번호 -->
                        	<h6 style="color: #fff"><spring:message code="message.footer.master_num"/></h6></p>    
                        </div>						
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="footer-widget about-widget">
						<h2>CONTACT INFO</h2>
						<div class="fw-latest-post-widget">
							<p>
								<h6 style="color: #fff">1544-0013</h6><br>
								<h6 style="color: #fff">sunnyside@helpinfo.com</h6>
							</p>							
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer section end -->
    <!--====== Javascripts & Jquery ======-->
	<script src="${context}/resources/js/divisima/jquery-3.2.1.min.js"></script>
	<script src="${context}/resources/js/divisima/bootstrap.min.js"></script>
	<script src="${context}/resources/js/divisima/jquery.slicknav.min.js"></script>
	<script src="${context}/resources/js/divisima/owl.carousel.min.js"></script>
	<script src="${context}/resources/js/divisima/jquery.nicescroll.min.js"></script>
	<script src="${context}/resources/js/divisima/jquery.zoom.min.js"></script>
	<script src="${context}/resources/js/divisima/jquery-ui.min.js"></script>
	<script src="${context}/resources/js/divisima/main.js"></script>	
</body>
</html>