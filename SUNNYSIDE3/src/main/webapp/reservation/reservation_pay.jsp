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
		#startPay{
			position: inherit;
			top: 18px;
			left: 50px;
		}
		#cancel{
			position: inherit;
			top: 18px;
			left: 230px;
		}
		.bottom button {
			width: 120px;
		}
		.CHKAll{
			background-color: lightgreen;
		}
		.agreeBox {
			position: inherit;
			top: 330px;
			left: 0px;
			font-size: 11px;
			border-top: 1px solid gray;
			width: 100%;
		}
		.COUPON {
			font-size: 11px;
		}
		#couponInfo button {
			float: right;
			margin-right: 5px;
			margin-top: -3px;
		}
		.finalCostInfo {
			background-color: lightyellow;
		}
		#couponBox {
			border-bottom: 1px dotted gray;
		}
		.BAR {
			padding-left: 5px;
			font-size: 17px;
			width: 100%;
			border-bottom: 1px solid gray;
		}
		.FINAL_COST {
			font-size: 10px;
			padding-left: 10px;
			margin: 0 auto;
		}
		#final_cost {
			font-size: 20px;
			padding-left: 10px;
			width: 100%;
			border-bottom: 1px dotted gray;
		}
		.PRODUCT_COST, .COUPON {
			float: left;
			font-size: 12px;
			color: gray;
			padding-left: 5px;
		}
		#rawCost {
			float: right;
			font-size: 8px;
			color: gray;
			padding-right: 5px;
		}
		#couponValue {
			float: right;
			font-size: 8px;
			color: gray;
			padding-right: 5px;
		}
		#topBarCost {
			float: right;
			padding-top: 17px;
			padding-right: 30px;
		}
		#topBarTitle {
			float: left;
			padding-left: 30px;
			padding-top: 17px;
		}
		.topBar {
			position: inherit;
			width: 100%;
			height: 5px;
			background-color: #282828;
		}
		.top {
			position: absolute;
			top: 0px;
			left: 0px;
			height: 50px;
			width: 430px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid gray;
		}
		.main {
			position: absolute;
			top: 50px;
			left: 0px;
			height: 400px;
			width: 320px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid gray;
		}
		.sub {
			position: absolute;
			top: 50px;
			left: 320px;
			height: 400px;
			width: 110px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid gray;
			padding: 5px;
			background-color: #f2f2f2;
		}
		.bottom {
			position: absolute;
			top: 450px;
			left: 0px;
			height: 80px;
			width: 430px;
			background-color: white;
			border-radius: .5rem;
			border: 1px solid gray;
		}
		</style>
	</head>
	<body>
		<c:choose>
			<c:when test="${vo != null}">
				<div class="container">
			<input type="hidden" id="hd_kortitle" value="">
			<input type="hidden" id="hd_cost" value="">
			<input type="hidden" id="hd_final" value="">
			<input type="hidden" id="hd_coupon" value="">
			
			<div class="top">
				<div class="topBar"></div>
				<label id="topBarTitle"></label>
				<label id="topBarCost"></label>
			</div>
			<div class="main">
				<div id="couponBox">
					<label class="BAR">쿠폰</label>
					<br/>
				</div>
				<div class="agreeBox">
					&nbsp;<input type="checkbox" id="chkAll" value="0" onclick="javascript:chkAll();">
							<b class="CHKAll">전체 동의하기</b>
							<br/>
					&nbsp;&nbsp;&nbsp;<input type="checkbox" id="chkA" value="0">
						위 상품의 구매조건 확인 결제진행 동의
					<br/>
					&nbsp;&nbsp;&nbsp;<input type="checkbox" id="chkB" value="0">
						거래정보 제공 동의 (제공 받는 판매자: SunnySide Theater)

				</div>
			</div>
			<div class="sub">
				<div class="finalCostInfo">
					<label class="FINAL_COST">총 결제금액</label>
					<br/>
					<label id="final_cost"></label>
					<br/>
					<div>
						<label class="PRODUCT_COST">상품금액</label>
						<br/>
						<label id="rawCost"></label>
						<br/>
						<label class="COUPON">쿠폰할인금액</label>
						<br/>
						<label id="couponValue">-</label>
						<br/>
					</div>
				</div>
			</div>
			<div class="bottom">
				<button id="startPay" class="btn btn-success btn-lg">결제</button>
				<button id="cancel" class="btn btn-default btn-lg">취소</button>
			</div>
		</div>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	
	
		<script src="${context}/resources/js/jquery-1.12.4.js"></script>
		<script src="${context}/resources/js/bootstrap.min.js"></script>
		<!-- 아임포트 가상결제 -->
		<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
		<script type="text/javascript">
    		$(document).ready(function(){
    			resizeTo(465, 580);
    			
    			var vo = "${vo.screenId}";
    			if(vo.length < 1){
    				alert("잘못된 접근입니다.");
    				self.close();
    				return;
    			}
    			
    			var userId = "${user.userId}";
    			if(userId.length < 1){
    				alert("로그인이 필요한 페이지입니다.");
    				self.close();
    				return;
    			}   			
    			
    			//넘어오는정보 -> 영화id 스크린id 지점id 상영관id 가격 성인유무 좌석정보
    			setTitle();
    			setRawCost();
    			setCoupon();
    			setFinalCost();
    		});
    		
    		function startPay(){
    			var userCellphone = '${user.cellphone}';
    			var userName = '${user.userName}';
    			var email = '${user.email}';
    			var productNm = $("#topBarTitle").text();
    			var cost = $("#hd_final").val();
    			var IMP = window.IMP; // 생략가능
    	        IMP.init('imp42104945'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
    	        var msg;
    	        
    	        IMP.request_pay({
    	            pg : 'kakaopay',
    	            pay_method : 'card',
    	            merchant_uid : 'merchant_' + new Date().getTime(),
    	            name : productNm,
    	            amount : cost,								//totalPrice
    	            buyer_email : email,
    	            buyer_name : userName,
    	            buyer_tel : '010-6779-3570'
    	            //m_redirect_url : 'http://www.naver.com'	//모바일 결제 후 이동될 주소
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
    	                success();
    	                seatUpdateUnable();
    	                //성공시 이동할 페이지
    	                location.replace='${context}/reservation/reservation_result.jsp';
    	            } else {
    	                msg = '결제에 실패하였습니다.\n';
    	                msg += '에러내용 : ' + rsp.error_msg;
    	                alert(msg);
    	            }
    	        });
    		}
    		
    		//영화제목
    		function setTitle(){
    			var movieId = "${vo.movieId}";
    			$.ajax({
    				type : "POST",
    				url : "${context}/screening/do_selectOne_movie.do",
    				dataType : "json",
    				data : {
    					"movieId" : movieId
    				}
				}).done(function(data){
					var movieInfo = data;
					var kortitle = movieInfo.kortitle;
					$("#topBarTitle").text("[영화] " + kortitle);
				});
    		};
        		
    		//영화의 원래 가격
    		function setRawCost(){
    			var cost = "${vo.cost}";
    			$("#hd_cost").val(cost);
    			$("#topBarCost").text(cost + "원");
    			$("#rawCost").text(cost + "원");
    			$("#hd_final").val(cost);
    		}
	
    		//쿠폰정보
    		function setCoupon(){
    			var userId = "${user.userId}";
    			$.ajax({
    				type : "POST",
    				url : "${context}/coupon/do_selectOne.do",
    				dataType : "json",
    				data : {
    					"userId" : userId
    				}
				}).done(function(data){
					var coupon = data;
					
					$("#couponBox>.couponInfo").detach();
					if(coupon == null){
						$("#couponBox").append(
								"<div id='couponInfo'>"+
								"&nbsp;<label class='COUPON'>쿠폰이 없습니다.</label>"+
								"</div>"
						);
					}else{
						var cNm = coupon.couponNm;
						var usable = coupon.usable;
						var discount = coupon.discount;
						
						$("#couponBox").append(
							"<div id='couponInfo'>"+
							"&nbsp;<label class='COUPON'>쿠폰이름: " + cNm + "</label>"+
							"</div>"
						);
						
						if(usable == '1'){
							$("#couponInfo").append(
									"<button class='btn btn-xs btn-danger'>이미사용</button>"
								);
						}else{
							$("#couponInfo").append(
									"<button onclick='javascript:cpON("+discount+");' class='btn btn-xs btn-success'>적용</button>"
								);
							
						}
					}
				});	
    		}
    		
    		//쿠폰적용
    		function cpON(discount){
    			$("#couponInfo>button").detach();
    			
    			$("#couponValue").text("(-)" + discount + "원");
    			$("#hd_coupon").val(discount);
    			setFinalCost();
    			
    			$("#couponInfo").append(
						"<button onclick='javascript:cpOFF("+discount+");' class='btn btn-xs btn-danger'>취소</button>"
					);
    			
    			
    		}
    		
    		//쿠폰해제
    		function cpOFF(discount){
    			$("#couponInfo>button").detach();
    			
    			$("#couponValue").text("-");
    			$("#hd_coupon").val("");
    			setFinalCost();
    			
    			$("#couponInfo").append(
						"<button onclick='javascript:cpON("+discount+");' class='btn btn-xs btn-success'>적용</button>"
					);
    		}
    		
    		//최종금액표시
    		function setFinalCost(){
    			var rawCost = $("#hd_cost").val();
    			var coupon = ($("#hd_coupon").val())? $("#hd_coupon").val():"0";
    			var finalCost = eval(parseInt(rawCost) - parseInt(coupon));
    			if(finalCost < 0) finalCost = 0;
    			$("#final_cost").text(finalCost + "원");
    		}
    		
    		//체크박스 모두체크
    		function chkAll(){
    			var chkAll = $("#chkAll").prop("checked");
    			if(chkAll == false){
    				$("#chkA").prop("checked", false);
        			$("#chkB").prop("checked", false);
    			}else{
    				$("#chkA").prop("checked", true);
        			$("#chkB").prop("checked", true);
    			}
    		}

    		//결제버튼
    		$("#startPay").on("click", function(){
    			var A = $("#chkA").prop("checked");
    			var B = $("#chkB").prop("checked");
    			if(A == false || B == false){
    				alert("약관에 동의해주세요.");
    				return;
    			}
    			startPay();
    		});
    		
    		//취소버튼
    		$("#cancel").on("click", function(){
    			if(confirm("결제를 취소하시겠습니까?")==false) return;
    			alert("결제를 취소합니다.");
    			self.close();
    		});
    		
    		//결제성공
    		function success(){
	   			var branchId = "${vo.branchId}";
	   			var roomId = "${vo.roomId}";
	   			var userId = "${user.userId}";
	   			var movieId = "${vo.movieId}";
	   			var adultCnt = "${vo.adultCnt}";
	   			var screenId = "${vo.screenId}";
	   			var payState = "1";
	   			var cost = $("#hd_final").val();
	   			
	   			//좌석문자열정보를 배열로
	   			var stringInfo = "${seatInfo}";
    			var arr = stringInfo.split("%");

				for(var i=1 ; i<arr.length ; i++){
					var seatNm = arr[i];console.log(seatNm + "  " + i);
					$.ajax({
	    				type : "POST",
	    				url : "${context}/reservation/do_save.do",
	    				dataType : "json",
	    				data : {
	    					"branchId" : branchId,
	    					"roomId" : roomId,
	    					"screenId" : screenId,
	    					"userId" : userId,
	    					"movieId" : movieId,
							"seatNm" : seatNm,
	    					"adultCnt" : adultCnt,
	    					"payState" : payState,
	    					"cost" : cost
	    				}
					}).done(function(data){
						var msg = data;console.log(seatNm + "  " + i);
						if(msg.msgId == "1"){
							//alert("1");
						}else{
							//alert("0");
						}						
					});
				}
    		}
    		
    		//좌석을 사용중으로 업데이트
    		function seatUpdateUnable(){
    			var roomId = "${vo.roomId}";
    			var stringInfo = "${seatInfo}";
    			var arr = stringInfo.split("%");

    			for(var i=1 ; i<arr.length ; i++){
    				var seatNm = arr[i];
	    			$.ajax({
	    				type : "POST",
	    				url : "${context}/seat/do_update.do",
	    				dataType : "json",
	    				data : {
	    					"roomId" : roomId,
							"seatNm" : seatNm,
							"useYN" : 0
	    				}
					}).done(function(data){
						var msg = data;
						if(msg.msgId == "1"){
							//alert("u1");
						}else{
							//alert("u0");
						}
					});
    			}
    		}
    	</script>
	</body>
</html>