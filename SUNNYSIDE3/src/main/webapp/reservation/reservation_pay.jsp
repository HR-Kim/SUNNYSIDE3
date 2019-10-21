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
		<title>결제페이지</title>
		<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
		<style type="text/css">
		.top {
			position: absolute;
			top: 0px;
			left: 0px;
			height: 80px;
			width: 430px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid black;
		}
		.main {
			position: absolute;
			top: 80px;
			left: 0px;
			height: 400px;
			width: 330px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid black;
		}
		.sub {
			position: absolute;
			top: 80px;
			left: 330px;
			height: 400px;
			width: 100px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid black;
		}
		.bottom {
			position: absolute;
			top: 480px;
			left: 0px;
			height: 80px;
			width: 430px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid black;
		}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="top">
				${seatArr }
			</div>
			<div class="main">
				${vo }
			</div>
			<div class="sub">
				
			</div>
			<div class="bottom">
				<button id="asd">결제</button>
			</div>
		</div>
	
	
		<script src="${context}/resources/js/jquery-1.12.4.js"></script>
		<script src="${context}/resources/js/bootstrap.min.js"></script>
		<!-- 아임포트 가상결제 -->
		<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
		<script type="text/javascript">
    		$(document).ready(function(){
    			
    		});
    		
    		function startPay(){
    			var IMP = window.IMP; // 생략가능
    	        IMP.init('iamport'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    	        var msg;
    	        
    	        IMP.request_pay({
    	            pg : 'kakaopay',
    	            pay_method : 'card',
    	            merchant_uid : 'merchant_' + new Date().getTime(),
    	            name : '그냥결제',
    	            amount : 100,					//totalPrice
    	            buyer_email : 'lgs1130@naver.com',		//email
    	            buyer_name : '박철수',					//name
    	            buyer_tel : '010-6779-357',				//phone
    	            buyer_addr : '서울시서울',					//address
    	            buyer_postcode : '123-456',
    	            //m_redirect_url : 'http://www.naver.com'
    	        }, function(rsp) {
    	            if ( rsp.success ) {
    	                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
    	                jQuery.ajax({
    	                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
    	                    type: 'POST',
    	                    dataType: 'json',
    	                    data: {
    	                        imp_uid : rsp.imp_uid
    	                        //기타 필요한 데이터가 있으면 추가 전달
    	                    }
    	                }).done(function(data) {
    	                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
    	                    if ( everythings_fine ) {
    	                        msg = '결제가 완료되었습니다.';
    	                        msg += '\n고유ID : ' + rsp.imp_uid;
    	                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
    	                        msg += '\결제 금액 : ' + rsp.paid_amount;
    	                        msg += '카드 승인번호 : ' + rsp.apply_num;
    	                        
    	                        alert(msg);
    	                    } else {
    	                        //[3] 아직 제대로 결제가 되지 않았습니다.
    	                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
    	                    }
    	                });
    	                //성공시 이동할 페이지
    	                location.href='${context}/order/paySuccess?msg='+msg;
    	            } else {
    	                msg = '결제에 실패하였습니다.';
    	                msg += '에러내용 : ' + rsp.error_msg;
    	                //실패시 이동할 페이지
    	                location.href="${context}/order/payFail";
    	                alert(msg);
    	            }
    	        });
    		}
    		
    		$("#asd").on("click", function(){
    			startPay();
    		});
    	</script>
	</body>
</html>