<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8>
<title>장바구니</title>
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

  <!-- catg header banner section -->
  <section id="aa-catg-head-banner">
   <img src="" alt="fashion img" height="300" style="margin-left: auto; margin-right: auto; display: block;">
   <div class="aa-catg-head-banner-area">
     <div class="container">
     </div>
   </div>
  </section>
  <!-- / catg header banner section -->
 
 <!-- Cart view section -->
 <section id="cart-view">
   <div class="container">
     <div class="row">
       <div class="col-md-12">
         <div class="cart-view-area">
           <div class="cart-view-table">
             <form action="" name="form1">
               <div class="table-responsive">
                  <table class="table">
                    <thead>
                      <tr>
                        <th>삭제</th>
                        <th>이미지</th>
                        <th>상품</th>
                        <th>가격</th>
                        <th>수량</th>
                        <th>합계</th>
                      </tr>
                    </thead>
                    <tbody>
                        <tr>
                           <td colspan="6">장바구니가 비었습니다.</td>
                       </tr>
                        	<tr>
	                          <td>
	                            <a class="remove" href="#" onclick=""><fa class="fa fa-close"></fa></a>
	                            <input type="hidden" name="cseq" value="${cartVO.cseq}">
	                          </td>
	                          <td><a href="MacaronicsServlet?command=product_detail&pseq=${cartVO.pseq}"><img src="" alt="img"></a></td>
	                          <td><a class="aa-cart-title" href="MacaronicsServlet?command=product_detail&pseq=${cartVO.pseq}">${cartVO.pname}</a></td>
	                          <td><fmt:formatNumber value="${cartVO.price2}" type="currency"  /></td>
	                          <td><input class="aa-cart-quantity" type="number" value="${ cartVO.quantity}"  min="1" max="100" readonly="readonly"></td>
	                          <td><fmt:formatNumber  value="${cartVO.price2*cartVO.quantity }"  type="currency" />  </td>
	                         </tr>
                       <tbody>
                  </table>
                  
                    <input type="hidden" value="cart_delete" name="command">
                </div>
             </form>
                 <!-- Cart Total view -->
                 <div class="cart-view-total">
                   <h4 style="text-align: center;">장바구니 합계</h4>
                   <table class="aa-totals-table">
                     <tbody>
                       <tr>
                         <th>배송비(3만원 이상 무료)</th>
                         <td>금액</td>          
                       </tr>
                       <tr>
                         <th>합계</th>
                         <td>금액</td>
                       </tr>
                     </tbody>
                   </table>
                   <a href="#" class="aa-cart-view-btn">주문하기</a>
                    <a href="MacaronicsServlet?command=index" class="aa-cart-view-btn">쇼핑 계속하기</a>
                    <a href="#" onclick="go_cart_all_delete()" class="btn btn-danger">장바구니 비우기</a>
                 </div>         
              
              
              
           </div>
         </div>
       </div>
     </div>
   </div>
 </section>
 <!-- / Cart view section -->
 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<script src="${context}/resources/js/jquery.validate.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		 $(document).ready(function(){
			 
		  });

	</script>

</body>
</html>