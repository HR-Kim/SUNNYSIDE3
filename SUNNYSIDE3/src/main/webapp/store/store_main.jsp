<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>스토어</title>

</head>
<body>
<div class="container">
	<div class="productadd">
		<input type="button" value="상품등록" id="addbtn" class="addbtn">
	</div>
    <h3 class="h3">팝콘</h3>
    <a style="margin-left: 1050px" href="do_retrieve_popcorn.do" class="btn_popcorn_product"><i class="fa fa-plus"></i> 더보기</a> 
    <div class="row" >
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <img class="pic-1" style="height: 270px" width="270px"  src="../resources/image/2019/10/plainPopcorn.jpg">		
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-001-000" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content" >
                    <h3 class="title">고소팝콘(L)</h3>
                    <div class="price">
                        <span>5000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
       				   <img class="pic-1" style="height: 270px" width="270px"  src="../resources/image/2019/10/caramelPopcorn.jpg">		
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-001-001" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">달콤팝콘(L)</h3>
                    <div class="price">
                        <span>6000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <img class="pic-1"  style="height: 270px" width="270px" src="../resources/image/2019/10/doublecheesePop.jpg">	
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-001-002" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">더블치즈팝콘(L)</h3>                    
                    <div class="price">
                        <span>6000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                      <img class="pic-1" style="height: 270px" width="270px"  src="../resources/image/2019/10/onionPop.jpg">	
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-001-003" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">바질어니언팝콘(L)</h3>
                    <div class="price">
                        <span>6000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<div class="container">
    <h3 class="h3">음료</h3>
    <a style="margin-left: 1050px" href="do_retrieve_drink.do" class="btn_drink_product"><i class="fa fa-plus"></i> 더보기</a> 
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <img class="pic-1" style="height: 270px" width="270px"  src="../resources/image/2019/10/coke.jpg">		
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-002-008" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">탄산음료(L)</h3>
                    <div class="price">
                        <span>2700원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                    <img class="pic-1"  style="height: 270px" width="270px" src="../resources/image/2019/10/orange.jpg">	
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-002-007" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">오렌지주스</h3>
                    <div class="price">
                        <span>3500원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                      <img class="pic-1" style="height: 270px" width="270px"  src="../resources/image/2019/10/americano.jpg">	
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-002-009" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">아메리카노(ICE)</h3>
                    <div class="price">
                        <span>4000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                      <img class="pic-1" style="height: 270px" width="270px"  src="../resources/image/2019/10/jamong.jpg">	
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191013-002-020" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">자몽에이드(L)</h3>
                    <div class="price">
                        <span>4500원</span>
                    </div>
                    <a class="add-to-cart" href="">+ Add To Cart</a>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<div class="container">
    <h3 class="h3">영화관람권</h3>
     <a style="margin-left: 1050px" href="do_retrieve_movieticket.do" class="btn_movieticket_product"><i class="fa fa-plus"></i> 더보기</a> 
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                   <img class="pic-1"  style="height: 270px" width="270px"  src="../resources/image/2019/10/movieVoucher.jpg">
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-003-004" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="#" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content"> 
                    <h3 class="title">영화 관람권</h3>
                    <div class="price">
                        <span>7000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ ADD TO CART</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                   <img class="pic-1"  style="height: 270px" width="270px"  src="../resources/image/2019/10/giftcard.jpg">
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-003-005" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="#" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">기프트카드</h3>
                    <div class="price">
                        <span>10000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ ADD TO CART</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                  <img class="pic-1"  style="height: 270px" width="270px"  src="../resources/image/2019/10/imax.jpg">
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191011-003-006" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="#" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">IMAX관람권</h3>
                    <div class="price">
                        <span>15000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ ADD TO CART</a>
                </div>
            </div>
        </div>
                <div class="col-md-3 col-sm-6">
            <div class="product-grid">
                <div class="product-image">
                  <img class="pic-1"  style="height: 270px" width="270px"  src="../resources/image/2019/10/3dmovie.jpg">
                    <ul class="social">
                        <li><a href="do_selectOne.do?productId=20191013-003-010" data-tip="상세보기"><i class="fa fa-search"></i></a></li>
                        <li><a href="#" data-tip="장바구니"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                </div>
                <div class="product-content">
                    <h3 class="title">3D관람권</h3>
                    <div class="price">
                        <span>12000원</span>
                    </div>
                    <a class="add-to-cart" href="">+ ADD TO CART</a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${context}/resources/js/bootstrap.min.js"></script>    
<script type="text/javascript">
	
	//등록
	$("#addbtn").on("click",function(){
		//alert("addbtn");
		if(false==confirm('새 상품을 등록 하시겠습니까?')) return;
		popup();
		
	});
	
	 function popup(){
         var url = "store_add.jsp";
         var name = "상품등록";
         var option = "width = 700, height = 400, top = 100, left = 400, location = no"
         window.open(url, name, option);
     }

	$(document).ready(function(){
		//alert("ready");
	});



</script>
</body>
</html>