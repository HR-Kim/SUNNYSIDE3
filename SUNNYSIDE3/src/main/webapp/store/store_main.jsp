<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%

		/**페이지 사이즈*/
		String pageSize="10"       ; 
		/**페이지 번호*/
		String pageNum= "1"       ;
		/**검색조건*/
		String searchDiv=""    ;
		/**검색어*/
		String searchWord=""   ;

		SearchVO vo = (SearchVO)request.getAttribute("vo");
		if(null!=vo){
			pageSize = StringUtil.nvl(vo.getPageSize()+"","10");
			pageNum = StringUtil.nvl(vo.getPageNum()+"","1");
			searchDiv = StringUtil.nvl(vo.getSearchDiv(),"");
			searchWord = StringUtil.nvl(vo.getSearchWord(),"");
		}else{
			pageSize = "10";
			pageNum ="1";
			searchDiv ="";
			searchWord ="";
			
		}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
${fileList}
<div class="container">
    <h3 class="h3">팝콘 </h3>
	    <div class="row">
		    <c:choose>
		    	<c:when test="${list.size()>0}">
		    		<c:forEach var="vo" items="${list }">
				        <div class="col-md-3 col-sm-6">
				            <div class="product-grid">
				                <div class="product-image">
				                    <img class="pic-1" style="height: 270px" width="270px" src="<c:out value="${vo.saveFileNm }"/>">		
				                    <ul class="social">
				                        <li><a href="" data-tip="Quick View"><i class="fa fa-search"></i></a></li>
				                        <li><a href="" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
				                        <li><a href="" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
				                    </ul>
				                     <a class="add-to-cart" href="">장바구니에 추가</a>
				                </div>
				                <div class="product-content">
				                    <h3 class="title"><a href="#"><c:out value="${vo.productNm }"/></a></h3>
				                    <div class="price">
				                        <span><c:out value="${vo.productCost }"/></span>
				                    </div>
				                    <a class="add-to-cart" href="">+ Add To Cart</a>
				                </div>
				            </div>
				        </div>
				    </c:forEach>
		        </c:when>
		    </c:choose>
		</div>
 </div>
<div class="container">
    <h3 class="h3">음료 </h3>
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid2">
                <div class="product-image2">
            		  <img class="pic-1" src="">	
                    <ul class="social">
                        <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <a class="add-to-cart" href="">장바구니</a>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Women's Designer Top</a></h3>
                    <span class="price">$599.99</span>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid2">
              <div class="product-image2">
                  <img class="pic-1" src="">	
                  <ul class="social">
                      <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                      <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                      <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                  </ul>
                  <a class="add-to-cart" href="">장바구니</a>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Women's Yellow Top</a></h3>
                    <span class="price">$699.99</span>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid2">
                <div class="product-image2">
                  <img class="pic-1" src="">	
                    <ul class="social">
                        <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <a class="add-to-cart" href="">장바구니</a>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Women's Designer Top</a></h3>
                    <span class="price">$599.99</span>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid2">
                <div class="product-image2">
                    <img class="pic-1" src="">	
                    <ul class="social">
                        <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <a class="add-to-cart" href="">장바구니</a>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Women's Designer Top</a></h3>
                    <span class="price">$599.99</span>
                </div>
            </div>
        </div>
    </div>
</div>
<hr>
<div class="container">
    <h3 class="h3">영화관람권</h3>
    <div class="row">
        <div class="col-md-3 col-sm-6">
            <div class="product-grid4">
                <div class="product-image4">
                   <img class="pic-1" src="">	
                    <ul class="social">
                        <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <span class="product-new-label">New</span>
                    <span class="product-discount-label">-10%</span>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Women's Black Top</a></h3>
                    <div class="price">
                        $14.40
                        <span>$16.00</span>
                    </div>
                    <a class="add-to-cart" href="">장바구니</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid4">
                <div class="product-image4">
                   <img class="pic-1" src="">
                    <ul class="social">
                        <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <span class="product-discount-label">-12%</span>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Men's Blue Shirt</a></h3>
                    <div class="price">
                        $17.60
                        <span>$20.00</span>
                    </div>
                    <a class="add-to-cart" href="">장바구니</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid4">
                <div class="product-image4">
                   <img class="pic-1" src="">
                    <ul class="social">
                        <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <span class="product-new-label">New</span>
                    <span class="product-discount-label">-10%</span>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Women's Black Top</a></h3>
                    <div class="price">
                        $14.40
                        <span>$16.00</span>
                    </div>
                    <a class="add-to-cart" href="">장바구니</a>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-sm-6">
            <div class="product-grid4">
                <div class="product-image4">
                  <img class="pic-1" src="">
                    <ul class="social">
                        <li><a href="#" data-tip="Quick View"><i class="fa fa-eye"></i></a></li>
                        <li><a href="#" data-tip="Add to Wishlist"><i class="fa fa-shopping-bag"></i></a></li>
                        <li><a href="#" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
                    </ul>
                    <span class="product-new-label">New</span>
                    <span class="product-discount-label">-10%</span>
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#">Women's Black Top</a></h3>
                    <div class="price">
                        $14.40
                        <span>$16.00</span>
                    </div>
                    <a class="add-to-cart" href="">장바구니</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>