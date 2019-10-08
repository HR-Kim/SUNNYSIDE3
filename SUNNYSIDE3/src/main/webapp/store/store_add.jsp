<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- �� 3���� ��Ÿ �±״� *�ݵ��* head �±��� ó���� �;��մϴ�; � �ٸ� ���������� �ݵ�� �� �±׵� *������* �;� �մϴ� -->
    <title>��ǰ���</title>

    <!-- ��Ʈ��Ʈ�� -->
    <!--<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">  -->

    <!-- IE8 ���� HTML5 ��ҿ� �̵�� ������ ���� HTML5 shim �� Respond.js -->
    <!-- WARNING: Respond.js �� ����� file:// �� ���� �������� �� ���� �������� �ʽ��ϴ�. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
 <h2> ��ǰ��� </h2>
 <form id="storeForm" name="storeForm" enctype="multipart/form-data" method="post">
  <table border="1">
  	<tr>
  		<td>��ǰ��</td>
  		<td><input type="text" name="productName" id="productName" /></td>
  	</tr>
  	<tr>
  		<td>�з�</td>
  		<td>
	  		<select>
	  			<option value="1">����</option>
	  			<option value="2">����</option>
	  			<option value="3">��ȭ���ű�</option>
	  		</select>
  		</td>
  	</tr>
  	<tr>
  		<td>����</td>
  		<td><input type="text" name="productPrice" id="productPrice" />��</td>
  	</tr> 
  	<tr>
  		<td>��ǰ����</td>
  		<td><textarea rows="5" cols="60" name="productInfo" id="productInfo"></textarea></td>
  	</tr>
  	<tr>
  		<td>��ǰ�̹���</td>
  		<td><input type="file" name="productImage" id="productImage" /></td>
  	</tr>
  	<tr>
  	 <td colspan="2" align="center">
  	 	<input type="button" value="���" id="addBtn">
  	 	<input type="button" value="���" id="listBtn">
  	 </td>
  	</tr>
  </table> 
 </form>
 <!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ��մϴ�) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- ��� �����ϵ� �÷������� �����մϴ� (�Ʒ�), ������ �ʴ´ٸ� �ʿ��� ������ ������ �����ϼ��� -->
 <script src="${context}/resources/js/bootstrap.min.js"></script>   
 <script type="text/javascript">
	$("#addBtn").on("click",function(){
		
		if(confirm("��ǰ�� ����Ͻðڽ��ϱ�?")==false) return;
		
		//ajax
     $.ajax({
        type:"POST",
        url:"${context}/store/do_save.do",
        dataType:"html",
        data:{
	           "productNm":$("#productNm").val(),
	           "productCost":$("#productCost").val(),
	           "pruductInfo":$("#pruductInfo").val(),
	           "img":$("#img").val()
       }, 
     success: function(data){ 
       var jData = JSON.parse(data);
       if(null != jData && jData.msgId=="1"){
         alert(jData.msgMsg);
         location.href ="${context}/store/get_retrieve.do";
       }else{
         alert(jData.msgId+"|"+jData.msgMsg);
       }
     },
     complete:function(data){
      
     },
     error:function(xhr,status,error){
         alert("error:"+error);
     }
    }); //--ajax  
		
	 }); 
 
 $(document).ready(function(){
	
		 
 });

</script>
</body>
</html>