<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

  <!-- catg header banner section -->
  <section id="aa-catg-head-banner">
   <img src="/dailyShop/img/slider/4.jpg" alt="fashion img" height="300" style="margin-left: auto; margin-right: auto; display: block;">
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
                        
                    <c:choose>
                        <c:when test="${cartList.size() ==0 }">
                            <tr>
                             <td colspan="6">장바구니가 비었습니다.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                        <c:forEach items="${cartList }" var="cartVO">
                         
                           <tr>
                            <td>
                              <a class="remove" href="#" onclick="go_cart_delete(${cartVO.cseq})"><fa class="fa fa-close"></fa></a>
                              <input type="hidden" name="cseq" value="${cartVO.cseq}">
                            </td>
                            <td><a href="MacaronicsServlet?command=product_detail&pseq=${cartVO.pseq}"><img src="images/${cartVO.image}" alt="img"></a></td>
                            <td><a class="aa-cart-title" href="MacaronicsServlet?command=product_detail&pseq=${cartVO.pseq}">${cartVO.pname}</a></td>
                            <td><fmt:formatNumber value="${cartVO.price2}" type="currency"  /></td>
                            <td><input class="aa-cart-quantity" type="number" value="${ cartVO.quantity}"  min="1" max="100" readonly="readonly"></td>
                            <td><fmt:formatNumber  value="${cartVO.price2*cartVO.quantity }"  type="currency" />  </td>
                           </tr>
                            
                           
                          </c:forEach>
                        </c:otherwise>
                    </c:choose>
                       </tbody>
                  </table>
                  
                    <input type="hidden" value="cart_delete" name="command">
                </div>
             </form>
              
             
            <c:if test="${cartList.size() !=0 }"> 
                 <!-- Cart Total view -->
                 <div class="cart-view-total">
                   <h4 style="text-align: center;">장바구니 합계</h4>
                   <table class="aa-totals-table">
                     <tbody>
                       <tr>
                         <th>배송비(3만원 이상 무료)</th>
                         <td><fmt:formatNumber type="currency" value="${totalPrice >30000 ? 0 :3000 }" /></td>          
                       </tr>
                       <tr>
                         <th>합계</th>
                         <td><fmt:formatNumber value="${totalPrice > 30000 ?  totalPrice: totalPrice+3000 }" type="currency"  /></td>
                       </tr>
                     </tbody>
                   </table>
                   <a href="#" class="aa-cart-view-btn">주문하기</a>
                    <a href="MacaronicsServlet?command=index" class="aa-cart-view-btn">쇼핑 계속하기</a>
                    <a href="#" onclick="go_cart_all_delete()" class="btn btn-danger">장바구니 비우기</a>
                 </div>         
            </c:if> 
              
              
              
           </div>
         </div>
       </div>
     </div>
   </div>
 </section>
 <!-- / Cart view section -->

</body>
</html>